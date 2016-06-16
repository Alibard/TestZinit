package com.becon.talon.testzinit.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.becon.talon.testzinit.DetailActivity;
import com.becon.talon.testzinit.R;
import com.becon.talon.testzinit.rest.DataModel;
import com.becon.talon.testzinit.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Админ on 16.06.2016.
 */
public class RecycleVewAdapter extends RecyclerView.Adapter<RecycleVewAdapter.ViewHolder> {
    private static final String TAG = RecycleVewAdapter.class.getSimpleName();
    private ArrayList<DataModel> dataModels;
    private Context context;
    private Intent intent;

    public RecycleVewAdapter(ArrayList<DataModel> dataModels, Context context) {
        this.dataModels = dataModels;
        this.context = context;
        intent = new Intent(context, DetailActivity.class);
    }

    @Override
    public RecycleVewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecycleVewAdapter.ViewHolder holder, final int position) {
        Picasso.with(context)
                .load(dataModels.get(position).getThumbnail())
                .into(holder.imageView);
        holder.textView.setText(dataModels.get(position).getTitle());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(Constants.URL,dataModels.get(position).getThumbnail());
                intent.putExtra(Constants.TITLE,dataModels.get(position).getTitle());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.thumbnail);
            textView = (TextView)itemView.findViewById(R.id.title);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.main_ll);
        }
    }
}
