package com.kiki0083.news_app;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<NewsData>  mDataset;
    private View.OnClickListener onClickListener;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView  m_title;
        public TextView  m_description;
        public SimpleDraweeView m_urlToImage;
        public View rootView;

        public MyViewHolder(View v) {
            super(v);
            m_title        = v.findViewById(R.id.textview_title);
            m_description  = v.findViewById(R.id.textview_description);
            m_urlToImage   = v.findViewById(R.id.image_view);
            rootView       = v;

            v.setClickable(true);
            v.setEnabled(true);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<NewsData> myDataset, Context context) {
        mDataset = myDataset;
        Fresco.initialize(context);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        Fresco.initialize(parent.getContext());
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_news, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.textView.setText(mDataset[position]);

        NewsData data = mDataset.get(position);

        holder.m_title.setText(data.getTitle());
        holder.m_description.setText(data.getDescription());
        holder.m_urlToImage.setImageURI(Uri.parse(data.getUrlToImage()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}