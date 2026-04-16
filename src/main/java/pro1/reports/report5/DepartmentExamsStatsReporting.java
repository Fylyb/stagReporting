package pro1.reports.report5;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.Exam;
import pro1.apiDataModel.ExamsList;
import pro1.reports.report5.reportDataModel.DepartmentExamsStatsReport;

import java.util.ArrayList;
import java.util.TreeSet;

public class DepartmentExamsStatsReporting {

    public static DepartmentExamsStatsReport GetReport(DataSource dataSource, String katedra) {
        String json = dataSource.getTerminyZkousek2(katedra);
        Gson gson = new Gson();
        ExamsList list = gson.fromJson(json, ExamsList.class);

        int realizedCount = 0;
        TreeSet<String> rooms = new TreeSet<>();

        if (list != null && list.termin != null) {
            for (Exam e : list.termin) {
                if (e.obsazeni > 0) {
                    realizedCount++;
                }

                if (e.mistnost != null && !e.mistnost.trim().isEmpty()) {
                    rooms.add(e.mistnost.trim());
                }
            }
        }

        return new DepartmentExamsStatsReport(realizedCount, new ArrayList<>(rooms));
    }
}