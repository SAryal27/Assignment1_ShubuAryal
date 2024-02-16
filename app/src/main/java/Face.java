// @author Shubu Aryal

import java.util.Random;
import android.graphics.Canvas;
import android.graphics.Paint;
public class Face {
    int skinColor;
    int eyeColor;
    int hairColor;
    int hairStyle;

    public Face(){
        randomize();
    }
    public void randomize(){
        //initializes all the variables to randomly selected valid values
        //this method should be called by the constructor
        Random random = new Random();
        skinColor = random.nextInt(0xFFFFFF);
        eyeColor = random.nextInt(0xFFFFFF);
        hairColor = random.nextInt(0xFFFFFF);
        hairStyle = random.nextInt(3); //3 options
    }

    public void onDraw(){
        //draws this face object upon a given canvas object
        /**
         * leave this method empty for part A
         */
    }

    public void drawFace(Canvas canvas){

    }
    public void drawEyes(Canvas canvas){

    }

    public void drawMouth(Canvas canvas){

    }
    public void drawHair(Canvas canvas){

    }

}

