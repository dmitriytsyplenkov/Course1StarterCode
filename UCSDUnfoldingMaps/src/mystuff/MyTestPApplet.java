package mystuff;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MyTestPApplet extends PApplet {

    private PImage backgroundImg;

    @Override
    public void setup() {
        size(600,600);
        setFillDependingOnTime();
        backgroundImg = loadImage("palmTrees.jpg");

    }

    public void setFillDependingOnTime(){

        GregorianCalendar calendar = new GregorianCalendar();
        int curSeconds = calendar.get(Calendar.SECOND);
        if (curSeconds > 30){
            curSeconds = 60 - curSeconds;
        }
        int rg = 255*curSeconds/30;

        fill(rg,rg,0);

    }

    @Override
    public void draw() {
        backgroundImg.resize(0,height);
        image(backgroundImg,0,0);
        setFillDependingOnTime();
        ellipse(width/4,height/5,width/5,height/5);
    }
}
