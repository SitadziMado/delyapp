package com.example.adrax.dely.core;

import java.util.ArrayList;

/**
 * Created by Максим on 09.07.2017.
 */

public class User {
    private User() {

    }

    public static void register(
            String username,
            String password,
            String mail,
            InternetCallback<Boolean> callback) {
        InternetTask task = new InternetTask(REGISTER_URL, new InternetCallback<String>() {
            @Override
            public void call(String s) {

            }
        });
    }

    public static void login(String username, String password, InternetCallback<User> callback) {
        InternetTask task = new InternetTask(LOGIN_URL, new InternetCallback<String>() {
            @Override
            public void call(String s) {

            }
        });
    }

    public void logout(InternetCallback<Boolean> callback) {
        InternetTask task = new InternetTask(LOGOUT_URL, new InternetCallback<String>() {
            @Override
            public void call(String s) {

            }
        });
    }

    public void order(Order order, InternetCallback<Boolean> callback) {
        InternetTask task = new InternetTask(ORDER_URL, new InternetCallback<String>() {
            @Override
            public void call(String s) {

            }
        });
    }

    public void syncOrders(InternetCallback<Order[]> callback) {
        InternetTask task = new InternetTask(SYNC_URL, new InternetCallback<String>() {
            @Override
            public void call(String s) {

            }
        });
    }

    public void currentOrder(InternetCallback<Order> callback) {
        InternetTask task = new InternetTask(CURRENT_URL, new InternetCallback<String>() {
            @Override
            public void call(String s) {

            }
        });
    }

    private RequestStatus requestStatusFromString(String status) {
        switch (status.toLowerCase())
        {
            case INCORRECT_AUTHORIZATION_DATA:
                return RequestStatus.INCORRECT_AUTHORIZATION_DATA;
            case SERVER_PROBLEMS:
                return RequestStatus.SERVER_PROBLEMS;
            case INCORRECT_REQUEST:
                return RequestStatus.REQUEST_INCORRECT;
            case LOGIN_REGISTERED:
                return RequestStatus.LOGIN_REGISTERED;
            case LOGIN_ALREADY_TAKEN:
                return RequestStatus.LOGIN_ALREADY_TAKEN;
            case LOADED:
                return RequestStatus.ORDER_LOADED;
            case ERROR:
                return RequestStatus.ORDER_ERROR;
            case STARTED:
                return RequestStatus.ORDER_STARTED;
            case BUSY:
                return RequestStatus.ORDER_BUSY;
            case TOO_MANY:
                return RequestStatus.ORDER_TOO_MANY;
            case OK:
                return RequestStatus.ORDER_OK;
            default:
                return RequestStatus.OTHER;
        }
    }

    private static final String REGISTER_URL = "http://adrax.pythonanywhere.com/register";
    private static final String LOGIN_URL = "http://adrax.pythonanywhere.com/login";
    private static final String LOGOUT_URL = "http://adrax.pythonanywhere.com/login";
    private static final String ORDER_URL = "http://adrax.pythonanywhere.com/load_delys";
    private static final String SYNC_URL = "http://adrax.pythonanywhere.com/send_delys";
    private static final String PEEK_URL = null; // "http://adrax.pythonanywhere.com/send_delys";
    private static final String CURRENT_URL = "http://adrax.pythonanywhere.com/current_delivery";

    /// Константы авторизации
    private static final String INCORRECT_AUTHORIZATION_DATA = "incorrect_auth";    /** Некорректная инфа для авторизации */
    private static final String INCORRECT_REQUEST = "405";                          /** Тоже какая-то ошибка */
    private static final String SERVER_PROBLEMS = "login_error";                    /** Сервак гуфнулся... */

    /// Константы регистрации
    private static final String LOGIN_REGISTERED = "registered";            /** Регистрация прошла успешно */
    private static final String LOGIN_ALREADY_TAKEN = "already_taken";      /** Логин занят */

    /// Константы заказов
    private static final String LOADED = "loaded";		          /** Новый заказ успешно создан */
    private static final String ERROR = "error";                  /** Ошибка отправки нового заказа на сервер */
    private static final String WAITING = "waiting";              /** Статусы заказов ниже */
    private static final String DELIVERING = "delivering";        /** В процессе... */
    private static final String DELIVERED = "delivered";          /** Вроде бы доставлено */
    private static final String DELIVERY_DONE = "delivery_done";  /** Абсолютный суккесс */
    private static final String STARTED = "delivery_started";     /** Всё хорошо, доставка началась */
    private static final String BUSY = "delivery_busy";           /** Пользователь - слоупок, рекомендуем обновиться до 10-ки */
    private static final String OK = "ok";                        /** Заказ успешно подтверждён (обеими сторонами) */
    private static final String TOO_MANY = "already_enough";      /** Только 1 заказ можно доставлять */

    private ArrayList<Order> m_orders = new ArrayList<>();
}
