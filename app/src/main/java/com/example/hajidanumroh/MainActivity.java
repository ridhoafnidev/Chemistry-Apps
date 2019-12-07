package com.example.hajidanumroh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.freshchat.consumer.sdk.Freshchat;
import com.freshchat.consumer.sdk.FreshchatConfig;
import com.freshchat.consumer.sdk.FreshchatUser;
import com.freshchat.consumer.sdk.exception.MethodNotAllowedException;

public class MainActivity extends AppCompatActivity {
    private Button btnShowConversations, btnShowFAQs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init
        FreshchatConfig freshchatConfig=new FreshchatConfig("aa4c2c01-93e1-42dd-845d-723ecba685b4","67a3eac3-43ab-468e-984d-d95a6aa9c014");
        Freshchat.getInstance(getApplicationContext()).init(freshchatConfig);

        //Update user information
        FreshchatUser user = Freshchat.getInstance(getApplicationContext()).getUser();
        user.setFirstName("Ridho").setLastName("Afni").setEmail("ridho@ridho.afni").setPhone("062", "082252416420");
        try {
            Freshchat.getInstance(getApplicationContext()).setUser(user);
        } catch (MethodNotAllowedException e) {
            Log.e("FreshchatError", e.toString());
        }

        btnShowFAQs = (Button) findViewById(R.id.btnShowFAQs);
        btnShowConversations = (Button) findViewById(R.id.btnShowConversations);
        btnShowFAQs.setOnClickListener(viewClickListener);
        btnShowConversations.setOnClickListener(viewClickListener);
    }

    View.OnClickListener viewClickListener = new View.OnClickListener() {
        @Override
        public void onClick (View v) {
            if(v.getId() == R.id.btnShowFAQs) {

                Freshchat.showFAQs(MainActivity.this);

            } else if(v.getId() == R.id.btnShowConversations) {

                Freshchat.showConversations(MainActivity.this);

            }
        }
    };

}
