package com.example.bmdc_events.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bmdc_events.Event;
import com.example.bmdc_events.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class EventsFragment extends Fragment {

    RecyclerView recyclerViewEvents;
    List<Event> events;
    EventAdapter adapter;

    View view;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_events_fragment, container, false);

        recyclerViewEvents = view.findViewById(R.id.recycle_view_events);
        events = new ArrayList<>();

        recyclerViewEventsAdapter();
        getEvents();
        return view;
    }

    public List<Map> getEvents(){

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        List<Map> mapArrayList = new ArrayList<>();

        firebaseFirestore.collection("events").get().addOnCompleteListener(task -> {

            if (!task.isSuccessful()) {
                Log.w(TAG, "Error getting documents.", task.getException());
                return;
            }

            if (task.getResult() != null) {
                for (QueryDocumentSnapshot document : task.getResult()) {

//                    Log.d(TAG, document.getId() + " => " + document.getData());

                    Map map = document.getData();
                    mapArrayList.add(map);

                    Event event = new Event(map.get("subject").toString(),
                            map.get("text").toString(),
                            map.get("date").toString(),
                            map.get("deadline date").toString(),
                            document.getId());

                    events.add(event);
                    adapter.notifyDataSetChanged();
//                    Toast.makeText(getContext(), event.toString(), Toast.LENGTH_LONG).show();
                }
            }else{
                Log.d(TAG, "maybe task getResult == null");
            }
        });
        return mapArrayList;
    }

    private void recyclerViewEventsAdapter(){

        RecyclerView recyclerView = view.findViewById(R.id.recycle_view_events);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new EventAdapter(getContext(), events);
        recyclerView.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.search_icon, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
        return true;
    }
}