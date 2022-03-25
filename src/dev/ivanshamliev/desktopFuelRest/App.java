package dev.ivanshamliev.desktopFuelRest;

import dev.ivanshamliev.desktopFuelRest.dtos.PriceHistory;
import dev.ivanshamliev.desktopFuelRest.gui.panel.gasStationPanel.GasStationPanel;
import dev.ivanshamliev.desktopFuelRest.httpClient.FuelApiGetMethodsContract;
import dev.ivanshamliev.desktopFuelRest.httpClient.RestHttpClient;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        FuelApiGetMethodsContract fuelApiGetMethodClient = new RestHttpClient("http://localhost:8080/api/");

        new GasStationPanel();
    }
}
