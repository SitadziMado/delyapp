package com.example.adrax.dely.core;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Максим on 09.07.2017.
 */

public class User {
    private User() {

    }

    public static User fromString(String jsonString) {
        User user = new User();
        try {
            JSONObject userData = new JSONObject(jsonString);
            user.m_about = userData.getString("About");
            user.m_hash = userData.getString("Hash");
            user.m_mail = userData.getString("Mail");
            user.m_middleName = userData.getString("Midname");
            user.m_money = String.valueOf(userData.getDouble("Money"));
            user.m_name = userData.getString("Name");
            user.m_phone = userData.getString("Selnum");
            user.m_surname = userData.getString("Surname");
        } catch (JSONException ex) {
            user = null;
        }

        return user;
    }

    public static void register(
            String username,
            String password,
            String mail,
            final InternetCallback<Boolean> callback) {
        InternetTask task = new InternetTask(REGISTER_URL, new InternetCallback<String>() {
            @Override
            public void call(String s) {
                Boolean result = Boolean.FALSE;

                switch (requestStatusFromString(s.toLowerCase())) {
                    case LOGIN_REGISTERED:
                        result = Boolean.TRUE;
                        break;

                    case LOGIN_ALREADY_TAKEN:
                        // result = Boolean.FALSE;
                        break;

                    default:
                        // result = Boolean.FALSE;
                        break;
                }

                callback.call(result);
            }
        });

        task.execute(
                "Name", username,
                "Password", password,
                "Mail", mail
        );
    }

    public static void login(
            String username,
            String password,
            final InternetCallback<User> callback) {
        InternetTask task = new InternetTask(LOGIN_URL, new InternetCallback<String>() {
            @Override
            public void call(String s) {
                User user = null;
                switch (requestStatusFromString(s.toLowerCase())) {
                    case INCORRECT_AUTHORIZATION_DATA:
                        break;

                    case SERVER_PROBLEMS:
                        break;

                    case REQUEST_INCORRECT:
                        break;

                    case OTHER:
                        user = User.fromString(s);
                        break;
                }

                callback.call(user);
            }
        });

        task.execute(
                "Username", username,
                "Password", password
        );
    }

    public void logout(final InternetCallback<Boolean> callback) {
        InternetTask task = new InternetTask(LOGOUT_URL, new InternetCallback<String>() {
            @Override
            public void call(String s) {
                callback.call(Boolean.TRUE);
            }
        });

        task.execute();
    }

    public void order(Order order, final InternetCallback<Boolean> callback) {
        InternetTask task = new InternetTask(ORDER_URL, new InternetCallback<String>() {
            @Override
            public void call(String s) {
                Boolean result = Boolean.FALSE;

                switch (requestStatusFromString(s.toLowerCase())) {
                    case ORDER_ERROR:
                        break;

                    case ORDER_LOADED:
                        result = Boolean.TRUE;
                        break;
                }

                callback.call(result);
            }
        });

        task.execute(
                "customer", order.getField("customer"),
                "from", order.getField("from"),
                "to", order.getField("to"),
                "cost", order.getField("cost"),
                "payment", order.getField("payment"),
                "padik", order.getField("padik"),
                "code", order.getField("code"),
                "floor", order.getField("floor"),
                "ko", order.getField("ko"),
                "num", order.getField("num"),
                "wt", order.getField("wt"),
                "size", order.getField("size"),
                "hash", order.getField("hash"),
                "description", order.getField("description")
        );
    }

    public void syncOrders(final InternetCallback<Order[]> callback) {
        InternetTask task = new InternetTask(SYNC_URL, new InternetCallback<String>() {
            @Override
            public void call(String s) {
                Order[] orders = null;
                if (!s.equals("404")) {
                    orders = Order.fromString(s);
                }

                callback.call(orders);
            }
        });

        task.execute();
    }

    public void currentOrder(final InternetCallback<Order> callback) {
        InternetTask task = new InternetTask(CURRENT_URL, new InternetCallback<String>() {
            @Override
            public void call(String s) {
                Order[] orders = null;
                if (!s.equals(ERROR)) {
                    orders = Order.fromString(s);
                }

                callback.call(orders[0]);
            }
        });

        task.execute("hash", m_hash);
    }

    public void cancel(final InternetCallback<Boolean> callback) {
        InternetTask task = new InternetTask(CANCEL_URL, new InternetCallback<String>() {
            @Override
            public void call(String s) {
                callback.call(Boolean.FALSE);
            }
        });

        task.execute();
    }

    public void start(Order order, final InternetCallback<Boolean> callback) {
        InternetTask task = new InternetTask(START_URL, new InternetCallback<String>() {
            @Override
            public void call(String s) {
                Boolean result = Boolean.FALSE;
                switch (requestStatusFromString(s.toLowerCase())) {
                    case ORDER_BUSY:
                        break;

                    case ORDER_TOO_MANY:
                        break;

                    case ORDER_STARTED:
                        result = Boolean.TRUE;
                        break;
                }

                callback.call(result);
            }
        });

        if (order == null) {
            throw new NullPointerException();
        }

        task.execute(
                "hash", m_hash,
                "dely_id", order.getField("dely_id"),
                "courier", m_login
        );
    }

    public void finish(Order order, final InternetCallback<Boolean> callback) {
        InternetTask task = new InternetTask(FINISH_URL, new InternetCallback<String>() {
            @Override
            public void call(String s) {
                Boolean result = Boolean.FALSE;
                switch (requestStatusFromString(s.toLowerCase())) {
                    case ORDER_BUSY:
                        break;

                    case ORDER_STARTED:
                        result = Boolean.TRUE;
                        break;
                }

                callback.call(result);
            }
        });

        if (order == null) {
            throw new NullPointerException();
        }

        task.execute(
                "hash", m_hash,
                "dely_id", order.getField("dely_id")
        );
    }

    public void accept(Order order, final InternetCallback<Boolean> callback) {
        InternetTask task = new InternetTask(ACCEPT_URL, new InternetCallback<String>() {
            @Override
            public void call(String s) {
                Boolean result = Boolean.FALSE;
                switch (requestStatusFromString(s.toLowerCase())) {
                    case ORDER_ERROR:
                        break;

                    case ORDER_OK:
                        result = Boolean.TRUE;
                        break;
                }

                callback.call(result);
            }
        });

        if (order == null) {
            throw new NullPointerException();
        }

        task.execute(
                "hash", m_hash,
                "dely_id", order.getField("dely_id")
        );
    }

    public void getOrderStatus(Order order, final InternetCallback<OrderStatus> callback) {
        InternetTask task = new InternetTask(STATUS_URL, new InternetCallback<String>() {
            @Override
            public void call(String s) {
                OrderStatus result;
                switch (s.toLowerCase()) {
                    case WAITING:
                        result = OrderStatus.WAITING;
                        break;

                    case DELIVERING:
                        result = OrderStatus.DELIVERING;
                        break;

                    case DELIVERED:
                        result = OrderStatus.DELIVERED;
                        break;

                    case DELIVERY_DONE:
                        result = OrderStatus.DELIVERY_DONE;
                        break;

                    case ERROR:
                        result = OrderStatus.ERROR;
                        break;

                    default:
                        result = OrderStatus.ERROR;
                        break;
                }

                callback.call(result);
            }
        });

        if (order == null) {
            throw new NullPointerException();
        }

        task.execute(
                "hash", m_hash,
                "dely_id", order.getField("dely_id")
        );
    }

    private static RequestStatus requestStatusFromString(String status) {
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

    private static final String CANCEL_URL = null; // "http://adrax.pythonanywhere.com/send_delys";
    private static final String START_URL = "http://adrax.pythonanywhere.com/ch_dely";
    private static final String FINISH_URL = "http://adrax.pythonanywhere.com/delivered";
    private static final String ACCEPT_URL = "http://adrax.pythonanywhere.com/delivery_done";
    private static final String STATUS_URL = "http://adrax.pythonanywhere.com/chosen";

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
    private String m_about;         /** Информация о юзвере  */
    private String m_hash;          /** Выданный хэш */
    private String m_mail;          /** Мыло пользователя */
    private String m_middleName;    /** Его отчество */
    private String m_money;         /** Его капуста (не используется) */
    private String m_name;          /** Его имя */
    private String m_phone;         /** Телефонный номер */
    private String m_surname;       /** Фамилия */
    private String m_login;         /** Текущий логин */
}
