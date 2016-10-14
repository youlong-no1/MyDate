package calendar_view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.administrator.myapplication.R;

import java.util.ArrayList;

/**
 * 项目名称：data
 * 类描述：
 * 创建人：qq2
 * 创建时间：2015/11/6 11:35
 * 修改人：qq2
 * 修改时间：2015/11/6 11:35
 * 修改备注：
 */
public class CalendarAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<DateBean> list;

    //选中状态标识
    private int clickTemp = -1;
    public CalendarAdapter(Context context,ArrayList<DateBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setSeclection(int position) {
        clickTemp = position;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.cell_view,null);
        final TextView txt= (TextView) view.findViewById(R.id.cell_view_txt);
        ImageView imageView= (ImageView) view.findViewById(R.id.cell_view_img);
        RelativeLayout relativeLayout= (RelativeLayout) view.findViewById(R.id.cell_view_rel);
        //设置日期背景
        if(list.get(position).getIndex()==0){
            relativeLayout.setBackgroundResource(R.mipmap.date_on);
            txt.setTextColor(context.getResources().getColor(R.color.black));
        }else{
            relativeLayout.setBackgroundResource(R.mipmap.date_off);
            txt.setTextColor(context.getResources().getColor(R.color.gray));
        }
            txt.setText(list.get(position).getDay() + "");
        //设置进度状态
        if(list.get(position).getStatus()==1){  //已完成
            imageView.setBackgroundResource(R.mipmap.date_yes);
        }else if(list.get(position).getStatus()==2){    //未完成
            imageView.setBackgroundResource(R.mipmap.date_no);
        }else{  //默认
            imageView.setBackgroundResource(0);
        }
        //设置选中状态
        if (clickTemp == position) {
            txt.setBackgroundResource(R.mipmap.date_select);
        } else {
            txt.setBackgroundColor(Color.TRANSPARENT);
        }
        return view;
    }
}
