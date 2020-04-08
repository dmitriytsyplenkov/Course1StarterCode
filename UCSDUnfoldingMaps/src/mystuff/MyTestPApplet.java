package mystuff;

import processing.core.PApplet;
import processing.core.PImage;

public class MyTestPApplet extends PApplet {

    PImage img = loadImage("https://processing.org/img/processing-web.png","png");

    @Override
    public void setup() {
        size(200,200);
    }

    @Override
    public void draw() {
        image(img,0,0);
    }
}
