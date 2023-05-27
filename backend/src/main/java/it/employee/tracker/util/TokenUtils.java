package it.employee.tracker.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import it.employee.tracker.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class TokenUtils {

    @Value("spring-security-example")
    private String APP_NAME;
    @Value("somesecret")
    public String SECRET;
    @Value("300000")
    private int ACCESS_TOKEN_EXPIRES_IN;
    @Value("600000")
    private int REFRESH_TOKEN_EXPIRES_IN;
    @Value("Authorization")
    private String AUTH_HEADER;

    private static final String AUDIENCE_WEB = "web";

    // Algoritam za potpisivanje JWT
    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setIssuer(APP_NAME)
                .setSubject(user.getUsername())
                .setAudience(generateAudience())
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate(ACCESS_TOKEN_EXPIRES_IN))
                .claim("role", user.getRoles().get(0).getName())
                .claim("name", user.getName())
                .claim("surname", user.getSurname())
                .claim("id", user.getId())

                .signWith(SIGNATURE_ALGORITHM, SECRET).compact();
    }

    public String generateRefreshToken(User user) {
        return Jwts.builder()
                .setIssuer(APP_NAME)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date()).setExpiration(generateExpirationDate(REFRESH_TOKEN_EXPIRES_IN))
                .signWith(SIGNATURE_ALGORITHM, SECRET).compact();
    }
    private String generateAudience() {
        return AUDIENCE_WEB;
    }

    private Date generateExpirationDate(int date) {
        return new Date(new Date().getTime() + date);
    }

    public String getToken(HttpServletRequest request) {
        String authHeader = getAuthHeaderFromHeader(request);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    public String getUsernameFromToken(String token) {
        String username;

        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            username = claims.getSubject();
        } catch (ExpiredJwtException ex) {
            throw ex;
        } catch (Exception e) {
            username = null;
        }

        return username;
    }

    public Date getIssuedAtDateFromToken(String token) {
        Date issueAt;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            issueAt = claims.getIssuedAt();
        } catch (ExpiredJwtException ex) {
            throw ex;
        } catch (Exception e) {
            issueAt = null;
        }
        return issueAt;
    }

    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException ex) {
            throw ex;
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        User user = (User) userDetails;
        final String username = getUsernameFromToken(token);
        final Date created = getIssuedAtDateFromToken(token);

        return (username != null
                && username.equals(((User) userDetails).getEmail())
        );
    }

    public int getAccessTokenExpiresIn() {
        return ACCESS_TOKEN_EXPIRES_IN;
    }

    public int getRefreshTokenExpiresIn() {
        return REFRESH_TOKEN_EXPIRES_IN;
    }

    public String getAuthHeaderFromHeader(HttpServletRequest request) {
        return request.getHeader(AUTH_HEADER);
    }


}