package edu.up.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.view.SurfaceView;
import android.graphics.Canvas;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private SurfaceView surfaceView;
    private Face face;
    private Spinner hairSpinner;
    private RadioGroup radioGroup;
    private SeekBar redSeekBar, greenSeekBar, blueSeekBar;
    private Button randomFaceButton;

    private String[] hairStyles = {"Style 1", "Style 2", "Style 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initialize views
        surfaceView = findViewById(R.id.surfaceView);
        hairSpinner = findViewById(R.id.spinner);
        radioGroup = findViewById(R.id.radioGroup);
        redSeekBar = findViewById(R.id.seekBarRed);
        greenSeekBar = findViewById(R.id.seekBarGreen);
        blueSeekBar = findViewById(R.id.seekBarBlue);
        randomFaceButton = findViewById(R.id.randomButton);

        // Initialize Face and draw random face on startup
        face = new Face();
        drawFace();
        updateViews();

        // Set up Spinner listener
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, hairStyles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hairSpinner.setAdapter(adapter);
        hairSpinner.setOnItemSelectedListener(new HairSpinnerListener());

        // Set up RadioGroup listener
        radioGroup.setOnCheckedChangeListener(new RadioGroupListener());

        // Set up SeekBar listeners
        redSeekBar.setOnSeekBarChangeListener(new ColorSeekBarChangeListener());
        greenSeekBar.setOnSeekBarChangeListener(new ColorSeekBarChangeListener());
        blueSeekBar.setOnSeekBarChangeListener(new ColorSeekBarChangeListener());

        // Set up Random Face button listener
        randomFaceButton.setOnClickListener(new RandomFaceButtonListener());
    }

    // Method to draw the face on the SurfaceView
    private void drawFace() {
        Canvas canvas = surfaceView.getHolder().lockCanvas();
        if (canvas != null) {
            face.onDraw(canvas);
            surfaceView.getHolder().unlockCanvasAndPost(canvas);
        }
    }

    // Listener for the hair spinner
    private class HairSpinnerListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
            face.setHairStyle(position);
            drawFace();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    }

    // Listener for the RadioGroup
    private class RadioGroupListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int color;
            if (checkedId == R.id.radioButtonHair) {
                color = face.getHairColor();
            } else if (checkedId == R.id.radioButtonEyes) {
                color = face.getEyeColor();
            } else if (checkedId == R.id.radioButtonSkin) {
                color = face.getSkinColor();
            } else {
                return;
            }
            redSeekBar.setProgress(android.graphics.Color.red(color));
            greenSeekBar.setProgress(android.graphics.Color.green(color));
            blueSeekBar.setProgress(android.graphics.Color.blue(color));
        }
    }

    // Listener for SeekBars
    private class ColorSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (!fromUser) {
                return;
            }

            int red = redSeekBar.getProgress();
            int green = greenSeekBar.getProgress();
            int blue = blueSeekBar.getProgress();

            int color = android.graphics.Color.rgb(red, green, blue);
            int radioButtonId = radioGroup.getCheckedRadioButtonId();

            if (radioButtonId == R.id.radioButtonHair) {
                face.setHairColor(color);
            } else if (radioButtonId == R.id.radioButtonEyes) {
                face.setEyeColor(color);
            } else if (radioButtonId == R.id.radioButtonSkin) {
                face.setSkinColor(color);
            }

            drawFace();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}
    }

    // Listener for the Random Face button
    private class RandomFaceButtonListener implements android.view.View.OnClickListener {
        @Override
        public void onClick(android.view.View v) {
            face.randomize();
            drawFace();
            updateViews();
        }
    }

    private void updateViews() {
        // Update the hair style spinner
        hairSpinner.setSelection(face.getHairStyle());

        // Update the radio buttons and seek bars.
        int red, green, blue;
        if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonHair) {
            red = Color.red(face.getHairColor());
            green = Color.green(face.getHairColor());
            blue = Color.blue(face.getHairColor());
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonEyes) {
            red = Color.red(face.getEyeColor());
            green = Color.green(face.getEyeColor());
            blue = Color.blue(face.getEyeColor());
        } else {
            red = Color.red(face.getSkinColor());
            green = Color.green(face.getSkinColor());
            blue = Color.blue(face.getSkinColor());
        }
        redSeekBar.setProgress(red);
        greenSeekBar.setProgress(green);
        blueSeekBar.setProgress(blue);
    }
}