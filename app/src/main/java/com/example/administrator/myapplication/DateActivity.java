package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import calendar_view.CalendarUtil;
import calendar_view.CalendarView;
import calendar_view.DateBean;


/**
 * 项目名称：Subscriber
 * 类描述：
 * 创建人：qq2
 * 创建时间：2015/11/9 10:36
 * 修改人：qq2
 * 修改时间：2015/11/9 10:36
 * 修改备注：
 */
public class DateActivity extends Activity implements View.OnClickListener, CalendarView.OnCalendarItemClickListener{

    private Context context;
    private CalendarView calendarView;
    private RelativeLayout backRel;
    private RelativeLayout leftRel;
    private RelativeLayout rightRel;
    private TextView dateTxt;
    private ListView listView;

    private int year=2015;
    private int month=5;
    private ArrayList<DateBean> arrayList;

//    private DateProgressListAdapter dateProgressListAdapter;
//    private ArrayList<DateProgressBean> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        context=this;
        initView();
        initData();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        calendarView= (CalendarView) findViewById(R.id.date_calendar_view);
        backRel= (RelativeLayout) findViewById(R.id.date_back_rel);
        leftRel= (RelativeLayout) findViewById(R.id.date_left_rel);
        rightRel= (RelativeLayout) findViewById(R.id.date_right_rel);
        dateTxt= (TextView) findViewById(R.id.date_title_txt);
        listView= (ListView) findViewById(R.id.date_list);

        calendarView.setOnCalendarItemClickListener(this);
        backRel.setOnClickListener(this);
        leftRel.setOnClickListener(this);
        rightRel.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        arrayList=new ArrayList<>();
        arrayList.add(new DateBean(2015,5,5,1));
        arrayList.add(new DateBean(2015,5,10,1));
        arrayList.add(new DateBean(2015, 5, 15, 1));
        arrayList.add(new DateBean(2015, 5, 20, 2));
        calendarView.initDate(year, month, arrayList);
        dateTxt.setText(CalendarUtil.getMonthEN(calendarView.getMonth()) + " " + calendarView.getYear());


//        list=new ArrayList<>();
////        list.add(new DateProgressBean("Packing declaration","2015/5/5"));
//        dateProgressListAdapter=new DateProgressListAdapter(context,list);
//        listView.setAdapter(dateProgressListAdapter);
    }

    @Override
    public void onCalendarItemClickListener(String date) {
        Toast.makeText(context, date, Toast.LENGTH_SHORT).show();
//        if(list.size()>0){
//            list.clear();
//        }
//        if(date.equals("2015/5/5")){
//            list.add(new DateProgressBean("Packing declaration",1430814000) );
//        }else if(date.equals("2015/5/10")){
//            list.add(new DateProgressBean("Packing declaration",1431224400) );
//            list.add(new DateProgressBean("Packing declaration",1431242400) );
//        }else if(date.equals("2015/5/15")){
//            list.add(new DateProgressBean("Packing declaration",1431652800) );
//            list.add(new DateProgressBean("Packing declaration",1431663600) );
//            list.add(new DateProgressBean("Packing declaration",1431678000) );
//        }
//
//        dateProgressListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.date_back_rel:
                    finish();
                break;
            case R.id.date_left_rel:
                calendarView.previousMonth();
                dateTxt.setText(CalendarUtil.getMonthEN(calendarView.getMonth())+" "+calendarView.getYear());
                break;
            case R.id.date_right_rel:
                calendarView.nextMonth();
                dateTxt.setText(CalendarUtil.getMonthEN(calendarView.getMonth()) + " " + calendarView.getYear());
                break;
        }
    }
}
