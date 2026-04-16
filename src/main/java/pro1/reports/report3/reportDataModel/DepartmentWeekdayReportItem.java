package pro1.reports.report3.reportDataModel;

public class DepartmentWeekdayReportItem {
    public String weekday;
    public int actionsCount;

    public DepartmentWeekdayReportItem(String weekday, int actionsCount) {
        this.weekday = weekday;
        this.actionsCount = actionsCount;
    }
}