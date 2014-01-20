package com.personal.ashishn.GridImageSearch;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by ashishn on 1/19/14.
 */
public class ConfigurationActivity extends Activity {
    Spinner imageSize;
    Spinner colorFilter;
    Spinner imageType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuration);
        imageSize = (Spinner) findViewById(R.id.spinner);
        colorFilter = (Spinner) findViewById(R.id.ColorFilter);
        imageType = (Spinner) findViewById(R.id.ImageType);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                        this, R.array.sizeArray, R.layout.spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_layout);

        imageSize.setAdapter(adapter);
    }
}
