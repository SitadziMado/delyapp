package com.example.adrax.dely.core;

/**
 * Created by Максим on 09.07.2017.
 */

public class Order {
    public Boolean cancel() {
        return Boolean.FALSE;
    }

    public Boolean start() {
        return Boolean.FALSE;
    }

    public Boolean finish() {
        return Boolean.FALSE;
    }

    public Boolean accept() {
        return Boolean.FALSE;
    }

    public OrderStatus getOrderStatus() {
        return m_orderStatus;
    }

    private static final String CANCEL_URL = null; // "http://adrax.pythonanywhere.com/send_delys";
    private static final String START_URL = "http://adrax.pythonanywhere.com/ch_dely";
    private static final String FINISH_URL = "http://adrax.pythonanywhere.com/delivered";
    private static final String ACCEPT_URL = "http://adrax.pythonanywhere.com/delivery_done";
    private static final String STATUS_URL = "http://adrax.pythonanywhere.com/chosen";

    private OrderStatus m_orderStatus;
}
