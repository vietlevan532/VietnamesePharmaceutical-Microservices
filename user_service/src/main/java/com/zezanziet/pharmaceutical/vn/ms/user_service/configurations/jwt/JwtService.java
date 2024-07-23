package com.zezanziet.pharmaceutical.vn.ms.user_service.configurations.jwt;

import com.zezanziet.pharmaceutical.vn.ms.user_service.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "920ecdab8b005429fa9b120e6c2be093f8c63706a2d559202c32093f9ce6624e";

    private static final long TOKEN_VALIDITY_IN_SECONDS = 86400;
    private static final long TOKEN_VALIDITY_IN_SECONDS_REMEMBER_ME = 2592000;

    private final long tokenValidityInMilliseconds;
    private final long tokenValidityInMillisecondsForRememberMe;

    public JwtService() {
        this.tokenValidityInMilliseconds = 1000 * TOKEN_VALIDITY_IN_SECONDS;
        this.tokenValidityInMillisecondsForRememberMe = 1000 * TOKEN_VALIDITY_IN_SECONDS_REMEMBER_ME;
    }

    public String extractLogin(String token) {
        return extractAllClaims(token).get("loginBy", String.class);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(User user, String loginBy, boolean rememberMe) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("loginBy", loginBy);
        claims.put("email", user.getEmail());
        claims.put("phone", user.getPhoneNumber());
        claims.put("status", user.getStatus());
        claims.put("active", user.getActive());
        claims.put("role", user.getRole());
        return generateToken(claims, user, rememberMe);
    }

    public boolean isTokenValid(String token, User user) {
        final String login = extractLogin(token);
        return (login.equals(user.getEmail()) ||
                login.equals(user.getUsername()) ||
                login.equals(user.getPhoneNumber())) &&
                !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private String generateToken(
            Map<String, Object> extraClaims,
            User user,
            boolean rememberMe
    ) {
        long validity = rememberMe ? this.tokenValidityInMillisecondsForRememberMe
                : this.tokenValidityInMilliseconds;
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // todo (let see code below to optimize code above) Technology used: AWS Secret Manager
    /*private static final String SECRET_NAME = "jwt-key";

    public String extractLogin(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        try (AwsSecretsManagerClient client = AwsSecretsManagerClient.builder().build()) {
            GetSecretValueRequest request = new GetSecretValueRequest()
                    .withSecretId(SECRET_NAME)
                    .withExtractParam("secret");
            GetSecretValueResponse response = client.getSecretValue(request);
            String secretValue = response.getSecretString();
            return Jwts
                    .parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretValue.getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (AwsException e) {
            throw new RuntimeException("Không thể lấy bí mật từ AWS Secrets Manager", e);
        }
    }*/
}
