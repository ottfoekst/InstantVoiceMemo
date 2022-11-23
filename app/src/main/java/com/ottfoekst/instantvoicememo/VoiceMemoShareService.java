package com.ottfoekst.instantvoicememo;

import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;

import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class VoiceMemoShareService {
    private final ActivityResultLauncher<Intent> launcher;

    public static VoiceMemoShareService start(ComponentActivity activity) {
        ActivityResultLauncher<Intent> launcher = activity.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            Intent intent = result.getData();
            if (result.getResultCode() == Activity.RESULT_OK && intent != null) {
                List<String> voices = intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if (!voices.isEmpty()) {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, voices.get(0));
                    sendIntent.setType("text/plain");

                    Intent shareIntent = Intent.createChooser(sendIntent, null);
                    activity.startActivity(shareIntent);
                }
            }
        });
        return new VoiceMemoShareService(launcher);
    }

    public void takeMemo() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        launcher.launch(intent);
    }
}
