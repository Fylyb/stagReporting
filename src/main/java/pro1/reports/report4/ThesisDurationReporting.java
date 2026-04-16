package pro1.reports.report4;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.Thesis;
import pro1.apiDataModel.ThesisList;
import pro1.reports.report4.reportDataModel.ThesisDurationReportItem;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ThesisDurationReporting {

    public static ThesisDurationReportItem[] GetReport(DataSource dataSource, String katedra, String[] years) {
        Gson gson = new Gson();
        List<ThesisDurationReportItem> reportItems = new ArrayList<>();

        for (String rok : years) {
            String json = dataSource.getKvalifikacniPrace(rok, katedra);
            ThesisList list = gson.fromJson(json, ThesisList.class);

            long celkemDnu = 0;
            int pocetPraci = 0;

            if (list != null && list.kvalifikacniPrace != null) {
                for (Thesis t : list.kvalifikacniPrace) {
                    if (t.datumZadani != null && t.datumZadani.isValid() &&
                            t.datumOdevzdani != null && t.datumOdevzdani.isValid()) {

                        long dny = ChronoUnit.DAYS.between(
                                t.datumZadani.toLocalDate(),
                                t.datumOdevzdani.toLocalDate()
                        );
                        celkemDnu += dny;
                        pocetPraci++;
                    }
                }
            }

            int prumer = 0;
            if (pocetPraci > 0) {
                prumer = (int) Math.round((double) celkemDnu / pocetPraci);
            }

            reportItems.add(new ThesisDurationReportItem(rok, prumer));
        }

        return reportItems.toArray(new ThesisDurationReportItem[0]);
    }
}