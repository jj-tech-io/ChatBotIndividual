package com.example.chatbot2;

import android.os.AsyncTask;

import java.io.IOException;

class MyCallBack extends AsyncTask<Integer, Void, String> {

    public MyCallBack() {
        super();
    }

    @Override
    protected String doInBackground(Integer... params) {
        int x = params[0];
        String reply = "";
        switch (x){
            case 0:
                reply = "Okay 0";
            case 1:
                reply = "Okay 1";
            case 2:
                reply = "Okay 2";
            case 3:
                reply = "Okay 3";
            case 4:
                reply = "Okay 4";
            default:
                return reply;
        }


    }



    @Override
    protected void onPostExecute(String cbM) {
        super.onPostExecute(cbM);
        String msg = cbM;
        try {
            MainActivity.getCBM();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}




