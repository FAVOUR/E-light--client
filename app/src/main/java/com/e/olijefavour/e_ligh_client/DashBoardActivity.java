package com.e.olijefavour.e_ligh_client;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class DashBoardActivity extends AppCompatActivity {

    ImageButton allQuotes;
            ImageButton todaysQuote;
            ImageButton randomQuote;




    FirebaseAuth firebaseAuth;

    FirebaseAuth.AuthStateListener firebaseAuthStateListener;
    private int RC_SIGN_IN=123;
    private String mUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        allQuotes= (ImageButton)findViewById(R.id.all_quotes);
        todaysQuote= (ImageButton)findViewById(R.id.all_quotes);
        randomQuote= (ImageButton)findViewById(R.id.all_quotes);



        firebaseAuth= FirebaseAuth.getInstance();

        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener(){


            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user =firebaseAuth.getCurrentUser();
                if(user != null){
                    //user is signed in

                }

                else{

                    onSignoutInitialize();
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.EmailBuilder().build(),
//                                            new AuthUI.IdpConfig.PhoneBuilder().build(),
//
//                                            new AuthUI.IdpConfig.FacebookBuilder().build(),
//                                            new AuthUI.IdpConfig.TwitterBuilder().build(),
                                            new AuthUI.IdpConfig.GoogleBuilder().build()
                                    ))
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };

        String currentUserId= FirebaseAuth.getInstance().getCurrentUser().getUid();

        Toast.makeText(DashBoardActivity.this, "Userid :" + currentUserId , Toast.LENGTH_SHORT).show();
    }

    private void onSignoutInitialize() {
    }

    private void onSignInInitialize(String username ) {

        mUsername=username;
    }


    @Override
    protected void  onResume() {
        super.onResume();

        firebaseAuth.addAuthStateListener(firebaseAuthStateListener);
    }


    @Override
    protected void  onPause() {
        super.onPause();

        firebaseAuth.removeAuthStateListener(firebaseAuthStateListener);
    }


    public void enter(View view) {

        Intent intent = new Intent(this,QuotesActivity.class);
        startActivity(intent);
    }
}





