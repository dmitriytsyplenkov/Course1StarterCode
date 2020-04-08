package mystuff;
import de.fhpotsdam.unfolding.core.Coordinate;
import de.fhpotsdam.unfolding.geo.MercatorProjection;
import de.fhpotsdam.unfolding.geo.Transformation;
import de.fhpotsdam.unfolding.providers.AbstractMapTileUrlProvider;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.Microsoft;
import processing.core.PApplet;

public class Yandex {
    public Yandex() {
    }

    public static class SimpleYandexProvider extends Yandex.YandexProvider {
        private int width;
        private int height;
        public SimpleYandexProvider() {
        }

        public SimpleYandexProvider(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public String[] getTileUrls(Coordinate var1) {
            String var2 = "https://static-maps.yandex.ru/1.x/?ll=" + var1.column + "," + var1.row  + "&z=" + (int)var1.zoom + "&l=map";
            System.out.println(var2);
            return new String[]{var2};

        }
    }

    public abstract static class YandexProvider extends AbstractMapTileUrlProvider {
        public YandexProvider() {
            super(new MercatorProjection(26.0F, new Transformation(1.068070779E7D, 0.0D, 3.355443185E7D, 0.0D, -1.06807089E7D, 3.355443057E7D)));
        }

        public String getZoomString(Coordinate var1) {
            return ""+ (int)var1.zoom;
        }

        public int tileWidth() {
            return 256;
        }

        public int tileHeight() {
            return 256;
        }

        public abstract String[] getTileUrls(Coordinate var1);
    }
}
