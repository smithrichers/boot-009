package com.example.boot009.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.boot009.entity.User;

import java.util.Date;

/**
 * @ Program       :  com.ljnt.blog.utils.TokenUtil
 * @ Description   :  token工具类（生成、验证）
 * @ Author        :  lj
 * @ CreateDate    :  2020-1-31 22:15
 */
public class TokenUtil {

    private static final long EXPIRE_TIME = 1000*60*10;//token到期时间10分钟
    private static final String TOKEN_SECRET = "ljdyaishijin**3nkjnj??";  //密钥盐

    /**
     * @param : [user]
     * @return : java.lang.String
     * @throws :
     * @Description ：生成token
     * @author : lj
     * @date : 2020-1-31 22:49
     */
    public static String sign(User user) {

        String token = null;
        try {
            Date expireAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("auth0")//发行人
                    .withClaim("username", user.getUsername())//存放数据
                    .withExpiresAt(expireAt)//过期时间
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (IllegalArgumentException | JWTCreationException je) {

        }
        return token;
    }


    /**
     * @param : [token]
     * @return : java.lang.Boolean
     * @throws :
     * @Description ：token验证
     * @author : lj
     * @date : 2020-1-31 22:59
     */
    public static Boolean verify(String token) {

        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();//创建token验证器
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            System.out.println("认证通过：");
            System.out.println("username: " + decodedJWT.getClaim("username").asString());
            System.out.println("过期时间：      " + decodedJWT.getExpiresAt());
            Date date = new Date();
            System.out.println("当前的日期是---->" + date);
            long diff = decodedJWT.getExpiresAt().getTime() - date.getTime();//这样得到的差值是毫秒级别
            long days = diff / (1000 * 60 * 60 * 24);
            long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            long s = (diff / 1000 - days * 24 * 60 * 60 - hours * 60 * 60 - minutes * 60)+1;
//            long min = ((diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60*60 )-minutes*(1000*60)) / (1000));
            System.out.println("剩余有效时间为：" + days + "天" + hours + "小时" + minutes + "分"+s+"秒");
        } catch (IllegalArgumentException | JWTVerificationException e) {
            //抛出错误即为验证不通过
            return false;
        }
        return true;
    }

}

