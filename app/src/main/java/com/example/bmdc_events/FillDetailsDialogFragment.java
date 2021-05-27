package com.example.bmdc_events;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FillDetailsDialogFragment extends DialogFragment {

    private EditText subject, text;
    private Spinner deadlineDateSpinner;
    private Button submit;
    private Bundle bundle;
    private String date;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_popup_window, container, false);

        subject = view.findViewById(R.id.et_subject);
        text = view.findViewById(R.id.et_text);
        deadlineDateSpinner = view.findViewById(R.id.deadline_spinner);
        submit = view.findViewById(R.id.popup_submit_button);

        bundle = this.getArguments(); //get argument from CalendarFragment
        date = bundle.getString("date");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();

        List<Date> dates = getDates(dateFormat.format(cal.getTime()), date);

        ArrayAdapter<Date> adp1 = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dates);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deadlineDateSpinner.setAdapter(adp1);

        deadlineDateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        submit.setOnClickListener(v -> {

            //you need to get -> subject, text, date, deadlineDate

            if (bundle != null){

                InitializeCloudFireStore fireStore = new InitializeCloudFireStore();
                fireStore.addEvent(subject.getText().toString(), text.getText().toString(), date, deadlineDateSpinner.getSelectedItem().toString());
                Toast.makeText(getActivity().getBaseContext(), "Subject - " + subject.getText() + "\nText - " + text.getText() + "\nDate - " + date + "\nDeadline - " + deadlineDateSpinner.getSelectedItem(), Toast.LENGTH_SHORT).show();
            }
            this.dismiss();
        });

        return view;

    }

    private List<Date> getDates(String dateString1, String dateString2) {
        ArrayList<Date> dates = new ArrayList<>();
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = null;
        Date date2 = null;

        try {
            date1 = df1.parse(dateString1);
            date2 = df1.parse(dateString2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);


        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        while(!cal1.after(cal2)) {
            dates.add(cal1.getTime());
            cal1.add(Calendar.DATE, 1);
        }

        date = String.valueOf(cal2.getTime());
        return dates;
    }
}