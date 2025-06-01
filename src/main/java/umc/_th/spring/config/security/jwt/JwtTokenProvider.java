package umc._th.spring.config.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import umc._th.spring.apiPayload.code.status.ErrorStatus;
import umc._th.spring.apiPayload.exception.handler.MemberHandler;
import umc._th.spring.config.properties.Constants;
import umc._th.spring.config.properties.JwtProperties;

import java.security.Key;
import java.util.Date;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes());
    }

    public String generateToken(Authentication authentication, JwtProperties.TokenType tokenType) {
        String email = authentication.getName();

        if(tokenType == JwtProperties.TokenType.ACCESS) {
            return Jwts.builder()
                    .setSubject(email)
                    .claim("role", authentication.getAuthorities().iterator().next().getAuthority())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration().getAccess()))
                    .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                    .compact();
        }
        
        return Jwts.builder()
                .setSubject(email)
                .claim("role", authentication.getAuthorities().iterator().next().getAuthority())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration().getAccess() * 42))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        String email = claims.getSubject();
        String role = claims.get("role", String.class);

        User principal = new User(email, "", Collections.singleton(() -> role));
        return new UsernamePasswordAuthenticationToken(principal, token, principal.getAuthorities());
    }

    public static String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(Constants.AUTH_HEADER);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(Constants.TOKEN_PREFIX)) {
            return bearerToken.substring(Constants.TOKEN_PREFIX.length());
        }
        return null;
    }

    public Authentication extractAuthentication(HttpServletRequest request){
        String accessToken = resolveToken(request);
        if(accessToken == null || !validateToken(accessToken)) {
            throw new MemberHandler(ErrorStatus.INVALID_TOKEN);
        }
        return getAuthentication(accessToken);
    }
}
