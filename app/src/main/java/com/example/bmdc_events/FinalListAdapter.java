package com.example.bmdc_events;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FinalListAdapter extends  RecyclerView.Adapter<FinalListAdapter.FinalListViewHolder>{

    private List<VerifiedEvent> list;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    FinalListAdapter(Context context, List<VerifiedEvent> list) {
        this.mInflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override @NonNull
    public FinalListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.users_event_card, parent, false);
        return new  FinalListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FinalListViewHolder holder, int position) {

        VerifiedEvent verifiedEvent = list.get(position);

        holder.subject.setText(verifiedEvent.getSubject());
        holder.date.setText(verifiedEvent.getDate());
        holder.download.setOnClickListener(v -> {
            /* TODO
               make pdf file with the list of who want to come (get from firebase the final lists collection )
            */
        });

        holder.show.setOnClickListener(v -> {
            holder.recyclerViewUsersList.setVisibility(View.VISIBLE);

            /*  TODO
                get the usersIdArray from document, then for every id send request to API so you get his info
                and print it to the recyclerview
            */

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FinalListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView subject, date;
        Button download, show;
        RecyclerView recyclerViewUsersList;

        FinalListViewHolder(View itemView){
            super(itemView);

            subject = itemView.findViewById(R.id.event_card_subject);
            date = itemView.findViewById(R.id.event_card_date);
            download = itemView.findViewById(R.id.event_card_download_users_list);
            show = itemView.findViewById(R.id.event_card_show_users_list);
            recyclerViewUsersList = itemView.findViewById(R.id.event_card_users_list_recyclerview);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null) mClickListener.onItemClick(v, getAdapterPosition());
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
