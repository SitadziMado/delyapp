package com.example.adrax.dely.fragments;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adrax.dely.R;
import com.example.adrax.dely.delivery.DeliveryOrder;
import com.example.adrax.dely.delivery.DeliveryOrderStatus;

import java.util.ArrayList;

import static com.example.adrax.dely.LoginActivity.user;
import static com.example.adrax.dely.MActivity.face_deliver_text;
import static com.example.adrax.dely.MActivity.face_delivery;
import static com.example.adrax.dely.MActivity.face_orders;
import static com.example.adrax.dely.MActivity.update_face;


public class FragmentFace extends Fragment {

    public TabHost face_tab;
    public TextView face_deliver_text_view;
    public Button btn_finish;
    public EditText text_code;
    //Объявляем RecyclerView
    RecyclerView rvMain;
    //Объявляем адаптер
    AdapterForFace adapterForFace;


    Context mContext;


    public FragmentFace() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_face, container, false);

        mContext = this.getActivity();


        face_deliver_text_view = (TextView) root.findViewById(R.id.text_cur_dely);

        face_tab = (TabHost) root.findViewById(R.id.fhost_tab);
        face_tab.setup();
        TabHost.TabSpec ts = face_tab.newTabSpec("ftab1");
        ts.setContent(R.id.ftab1);
        ts.setIndicator("Доставки");
        face_tab.addTab(ts);
        ts = face_tab.newTabSpec("ftab2");
        ts.setContent(R.id.ftab2);
        ts.setIndicator("Заказы");
        face_tab.addTab(ts);
        ts = face_tab.newTabSpec("ftab3");
        ts.setContent(R.id.ftab3);
        ts.setIndicator("История");
        face_tab.addTab(ts);
        face_tab.getTabWidget().getChildAt(2).setVisibility(View.GONE);
        // Inflate the layout for this fragment

        //обновляем заказы/доставки
        update_face();



        //окончание доставки
        btn_finish = (Button) root.findViewById(R.id.btn_finish);
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //if(user.orderStart(selected_id))
                user.orderFinish(Integer.parseInt(face_delivery.getId()));
                Toast.makeText(getActivity(),"Доставка завершена!", Toast.LENGTH_LONG).show();
                face_deliver_text_view.append("\n Дождитесь подтверждения заказчика.");
                if (user.orderStatus(face_delivery) == DeliveryOrderStatus.DELIVERY_DONE | user.orderStatus(face_delivery) == DeliveryOrderStatus.DELIVERED) btn_finish.setVisibility(View.GONE);
                else btn_finish.setVisibility(View.VISIBLE);
            }

        });

        // Code
        text_code = (EditText) root.findViewById(R.id.text_code);

        if (face_delivery==null | user.orderStatus(face_delivery) == DeliveryOrderStatus.DELIVERY_DONE | user.orderStatus(face_delivery) == DeliveryOrderStatus.DELIVERED)
        {
            text_code.setVisibility(View.GONE);
            btn_finish.setVisibility(View.GONE);
        }
        else
        {
            text_code.setVisibility(View.VISIBLE);
            btn_finish.setVisibility(View.VISIBLE);
        }

        //Привязываем RecyclerView к элементу
        rvMain = (RecyclerView)root.findViewById(R.id.face_orders_list);
        //И установим LayoutManager
        rvMain.setLayoutManager(new LinearLayoutManager(getActivity()));
        //свистелки-перделки
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvMain.getContext(),
                llm.getOrientation());
        rvMain.addItemDecoration(dividerItemDecoration);
        //Создаём адаптер
        adapterForFace = new AdapterForFace(mContext,getOrders());
        //Применим наш адаптер к RecyclerView
        rvMain.setAdapter(adapterForFace);
        // Inflate the layout for this fragment

        return root;
    }


    private ArrayList<Dely> getOrders() {
        ArrayList<Dely> delys = new ArrayList<>();
        Dely Poof;
        if (face_orders !=null)
        {
            for (Integer i=0;i<face_orders.length;i++)
            if (user.orderStatus(face_orders[i]) != DeliveryOrderStatus.DELIVERY_DONE){
                DeliveryOrder cur = face_orders[i];
                Poof = new Dely();
                Poof.id = i.toString();
                Poof.description = "Название: " + cur.getName();
                Poof.from = "Откуда: "+ cur.getFrom();
                Poof.to = "Куда: "+ cur.getTo();
                Poof.customer = "Заказчик: "+ cur.getCustomer();
                Poof.phonenumber = "Номер телефона: " + cur.getTelephoneNumber();
                if (cur.getCost().equals("")) Poof.cost = "Аванс: 0 руб.";
                else Poof.cost ="Аванс: " + cur.getCost() + " руб.";
                if (cur.getPayment().equals("")) Poof.payment = "Оплата: 0 руб.";
                else Poof.payment ="Оплата: " + cur.getPayment() + " руб.";
                delys.add(Poof);
            }
            else {
                Poof = new Dely();
                Poof.description = "на данный момент";
                Poof.from = "заказов нет.";
                Poof.to = "";
                Poof.id = "Увы,";
                Poof.customer = "Но вы всегда";
                Poof.phonenumber = "можете оформить";
                Poof.cost = "";
                Poof.payment = "новый!";
                delys.add(Poof);
            }
        } else {
            Poof = new Dely();
            Poof.description = "на данный момент";
            Poof.from = "заказов нет.";
            Poof.to = "";
            Poof.id = "Увы,";
            Poof.customer = "Но вы всегда";
            Poof.phonenumber = "можете оформить";
            Poof.cost = "";
            Poof.payment = "новый!";
            delys.add(Poof);
        }

        return delys;
    }

    //обновление доставки
    public void Kostyl_GetUpdateText()
    {
        face_deliver_text_view.setText(face_deliver_text);
    }


}
