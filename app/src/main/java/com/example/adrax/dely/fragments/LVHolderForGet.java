package com.example.adrax.dely.fragments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.adrax.dely.MActivity;
import com.example.adrax.dely.R;

import static com.example.adrax.dely.MActivity.delyDescription;
import static com.example.adrax.dely.MActivity.orders;
import static com.example.adrax.dely.MActivity.selected_id;

public final class LVHolderForGet extends RecyclerView.ViewHolder {
    //объявим поля, созданные в файле интерфейса itemView.xml
    TextView tvFrom;
    TextView tvTo;
    String tvId;
    TextView tvCustomer;
    TextView tvPhoneNumber;
    TextView tvCost;
    TextView tvPayment;
    TextView tvDescription;
    LinearLayout Item;

    private Context mContext;


    //объявляем конструктор
    public LVHolderForGet(View itemView, Context context){
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

                if (null != orders) {
                    int id = Integer.parseInt(tvId);
                    //id = 0;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Название: ");
                    sb.append(orders[id].getName());
                    sb.append("\n");
                    sb.append("Откуда: ");
                    sb.append(orders[id].getFrom());
                    sb.append("\n");
                    sb.append("Куда: ");
                    sb.append(orders[id].getTo());
                    sb.append("\n");
                    sb.append("Заказчик: ");
                    sb.append(orders[id].getCustomer());
                    sb.append("\n");
                    sb.append("Номер телефона: ");
                    sb.append(orders[id].getTelephoneNumber());
                    sb.append("\n");
                    sb.append("Оплата: ");
                    sb.append(orders[id].getPayment());
                    sb.append("\n");
                    if (!orders[id].getCost().equals("")) {
                        sb.append("Аванс: ");
                        sb.append(orders[id].getCost());
                        sb.append("руб. \n");
                    }
                    if (!orders[id].getWeight().equals("")) {
                        sb.append("Вес: ");
                        sb.append(orders[id].getWeight());
                        sb.append("\n");
                    }
                    if (!orders[id].getSize().equals("")) {
                        sb.append("Размер: ");
                        sb.append(orders[id].getSize());
                        sb.append("\n");
                    }
                    if (!orders[id].getCode().equals("")) {
                        sb.append("Код домофона: ");
                        sb.append(orders[id].getCode());
                        sb.append("\n");
                    }
                    if (!orders[id].getEntrance().equals("")) {
                        sb.append("Подъезд: ");
                        sb.append(orders[id].getEntrance());
                        sb.append("\n");
                    }
                    if (!orders[id].getFloor().equals("")) {
                        sb.append("Этаж: ");
                        sb.append(orders[id].getFloor());
                        sb.append("\n");
                    }

                    selected_id = Integer.parseInt(orders[id].getId());

                    delyDescription = sb.toString();

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
            feeds.DelyShowFragment();
        }
    }
}
