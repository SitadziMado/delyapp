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
import com.example.adrax.dely.delivery.DeliveryOrder;
import com.example.adrax.dely.R;

import java.util.ArrayList;

import static com.example.adrax.dely.LoginActivity.user;
import static com.example.adrax.dely.MActivity.orders;


public class FragmentGet extends Fragment {

    //delys list
    //delys list
    //Объявляем RecyclerView
    RecyclerView rvMain;
    //Объявляем адаптер
    AdapterForGet adapterForGet;

    Context mContext;


    public FragmentGet() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = this.getActivity();
        View root = inflater.inflate(R.layout.fragment_get, container, false);

            //RecyclerView
        //Привязываем RecyclerView к элементу
        rvMain = (RecyclerView)root.findViewById(R.id.delys_list);
        //И установим LayoutManager
        rvMain.setLayoutManager(new LinearLayoutManager(getActivity()));
        //свистелки-перделки
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvMain.getContext(),
                llm.getOrientation());
        rvMain.addItemDecoration(dividerItemDecoration);
        //Создаём адаптер
        adapterForGet = new AdapterForGet(mContext,getDelys());
        //Применим наш адаптер к RecyclerView
        rvMain.setAdapter(adapterForGet);
        // Inflate the layout for this fragment
        return root;

    }

    public void update(Context mycontext) {
        //Создаём адаптер
        adapterForGet = new AdapterForGet(mycontext, getDelys());
        //Применим наш адаптер к RecyclerView
        rvMain.setAdapter(adapterForGet);
        // Inflate the layout for this fragment
    }

    private ArrayList<Dely> getDelys() {
        ArrayList<Dely> delys = new ArrayList<>();
        Dely Poof;
        orders = user.orderList();

       if (null != orders) {
            for (Integer i = 0; i < orders.length; ++i) {
                DeliveryOrder cur = orders[i];
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
        } else {
           Poof = new Dely();
           Poof.description = "на данный момент";
           Poof.from = "заказов нет.";
           Poof.to = "";
           Poof.id = "Увы,";
           Poof.customer = "Но вы всегда";
           Poof.phonenumber = "можете оформить";
           Poof.cost = "";
           Poof.payment = "новый заказ!";
           delys.add(Poof);
       }
        return delys;
    }
}
