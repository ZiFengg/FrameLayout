package com.example.zifeng.framelayout;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ZiFeng on 2018/3/17.
 */
public class MainActivity extends Activity {

    private int currentColor = 0;
    // 定义一个colors的颜色资源数组
    final int[] colors = new int[]{
            R.color.color1, R.color.color2,
            R.color.color3, R.color.color4,
            R.color.color5, R.color.color6
    };
    // 定义一个names的id数组
    final int[] names = new int[]{
            R.id.view01, R.id.view02,
            R.id.view03, R.id.view04,
            R.id.view05, R.id.view06
    };
    // 定义一个TextView数组
    TextView[] views = new TextView[names.length];
    // 定义一个Handler类
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 判断消息来源是不是本程序
            if(msg.what == 0x123){
                // 改变所有TextView颜色一次
                for (int i = 0; i < names.length; i++){
                    views[i].setBackgroundResource(
                       colors[(i + currentColor) % names.length]);
                }
                currentColor++;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // 循环实例化TextView
        for (int i = 0; i < names.length; i++){
            views[i] = (TextView) findViewById(names[i]);
        }
        // 开启一个线程0.2秒周期性的改变currentColor变量值
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // 发送一条消息通知handler改变textview组件背景色
                handler.sendEmptyMessage(0x123);
            }
        },0,200);//第二个参数表延时，第三个参数表示循环时间
        }
}
