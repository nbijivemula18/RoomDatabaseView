package com.ashish.roomwithlivedata.activities;


import android.arch.lifecycle.Observer;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.AdapterView;

import com.ashish.roomwithlivedata.R;
import com.ashish.roomwithlivedata.Utils.Utils;
import com.ashish.roomwithlivedata.adaptors.PersonAdaptor;
import com.ashish.roomwithlivedata.database.AppDatabase;
import com.ashish.roomwithlivedata.database.AppExecutors;
import com.ashish.roomwithlivedata.model.MusicInfo;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private PersonAdaptor mAdapter;
    private AppDatabase mDb;
    File[] fileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        Utils.PermissionCheck(MainActivity.this, MainActivity.this, Utils.permissionALL, 1);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter and attach it to the RecyclerView
        mDb = AppDatabase.getInstance(getApplicationContext());
        mAdapter = new PersonAdaptor(this,mDb);
        mRecyclerView.setAdapter(mAdapter);

        retrieveTasks();
    }

    private void retrieveTasks() {
        mDb.musicFileDao().loadAll().observe(this, new Observer<List<MusicInfo>>() {
            @Override
            public void onChanged(@Nullable List<MusicInfo> musicInfoList) {

                mAdapter.setTasks(musicInfoList);
                if (musicInfoList.size() == 0)
                      firstTimeInsert();
            }
        });
    }

    public void firstTimeInsert() {

        File pathList = new File(Environment.getExternalStorageDirectory(),"");
        fileList = pathList.listFiles();
        if (fileList == null) return;
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < fileList.length; i++) {
                    if(fileList[i].getName().endsWith("mp3")) {
                        MusicInfo musicInfo = new MusicInfo(fileList[i].getName(), "", "0");
                        mDb.musicFileDao().insertMusic(musicInfo);
                    }

                }
                retrieveTasks();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        retrieveTasks();
    }
}
