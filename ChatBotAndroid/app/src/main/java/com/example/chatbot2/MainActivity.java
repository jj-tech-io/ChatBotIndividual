package com.example.chatbot2;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Person person = new Person();
    static ChatBot chatBot = new ChatBot();
    int question = 1;
    int level = 1;
    public int branch = 1;
    static String cbMsg;
    static String userMsg;
    static boolean cont = false;
    FloatingActionButton btnMessage;
    EditText editTxtMessage;
    RecyclerView recyclerView;
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> messages = new ArrayList<>();
    ArrayList<Integer> images = new ArrayList<>();
    Drawable curved_back;
    MyAdapter myAdapter;

//    CountDownTimer timer = new CountDownTimer(1000,1) {
//        @Override
//        public void onTick(long l) {
//            //Toast.makeText(MainActivity.this, "Tick", Toast.LENGTH_LONG).show();
//        }
//
//        @Override
//        public void onFinish() {
//            //Toast.makeText(MainActivity.this, "Finished delay", Toast.LENGTH_LONG).show();
//            try {
//                chatBot();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            question++;
//            recyclerView.smoothScrollToPosition(myAdapter.getItemCount());
//        }
//    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnMessage = findViewById(R.id.btnMessage);
        editTxtMessage = findViewById(R.id.editTxtMessage);
        recyclerView = findViewById(R.id.recyclerView);
        myAdapter = new MyAdapter(this, names, messages, images);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        layoutManager.scrollToPosition(myAdapter.getItemCount() - 1);
        //recyclerView.scrollToPosition(myAdapter.getItemCount());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
        images.add(R.drawable.bot);
        names.add("Bot");
        messages.add(chatBot.getStatement(0) + '\n' + chatBot.getStatement(1));


        //myAdapter.notifyDataSetChanged();

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String m = editTxtMessage.getText().toString();


                images.add(R.drawable.user);
                names.add("User");
                messages.add(m);
                myAdapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(myAdapter.getItemCount());
                //recyclerView.setBackgroundColor(Color.BLUE);
                CallBack callBack = new CallBack();

                callBack.execute();


                //timer.start();

            }
        });

    }

    public static String getUserIN() {
        return userMsg;
    }

    public static String getCBM() throws IOException {
        return cbMsg;

    }

    public void chatBot(String msg) throws IOException {
        images.add(R.drawable.bot);
        names.add("Bot");
        messages.add(msg);
        myAdapter.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(myAdapter.getItemCount());
    }

    public class CallBack extends AsyncTask<Void, Void, String> {

        public CallBack() {
            super();
        }

        @Override
        protected String doInBackground(Void... params) {

            String reply = "";
            switch (question) {
                case 0:
                    question++;
                    reply = "Okay 0";
                    return reply;
                case 1:
                    reply = "Okay 1";
                    question++;
                    return reply;
                case 2:
                    reply = "Okay 2";
                    question++;
                    return reply;
                case 3:
                    reply = "Okay 3";
                    question++;
                    return reply;
                case 4:
                    reply = "Okay 4";
                    question = 0;
                    return reply;
                default:
                    question = 0;
                    return "hmmmm";
            }


        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                Thread.sleep(500);
                chatBot(s);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}