package com.springboot.mycgv.config.exception;

public class AccessTokenException extends RuntimeException {

    TOKEN_ERROR token_error;

    public enum TOKEN_ERROR {

        UNACCEPT(401, "token is null or too short"),
        BADTYPE(401, "Token Type Bearer");

        private int status;
        private String msg;

        TOKEN_ERROR(int status, String msg) {
            this.status = status;
            this.msg = msg;
        }
    }

    public AccessTokenException(TOKEN_ERROR token_error) {
        super(token_error.name());
        this.token_error = token_error;

    }
}
