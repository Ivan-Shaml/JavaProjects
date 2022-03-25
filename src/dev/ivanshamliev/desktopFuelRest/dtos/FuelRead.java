package dev.ivanshamliev.desktopFuelRest.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class FuelRead {
    private Integer id;
    private String name;
    private Double pricePerLiter;
    private String lastUpdate;
    private GasStationRead gasStation;
}
