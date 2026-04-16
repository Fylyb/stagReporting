package pro1.reports.report3;

import pro1.DataSource;
import pro1.apiDataModel.Action;
import pro1.apiDataModel.ActionList;
import pro1.reports.report3.reportDataModel.DepartmentWeekdayReportItem;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public class DepartmentWeekdaysReporting {

    public static DepartmentWeekdayReportItem[] GetReport(DataSource dataSource, String rok, String katedra, String[] days) {
        String jsonRaw = dataSource.getRozvrhByKatedra(rok, katedra);
        Gson gson = new Gson();

        ActionList actionList = gson.fromJson(jsonRaw, ActionList.class);

        Action[] vsechnyAkce = (actionList != null) ? actionList.rozvrhovaAkce : new Action[0];

        List<DepartmentWeekdayReportItem> vysledek = new ArrayList<>();

        if (days != null && vsechnyAkce != null) {
            for (String den : days) {
                int pocitadlo = 0;
                for (Action akce : vsechnyAkce) {
                    if (akce.denZkr != null && den.equalsIgnoreCase(akce.denZkr)) {
                        pocitadlo++;
                    }
                }
                vysledek.add(new DepartmentWeekdayReportItem(den, pocitadlo));
            }
        }
        return vysledek.toArray(new DepartmentWeekdayReportItem[0]);
    }
}