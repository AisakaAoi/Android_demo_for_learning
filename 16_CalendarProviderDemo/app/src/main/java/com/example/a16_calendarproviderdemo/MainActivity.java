package com.example.a16_calendarproviderdemo;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;

import java.util.Calendar;
import java.util.TimeZone;

@RequiresApi(api = Build.VERSION_CODES.M)
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkCalendarPermission();
        }
        queryCalendars();
    }

    // 向日历里添加提醒事件
    public void addAlertEvent(View view) {
        // MainActivity: _id == 1
        long calID = 1;
        Calendar beginTime = Calendar.getInstance();
        // 年/月（从0开始）/日/时/分
        beginTime.set(2019, 10, 11, 0, 0);
        long beginTimeMills = beginTime.getTimeInMillis();
        // 结束时间
        Calendar endTime = Calendar.getInstance();
        beginTime.set(2019, 10, 11, 23, 59);
        long endTimeMills = endTime.getTimeInMillis();
        // 事件内容
        String timeZone = TimeZone.getDefault().getID();
        Log.d(TAG, "timeZone -> " + timeZone);
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.DTSTART, beginTimeMills);
        values.put(CalendarContract.Events.DTEND, endTimeMills);
        values.put(CalendarContract.Events.CALENDAR_ID, calID);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, timeZone);
        values.put(CalendarContract.Events.TITLE, "这个是标题");
        values.put(CalendarContract.Events.DESCRIPTION, "这个是描述");
        values.put(CalendarContract.Events.EVENT_LOCATION, "深圳");
        // 插入数据
        Uri uri = CalendarContract.Events.CONTENT_URI;
        ContentResolver contentResolver = getContentResolver();
        Uri resultUri = contentResolver.insert(uri, values);
        Log.d(TAG, "resultUri -> " + resultUri);
    }

    private void checkCalendarPermission() {
        int readPermission = checkSelfPermission(Manifest.permission.READ_CALENDAR);
        int writePermission = checkSelfPermission(Manifest.permission.WRITE_CALENDAR);
        if (readPermission == PackageManager.PERMISSION_GRANTED) {
            // 表示有权限
        } else {
            Log.d(TAG, "requestPermission...");
            // 去获取权限
            requestPermissions(new String[]{Manifest.permission.READ_CALENDAR,Manifest.permission.WRITE_CALENDAR}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @org.jetbrains.annotations.NotNull String[] permissions, @NonNull @org.jetbrains.annotations.NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            // 判断结果
            if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "has permission...");
                // 有权限
            } else {
                Log.d(TAG, "no permission...");
                // 没权限
                finish();
            }
        }
    }

    private void queryCalendars() {
        ContentResolver contentResolver = getContentResolver();
//        Uri uri = Uri.parse("content://" + "com.android.calendar/" + "calendars");
        Uri uri = CalendarContract.Calendars.CONTENT_URI;
        Cursor query = contentResolver.query(uri, null, null, null, null);
        String[] columnNames = query.getColumnNames();
        while (query.moveToNext()) {
            Log.d(TAG, "===========================");
            for (String columnName : columnNames) {
                Log.d(TAG, columnName + " ==== " + query.getString(query.getColumnIndex(columnName)));
            }
            Log.d(TAG, "===========================");
        }
    }
}