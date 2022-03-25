package dev.ivanshamliev.desktopFuelRest.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data @NoArgsConstructor @AllArgsConstructor
public class GasStationRead {
    private Integer id;
    private String name;
    private String streetAddress;
    private City location;

    @Override
    public String toString() {
        return this.name;
    }
}
