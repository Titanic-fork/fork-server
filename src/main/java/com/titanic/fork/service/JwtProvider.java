package com.titanic.fork.service;

import com.titanic.fork.web.dto.request.AccountRequestDto;
import com.titanic.fork.web.login.LoginEnum;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class JwtProvider {

    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 10;

    public String createJwtTokenWithEmail(String email) {
        Map<String, Object> header = new HashMap<>();
        header.put(LoginEnum.TYP.getValue(), LoginEnum.TYP_VALUE.getValue());
        header.put(LoginEnum.ALG.getValue(), LoginEnum.ALG.getValue());

        Map<String, Object> payload = new HashMap<>();
        payload.put(LoginEnum.AUTHORIZATION.getValue(), email);

        return Jwts.builder()
                .setHeader(header)
                .setClaims(payload)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SIGNATURE_ALGORITHM, LoginEnum.SECRET_KEY.getValue())
                .compact();
    }

    public static String parseJwt(String jwtToken) {
        return Jwts.parser()
                .setSigningKey(LoginEnum.SECRET_KEY.getValue())
                .parseClaimsJws(jwtToken)
                .getBody()
                .getSubject();
    }

    public void loadJwtToHeader(HttpServletResponse response, AccountRequestDto accountRequestDto) {
        response.setHeader(LoginEnum.AUTHORIZATION.getValue(), createJwtTokenWithEmail(accountRequestDto.getEmail()));
        log.info("jwtToken, {}", createJwtTokenWithEmail(accountRequestDto.getEmail()));
    }
}
