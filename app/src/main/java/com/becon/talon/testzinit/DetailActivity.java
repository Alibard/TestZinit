package com.becon.talon.testzinit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.becon.talon.testzinit.utils.Constants;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private ImageView image;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initContent();
        setUpCOntent();
    }

    private void setUpCOntent() {
        Picasso.with(this)
                .load(getIntent()
                        .getExtras()
                        .getString(Constants.URL))
                .into(image);
        title.setText(getIntent()
                        .getExtras()
                        .getString(Constants.TITLE));
    }

    private void initContent() {
        image = (ImageView)findViewById(R.id.image);
        title = (TextView)findViewById(R.id.title);
    }
}
