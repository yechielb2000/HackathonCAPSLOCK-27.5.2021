package com.example.bmdc_events;

public class Event {

    private String subject, text, date, deadlineDate, eventId;

    public Event(String subject, String text, String date, String deadlineDate, String eventId) {
        this.subject = subject;
        this.text = text;
        this.date = date;
        this.deadlineDate = deadlineDate;
        this.eventId = eventId;
    }

    public String getEventId() {
        return eventId;
    }

    public String getSubject() {
        return subject;
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

    public String getDeadlineDate() {
        return deadlineDate;
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
