package com.example.bmdc_events;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class CalenderFragment extends Fragment{

    CalendarView calendarView;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_calender_fragment, container, false);

        calendarView = view.findViewById(R.id.calender);

        calendarView.setOnDateChangeListener(myCalendarListener);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().getActualMinimum(Calendar.HOUR_OF_DAY));
        long date = calendar.getTime().getTime();
        calendarView.setMinDate(date);

        return view;
    }

    CalendarView.OnDateChangeListener myCalendarListener = (view, year, month, day) -> {

        month = month + 1;
        String newDate = year+"-"+month+"-"+day;

        Bundle bundle = new Bundle();
        bundle.putString("date", newDate);

        FillDetailsDialogFragment dialogFragment = new FillDetailsDialogFragment();
        dialogFragment.setArguments(bundle); // send argument to FillDetailsDialogFragment
        dialogFragment.show(getActivity().getSupportFragmentManager(), "dialog fragment"); //show FillDetailsDialogFragment
    };

}