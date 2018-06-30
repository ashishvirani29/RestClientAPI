package com.example.restclientapi;

import android.app.Activity;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class DataAdapter extends Adapter<DataAdapter.ViewHolder> {
    private static final String TAG = "StickerAdapter";
    Activity context;
    ArrayList<UserData> userDataArrayList;
    private OnClickCallback<ArrayList<String>, Integer, String, Activity> mSingleCallback;

    public DataAdapter(Activity posterActivity, ArrayList<UserData> userDataArrayList) {
        this.context = posterActivity;
        this.userDataArrayList = userDataArrayList;
    }

    public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        RelativeLayout layout;

        public ViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.item_image);
            this.name = (TextView) view.findViewById(R.id.txtName);
            this.layout = (RelativeLayout) view.findViewById(R.id.lay);
        }
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public int getItemCount() {
        return this.userDataArrayList.size();
    }

    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

        viewHolder.name.setText(userDataArrayList.get(position).getName());
    }

    public void setItemClickCallback(final OnClickCallback singleCallback) {
        this.mSingleCallback = singleCallback;
    }

    public ViewHolder onCreateViewHolder(ViewGroup arg0, int position) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(arg0.getContext()).inflate(R.layout.data_listrow, arg0, false));
        arg0.setId(position);
        arg0.setFocusable(false);
        arg0.setFocusableInTouchMode(false);
        return viewHolder;
    }

    public int getItemViewType(int position) {
        return position;
    }
}
