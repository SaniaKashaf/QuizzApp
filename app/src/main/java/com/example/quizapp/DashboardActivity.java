package com.example.quizapp;

import static com.example.quizapp.MainActivity.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
CountDownTimer countDownTimer;
int timerValue=20;
ProgressBar progressBar;
List<ModelClass> allQuestionList;
ModelClass modelClass;
int index=0;
TextView tvcardQuestion,optiona,optionb,optionc,optiond;
CardView cardQuestion,cardA,cardB,cardC,cardD;
int correctCount =0;
int wrongCount =0;
LinearLayout nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        progressBar=findViewById(R.id.progress);
        tvcardQuestion=findViewById(R.id.tv_card_question);
        optiona=findViewById(R.id.tv_card_OPtiona);
        optionb=findViewById(R.id.tv_card_OPtionb);
        optionc=findViewById(R.id.tv_card_OPtionc);
        optiond=findViewById(R.id.tv_card_OPtiond);
        cardQuestion=findViewById(R.id.cardview_question);
        cardA=findViewById(R.id.cardview_A);
        cardB=findViewById(R.id.cardview_B);
        cardC=findViewById(R.id.cardview_C);
        cardD=findViewById(R.id.cardview_D);
        nextBtn=findViewById(R.id.btn_next);


        allQuestionList=list;
        //Collections.shuffle(allQuestionList);
        modelClass=allQuestionList.get(index);
        setAllData();
      /*  cardA.setBackgroundColor(getResources().getColor(R.color.white));
        cardB.setBackgroundColor(getResources().getColor(R.color.white));
        cardC.setBackgroundColor(getResources().getColor(R.color.white));
        cardD.setBackgroundColor(getResources().getColor(R.color.white));*/
        nextBtn.setClickable(false);

        countDownTimer=new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long l) {
                timerValue=timerValue-1;
                progressBar.setProgress(timerValue);}
            @Override
            public void onFinish() {
                Dialog dialog=new Dialog(DashboardActivity.this,R.style.Dialog);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setContentView(R.layout.time_out_dialog);
                dialog.findViewById(R.id.btn_tryAgain).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DashboardActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                });
                dialog.show();
            }
        }.start();

    }

    private void setAllData() {
        tvcardQuestion.setText(modelClass.getQuestion());
        optiona.setText(modelClass.getOA());
        optionb.setText(modelClass.getOB());
        optionc.setText(modelClass.getOC());
        optiond.setText(modelClass.getOD());
      /*  timerValue=20;
        countDownTimer.cancel();
        countDownTimer.start();*/
    }
    public void Correct(CardView cardView){
        cardView.setBackgroundColor(getResources().getColor(R.color.green));
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correctCount++;
                index++;
                modelClass=allQuestionList.get(index);
                resetColor();
                setAllData();
            }
        });

}
public void Wrong(CardView cardA){
        cardA.setBackgroundColor(getResources().getColor(R.color.red));
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wrongCount++;
                if (index<allQuestionList.size()-1){
                    index++;
                    modelClass=allQuestionList.get(index);
                    resetColor();
                    setAllData();

                }
                else {
                    GameWon();
                }
            }
        });

}

    public void GameWon() {
        Intent intent=new Intent(DashboardActivity.this,WonActivity.class);
        intent.putExtra("correct",correctCount);
        intent.putExtra("wrong",wrongCount);
        startActivity(intent);
    }
    public void enableButton(){
        cardA.setClickable(true);
        cardB.setClickable(true);
        cardC.setClickable(true);
        cardD.setClickable(true);
    }
    public void disableButton(){
        cardA.setClickable(false);
        cardB.setClickable(false);
        cardC.setClickable(false);
        cardD.setClickable(false);
    }
    public void resetColor(){
        enableButton();
        nextBtn.setClickable(false);
        cardA.setBackgroundColor(getResources().getColor(R.color.white));
        cardB.setBackgroundColor(getResources().getColor(R.color.white));
        cardC.setBackgroundColor(getResources().getColor(R.color.white));
        cardD.setBackgroundColor(getResources().getColor(R.color.white));
    }

    public void OPtionAClick(View view) {
        disableButton();
        nextBtn.setClickable(true);
        if (modelClass.getOA().equals(modelClass.getAns())){
            cardA.setCardBackgroundColor(getResources().getColor(R.color.green));

        if (index <allQuestionList.size() -1){
           Correct(cardA);
          }
        else {
            GameWon();
        }
        }
        else{
          Wrong(cardA);
        }
    }

    public void OPtionBClick(View view) {
      disableButton();
        nextBtn.setClickable(true);
        if (modelClass.getOB().equals(modelClass.getAns())){
            cardB.setCardBackgroundColor(getResources().getColor(R.color.green));
            if (index < allQuestionList.size() -1){
               Correct(cardB);
            }
            else {
                GameWon();
            }
        }
        else{
            Wrong(cardB);
        }
    }

    public void OPtionCClick(View view) {
        disableButton();
        nextBtn.setClickable(true);
        if (modelClass.getOC().equals(modelClass.getAns())){
            cardC.setCardBackgroundColor(getResources().getColor(R.color.green));

            if (index < allQuestionList.size() -1){
               Correct(cardC);
            }
            else {
                GameWon();
            }
        }
        else{
            Wrong(cardC);
        }
    }

    public void OPtionDClick(View view) {
        disableButton();
        nextBtn.setClickable(true);
        if (modelClass.getOD().equals(modelClass.getAns())){
            cardD.setCardBackgroundColor(getResources().getColor(R.color.green));

            if (index < allQuestionList.size() -1){
               Correct(cardD);
            }
            else {
                GameWon();
            }
        }
        else{
            Wrong(cardD);
        }
    }
}