package mystuff;

import processing.core.PApplet;
import processing.core.PImage;

public class MyTestPApplet extends PApplet {

    PImage img;

    @Override
    public void setup() {
        size(200,200);
        img = loadImage("C:\\Users\\Олдридж\\IdeaProjects\\Course1StarterCode\\UCSDUnfoldingMaps\\data\\palmTrees.jpg");
    }

    @Override
    public void draw() {
        image(img,0,0);
    }
}
