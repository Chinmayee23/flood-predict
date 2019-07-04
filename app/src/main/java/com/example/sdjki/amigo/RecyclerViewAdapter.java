package com.example.sdjki.amigo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> mwind=new ArrayList<>();
    private ArrayList<String> mtemp=new ArrayList<>();
    private ArrayList<String> mpressure=new ArrayList<>();
    private ArrayList<String> mHumidity=new ArrayList<>();
    private ArrayList<String> mprecip=new ArrayList<>();
    private ArrayList<doc> mdoc=new ArrayList<>();
    private ArrayList<String>mNames=new ArrayList<>();
    private ArrayList<String>mImageUrls=new ArrayList<>();
    private ArrayList<String>mflood=new ArrayList<>();
    private Context mContext;


    public RecyclerViewAdapter(Context context, ArrayList<String> mNames, ArrayList<String> mImageUrls ,ArrayList<String> mHumidity,ArrayList<String> mprecip,
                               ArrayList<String> mpressure,ArrayList<String> mtemp,ArrayList<String> mwind,ArrayList<String> mflood, ArrayList<doc> mdoc) {
        this.mdoc = mdoc;
        mContext = context;
        this.mNames=mNames;
        this.mImageUrls=mImageUrls;
        this.mHumidity=mHumidity;
        this.mprecip=mprecip;
        this.mpressure=mpressure;
        this.mtemp=mtemp;
        this.mwind=mwind;
        this.mflood=mflood;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG,"onBindViewHolder: called.");
        holder.image.setText(mHumidity.get(position));
        holder.image1.setText(mprecip.get(position));
        holder.image2.setText(mpressure.get(position));
        holder.image3.setText(mtemp.get(position));
        holder.image4.setText(mwind.get(position));
        holder.imageName.setText(mNames.get(position));
        if(mflood.get(position).equals("yes"))
        {   holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Flood Alert!!", Toast.LENGTH_SHORT).show();
            }
        });
            holder.t.setVisibility(View.VISIBLE);
            holder.t1.setVisibility(View.VISIBLE);

            holder.parentLayout.setBackgroundColor(Color.RED);
            Log.i("Send email", "");


            try {
                holder.t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent.setData(Uri.parse("https://www.ieeevesit.org/teproject/map.html"));
                        mContext.startActivity(intent);
                        //Intent activity2 = new Intent("com.example.sdjki.Amigo.MapsActivity");
                        //activity2.putExtra("precip", image1.getText().toString());
                        //mContext.startActivity(activity2);
                        //Toast.makeText(mContext, "lololol", Toast.LENGTH_SHORT).show();
                    }
                });
                holder.t1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String[] TO = {
                                "sdjkillers@gmail.com"
                        };
                        String[] CC = {
                                "2016.abhishek.kalgutkar@ves.ac.in"
                        };
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setData(Uri.parse("mailto:"));
                        emailIntent.setType("text/plain");
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                        emailIntent.putExtra(Intent.EXTRA_CC, CC);
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Flood alert!!");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "flood on 27-09-2017!!");
                        //Toast.makeText(mContext, "Flood Alert!!", Toast.LENGTH_SHORT).show();
                         mContext.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                        //  mContext.stopService(Intent.createChooser(emailIntent));
                       // mContext.stopService(emailIntent);
                           // Log.i("Finished sending email...", "");

                    }
                });
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(mContext, "There is no email client installed.", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            holder.t.setVisibility(View.INVISIBLE);
            holder.t1.setVisibility(View.INVISIBLE);

            holder.parentLayout.setBackgroundColor(Color.GREEN);

            holder.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "No flood", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mImageUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView image;
        TextView image1;
        TextView image2;
        TextView image3;
        TextView image4;
        Button t,t1;
        TextView imageName;
        RelativeLayout parentLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            image1=itemView.findViewById(R.id.image1);
            image2=itemView.findViewById(R.id.image2);
            image3=itemView.findViewById(R.id.image3);
            image4=itemView.findViewById(R.id.image4);
            imageName=itemView.findViewById(R.id.image_name);
            parentLayout=itemView.findViewById(R.id.parent_layout);
            t=itemView.findViewById(R.id.button);
            t1=itemView.findViewById(R.id.button3);

        }
    }
}
