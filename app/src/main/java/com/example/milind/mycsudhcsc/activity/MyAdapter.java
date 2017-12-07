package com.example.milind.mycsudhcsc.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.milind.mycsudhcsc.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Milind on 12/1/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;

    private static int currentPosition = 0;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ListItem listItem = listItems.get(position);

        holder.textViewName.setText(listItem.getName());
        holder.textViewEmail.setText(listItem.getEmail());
        holder.textViewPhone.setText(listItem.getPhone());
        holder.textViewOffice.setText(listItem.getOffice());
        holder.textViewWebsite.setText(listItem.getWebsite());

        Picasso.with(context)
                .load(listItem.getImageUrl())
                .into(holder.imageView);

        holder.linearLayout.setVisibility(View.GONE);

        //if the position is equals to the item position which is to be expanded
        if (currentPosition == position) {
            //creating an animation
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);

            //toggling visibility
            holder.linearLayout.setVisibility(View.VISIBLE);

            //adding sliding effect
            holder.linearLayout.startAnimation(slideDown);
        }

        holder.textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getting the position of the item to expand it
                currentPosition = position;

                //reloding the list
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewName,textViewEmail, textViewPhone,
                textViewOffice, textViewWebsite;
        ImageView imageView;
        LinearLayout linearLayout;

        ViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewEmail = (TextView) itemView.findViewById(R.id.textViewEmail);
            textViewPhone = (TextView) itemView.findViewById(R.id.textViewPhone);
            textViewOffice = (TextView) itemView.findViewById(R.id.textViewOffice);
            textViewWebsite = (TextView) itemView.findViewById(R.id.textViewWebsite);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }
}
