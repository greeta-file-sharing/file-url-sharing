package io.swagger.interceptor;

import java.util.Map;
import java.util.Optional;

import io.swagger.model.User;
import io.swagger.model.UserRepository;
import io.swagger.service.JwtService;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.interfaces.Claim;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class UserAuthenticateInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserRepository repository;

    @Autowired
    private JwtService jwtService;

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) throws Exception {
        String url = request.getRequestURI();

        if (request.getMethod().equalsIgnoreCase("options")) {
            return true;
        }

        switch (url) {
            case "/v1/users":
            case "/v1/login":
                if (request.getMethod().equals("POST")) {
                    return true;
                }
                break;
	        default:
	            break;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            int offset = "Bearer ".length();
            String token = authHeader.substring(offset);
            Map<String, Claim> claims = this.jwtService.verifyToken(token);
            Claim username = claims.get("username"); 
            
            if (username != null) {
                User user = this.repository.findByEmailId(username.toString());

                if (user == null) {
                    return false;
                }

                request.setAttribute("user", user);
                return true;
            }

            return false;
        }

        throw new Exception("Forbidden!");
    }
}
