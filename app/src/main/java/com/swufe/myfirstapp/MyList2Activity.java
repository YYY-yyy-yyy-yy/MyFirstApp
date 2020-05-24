package com.swufe.myfirstapp;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyList2Activity extends ListActivity implements Runnable, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    //in fact this is week12
    Handler handler;
    private String TAG = "MyList2";
    private List<HashMap<String,String>> listItem; //存放文字、图片信息
    private SimpleAdapter listItemAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListView();
//        MyAdapterActivity myAdapter = new MyAdapterActivity(this,R.layout.list_item,listItem);
//        this.setListAdapter(myAdapter);

        Thread t = new Thread(this);
        t.start();

        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==7){
                    listItem = (List<HashMap<String,String>>) msg.obj;
                    listItemAdapter = new SimpleAdapter(MyList2Activity.this,listItem,//listIteam数据源
                            R.layout.list_item,//ListTtem的xml布局实现
                            new String[] {"ItemTitle","ItemDetail"},
                            new int[]{R.id.itemTitle,R.id.itemDetail}
                    );
                    setListAdapter(listItemAdapter);
                }
                super.handleMessage(msg);
            }
        };
//        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int positon, long id) {
//            }
//        });
        getListView().setOnItemClickListener(this);
        getListView().setOnItemLongClickListener(this);
    }
    private void initListView(){
        listItem = new ArrayList<HashMap<String, String>>();
        for(int i =0;i<10;i++){
            HashMap<String,String> map = new HashMap<String, String>();
            map.put("ItemTitle","Rate:"+i);//标题文字
            map.put("ItemDetail","detail"+i);//标题描述
            listItem.add(map);
        }
        //生成适配器的Item和动态数组对应元素
        listItemAdapter = new SimpleAdapter(this,listItem,//listIteam数据源
                R.layout.list_item,//ListTtem的xml布局实现
                new String[] {"ItemTitle","ItemDetail"},
                new int[]{R.id.itemTitle,R.id.itemDetail}
        );
    }

    @Override
    public void run() {
        List<HashMap<String,String>> retList = new ArrayList<HashMap<String, String>>();
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.usd-cny.com/bankofchina.htm").get();
            Log.i(TAG,"run:"+doc.title());
            Elements tables = doc.getElementsByTag("table");
            Element table1 = tables.get(0);
            Elements tds = table1.getElementsByTag("td");
            for (int i =0;i<tds.size();i+=6){
                Element td1 = tds.get(i);
                Element td2 = tds.get(i+5);
                Log.i(TAG,"run:"+td1.text()+"==>"+td2.text());
                String str1 = td1.text();
                String val = td2.text();
                HashMap<String,String> map = new HashMap<String, String>();
                map.put("ItemTitle",str1);
                map.put("ItemDetail",val);
                retList.add(map);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Message msg  = handler.obtainMessage(7);
        msg.obj = retList;
        handler.sendMessage(msg);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG,"onItemClick:parent"+parent);
        Log.i(TAG,"onItemClick:view"+view);
        Log.i(TAG,"onItemClick:position"+position);
        Log.i(TAG,"onItemClick:id"+id);
        HashMap<String,String> map = (HashMap<String, String>)getListView().getItemAtPosition(position);
        String titleStr = map.get("ItemTitle");
        String detailStr = map.get("ItemDetail");
        TextView title = view.findViewById(R.id.itemTitle);
        TextView detail = view.findViewById(R.id.itemDetail);
        String title2 = String.valueOf(title.getText());
        String detail2 = String.valueOf(detail.getText());

        //打开新的页面传入参数
        Intent rateCalc = new Intent(this,RateCalcActivity.class);
        rateCalc.putExtra("title",titleStr);
        rateCalc.putExtra("rate",Float.parseFloat(detailStr));
                startActivity(rateCalc);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        Log.i(TAG,"长按：position"+position);
        //删除操作
//        listItem.remove(position);
//        listItemAdapter.notifyDataSetChanged();
        //构造对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示").setMessage("请确认是否删除当前数据").setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG,"onclick对话框事件");
                listItem.remove(position);
                listItemAdapter.notifyDataSetChanged();
            }
        }).setNegativeButton("否",null);
        builder.create().show();

        Log.i(TAG,"onItemLongClick"+listItem.size());
        return true;//true说明屏蔽短按事件
    }
}
