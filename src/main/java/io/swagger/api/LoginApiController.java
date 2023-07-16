package io.swagger.api;

import io.swagger.service.JwtService;
import io.swagger.service.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-02-27T07:39:21.717Z[GMT]")
@RestController
public class LoginApiController implements LoginApi {

    @Autowired
    private UserService service;

    @Autowired
    private JwtService jwtService;

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public LoginApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<String> login(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true,schema=@Schema()) @RequestParam(value="emailId", required=true)  String emailId,@Parameter(in = ParameterIn.DEFAULT, description = "", required=true,schema=@Schema()) @RequestParam(value="password", required=true)  String password) throws NoSuchAlgorithmException {
        String accept = request.getHeader("Accept");
    
        if (accept != null && accept.contains("application/json")) {
            if (this.service.authenticateUser(emailId, password)) {
                String token = jwtService.generateToken(emailId);    
                return new ResponseEntity<String>(token, HttpStatus.OK);
            }
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }
}
