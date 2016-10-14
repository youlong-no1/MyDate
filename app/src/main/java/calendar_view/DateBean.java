package calendar_view;

/**
 * 项目名称：data
 * 类描述：
 * 创建人：qq2
 * 创建时间：2015/11/6 13:47
 * 修改人：qq2
 * 修改时间：2015/11/6 13:47
 * 修改备注：
 */
public class DateBean {

    private int year;
    private int month;
    private int day;
    private int index;  //-1.上月   0.当月   1.下月
    private int status;  //完成状态 0.默认 1.已完成 2.未完成

    public DateBean(int year, int month,int day,int status) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.status=status;
    }
    public DateBean(int year, int month,int day,int index,int status) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.index=index;
        this.status=status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
