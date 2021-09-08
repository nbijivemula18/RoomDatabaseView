package com.ashish.roomwithlivedata.adaptors;


import android.content.Context;
import android.database.Observable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ashish.roomwithlivedata.R;
import com.ashish.roomwithlivedata.database.AppDatabase;
import com.ashish.roomwithlivedata.database.AppExecutors;
import com.ashish.roomwithlivedata.model.MusicInfo;

import java.util.List;

public class PersonAdaptor extends RecyclerView.Adapter<PersonAdaptor.MyViewHolder> {
    private Context context;
    private List<MusicInfo> mPersonList;
    private AppDatabase mDb;

    public PersonAdaptor(Context context, AppDatabase mDb) {
        this.context = context;
        this.mDb = mDb;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.person_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PersonAdaptor.MyViewHolder myViewHolder, int i) {
        myViewHolder.name.setText(mPersonList.get(i).getName());
        final int position = i;
        if (mPersonList.get(i).getStats().equals("0"))
            myViewHolder.editImage.setImageResource(R.drawable.ic_action_name);
        else myViewHolder.editImage.setImageResource(R.drawable.ic_action_select);
        myViewHolder.editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPersonList.get(position).getStats().equals("0")) {
                    mPersonList.set(position, new MusicInfo(mPersonList.get(position).getName(), "", "1"));
                    myViewHolder.editImage.setImageResource(R.drawable.ic_action_select);
                    //mDb.musicFileDao().updateMusic("1",mPersonList.get(position).getId());

                } else {
                    mPersonList.set(position, new MusicInfo(mPersonList.get(position).getName(), "", "0"));
                  //  mDb.musicFileDao().updateMusic("0",mPersonList.get(position).getId());
                    myViewHolder.editImage.setImageResource(R.drawable.ic_action_name);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mPersonList == null) {
            return 0;
        }
        return mPersonList.size();

    }

    public void setTasks(List<MusicInfo> personList) {
        mPersonList = personList;
        notifyDataSetChanged();
    }

    public List<MusicInfo> getTasks() {

        return mPersonList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, pincode, number, city;
        ImageView editImage;
        AppDatabase mDb;

        MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            mDb = AppDatabase.getInstance(context);
            name = itemView.findViewById(R.id.person_name);
            editImage = itemView.findViewById(R.id.edit_Image);

        }
    }
}
