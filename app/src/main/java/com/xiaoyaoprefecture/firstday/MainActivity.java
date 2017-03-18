package com.xiaoyaoprefecture.firstday;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * 测试dialog的简单玩法
 * Alertdialog/progressdialog
 */
public class MainActivity extends AppCompatActivity {
    Button mBtn,mBtn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * 初始化数据
     */
    private void init() {
        findView();
        setLisener();
    }

    /**
     * 给Button设置监听事件
     */
    private void setLisener() {
        //点击显示对话框
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //构建对话框对象
                AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                //绘制对话框
                dialog.setTitle("爱情敢不敢");
                dialog.setMessage("是否确定退出游戏");
                //设置对话框外的点击是否可以取消显示对话框（包括返回键）
                dialog.setCancelable(false);//可以
                dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"您点击了ok",Toast.LENGTH_LONG).show();
                    }
                });
                dialog.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"您点击了Cancle",Toast.LENGTH_LONG).show();

                    }
                });
                dialog.show();
            }
        });
        //点击显示进度条对话框
        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog dialog=new ProgressDialog(MainActivity.this);
                dialog.setTitle("爱情纠结病");
                dialog.setMessage("正在加载中");
                dialog.setCancelable(true);
                dialog.show();
            }
        });
    }

    /**
     * 找控件
     */
    private void findView() {
        mBtn= (Button) findViewById(R.id.mBtn);
        mBtn2= (Button) findViewById(R.id.mBtn2);
    }
}
