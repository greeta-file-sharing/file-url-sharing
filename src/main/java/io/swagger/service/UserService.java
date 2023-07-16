package io.swagger.service;

import io.swagger.model.User;
import io.swagger.model.UserRepository;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public boolean authenticateUser(final String emailId, final String password) throws NoSuchAlgorithmException {
        User user = this.repository.findByEmailId(emailId);

        if (user == null) {
            return false;
        }

        return user.getHashedPassword().equals(user.getPassword());
    }
}
