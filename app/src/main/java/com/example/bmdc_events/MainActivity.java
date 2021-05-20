package com.example.bmdc_events;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    CalendarView calendarView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //test 0.3

        textView = findViewById(R.id.text);


        calendarView = (CalendarView) findViewById(R.id.calender);

        calendarView.setOnDateChangeListener(myCalendarListener);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().getActualMinimum(Calendar.HOUR_OF_DAY));
        long date = calendar.getTime().getTime();
        calendarView.setMinDate(date);
    }

    CalendarView.OnDateChangeListener myCalendarListener = (view, year, month, day) -> {

        month = month + 1;
        String newDate = year+"-"+month+"-"+day;
       textView.setText(newDate);
    };
}