package dev.ivanshamliev.desktopFuelRest.httpClient;

import dev.ivanshamliev.desktopFuelRest.dtos.City;
import dev.ivanshamliev.desktopFuelRest.dtos.FuelRead;
import dev.ivanshamliev.desktopFuelRest.dtos.FuelReadDto;
import dev.ivanshamliev.desktopFuelRest.dtos.GasStationRead;
import dev.ivanshamliev.desktopFuelRest.exception.BadRequestException;
import dev.ivanshamliev.desktopFuelRest.exception.NotFoundException;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public interface FuelApiGetMethodsContract {

    HttpResponse<String> sendGetRequest(String url);

    // GET /api/city/
    ArrayList<City> getCities() throws IOException, NotFoundException;

    //GET /api/city/{id}
    City getCityById(int id) throws IOException, NotFoundException;

    // GET /api/station/{cityName}
    ArrayList<GasStationRead> getGasStationsByCityName(String cityName) throws IOException, NotFoundException;

    // GET /api/station/{id}
    GasStationRead getGasStationById (int id) throws IOException, NotFoundException;

    // GET /api/station/{id}/fuel
    ArrayList<FuelRead> getFuelsForGasStation (int id) throws IOException, NotFoundException;

    //GET /api/fuel/{id}
    FuelRead getFuelById(int id) throws IOException, NotFoundException;

    // GET /api/fuel/{id}/price
    FuelReadDto getFuelPriceHistoryByFuelId (int id) throws IOException, NotFoundException;

    // GET /api/fuel/{id}/price/{date}
    FuelReadDto getFuelPriceHistoryByDate (int id, String date) throws IOException, NotFoundException, BadRequestException;
}
