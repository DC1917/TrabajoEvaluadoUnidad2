package com.example.proyectomapas;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class AccesoActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso);
        //Configuramos el video para la reproduccion local
        VideoView videoView = findViewById(R.id.videoView);
        //Construimos una URI del video
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.trailer;
        //Convierto la cadena en un URI
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        //Agrego controles de reproducción del video
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        //Vinculamos el controlador de video para mostrar en la app
        mediaController.setAnchorView(videoView);
        videoView.start();
    }
}


