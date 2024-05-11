package com.example.recyclerviewwithfilter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class YourAdapter extends RecyclerView.Adapter<YourAdapter.ViewHolder> {

    private List<YourItem> itemList;
    private List<YourItem> filteredList;
    private Context context;

    public YourAdapter(Context context, List<YourItem> itemList) {
        this.context = context;
        this.itemList = itemList;
        this.filteredList = new ArrayList<>(itemList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        YourItem item = filteredList.get(position);
        holder.txtName.setText(item.getName());
        holder.txtValue.setText(String.valueOf(item.getValue()));
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public void filter(String text) {
        filteredList.clear();
        if (text.isEmpty()) {
            filteredList.addAll(itemList);
        } else {
            text = text.toLowerCase();
            for (YourItem item : itemList) {
                if (item.getName().toLowerCase().contains(text)) {
                    filteredList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtValue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtValue = itemView.findViewById(R.id.txtValue);
        }
    }
}
