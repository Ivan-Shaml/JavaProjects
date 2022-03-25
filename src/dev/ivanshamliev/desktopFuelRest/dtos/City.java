package dev.ivanshamliev.desktopFuelRest.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class City {
    private Integer id;
    private String name;

    @Override
    public String toString() {
        return this.name;
    }
}
