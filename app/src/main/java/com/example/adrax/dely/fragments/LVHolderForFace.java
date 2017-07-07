package com.example.adrax.dely.fragments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.adrax.dely.MActivity;
import com.example.adrax.dely.R;

import static com.example.adrax.dely.MActivity.face_cur_order_text;
import static com.example.adrax.dely.MActivity.face_orders;
import static com.example.adrax.dely.MActivity.selected_id;

public final class LVHolderForFace extends RecyclerView.ViewHolder {
    //объявим поля, созданные в файле интерфейса itemView.xml
    TextView tvFrom;
    TextView tvTo;
    String tvId;
    TextView tvCustomer;
    TextView tvPhoneNumber;
    TextView tvCost;
    TextView tvPayment;
    TextView tvDescription;
    public LinearLayout Item;

    private Context mContext;

    //объявляем конструктор
    public LVHolderForFace(Context context, View itemView){
        super(itemView);

        mContext = context;

        //привязываем элементы к полям
        tvDescription = (TextView)itemView.findViewById(R.id.tvDescription);
        tvFrom = (TextView)itemView.findViewById(R.id.tvFrom);
        tvTo = (TextView)itemView.findViewById(R.id.tvTo);
        tvCustomer = (TextView)itemView.findViewById(R.id.tvCustomer);
        tvPhoneNumber = (TextView)itemView.findViewById(R.id.tvPhoneNumber);
        tvCost = (TextView)itemView.findViewById(R.id.tvCost);
        tvPayment = (TextView)itemView.findViewById(R.id.tvPayment);
        Item = (LinearLayout)itemView.findViewById(R.id.item);
        Item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (face_orders != null) {
                    int id = Integer.parseInt(tvId);
                    StringBuilder sb = new StringBuilder();
                    sb.append("Название: ");
                    sb.append(face_orders[id].getName());
                    sb.append("\n");
                    sb.append("Откуда: ");
                    sb.append(face_orders[id].getFrom());
                    sb.append("\n");
                    sb.append("Куда: ");
                    sb.append(face_orders[id].getTo());
                    sb.append("\n");
                    sb.append("Заказчик: ");
                    sb.append(face_orders[id].getCustomer());//ToDo:customer -> courier
                    sb.append("\n");
                    sb.append("Номер телефона: ");
                    sb.append(face_orders[id].getTelephoneNumber());
                    sb.append("\n");
                    sb.append("Оплата: ");
                    sb.append(face_orders[id].getPayment());
                    sb.append("\n");
                    if (!face_orders[id].getCost().equals("")) {
                        sb.append("Аванс: ");
                        sb.append(face_orders[id].getCost());
                        sb.append("руб. \n");
                    }
                    if (!face_orders[id].getWeight().equals("")) {
                        sb.append("Вес: ");
                        sb.append(face_orders[id].getWeight());
                        sb.append("\n");
                    }
                    if (!face_orders[id].getSize().equals("")) {
                        sb.append("Размер: ");
                        sb.append(face_orders[id].getSize());
                        sb.append("\n");
                    }
                    if (!face_orders[id].getCode().equals("")) {
                        sb.append("Код домофона: ");
                        sb.append(face_orders[id].getCode());
                        sb.append("\n");
                    }
                    if (!face_orders[id].getEntrance().equals("")) {
                        sb.append("Подъезд: ");
                        sb.append(face_orders[id].getEntrance());
                        sb.append("\n");
                    }
                    if (!face_orders[id].getFloor().equals("")) {
                        sb.append("Этаж: ");
                        sb.append(face_orders[id].getFloor());
                        sb.append("\n");
                    }

                    sb.append("\n");
                    //face_tab.getTabWidget().getChildAt(3).setVisibility(View.VISIBLE);
                    //face_tab.setCurrentTabByTag("ftab4");
                    selected_id = Integer.parseInt(face_orders[id].getId());

                    face_cur_order_text = sb.toString();
                    switchFragment();
                }


            }
        });
    }

    private void switchFragment() {
        if (mContext == null)
            return;
        if (mContext instanceof MActivity) {
            MActivity feeds = (MActivity) mContext;
            feeds.OrderShowFragment();
        }
    }
}
