package com.example.bmdc_events;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;
import static com.example.bmdc_events.MainActivity.MY_PREFS_NAME;

public class CalenderFragment extends Fragment{

    CalendarView calendarView;
    TextView text;
    private boolean isAdmin;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_calender_fragment, container, false);

        SharedPreferences prefs = getContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String id = prefs.getString("Id", null);//"No name defined" is the default value.

        calendarView = view.findViewById(R.id.calender);
        text = view.findViewById(R.id.text);

//        isAdmin = id.equals("60ad1c30c90b0e0f84efba94");
          isAdmin = true;

        if (!isAdmin)
            text.setText("Only an admin can make an appointment");

        calendarView.setOnDateChangeListener(myCalendarListener);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().getActualMinimum(Calendar.HOUR_OF_DAY));
        long date = calendar.getTime().getTime();
        calendarView.setMinDate(date);

        return view;
    }

    CalendarView.OnDateChangeListener myCalendarListener = (view, year, month, day) -> {

        if (!isAdmin)
            Toast.makeText(getContext(), "Only an admin can make an appointment", Toast.LENGTH_SHORT).show();

        else {

            month = month + 1;
            String newDate = year+"-"+month+"-"+day;

            Bundle bundle = new Bundle();
            bundle.putString("date", newDate);

            FillDetailsDialogFragment dialogFragment = new FillDetailsDialogFragment();
            dialogFragment.setArguments(bundle); // send argument to FillDetailsDialogFragment
            dialogFragment.show(getActivity().getSupportFragmentManager(), "dialog fragment"); //show FillDetailsDialogFragment

        }

    };

}