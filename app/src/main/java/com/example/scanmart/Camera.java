package com.example.scanmart;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class Camera extends AppCompatActivity {

    Button scanbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        scanbtn = findViewById(R.id.scanbtn);
        scanbtn.setOnClickListener(v -> {
                scanCode();
                }

        );

    }
    private void scanCode(){
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to turn on Flash on");
        options.setBeepEnabled(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);

    }

    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result ->
    {
        if(result.getContents() != null){
            AlertDialog.Builder builder = new  AlertDialog.Builder(Camera.this);
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            }).show();
        }
    });
}