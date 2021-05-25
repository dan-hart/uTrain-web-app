package org.launchcode.uTrain;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class GeocodingExample {

    public static void main(String[] args) throws IOException, InterruptedException {

        ObjectMapper mapper = new ObjectMapper();
        Geocoder geocoder = new Geocoder();

        String response = geocoder.GeocodeSync("60901");
        JsonNode responseJsonNode = mapper.readTree(response);

        JsonNode items = responseJsonNode.get("items");

        for (JsonNode item : items) {
            JsonNode address = item.get("address");
            String label = address.get("label").asText();
            JsonNode position = item.get("position");

            String lat = position.get("lat").asText();
            String lng = position.get("lng").asText();
            System.out.println(label + " is located at " + lat + "," + lng + ".");
        }
    }
}
