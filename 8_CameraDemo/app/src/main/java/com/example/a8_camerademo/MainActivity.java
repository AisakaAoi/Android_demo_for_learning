package com.example.a8_camerademo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView mTakePhotoBtn;
    private ImageView mResultContainer;

    private static final int REQUEST_CODE_FOR_PIC = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initListener() {
        mTakePhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击后跳转到系统的相机界面
                Intent intent = new Intent();
                intent.setAction("android.media.action.IMAGE_CAPTURE");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                startActivityForResult(intent, REQUEST_CODE_FOR_PIC);
            }
        });
    }

    private void initView() {
        mResultContainer = (ImageView) this.findViewById(R.id.photo_result_container);
        mTakePhotoBtn = (ImageView) this.findViewById(R.id.take_photo);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_FOR_PIC) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                Bitmap result = data.getParcelableExtra("data");
                if (result != null) {
                    mResultContainer.setImageBitmap(result);
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "您取消了拍照！", Toast.LENGTH_SHORT).show();
            }
        }
    }
}