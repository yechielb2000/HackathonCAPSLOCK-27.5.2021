package com.example.bmdc_events;

public class Event {

    private String subject, text, date, deadlineDate;

    public Event(String subject, String text, String date, String deadlineDate) {
        this.subject = subject;
        this.text = text;
        this.date = date;
        this.deadlineDate = deadlineDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(String deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String toString() {
        return "Event{" +
                "subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                ", date='" + date + '\'' +
                ", deadlineDate='" + deadlineDate + '\'' +
                '}';
    }
}
