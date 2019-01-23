package com.example.pramod.androidhivefirebase.uploadPic;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pramod.androidhivefirebase.R;
import com.example.pramod.androidhivefirebase.TabLayout.HomeFragment;
import com.example.pramod.androidhivefirebase.TabLayout.SportFragment;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

    /**
     * Created by Belal on 2/23/2017.
     */

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private Context context;
        private List<Upload> uploads;
       private OnItemClickListener mListtener;
        public MyAdapter(Context context, List<Upload> uploads) {
            this.uploads = uploads;
            this.context = context;
        }
///////////////////




        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_images, parent, false);
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Upload upload = uploads.get(position);

            holder.textViewName.setText(upload.getName());

            Glide.with(context).load(upload.getUrl()).into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return uploads.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener ,
        View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

            public TextView textViewName;
            public ImageView imageView;

            public ViewHolder(View itemView) {
                super(itemView);

                textViewName = (TextView) itemView.findViewById(R.id.textViewName);
                imageView = (ImageView) itemView.findViewById(R.id.imageView1);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
            }

            @Override
            public void onClick(View v) {
               if(mListtener !=null){
                   int position=getAdapterPosition();
                   if(position !=RecyclerView.NO_POSITION){
                       mListtener.onItemClick(position);
                   }
               }
            }

            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.setHeaderTitle("selecte Action");
                MenuItem doWhatever=menu.add(Menu.NONE,1,1,"Do whatever");
                 MenuItem delete=menu.add(Menu.NONE,2,2,"Delete");
                 doWhatever.setOnMenuItemClickListener(this);
                 delete.setOnMenuItemClickListener(this);
            }

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(mListtener !=null){
                    int position=getAdapterPosition();
                    if(position !=RecyclerView.NO_POSITION){
                  switch (item.getItemId()){
                      case 1:
                           mListtener.onWhatEverClick(position);
                           return true;
                      case 2:
                          mListtener.onDeleteClick(position);
                          return true;

                  }
                    }
                }
                return false;
            }
        }
        public interface OnItemClickListener{
            void onItemClick(int position);

            void onWhatEverClick(int position);

            void onDeleteClick(int position);

        }
        public void setOnItemClickListener(OnItemClickListener listener){
mListtener=listener;
        }
    }
