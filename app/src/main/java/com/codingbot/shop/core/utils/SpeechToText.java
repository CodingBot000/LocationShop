package com.codingbot.shop.core.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

import java.util.ArrayList;

public class SpeechToText {
    public void start(Context context) {
        System.out.println("SpeechToText start");
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                "com.domain.app");

        SpeechRecognizer recognizer = SpeechRecognizer
                .createSpeechRecognizer(context.getApplicationContext());
        RecognitionListener listener = new RecognitionListener() {
            @Override
            public void onResults(Bundle results) {
                ArrayList<String> voiceResults = results
                        .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (voiceResults == null) {
                    System.out.println("SpeechToText No voice results");
                } else {
                    System.out.println("SpeechToText Printing matches: ");
                    for (String match : voiceResults) {
                        System.out.println("SpeechToText match:"+match);
                    }
                }
            }

            @Override
            public void onReadyForSpeech(Bundle params) {
                System.out.println("SpeechToText Ready for speech");
            }

            /**
             *  ERROR_NETWORK_TIMEOUT = 1;
             *  ERROR_NETWORK = 2;
             *  ERROR_AUDIO = 3;
             *  ERROR_SERVER = 4;
             *  ERROR_CLIENT = 5;
             *  ERROR_SPEECH_TIMEOUT = 6;
             *  ERROR_NO_MATCH = 7;
             *  ERROR_RECOGNIZER_BUSY = 8;
             *  ERROR_INSUFFICIENT_PERMISSIONS = 9;
             *
             * @param error code is defined in SpeechRecognizer
             */
            @Override
            public void onError(int error) {
                System.err.println("SpeechToText Error listening for speech: " + error);
            }

            @Override
            public void onBeginningOfSpeech() {
                System.out.println("SpeechToTextSpeech onBeginningOfSpeech");
            }

            @Override
            public void onBufferReceived(byte[] buffer) {
                System.out.println("SpeechToTextSpeech onBufferReceived");

            }

            @Override
            public void onEndOfSpeech() {
                System.out.println("SpeechToTextSpeech onEndOfSpeech");

            }

            @Override
            public void onEvent(int eventType, Bundle params) {
                System.out.println("SpeechToTextSpeech onEvent");

            }

            @Override
            public void onPartialResults(Bundle partialResults) {
                System.out.println("SpeechToTextSpeech onPartialResults");

            }

            @Override
            public void onRmsChanged(float rmsdB) {
                System.out.println("SpeechToTextSpeech onRmsChanged");

            }
        };
        recognizer.setRecognitionListener(listener);
        recognizer.startListening(intent);
    }
}
