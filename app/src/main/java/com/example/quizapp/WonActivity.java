package com.example.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class WonActivity extends AppCompatActivity {
 CircularProgressBar circularProgressBar;
 TextView resultText,BackIcon;
 int correct,wrong;
 LinearLayout btnShare;
Button btnExit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);
        circularProgressBar=findViewById(R.id.circularProgressBar);
        btnShare=findViewById(R.id.btnShare);
        resultText=findViewById(R.id.textResult);
        BackIcon=findViewById(R.id.backIcon);
        btnExit=findViewById(R.id.btn_exit);
        correct=getIntent().getIntExtra("correct",0);
        wrong=getIntent().getIntExtra("wrong ",0);
        circularProgressBar.setProgress(correct);
        resultText.setText(correct+"/7");
       btnShare.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               try{
                   Intent shareIntent=new Intent(Intent.ACTION_SEND);
                           shareIntent.setType("text/plain");
                           shareIntent.putExtra(Intent.EXTRA_SUBJECT,"My application name");
                           String shareMessage="\ni got"+correct+"Out of 20 you can Also Try";
                           shareMessage=shareMessage+ "https://play.google.com/store/apps/details?id="+BuildConfig.APPLICATION_ID+"\n\n";
                           shareIntent .putExtra(Intent.EXTRA_TEXT,shareMessage);
                           startActivity(Intent.createChooser(shareIntent,"choose one"));
               } catch (Exception e){

               }




           }
       });
       BackIcon.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                Intent intent=new Intent(WonActivity.this,MainActivity.class);
        startActivity(intent);
           }
       });
btnExit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(WonActivity.this);
        builder.setMessage("Do you want to exit ?");
        builder.setTitle("Alert !");
        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        //builder.setCancelable(false);
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            finish();
        });
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
});

    }
    }

