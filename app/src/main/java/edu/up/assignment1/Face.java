// @author Shubu Aryal
package edu.up.assignment1;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;

public class Face {
    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private int hairStyle;

    public Face() {
        randomize();
    }

    public void randomize() {
        //initializes all the variables to randomly selected valid values
        //this method should be called by the constructor
        Random random = new Random();
        skinColor = random.nextInt(0xFFFFFF);
        eyeColor = random.nextInt(0xFFFFFF);
        hairColor = random.nextInt(0xFFFFFF);
        hairStyle = random.nextInt(3); //3 options
    }

    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR); //clear everytime we draw so it doesnt overlap
        drawFace(canvas);
        drawMouth(canvas);
        drawHair(canvas);
        drawEyes(canvas);
    }

    public void drawFace(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(skinColor);
        canvas.drawCircle(400, 400, 200, paint);
    }


    public void drawMouth(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawRect(460, 520, 520, 550, paint);
    }

    public void drawHair(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(hairColor);
        if (hairStyle == 0) {
            canvas.drawRect(300, 200, 500, 300, paint);
        } else if (hairStyle == 1) {
            canvas.drawOval(300, 200, 500, 300, paint);
        } else {
            for (int i = 20; i < 400; i += 20) {
                canvas.drawCircle(240 + i, 250, 30, paint);
            }
        }
    }

    public void drawEyes(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(eyeColor);
        canvas.drawCircle(430, 430, 10, paint);
        canvas.drawCircle(530, 430, 10, paint);
    }

    public int getHairColor() {
        return hairColor;
    }

    public void setHairColor(int hairColor) {
        this.hairColor = hairColor;
    }

    public void setHairStyle(int style){
        this.hairStyle = style;
    }
    public int getHairStyle(){
        return hairStyle;
    }

    public int getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(int skinColor) {
        this.skinColor = skinColor;
    }

    public int getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(int eyeColor) {
        this.eyeColor = eyeColor;
    }
}

