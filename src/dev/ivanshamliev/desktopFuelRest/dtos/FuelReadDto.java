package dev.ivanshamliev.desktopFuelRest.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuelReadDto {
    private Integer id;
    private String name;
    private Double pricePerLiter;
    private String lastUpdate;
    private GasStationRead gasStation;
    private ArrayList<PriceHistory> priceHistory;
}