package com.sigpher.demobase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.sigpher.base.image.DefaultOption;
import com.sigpher.base.image.ImageLoader;
import com.sigpher.base.image.LoadListener;
import com.sigpher.base.image.ScaleType;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_main);
        startActivity(new Intent(this, SecondActivity.class));
        ImageLoader.load(this, new DefaultOption.Builder(R.drawable.app_img_wallpaper)
                .placeholder(R.mipmap.leak_canary_icon)
                .error(R.mipmap.app_ic_launcher_round)
                .crossFade(300)
                .addListener(new LoadListener() {
                    @Override
                    public boolean onSuccess(Object resource) {
                        return false;
                    }

                    @Override
                    public boolean onFailure(Throwable e) {
                        return false;
                    }
                })
                .scaleType(ScaleType.CIRCLE_CROP)
                .into(new ImageView(this)));
    }
}
