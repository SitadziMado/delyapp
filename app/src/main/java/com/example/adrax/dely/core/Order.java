package com.example.adrax.dely.core;

import org.jetbrains.annotations.Contract;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Максим on 09.07.2017.
 */

public class Order {
    public Order(String... params) {
        if ((params.length & 1) == 0) {
            for (int i = 0; i < params.length; i += 2) {
                String key = params[i];
                String value = params[i + 1];

                if (key == null || value == null || key.equals("") || value.equals("")) {
                    throw new IllegalArgumentException("Параметры не могут быть пустыми.");
                }

                setField(key, value);
            }
        } else {
            throw new IllegalArgumentException(
                    "Требуется четное количество аргументов для составления пар."
            );
        }
    }

    public Order(
            String customer,
            String from,
            String to,
            String cost,
            String payment,
            String entrance,
            String code,
            String floor,
            String room,
            String telephoneNumber,
            String weight,
            String size,
            String time,
            String name) {
        initialize(
                customer,
                from,
                to,
                cost,
                payment,
                entrance,
                code,
                floor,
                room,
                telephoneNumber,
                weight,
                size,
                time,
                name
        );
    }

    public static Order[] fromString(String jsonString) {
        Order[] ret;
        try {
            /// Список текущих заказов
            ArrayList<Order> orders = new ArrayList<>();
            JSONObject list = new JSONObject(jsonString);

            for (Integer i = 1; list.has(i.toString()); ++i) {
                // По порядку выгружаем доступные заказы
                JSONObject cur = list.getJSONObject(i.toString());

                Order order = new Order();

                Iterator<String> it = cur.keys();

                while (it.hasNext()) {
                    String key = it.next();
                    order.setField(key, cur.getString(key));
                }

                orders.add(order);

                /* orders.add(new Order(
                        cur.getString("customer"),
                        cur.getString("from"),
                        cur.getString("to"),
                        cur.getString("cost"),
                        String.valueOf(cur.getDouble("payment")),
                        "", // cur.getString("padik")
                        cur.getString("code"),
                        cur.getString("floor"),
                        cur.getString("ko"),
                        cur.getString("num"),
                        cur.getString("recnum"),
                        cur.getString("wt"),
                        cur.getString("size"),
                        cur.getString("time"),
                        cur.getString("description"
                ))); */
            }

            ret = new Order[orders.size()];
            ret = orders.toArray(ret);
        } catch (JSONException ex) {
            ret = null;
        }
        return ret;
    }

    public void setField(String fieldName, String fieldValue) {
        m_fields.put(fieldName, fieldValue);
    }

    public String getField(String fieldName) {
        return m_fields.get(fieldName);
    }

    @Contract(
        "null, null, null, null, null, null, null, null, null, null, null, null, null, null -> fail"
    )
    private void initialize(
            String customer,
            String from,
            String to,
            String cost,
            String payment,
            String entrance,
            String code,
            String floor,
            String room,
            String telephoneNumber,
            String weight,
            String size,
            String time,
            String name) {
        m_customer = customer;
        m_from = from;
        m_to = to;
        m_cost = cost;
        m_payment = payment;
        m_entrance = entrance;
        m_code = code;
        m_floor = floor;
        m_room = room;
        m_telephoneNumber = telephoneNumber;
        m_weight = weight;
        m_size = size;
        m_time = time;
        m_name = name;
    }

    private OrderStatus m_orderStatus;
    private HashMap<String, String> m_fields = new HashMap<>();

    private int m_id = -1;
    private String m_customer;
    private String m_from;
    private String m_to;
    private String m_cost;
    private String m_payment;
    private String m_entrance;
    private String m_code;
    private String m_floor;
    private String m_room;
    private String m_telephoneNumber;
    private String m_weight;
    private String m_size;
    private String m_time;                         /** Время заказа */
    private String m_widthHeightLength;
    private String m_name;
    // private String m_additionalTelephoneNumber;
}
