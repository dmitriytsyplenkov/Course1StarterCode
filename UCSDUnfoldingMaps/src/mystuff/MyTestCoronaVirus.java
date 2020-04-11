package mystuff;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import parsing.ParseFeed;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTestCoronaVirus extends PApplet {
    private UnfoldingMap map;
    private Map<String, Integer> incidentsByCountry;
    private List<Feature> countries;
    private List<Marker> countryMarkers;
    private int topIncedentsInSingleCountry = 0;


    @Override
    public void setup() {
        size(800, 600, OPENGL);
        map = new UnfoldingMap(this, 50, 50, 700, 500, new OpenStreetMap.OpenStreetMapProvider());
        MapUtils.createDefaultEventDispatcher(this, map);
        incidentsByCountry = parseCorona("https://api.covid19api.com/summary");
        countries = GeoJSONReader.loadData(this, "countries.geo.json");
        countryMarkers = MapUtils.createSimpleMarkers(countries);
        map.addMarkers(countryMarkers);
        map.zoomToLevel(1);
        shadeMarkers();
    }

    private void shadeMarkers() {
        for (Marker marker : countryMarkers) {
            String countryId = (String)marker.getProperty("name");
            //System.out.println(countryId);
            if (countryId.equals("Russia")) {
                countryId = "Russian Federation";
            }
            if (incidentsByCountry.containsKey(countryId)) {
                int coronaConfirmed = incidentsByCountry.get(countryId);
                int colorLevel = (int) map(log(coronaConfirmed), 1, log(topIncedentsInSingleCountry), 10, 255);
                marker.setColor(color(colorLevel, 100, 255 - colorLevel));
            } else {
                marker.setColor(color(150, 150, 150));
            }

        }
        //System.out.println(topIncedentsInSingleCountry);
    }

    private Map<String, Integer> parseCorona(String URL)  {
        Map<String, Integer> coronaMap = new HashMap<String, Integer>();
        try {
            JSONObject json = loadJSONObject(URL);
            JSONArray countries = json.getJSONArray("Countries");
            for (int i = 0; i < countries.size(); i++) {
                JSONObject country = countries.getJSONObject(i);
                int confirmed = country.getInt("TotalConfirmed");
                coronaMap.put(country.getString("Country"),confirmed);
                //System.out.println(country.getString("Country")+" "+ country.getInt("TotalConfirmed"));
                if(confirmed>topIncedentsInSingleCountry)
                    topIncedentsInSingleCountry = confirmed;
            }
        } catch (Exception exc) {
            PApplet.println(exc.toString());
        }

    return coronaMap;
}

    @Override
    public void draw() {
        map.draw();
    }
}
