package com.example.bmdc_events;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import retrofit2.http.GET;

import static android.content.Context.MODE_PRIVATE;
import static com.example.bmdc_events.MainActivity.MY_PREFS_NAME;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Event> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, List<Event> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Event event = mData.get(position);

        holder.subject.setText("Subject : " + event.getSubject());
        holder.text.setText(event.getText());
        holder.date.setText("Date : " + event.getDate());
        holder.deadlineDate.setText("DeadLine Date : " + event.getDeadlineDate());

        holder.acceptButton.setOnClickListener(v -> {

            SharedPreferences preferences = mInflater.getContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            String id = preferences.getString("Id", null);

            FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
            fireStore.collection("events").get().addOnSuccessListener(queryDocumentSnapshots -> {
                Toast.makeText(mInflater.getContext(), "userId : " + id, Toast.LENGTH_SHORT).show();
            });
        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView subject, text, date, deadlineDate;
        Button acceptButton;

        ViewHolder(View itemView) {
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

    // convenience method for getting data at click position
    Event getItem(int id) {
        return mData.get(id);
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