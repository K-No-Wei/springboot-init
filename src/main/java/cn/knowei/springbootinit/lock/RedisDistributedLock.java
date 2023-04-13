package cn.knowei.springbootinit.lock;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author: knowei
 * @Description:
 * @Date: Create in 20:15 2023-04-13
 */
public class RedisDistributedLock implements Lock {
    private StringRedisTemplate stringRedisTemplate;
    private String lockName;
    private String uuidValue;
    private long expireTime;

    public RedisDistributedLock(StringRedisTemplate stringRedisTemplate, String lockName) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.lockName = lockName;
        this.uuidValue = UUID.randomUUID() + ":" + Thread.currentThread().getId();
        this.expireTime = 50L;
    }

    @Override
    public void lock() {
        tryLock();
    }

    @Override
    public void unlock() {
        String script = "if redis.call('hexists', KEYS[1], ARGV[1]) == 0 then " +
                " return nil " +
                " elseif redis.call('hincryby',KEYS[1],ARGV[1],-1) == 0 then " +
                " return redis.call('del',KEYS[1]) " +
                " else " +
                " return 0 " +
                " end ";
        Long flag = stringRedisTemplate.execute(new DefaultRedisScript<>(script, Long.class), Arrays.asList(lockName), uuidValue, String.valueOf(expireTime));

        if (null == flag) {
            throw new RuntimeException("this lock doesn't exist ");
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        try {
            tryLock(-1L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        if (time == -1L) {
            String script = "\n" +
                    "if redis.call('exists',KEYS[1]) == 0 or redis.call('hincrby',KEYS[1],ARGV[1]) == 1 then " +
                    " redis.call('hincrby',KEYS[1], ARGV[1], 1) " +
                    " redis.call('expire',KEYS[1],ARGV[2]) " +
                    " return 1 " +
                    " else " +
                    "  return 0 " +
                    " end ";
            // 加锁
            while (!stringRedisTemplate.execute(new DefaultRedisScript<>(script, Boolean.class), Arrays.asList(lockName), uuidValue, String.valueOf(expireTime))){
                TimeUnit.MILLISECONDS.sleep(60);
            }

            // 加锁成功，监听key
            resetExpire();
            return true;
        }
        return false;
    }

    /**
     * 监听key
     */
    private void resetExpire() {
        String script = "if redis.call('hexists',KEYS[1],ARGV[1]) == 1 then " +
                "return redis.call('expire',KEYS[1],ARGV[2]) " +
                "else " +
                "return 0 " +
                "end";
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (stringRedisTemplate.execute(new DefaultRedisScript<>(script, Boolean.class), Arrays.asList(lockName), uuidValue, String.valueOf(expireTime))) {
                    resetExpire();
                }
            }
        }, (this.expireTime * 1000) / 3);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
