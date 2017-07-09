package com.example.adrax.dely.core;

/**
 * Created by Максим on 09.07.2017.
 */

public enum RequestStatus {
    OTHER,
    INCORRECT_AUTHORIZATION_DATA,
    SERVER_PROBLEMS,
    REQUEST_INCORRECT,
    LOGIN_REGISTERED,
    LOGIN_ALREADY_TAKEN,
    ORDER_LOADED,
    ORDER_TOO_MANY,
    ORDER_STARTED,
    ORDER_BUSY,
    ORDER_OK,
    ORDER_ERROR,
}