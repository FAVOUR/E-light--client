package com.e.olijefavour.e_ligh_client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class QuotesActivity extends AppCompatActivity {


    RecyclerView allQuotesRV;
    QuotesAdapter qoutesAdapter;
    List<Quotes> mQuotesList;
    private FirebaseFirestore mFireStore;
    private ProgressBar progressBar;
    private String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);
        mFireStore=FirebaseFirestore.getInstance();
        currentUserId= FirebaseAuth.getInstance().getCurrentUser().getUid();
        mQuotesList =new ArrayList<>();
        progressBar=(ProgressBar)findViewById(R.id.progress_bar);
        if(mQuotesList==null){
          progressBar.setVisibility(View.VISIBLE);
            Toast.makeText(QuotesActivity.this, "Loadong......pls wait" , Toast.LENGTH_SHORT).show();

        }

        else{


            Toast.makeText(QuotesActivity.this, "Userid :" + currentUserId , Toast.LENGTH_SHORT).show();
            Toast.makeText(QuotesActivity.this, "RecivedData" , Toast.LENGTH_SHORT).show();

            int number=  mQuotesList.size();
            Toast.makeText(QuotesActivity.this, "added Quotes " + number , Toast.LENGTH_SHORT).show();

            progressBar.setVisibility(View.GONE);


            Toast.makeText(QuotesActivity.this, "Visibility gone"  , Toast.LENGTH_SHORT).show();



        mFireStore.collection("Users").document(currentUserId).collection("Notifications").addSnapshotListener(this,new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if(e != null){
                    Toast.makeText( QuotesActivity.this, "List Retrieval failed." +  e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                for(DocumentChange doc:documentSnapshots.getDocumentChanges()){
//                     if(doc.getType() == DocumentChange.Type.ADDED);
                    Quotes quotes = doc.getDocument().toObject(Quotes.class);
                    Toast.makeText(QuotesActivity.this, "Extracted all data " , Toast.LENGTH_SHORT).show();

                    mQuotesList.add(quotes);
                  int number=  mQuotesList.size();
                    Toast.makeText(QuotesActivity.this, "added Quotes " + number , Toast.LENGTH_SHORT).show();
                    qoutesAdapter.notifyDataSetChanged();

//                     String name = doc.getDocument().getString("name");
                }
            }
        });

            allQuotesRV =(RecyclerView)findViewById(R.id.rvquotes);
            qoutesAdapter=new QuotesAdapter(this, mQuotesList);

            allQuotesRV.setHasFixedSize(true);
            allQuotesRV.setLayoutManager(new LinearLayoutManager(this));
            allQuotesRV.setAdapter(qoutesAdapter);

        }



    }
}
