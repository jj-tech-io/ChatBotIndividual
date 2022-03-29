package com.example.chatbot2;

import java.util.ArrayList;

public class Branch {

    public String question = " ";
    public String followUp = " ";
    ArrayList<String> objectives = new ArrayList<>();
    public String reply = "";
    public int state = 1;

    public Branch(String question,String followUp, ArrayList<String> objectives) {
        this.question = question;
        this.followUp = followUp;
        this.objectives = objectives;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
