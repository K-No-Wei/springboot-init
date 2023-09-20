package cn.knowei.springbootinit.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @Author: knowei
 * @Description:
 * @Date: Create in 14:10 2023-04-15
 */
public class JwtUtils {
    private static final String secretKey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9"; //进行数字签名的私钥，一定要保管好

    public static void main(String[] args) throws Exception {

        //获取系统的当前时间
    /*    long ttlMillis = System.currentTimeMillis();
        Date now = new Date(ttlMillis);*/

        //生成jwt令牌
       /* JwtBuilder jwtBuilder = Jwts.builder()
                .setId("66")//设置jwt编码
                .setSubject("程序员")//设置jwt主题
                .setIssuedAt(new Date())//设置jwt签发日期
                //.setExpiration(date)//设置jwt的过期时间
                .claim("t", "admin")
                .claim("company", "itheima")
                .signWith(SignatureAlgorithm.HS256, secretKey);

        //生成jwt
        String jwtToken = jwtBuilder.compact();
        System.out.println(jwtToken);*/
//        String s = acquireJWT("123456", "1", "123");
      /*  byte[] bytes = DatatypeConverter.parseBase64Binary(secretKey);
        //解析jwt,得到其内部的数据
        Claims claims = Jwts.parser().setSigningKey(bytes).parseClaimsJws(s).getBody();
        System.out.println(claims);

        Claims claims1 = parseJWT(s);
        System.out.println(claims1);*/
    }

    /**
     *生成串
     * @param token
     * @param id
     * @param account
     * @return
     * @throws Exception
     */
    public static String acquireJWT(Long id,String account)  {
        //生成jwt令牌
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(id.toString())//设置jwt
                .setSubject("测试")//设置jwt主题
                .setIssuedAt(new Date())//设置jwt签发日期
                //.setExpiration(date)//设置jwt的过期时间
                .claim("userId", id)
                .claim("userName", account)
                .signWith(SignatureAlgorithm.HS256, secretKey);
        return jwtBuilder.compact();
    }

    /**
     * 解析JWT字符串
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
