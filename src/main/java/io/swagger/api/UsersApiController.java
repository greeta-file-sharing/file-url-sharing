package io.swagger.api;

import io.swagger.model.User;
import io.swagger.model.UserRepository;
import io.swagger.model.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-02-27T07:39:21.717Z[GMT]")
@RestController
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private UserRepository repository;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> createUser(@Parameter(in = ParameterIn.DEFAULT, description = "Send the User Object", required=true, schema=@Schema()) @Valid @RequestBody User body) throws NoSuchAlgorithmException {
        User user = this.repository.findByEmailId(body.getEmailId());

        if (user != null) {
            // Fails uniqueness.
            return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
        }

        body.setPassword(body.getHashedPassword());

        // Create user in database.
        this.repository.save(body);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    public ResponseEntity<Users> listUsers(@Parameter(in = ParameterIn.QUERY, description = "How many items to return at one time (max 100)" ,schema=@Schema()) @Valid @RequestParam(value = "limit", required = false) Integer limit) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Users>(objectMapper.readValue("[ {\n  \"firstName\" : \"firstName\",\n  \"lastName\" : \"lastName\",\n  \"phone\" : \"phone\",\n  \"emailId\" : \"emailId\",\n  \"id\" : 0,\n  \"tag\" : \"tag\"\n}, {\n  \"firstName\" : \"firstName\",\n  \"lastName\" : \"lastName\",\n  \"phone\" : \"phone\",\n  \"emailId\" : \"emailId\",\n  \"id\" : 0,\n  \"tag\" : \"tag\"\n} ]", Users.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Users>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Users>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<User> showUserById(@Parameter(in = ParameterIn.PATH, description = "The id of the user to retrieve", required=true, schema=@Schema()) @PathVariable("userId") String userId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<User>(objectMapper.readValue("{\n  \"firstName\" : \"firstName\",\n  \"lastName\" : \"lastName\",\n  \"phone\" : \"phone\",\n  \"emailId\" : \"emailId\",\n  \"id\" : 0,\n  \"tag\" : \"tag\"\n}", User.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
    }

}
