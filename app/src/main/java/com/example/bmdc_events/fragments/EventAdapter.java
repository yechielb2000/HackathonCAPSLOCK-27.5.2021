package com.example.bmdc_events.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bmdc_events.Event;
import com.example.bmdc_events.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import static android.content.Context.MODE_PRIVATE;
import static com.example.bmdc_events.MainActivity.MY_PREFS_NAME;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private List<Event> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    EventAdapter(Context context, List<Event> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.event_card, parent, false);
        return new EventViewHolder(view);
    }

    // binds the data to the TextView in each row

    @Override @SuppressLint("SetTextI18n")
    public void onBindViewHolder(EventViewHolder holder, int position) {
        Event event = mData.get(position);

        holder.subject.setText("Subject : " + event.getSubject());
        holder.text.setText(event.getText());
        holder.date.setText("Date : " + event.getDate());
        holder.deadlineDate.setText("DeadLine Date : " + event.getDeadlineDate());

        FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
        DocumentReference washingtonRef = fireStore.collection("events").document(event.getEventId());

        holder.acceptButton.setOnClickListener(v -> {

            SharedPreferences preferences = mInflater.getContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            String id = preferences.getString("Id", null);

            if (holder.acceptButton.getText().toString().equals("Subscribe")) {

                washingtonRef.update("usersIdArray", FieldValue.arrayUnion(id));

                holder.acceptButton.setText("unSubscribe");

            }else{

                washingtonRef.update("usersIdArray", FieldValue.arrayRemove(id));

                holder.acceptButton.setText("Subscribe");

            }

        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView subject, text, date, deadlineDate;
        Button acceptButton;

        EventViewHolder(View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.subject);
            text = itemView.findViewById(R.id.text);
            date = itemView.findViewById(R.id.date);
            deadlineDate = itemView.findViewById(R.id.deadline_date);
            acceptButton = itemView.findViewById(R.id.accept_button);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}