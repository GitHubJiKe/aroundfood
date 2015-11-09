package com.ypf.aroundfood.entity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.ypf.aroundfood.R;

public class DetialActivity extends AppCompatActivity {
    TextView nav;
    TextView sb;
    TextView tags;
    TextView nearby;
    TextView remarks;
    TextView price;
    TextView phone;
    ImageView image;
    private String phone1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detial);
        setViews();
        setContent();
        setListener();
    }

    private void setListener() {
        phone.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone1));
                //此处代码在低版本中无法调用
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });
    }


    private void setContent() {
        Intent intent=getIntent();
        AroundFood.Result result= (AroundFood.Result) intent.getSerializableExtra("result");
        String nav1=result.getNavigation();
        String photo1=result.getPhotos();
        StringBuilder sb1=new StringBuilder();
        sb1.append(result.getCity());
        sb1.append(result.getArea());
        sb1.append(result.getAddress());
        String tags1=result.getTags();
        String nearby1=result.getNearby_shops();
        String remarks1=result.getAll_remarks();
        String price1=result.getAvg_price();
        phone1=result.getPhone();
        nav.setText("类导航："+nav1);
        sb.setText("地址："+sb1);
        tags.setText("标签："+tags1);
        nearby.setText("附近美食："+nearby1);
        remarks.setText("总点评数："+remarks1);
        price.setText("人均消费：" + price1);
        phone.setText("电话：" + phone1+"       点击快速预约");
        ImageLoader imageLoader=ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(DetialActivity.this));
        imageLoader.displayImage(photo1,image);
    }

    private void setViews() {
        nav= (TextView) findViewById(R.id.nav);
        sb= (TextView) findViewById(R.id.address);
        tags= (TextView) findViewById(R.id.tags);
        nearby= (TextView) findViewById(R.id.nearby);
        remarks= (TextView) findViewById(R.id.remarks);
        price= (TextView) findViewById(R.id.price);
        phone= (TextView) findViewById(R.id.phone);
        image= (ImageView) findViewById(R.id.imageView2);
    }
}
