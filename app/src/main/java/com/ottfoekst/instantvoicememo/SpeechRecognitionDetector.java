package com.ottfoekst.instantvoicememo;

import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.speech.RecognizerIntent;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SpeechRecognitionDetector {
    public static boolean isAvailable(ContextWrapper wrapper) {
        PackageManager pm = wrapper.getPackageManager();
        return !pm.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0).isEmpty();
    }
}
