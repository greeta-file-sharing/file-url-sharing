package io.swagger.store;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;

// StorE.
public interface TokenStore {

    String create(HttpServletRequest request, Token token);
    Optional<Token> read(HttpRequest request, String tokenId);
    void revoke(HttpRequest request, String tokenId);

    class Token implements SecureToken {
        public final Object expiry;
        public final String username;
        public final Map<String, String> attributes;

        public Token(Object expiry2, String string) {
            this.expiry = expiry2;
            this.username = string;
            this.attributes = new ConcurrentHashMap<>();
        }

        @Override
        public Optional<Token> read(HttpRequest request, String tokenId) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void revoke(HttpRequest request, String tokenId) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public String create(HttpServletRequest request, Token token) {
            // TODO Auto-generated method stub
            return null;
        }
    }

}