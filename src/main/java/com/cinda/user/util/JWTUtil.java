package com.cinda.user.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JWTUtil {
    private static final String ISSUER = "Cinda";

    public static String generateToken(String role, String secretKey){
        Algorithm algorithm  = Algorithm.HMAC256(secretKey);
        Date now = new Date();

        // 设置到期时间只需要在redis体现就行 目前没有作用 先去掉
        String token = JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(now)
//                .withExpiresAt(expireTime)
                .withSubject("法律文书token")
                .withClaim("role", role)
                .sign(algorithm);
        return token;
    }

    public static boolean verifyToken(String token, String secretKey){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            jwtVerifier.verify(token);
            return true;
        }catch (JWTDecodeException | SignatureVerificationException | TokenExpiredException e){
            return false;
        }
    }


    public static String getRoleInfo(String token){
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaim("role").asString();
    }
}
