package dev.ivanshamliev.desktopFuelRest.httpClient;

import dev.ivanshamliev.desktopFuelRest.dtos.FuelRead;
import dev.ivanshamliev.desktopFuelRest.dtos.FuelReadDto;
import dev.ivanshamliev.desktopFuelRest.dtos.GasStationRead;
import dev.ivanshamliev.desktopFuelRest.exception.BadRequestException;
import dev.ivanshamliev.desktopFuelRest.exception.NotFoundException;
import dev.ivanshamliev.desktopFuelRest.dtos.City;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class RestHttpClient implements FuelApiGetMethodsContract {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String baseUrl;

    public RestHttpClient(String baseUrl) {
        this.httpClient = HttpClient.newBuilder().build();
        this.objectMapper = new ObjectMapper();
        this.baseUrl = baseUrl;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public HttpResponse<String> sendGetRequest(String url) {

        try {
            HttpRequest getCities = HttpRequest.newBuilder()
                    .uri(new URI(baseUrl + url))
                    .GET()
                    .build();
            return this.httpClient.send(getCities, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

        return null;
    }

    // GET localhost:8080/api/city/
    public ArrayList<City> getCities() throws IOException, NotFoundException {
        String url = "city/";
        HttpResponse<String> response = sendGetRequest(url);

        if (response.statusCode() == 200) {
            String body = response.body();
            return this.objectMapper.readValue(body, new TypeReference<ArrayList<City>>() {
            });
        } else if (response.statusCode() == 404) {
            throw new NotFoundException(response.body());
        }
        return null;
    }

    //GET localhost:8080/api/city/{id}
    public City getCityById(int id) throws IOException, NotFoundException {
        String url = "city/" + id;
        HttpResponse<String> response = sendGetRequest(url);

        if (response.statusCode() == 200) {
            String body = response.body();
            return this.objectMapper.readValue(body, City.class);
        } else if (response.statusCode() == 404) {
            throw new NotFoundException(response.body());
        }
        return null;
    }

    // GET localhost:8080/api/station/city/{cityName}
    public ArrayList<GasStationRead> getGasStationsByCityName(String cityName) throws IOException, NotFoundException {
        String url = "station/city/" + cityName;
        HttpResponse<String> response = sendGetRequest(url);

        if (response.statusCode() == 200) {
            String body = response.body();
            return this.objectMapper.readValue(body, new TypeReference<ArrayList<GasStationRead>>() {
            });
        } else if (response.statusCode() == 404) {
            throw new NotFoundException(response.body());
        }
        return null;
    }

    // GET localhost:8080/api/station/{id}
    public GasStationRead getGasStationById(int id) throws IOException, NotFoundException {
        String url = "station/" + id;
        HttpResponse<String> response = sendGetRequest(url);

        if (response.statusCode() == 200) {
            String body = response.body();
            return this.objectMapper.readValue(body, GasStationRead.class);
        } else if (response.statusCode() == 404) {
            throw new NotFoundException(response.body());
        }
        return null;
    }

    // GET localhost:8080/api/station/{id}/fuel
    public ArrayList<FuelRead> getFuelsForGasStation(int id) throws IOException, NotFoundException {
        String url = "station/" + id + "/fuel";
        HttpResponse<String> response = sendGetRequest(url);

        if (response.statusCode() == 200) {
            String body = response.body();
            return this.objectMapper.readValue(body, new TypeReference<ArrayList<FuelRead>>() {
            });
        } else if (response.statusCode() == 404) {
            throw new NotFoundException(response.body());
        }
        return null;
    }

    //GET localhost:8080/api/fuel/{id}
    public FuelRead getFuelById(int id) throws IOException, NotFoundException {
        String url = "fuel/" + id;
        HttpResponse<String> response = sendGetRequest(url);

        if (response.statusCode() == 200) {
            String body = response.body();
            return this.objectMapper.readValue(body, FuelRead.class);
        } else if (response.statusCode() == 404) {
            throw new NotFoundException(response.body());
        }
        return null;
    }

    // GET localhost:8080/api/fuel/{id}/price
    public FuelReadDto getFuelPriceHistoryByFuelId(int id) throws IOException, NotFoundException {
        String url = "fuel/" + id + "/price";
        HttpResponse<String> response = sendGetRequest(url);

        if (response.statusCode() == 200) {
            String body = response.body();
            return this.objectMapper.readValue(body, FuelReadDto.class);
        } else if (response.statusCode() == 404) {
            throw new NotFoundException(response.body());
        }
        return null;
    }

    // GET localhost:8080/api/fuel/{id}/price/{date}
    public FuelReadDto getFuelPriceHistoryByDate(int id, String date) throws IOException, NotFoundException, BadRequestException {
        String url = "fuel/" + id + "/price/" + date;
        HttpResponse<String> response = sendGetRequest(url);

        if (response.statusCode() == 200) {
            String body = response.body();
            return this.objectMapper.readValue(body, FuelReadDto.class);
        } else if (response.statusCode() == 404) {
            throw new NotFoundException(response.body());
        } else if (response.statusCode() == 400) {
            throw new BadRequestException(response.body());
        }
        return null;
    }

}
