package com.sigpher.demobase;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.sigpher.base.image.DefaultOption;
import com.sigpher.base.image.ImageLoader;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_main);
        ImageView iv = findViewById(R.id.app_iv);
        ImageLoader.load(this, new DefaultOption.Builder(R.drawable.app_img_wallpaper).into(iv));
    }
}
