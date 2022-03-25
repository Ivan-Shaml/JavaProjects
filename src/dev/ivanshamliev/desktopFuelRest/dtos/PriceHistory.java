package dev.ivanshamliev.desktopFuelRest.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Data @NoArgsConstructor @AllArgsConstructor
public class PriceHistory {
    private String timeUpdated;
    private Double oldPrice;
    private Double newPrice;

    public LocalDate getTimeUpdatedAsLocalDate(){
        try {
            var date = this.timeUpdated.substring(0, this.timeUpdated.indexOf('T'));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(date, formatter);
        }catch (DateTimeParseException px) {
            px.printStackTrace();
        }
        return null;
    }
}
