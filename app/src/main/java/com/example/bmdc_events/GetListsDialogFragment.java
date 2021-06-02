package com.example.bmdc_events;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;


public class GetListsDialogFragment  extends DialogFragment{


    RecyclerView recyclerViewFinalList;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_dialog_get_lists, container, false);


        recyclerViewFinalList = view.findViewById(R.id.final_list_recyclerview);

        //todo get from final lists collection the list of the events

        //put the eventsLists in


        return view;
    }
}
