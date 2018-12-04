package com.example.mroot.timedifftool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import utils.ToolUtils;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_start_time)
    EditText et_start_time;
    @BindView(R.id.et_end_time)
    EditText et_end_time;
    @BindView(R.id.et_diff_time)
    EditText et_diff_time;

    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_compute)
    public void compute() {
        String startTime = et_start_time.getText().toString();
        String endTime = et_end_time.getText().toString();

        Date date1 = ToolUtils.str2date(startTime);
        Date date2 = ToolUtils.str2date(endTime);
        if (date1 == null || date2 == null) {
            Toast.makeText(this, "输入的时间格式错误", Toast.LENGTH_SHORT).show();
            return;
        }

        int seconds = ToolUtils.computeDiff(date1, date2);
        if (seconds <= 60) {
            et_diff_time.setText(seconds + " s");
        } else {
            int min = seconds / 60;
            int temp = seconds % 60;
            et_diff_time.setText(min + " min " + temp + " s");
        }
        Toast.makeText(this, "计算结束", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_clear)
    public void clear() {
        et_start_time.setText("");
        et_end_time.setText("");
        et_diff_time.setText("");
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            //不会调用周期函数，如onDestroy()
            System.exit(0);
        }
    }
}
