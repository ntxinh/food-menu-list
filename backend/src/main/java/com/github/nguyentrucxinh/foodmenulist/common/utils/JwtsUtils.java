package com.github.nguyentrucxinh.foodmenulist.common.utils;

import com.github.nguyentrucxinh.foodmenulist.common.constants.SecurityConstants;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;

public class JwtsUtils {

    private static final Logger LOGGER = Logger.getLogger(EncryptionUtils.class.getName());
    private static final String SECRET_KEY = "xm8EV6Hy5RMFK4EEACIDAwQus";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 15; // 15 days

    private JwtsUtils() {
    }

    public static String createToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET.getBytes())
                .compact();
    }

    public static String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY.getBytes())
                .compact();
    }

    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    public static Map<String, Object> parseTokenToMap(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {
            LOGGER.info("Invalid JWT Token");
            return null;
        } catch (ExpiredJwtException expiredEx) {
            LOGGER.info("JWT Token is expired");
            return null;
        }
    }
}
