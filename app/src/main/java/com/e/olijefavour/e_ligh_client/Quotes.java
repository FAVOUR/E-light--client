package com.e.olijefavour.e_ligh_client;

/**
 * Created by Olije favour on 4/29/2018.
 */

public class Quotes {

    String from, message;

    public Quotes(){

    }

    public Quotes(String message,String from){
        this.from=from;
        this.message=message;
    }

    public String getFrom(){
      return  from;
    }

    public String getMessage(){
   return message;
    }

    public void setMessage(String message){
        this.message =message;

    }

    public  void setFrom(String from){
        this.from=from;

    }
}
