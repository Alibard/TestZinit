package com.becon.talon.testzinit;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.becon.talon.testzinit.adapters.RecycleVewAdapter;
import com.becon.talon.testzinit.rest.DataModel;
import com.becon.talon.testzinit.rest.RestClientV2;
import com.becon.talon.testzinit.utils.Constants;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView myMeinList;
    private RecycleVewAdapter adapter;
    private ArrayList<DataModel> dataModels = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private Thread t;
    private boolean allDone = false;
    private Intent intent;
    private int randomNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this, DetailActivity.class);
        initContent();
        setCall();
    }

    private void setCall() {
        Call<ArrayList<DataModel>> getData = new RestClientV2().getApiService().getData(Constants.CLIENT);
        getData.enqueue(new Callback<ArrayList<DataModel>>() {
            @Override
            public void onResponse(Call<ArrayList<DataModel>> call, Response<ArrayList<DataModel>> response) {
                if (response.code() == 200) {
                    Log.i(TAG, "onResponse: " + response.body().toString());
                    if (!response.body().isEmpty()) {
                        dataModels = response.body();
                        setUpContent(dataModels);
                        setUpTimer();
                        allDone = true;
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Some Proglem the agan laiter", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<DataModel>> call, Throwable t) {

            }
        });
    }

    private void setUpContent(ArrayList<DataModel> body) {
        adapter = new RecycleVewAdapter(body, MainActivity.this);
        myMeinList.setLayoutManager(linearLayoutManager);
        myMeinList.setAdapter(adapter);
    }

    private void initContent() {
        myMeinList = (RecyclerView) findViewById(R.id.my_mein_list);
        linearLayoutManager = new LinearLayoutManager(this);
    }


    private void doalog() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:

                       intent.putExtra(Constants.TITLE, dataModels.get(randomNum).getTitle());
                       intent.putExtra(Constants.URL, dataModels.get(randomNum).getThumbnail());
                        startActivity(intent);
                        Log.i(TAG, "onClick: ++");
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        setUpTimer();
                        break;
                }
            }
        };
        Random rand = new Random();
        randomNum = rand.nextInt((dataModels.size() - 0) + 1) + 0;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String currentDateandTime = sdf.format(new Date());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.image, null);
;       ImageView imageView = (ImageView)dialogLayout.findViewById(R.id.image);
        TextView text   = (TextView)dialogLayout.findViewById(R.id.text);
        text.setText(dataModels.get(randomNum).getTitle());
        Picasso.with(this)
                .load(dataModels.get(randomNum).getThumbnail())
                .into(imageView);
//
        builder.setView(dialogLayout);
        builder.setTitle(currentDateandTime);
        builder.setPositiveButton("Детальніше", dialogClickListener)
                .setNegativeButton("Відміна", dialogClickListener).show();
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (t != null) {
            if (t.isAlive()) {
                t.interrupt();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (allDone) {
            setUpTimer();
        }
    }


    private void setUpTimer() {
        t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    runOnUiThread(runn1);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }


    Runnable runn1 = new Runnable() {
        public void run() {
            doalog();
        }
    };
}