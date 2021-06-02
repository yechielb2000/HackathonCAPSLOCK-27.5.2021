package com.example.bmdc_events;

import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

public class VerifiedEvent {

    String subject, date;
    Button download, show;
    RecyclerView recyclerViewUsersList;

    public VerifiedEvent(String subject, String date, Button download, Button show, RecyclerView recyclerViewUsersList) {
        this.subject = subject;
        this.date = date;
        this.download = download;
        this.show = show;
        this.recyclerViewUsersList = recyclerViewUsersList;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Button getDownload() {
        return download;
    }

    public void setDownload(Button download) {
        this.download = download;
    }

    public Button getShow() {
        return show;
    }

    public void setShow(Button show) {
        this.show = show;
    }

    public RecyclerView getRecyclerViewUsersList() {
        return recyclerViewUsersList;
    }

    public void setRecyclerViewUsersList(RecyclerView recyclerViewUsersList) {
        this.recyclerViewUsersList = recyclerViewUsersList;
    }
}
