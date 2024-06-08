package com.codingbot.shop

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.MotionEvent
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier

import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.codingbot.shop.ui.theme.LocationShopTheme
import java.util.Locale


class SpeechActivity : ComponentActivity() {
    private var speechRecognizer: SpeechRecognizer? = null
//    private var editText: EditText? = null
//    private var micButton: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
//            checkPermission()
        }
        setContent {
            LocationShopTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
//        setContentView(R.layout.activity_main)
//
////        editText = findViewById(R.id.text)
////        micButton = findViewById(R.id.button)
//        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
//        val speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
//        speechRecognizerIntent.putExtra(
//            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
//            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
//        )
//        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
//        speechRecognizer?.setRecognitionListener(object : RecognitionListener {
//            override fun onReadyForSpeech(bundle: Bundle) {}
//            override fun onBeginningOfSpeech() {
//                editText!!.setText("")
//                editText!!.hint = "Listening..."
//            }
//
//            override fun onRmsChanged(v: Float) {}
//            override fun onBufferReceived(bytes: ByteArray) {}
//            override fun onEndOfSpeech() {}
//            override fun onError(i: Int) {}
//            override fun onResults(bundle: Bundle) {
//                micButton!!.setImageResource(R.drawable.ic_mic_black_off)
//                val data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
//                editText!!.setText(data!![0])
//            }
//
//            override fun onPartialResults(bundle: Bundle) {}
//            override fun onEvent(i: Int, bundle: Bundle) {}
//        })
//        micButton!!.setOnTouchListener { view, motionEvent ->
//            if (motionEvent.action == MotionEvent.ACTION_UP) {
//                speechRecognizer?.stopListening()
//            }
//            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
//                micButton!!.setImageResource(R.drawable.ic_mic_black_24dp)
//                speechRecognizer?.startListening(speechRecognizerIntent)
//            }
//            false
//        }
    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        speechRecognizer!!.destroy()
//    }
//
//    private fun checkPermission() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            ActivityCompat.requestPermissions(
//                this,arrayOf<String>(Manifest.permission.RECORD_AUDIO), RecordAudioRequestCode);
//        }
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<String>,
//        grantResults: IntArray
//    ) {
//
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == RecordAudioRequestCode && grantResults.size > 0) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) Toast.makeText(
//                this,
//                "Permission Granted",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//    }
//
//    companion object {
//        const val RecordAudioRequestCode = 1
//    }
}