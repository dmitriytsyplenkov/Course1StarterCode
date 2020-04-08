package mystuff;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.MarkerFactory;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import parsing.ParseFeed;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class MyTestEarthQuakeMap extends PApplet {
    private UnfoldingMap map;

    @Override
    public void setup() {
        size(950, 600, OPENGL);
        map = new UnfoldingMap(this, 200, 50, 700, 500, new OpenStreetMap.OpenStreetMapProvider());
        MapUtils.createDefaultEventDispatcher(this, map);
        List<PointFeature> eqs = ParseFeed.parseEarthquake(this,"2.5_week.atom");
        List<Marker> markers = new ArrayList<Marker>();
        for (PointFeature eq : eqs) {
            markers.add(new SimplePointMarker(eq.getLocation(), eq.getProperties()));
        }
        int yellow = color(255, 255, 0);
        int gray = color(150, 150, 150);
//        for (Marker mk : markers) {
//            if ((int) mk.getProperty("age") > 2000) {
//                mk.setColor(yellow);
//            } else {
//                mk.setColor(gray);
//            }
//        }
        map.addMarkers(markers);
    }

    @Override
    public void draw() {
        background(220);
        map.draw();
    }
}
