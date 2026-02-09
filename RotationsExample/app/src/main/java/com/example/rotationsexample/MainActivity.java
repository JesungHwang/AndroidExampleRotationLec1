package com.example.rotationsexample;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    public final static String tag = "==MainActivity==";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(tag, "onCreate");
        detectDevice();
    }
    public void detectDevice() {
        TextView orientation = findViewById(R.id.orientation);
        TextView layout = findViewById(R.id.layout);
        // lookup the display object, figure out if the phone
        // is in portrait or landscape mode.
        String rot = "";
        Display display = getDisplay();
        if (display.getRotation() == Surface.ROTATION_0 ||
                display.getRotation() == Surface.ROTATION_180) {
            rot = "Portrait ";
        } else {
            rot = "Landscape";
        }
        orientation.setText(rot);
        // get the metrics object to look up the dimensions of
        // the display in pixels. A rect object provides the details
        WindowMetrics metrics = getWindowManager().getCurrentWindowMetrics();
        Rect r = metrics.getBounds();
        layout.setText(" height = " + (r.bottom - r.top) + " width = " +
                (r.right - r.left));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        detectDevice();
        appendTime();
        Log.d(tag, "onCreate");
    }
    public void appendTime() {
        // lookup the current time
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        // get the current orientation text, append the time
        // to the text, search for the trailing space.
        TextView orientation = findViewById(R.id.orientation);
        String newStr = orientation.getText() + " ";
        // find the first trailing space
        int ind = newStr.indexOf(' ');
        // append the current time
        newStr = newStr.substring(0, ind+1) + formatter.format(date);
        orientation.setText(newStr);
    }
    public void Next(View view) {
        Intent startActivityAgain = new Intent(this,
                MainActivity.class);
        startActivity(startActivityAgain);
        Log.d(tag,"Next Button Pressed");
    }
    @Override
    public void onResume() {
        super.onResume();
        detectDevice();
        appendTime();
        Log.d(tag,"onResume()");
    }
}