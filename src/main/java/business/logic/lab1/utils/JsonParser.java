package business.logic.lab1.utils;

import business.logic.lab1.model.Hotel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JsonParser {

    public String getDestinationID(String jsonBody) {
        JSONObject obj = new JSONObject(jsonBody);
        JSONArray cityArray = obj.getJSONArray("suggestions").getJSONObject(0).getJSONArray("entities");
        if (cityArray.length() > 0) {
            return cityArray.getJSONObject(0).getString("destinationId");
        } else {
            return null;
        }
    }

    public List<Hotel> retrieveHotels(String JsonBody) {
        JSONObject obj = new JSONObject(JsonBody);
        JSONArray hotelsArray = obj.getJSONObject("data").getJSONObject("body").getJSONObject("searchResults").getJSONArray("results");
        ArrayList<Hotel> hotelArrayList = new ArrayList<>();
        for (Object h : hotelsArray) {
            JSONObject JsonObj = (JSONObject) h;
            Hotel hotel = new Hotel();
            hotel.setId(JsonObj.getLong("id"));
            hotel.setName(JsonObj.getString("name"));
            hotel.setPrice(JsonObj.getJSONObject("ratePlan").getJSONObject("price").getString("current"));
            hotelArrayList.add(hotel);
        }
        return hotelArrayList;
    }
}
