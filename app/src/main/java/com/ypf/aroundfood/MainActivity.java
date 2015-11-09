package com.ypf.aroundfood;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.baidu.location.BDLocation;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;
import com.ypf.aroundfood.entity.AroundFood;
import com.ypf.aroundfood.entity.DetialActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<AroundFood.Result>listdata;
    double lat;
    double lng;
    String city;
    MyAdapter adapter;
    BDLocation location=new BDLocation();
    private AdapterView.OnItemClickListener l=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent=new Intent(MainActivity.this, DetialActivity.class);
            intent.putExtra("result", listdata.get(position));
            startActivity(intent);
        }
    };
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            listdata=new ArrayList<>();
            if (msg.what==1){
                listdata= (List<AroundFood.Result>) msg.obj;
                Log.i("food1",""+listdata.size());
            }else if(msg.what==2){
                listdata= (List<AroundFood.Result>) msg.obj;
                Log.i("food2",""+listdata.size());
                adapter=new MyAdapter();
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(l);

            }
        }
    };
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv= (ListView) findViewById(R.id.listView);
        getLocation();
        getCity();
        getDataFromLocation();

//        getDataFromCity();
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return listdata.size();
        }

        @Override
        public Object getItem(int position) {
            return listdata.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder=null;
            if (convertView==null){
                holder=new ViewHolder();
                convertView=LayoutInflater.from(MainActivity.this).inflate(R.layout.item_food,null);
                holder.tv1= (TextView) convertView.findViewById(R.id.textView2);
                holder.tv2= (TextView) convertView.findViewById(R.id.textView3);
                holder.iv=(ImageView)convertView.findViewById(R.id.imageView);
                convertView.setTag(holder);
            }
            AroundFood.Result result=listdata.get(position);
            holder= (ViewHolder) convertView.getTag();
            holder.tv1.setText(result.getName());
            StringBuilder sb=new StringBuilder();
            sb.append(result.getCity());
            sb.append(result.getArea());
            sb.append(result.getAddress());
            holder.tv2.setText(sb);
            ImageLoader imageloader=ImageLoader.getInstance();
            imageloader.init(ImageLoaderConfiguration.createDefault(MainActivity.this));
            DisplayImageOptions options=DisplayImageOptions.createSimple();
            options.isCacheInMemory();
            options.isCacheOnDisk();
            options.isConsiderExifParams();
            imageloader.displayImage(result.getPhotos(), holder.iv, options, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String s, View view) {

                }

                @Override
                public void onLoadingFailed(String s, View view, FailReason failReason) {
                    ImageView image = (ImageView) view;
                    image.setImageResource(R.drawable.ic_launcher);
                }

                @Override
                public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                    ImageView image = (ImageView) view;
                    image.setImageBitmap(bitmap);
                }

                @Override
                public void onLoadingCancelled(String s, View view) {

                }
            });
            return convertView;
        }

        class ViewHolder{
            TextView tv1;
            TextView tv2;
            ImageView iv;
        }
    }

    private void getDataFromCity() {
        Parameters parameters=new Parameters();
        parameters.add("city",city);
        JuheData.executeWithAPI(this, 45, "http://apis.juhe.cn/catering/querybycity", JuheData.GET,parameters , new DataCallBack() {
            @Override
            public void onSuccess(int i, String s) {
                AroundFood food=JSON.parseObject(s, AroundFood.class);
                Message msg=Message.obtain();
                msg.obj=food.getResult();
                msg.what=1;
                handler.sendMessage(msg);
            }

            @Override
            public void onFinish() {
                Log.i("city+finish","finish");
            }

            @Override
            public void onFailure(int i, String s, Throwable throwable) {
                Log.i("failure",""+s);
            }
        });
    }
    private void getCity() {
        city=location.getCity();
    }

    private void getLocation() {
        lat=location.getLatitude();
        lng=location.getLongitude();
        if (lat == 4.9E-324) {
            lat = 39.882348;
            lng = 116.471556;
        }
    }

    public void getDataFromLocation() {
        Parameters parameters=new Parameters();
        parameters.add("lat",lat);
        parameters.add("lng",lng);
        JuheData.executeWithAPI(MainActivity.this, 45, "http://apis.juhe.cn/catering/query", JuheData.GET,parameters , new DataCallBack() {
            @Override
            public void onSuccess(int i, String s) {
                AroundFood food=JSON.parseObject(s, AroundFood.class);
                Log.i("food",""+food);
                Message msg=Message.obtain();
                msg.obj=food.getResult();
                Log.i("obj",""+food.getResult());
                msg.what=2;
                handler.sendMessage(msg);
            }

            @Override
            public void onFinish() {
                Log.i("location+finish","finish");
            }

            @Override
            public void onFailure(int i, String s, Throwable throwable) {
                Log.i("location+finish",""+s);
            }
        });
    }
}
