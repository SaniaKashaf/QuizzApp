package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
 public  static ArrayList<ModelClass> list;
 DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("Questions");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    ModelClass modelClass=dataSnapshot.getValue(ModelClass.class);
                    list.add(modelClass);
                }
                 Intent intent=new Intent(MainActivity.this,DashboardActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
   /*     list.add(new ModelClass("1) Android is -","a. an operating system","b. a web browser","c. a web server","d. None of the above","a. an operating system"));
        list.add(new ModelClass("2) Under which of the following Android is licensed?","a. OSS","b. Sourceforge","c. Apache/MIT","d. None of the above","c. Apache/MIT"));
        list.add(new ModelClass("3) For which of the following Android is mainly developed?","a. Servers","b. Desktops","c. Laptops","d. Mobile devices","d. Mobile devices"));
        list.add(new ModelClass("4) Android is -","a. an operating system","b. a web browser","c. a web server","d. None of the above","a. an operating system"));
        list.add(new ModelClass("5) Which of the following virtual machine is used by the Android operating system?","a. JVM","b. Dalvik virtual machine","c. Simple virtual machine","d. None of the above","b. Dalvik virtual machine"));
        list.add(new ModelClass("6) Tanzeel like","a. Burger","b. Bariyani","c. Shuwrama","d. None of the above","d. None of the above"));
        list.add(new ModelClass("7) Tanzeel favourit color","a. Pink","b. red","c. black","d. None of the above","c. black"));
*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               /* Intent intent=new Intent(MainActivity.this,DashboardActivity.class);
                startActivity(intent);*/
            }
        },1500);
    }
}