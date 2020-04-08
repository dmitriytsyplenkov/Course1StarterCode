package mystuff;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import parsing.ParseFeed;
import processing.core.PApplet;

import java.util.List;
import java.util.Map;

public class MyTestLifeExpectancy extends PApplet {
    private UnfoldingMap map;
    private Map<String, Float> lifeExpectancyByCountry;
    private List<Feature> countries;
    private List<Marker> countryMarkers;

    @Override
    public void setup() {
        size(800, 600, OPENGL);
        map = new UnfoldingMap(this, 50, 50, 700, 500, new OpenStreetMap.OpenStreetMapProvider());
        MapUtils.createDefaultEventDispatcher(this, map);
        lifeExpectancyByCountry = ParseFeed.loadLifeExpectancyFromCSV(this,"LifeExpectancyWorldBank.csv");
        countries = GeoJSONReader.loadData(this, "countries.geo.json");
        countryMarkers = MapUtils.createSimpleMarkers(countries);
        map.addMarkers(countryMarkers);
        shadeMarkers();
    }

    private void shadeMarkers() {
        for (Marker marker : countryMarkers) {
            String countryId = marker.getId();
            if (lifeExpectancyByCountry.containsKey(countryId)) {
                float lifeExp = lifeExpectancyByCountry.get(countryId);
                int colorLevel = (int) map(lifeExp, 40, 90, 10, 255);
                marker.setColor(color(255 - colorLevel, 100, colorLevel));
            } else {
                marker.setColor(color(150,150,150));
            }

        }
    }

    @Override
    public void draw() {
        map.draw();
    }
}
