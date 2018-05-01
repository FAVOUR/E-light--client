package com.e.olijefavour.e_ligh_client;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

/**
 * Created by Olije favour on 4/28/2018.
 */

public class QuotesAdapter  extends RecyclerView.Adapter<QuotesAdapter.ViewHolder> {

    private  List<Quotes> mQuotesList;
    private  Context context;
    private FirebaseFirestore firebaseFirestore;


    QuotesAdapter(Context context, List<Quotes>mQuotesList){
       this.mQuotesList=mQuotesList;
        this.context=context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.qoutes_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        firebaseFirestore = FirebaseFirestore.getInstance();
        String from_id=mQuotesList.get(position).getFrom();

        holder.quotesList.setText(mQuotesList.get(position).getMessage());

        firebaseFirestore.collection("Users").document(from_id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                documentSnapshot.get("name");
                documentSnapshot.get("image");

//
//                holder.mQuoteImage.setText(name);
//
//                Implementing Glide
//                RequestOptio
            }
        });

//        final Context context =holder.mView.getContext();
//        final String userName=usersList.get(position).getName();
//        final String userId=usersList.get(position).userId;
//
        holder.mView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {


//                Intent sendIntent =new Intent(context,SendActivity.class);
//                sendIntent.putExtra("Username",userName );
//                sendIntent.putExtra("UserId",userId );
//                context.startActivity(sendIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mQuotesList.size();

    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        View mView;
        public TextView quotesList;


        public ViewHolder(View itemView) {
            super(itemView);
            mView=itemView;

            quotesList =(TextView)mView.findViewById(R.id.quote);




        }
    }
}
