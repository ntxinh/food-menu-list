package com.github.nguyentrucxinh.foodmenulist.config;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private static final Logger log = Logger.getLogger(JWTAuthorizationFilter.class.getName());

    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String path = req.getRequestURI();

        log.info("Path: " + path);

        if (!path.startsWith(SecurityConstants.API_ADMIN_URL + "/")) {
            chain.doFilter(req, res);
            return;
        }

        String header = req.getHeader(SecurityConstants.HEADER_STRING);

        log.info("Header: " + header);

        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.HEADER_STRING);
        log.info("Token: " + token);
        if (token != null) {
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(SecurityConstants.SECRET.getBytes())
                        .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
                        .getBody();

                // parse the token.
                String user = claims.getSubject();

                log.info("User: " + user);

                if (user != null) {
                    return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                }
                return null;
            } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {
                log.info("Invalid JWT Token");
                throw new BadCredentialsException("Invalid JWT token: ", ex);
            } catch (ExpiredJwtException expiredEx) {
                log.info("JWT Token is expired");
                throw new BadCredentialsException("JWT Token is expired: ", expiredEx);
            }
        }
        return null;
    }
}