package bz.nils.dev.jmettbot.Api.Interceptor;

import bz.nils.dev.jmettbot.Data.ApiKeys.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class ApiTokenInterceptor implements HandlerInterceptor {
    private final ApiKeyService apiKeyService;

    @Autowired
    public ApiTokenInterceptor(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String apiKeyHashed = hash(request.getHeader("X-APIKEY"));

        if (!apiKeyService.keyExists(apiKeyHashed)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private String hash(String input) throws Exception {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Hashing went wrong");
        }
    }
}
