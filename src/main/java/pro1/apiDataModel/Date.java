package pro1.apiDataModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {
    public String value;

    public boolean isValid() {
        return value != null && !value.trim().isEmpty();
    }

    public LocalDate toLocalDate() {
        String datePart = value.trim();

        if (datePart.contains(" ")) {
            datePart = datePart.substring(0, datePart.indexOf(" "));
        } else if (datePart.contains("T")) {
            datePart = datePart.substring(0, datePart.indexOf("T"));
        }

        if (datePart.contains(".")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
            return LocalDate.parse(datePart, formatter);
        } else {
            return LocalDate.parse(datePart);
        }
    }
}