package org.example.taskify.exception;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    REVOKED_TOKEN(1001, HttpStatus.UNAUTHORIZED, "Token has been revoked"),
    INVALID_TOKEN(1002, HttpStatus.UNAUTHORIZED, "Invalid token signature"),
    EXPIRED_TOKEN(1003, HttpStatus.UNAUTHORIZED, "Token has expired"),
    INVALID_CREDENTIALS(1006, HttpStatus.UNAUTHORIZED, "Invalid username or password"),
    UNAUTHENTICATED(1007, HttpStatus.UNAUTHORIZED, "Unauthenticated request"),
    ACCESS_DENIED(1008, HttpStatus.FORBIDDEN, "Access denied"),

    USERNAME_ALREADY_EXISTS(2000, HttpStatus.UNAUTHORIZED, "Username already exists"),
    USER_NOT_FOUND(2001, HttpStatus.NOT_FOUND, "User not found"),
    PERMISSION_ALREADY_EXISTS(2002, HttpStatus.BAD_REQUEST, "Permission already exists"),
    ROLE_ALREADY_EXISTS(2003, HttpStatus.BAD_REQUEST, "Role already exists"),
    PASSWORD_ALREADY_EXISTS(2004, HttpStatus.BAD_REQUEST, "Password already exists"),

    TAG_ALREADY_EXISTS(2005, HttpStatus.BAD_REQUEST, "Tag already exists"),
    TAG_NOT_FOUND(2006, HttpStatus.NOT_FOUND, "Tag not found"),

    TODO_NOT_FOUND(2007, HttpStatus.NOT_FOUND, "Todo not found"),

    UNCATEGORIZED_ERROR(5000, HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred"),
    ;

    int code;
    HttpStatus statusCode;
    String message;
}
