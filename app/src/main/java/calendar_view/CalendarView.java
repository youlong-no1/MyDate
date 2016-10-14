package calendar_view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;


import com.example.administrator.myapplication.R;

import java.util.ArrayList;

/**
 * 项目名称：data
 * 类描述：日历
 * 创建人：qq2
 * 创建时间：2015/11/5 18:02
 * 修改人：qq2
 * 修改时间：2015/11/5 18:02
 * 修改备注：
 */
public class CalendarView extends RelativeLayout implements AdapterView.OnItemClickListener{

    //上月标记
    private int PREVIOUS_MONTH=-1;
    //当月标记
    private int SAME_MONTH=0;
    //下月标记
    private int NEXT_MONTH=1;
    //上下文
    private Context context;
    private ViewPager viewPager;
    //gridview
    private GridView gridView;
    private CalendarAdapter calendarAdapter;
    //日期总数据
    private ArrayList<DateBean> arrayList;
    //进度日期数据
    private ArrayList<DateBean> list;
    //年
    private int year;
    //月
    private int month;
    //当月第一天星期
    private int indexStart;
    //当月最后一天星期
    private int indexEnd;
    //点击接口
    private OnCalendarItemClickListener onCalendarItemClickListener;
    public CalendarView(Context context) {
        super(context);
        this.context=context;
        init();
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        init();
    }

    /**
     * 初始界面
     */
    private void init(){
        View view= LayoutInflater.from(context).inflate(R.layout.calendar_view,null);

        gridView= (GridView) view.findViewById(R.id.calendar_gridview);
//        viewPager= (ViewPager) view.findViewById(R.id.viewpager);
        gridView.setOnItemClickListener(this);


        arrayList=new ArrayList<>();
        calendarAdapter=new CalendarAdapter(context,arrayList);
        gridView.setAdapter(calendarAdapter);
        addView(view);
    }

    /**
     * 初始数据
     * @param year
     * @param month
     * @param list  进度日期数据
     */
    public void initDate(int year,int month,ArrayList<DateBean> list){
        this.year=year;
        this.month=month;
        this.list=list;
        loadData(year, month, list);
    }
    /**
     * 加载当月信息
     * @param year
     * @param month
     */
    private void loadData(int year,int month,ArrayList<DateBean> list){

        if(arrayList.size()>0){
            arrayList.clear();
        }
        //当月天数
        int currentMaxDays=CalendarUtil.getDaysByYearMonth(year, month);
        //当月第一天星期
        String weekStart=CalendarUtil.getDayOfWeekByDate(new DateBean(year, month, 1,0,0));
        indexStart=CalendarUtil.getDayOfWeekByDate(weekStart);

        //当月最后一天星期
        String weekEnd=CalendarUtil.getDayOfWeekByDate(new DateBean(year, month, currentMaxDays,0,0));
        indexEnd=CalendarUtil.getDayOfWeekByDate(weekEnd);
        //上一月天数
        int previousMaxDays;
        if(month==1){
            previousMaxDays=CalendarUtil.getDaysByYearMonth(year-1, 12);
        }else{
            previousMaxDays=CalendarUtil.getDaysByYearMonth(year, month-1);
        }

        //下一月天数
        int nextMaxDays=CalendarUtil.getDaysByYearMonth(year, month + 1);

        //添加上月数据
        if(indexStart>0){
            for (int i=0;i<indexStart;i++){
                if(month==1){
                    arrayList.add(new DateBean(year-1,12,previousMaxDays-((indexStart-1)-i),PREVIOUS_MONTH,0));
                }else{
                    arrayList.add(new DateBean(year,month-1,previousMaxDays-((indexStart-1)-i),PREVIOUS_MONTH,0));
                }
            }

        }

        //添加当月数据
        for (int i=1;i<=currentMaxDays;i++){
            arrayList.add(new DateBean(year,month,i,SAME_MONTH,0));
        }
        //设置任务完成情况日期
        for(int i=0;i<list.size();i++){
            for (int j=0;j<arrayList.size();j++){
                if(list.get(i).getYear()==arrayList.get(j).getYear()&&list.get(i).getMonth()==arrayList.get(j).getMonth()&&list.get(i).getDay()==arrayList.get(j).getDay()){
                    arrayList.get(j).setStatus(list.get(i).getStatus());
                }
            }
        }

        //添加下月数据
        if(indexEnd<6){
            for (int i=1;i<=(6-indexEnd);i++){
                if(month==12){
                    arrayList.add(new DateBean(year+1,1,i,NEXT_MONTH,0));
                }else{
                    arrayList.add(new DateBean(year,month+1,i,NEXT_MONTH,0));
                }

            }
        }
        calendarAdapter.notifyDataSetChanged();
    }

    /**
     * 上一月
     */
    public void previousMonth(){
        calendarAdapter.setSeclection(-1);
        month--;
        if (month == 0) {
            month=12;
            year--;
        }
        loadData(year, month,list);
    }

    /**
     * 下一月
     */
    public void nextMonth(){
        calendarAdapter.setSeclection(-1);
        month++;
        if(month==13){
            month=1;
            year++;
        }

        loadData(year, month, list);
    }

    /**
     * 获取年份
     * @return
     */
    public int getYear(){
        return year;
    }
    /**
     * 获取月份
     * @return
     */
    public int getMonth(){
        return month;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position>=indexStart&&position<arrayList.size()-(6-indexEnd)){
        calendarAdapter.setSeclection(position);
        calendarAdapter.notifyDataSetChanged();
        //返回点击数据
        String date=arrayList.get(position).getYear()+"/"+arrayList.get(position).getMonth()+"/"+arrayList.get(position).getDay();
        onCalendarItemClickListener.onCalendarItemClickListener(date);
        }
    }

    public interface OnCalendarItemClickListener{
        public void onCalendarItemClickListener(String date);
    }
    public void setOnCalendarItemClickListener(OnCalendarItemClickListener listener){
        onCalendarItemClickListener = listener;
    }
}
