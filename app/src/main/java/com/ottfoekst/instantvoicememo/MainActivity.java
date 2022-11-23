package com.ottfoekst.instantvoicememo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final VoiceMemoShareService service = VoiceMemoShareService.start(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (SpeechRecognitionDetector.isAvailable(this)) {
            service.takeMemo();
        } else {
            findViewById(R.id.imageButton).setEnabled(false);
            Toast.makeText(this, "Speech Recognition Not Supported", Toast.LENGTH_LONG).show();
        }
    }

    public void speakNow(View view) {
        service.takeMemo();
    }
}