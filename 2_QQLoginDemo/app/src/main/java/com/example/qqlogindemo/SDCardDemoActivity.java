package com.example.qqlogindemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;

/**
 * <pre>
 * author : ZONGNAN.CHEN
 * time : 2021/07/16
 * </pre>
 */
public class SDCardDemoActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "SDCardDemoActivity";
    private Button mWriteDataBtn;
    private Button mCheckSDCardBtn;
    private Button mGetFreeSizeBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sd_card);
        initView();
        initListener();
    }

    private void initListener() {
        mWriteDataBtn.setOnClickListener(this);
        mCheckSDCardBtn.setOnClickListener(this);
        mGetFreeSizeBtn.setOnClickListener(this);
    }

    private void initView() {
        mWriteDataBtn = (Button) this.findViewById(R.id.write_data_2_sd_card_btn);
        mCheckSDCardBtn = (Button) this.findViewById(R.id.sd_card_check_btn);
        mGetFreeSizeBtn = (Button) this.findViewById(R.id.get_sd_card_free_size);
    }

    @Override
    public void onClick(View v) {
        if (v == mWriteDataBtn) {
            // 写数据到sd卡上
//            File filePath = new File("/storage/sdcard");
            // api 就是sd卡路径 /storage/sdcard
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            File file = new File(externalStorageDirectory, "info.txt");
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write("AisakaAoi".getBytes());
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (v == mCheckSDCardBtn) {
            String state = Environment.getExternalStorageState();
            if (state.equals(Environment.MEDIA_MOUNTED)) {
                Log.d(TAG, "SD卡已挂载，可用");
            } else if (state.equals(Environment.MEDIA_UNMOUNTED)) {
                Log.d(TAG, "SD卡已卸载，不可用");
            }
        } else if (v == mGetFreeSizeBtn) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            Log.d(TAG, "externalStorageDirectory == " + externalStorageDirectory);
            long freeSpace = externalStorageDirectory.getFreeSpace();
            // 把long转成直观的空间大小 比如KB/MB/GB
            String sizeText = Formatter.formatFileSize(this, freeSpace);
            Log.d(TAG, "free size == " + sizeText);
        }
    }
}
