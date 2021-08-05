package com.example.a18_looperpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements MyViewPager.OnViewPagerTouchListener, ViewPager.OnPageChangeListener {

    private MyViewPager mLoopPager;
    private LooperPagerAdapter mLooperPagerAdapter;

//    private static List<Integer> sColors = new ArrayList<>();
    private static List<Integer> sPics = new ArrayList<>();

    static {
        sPics.add(R.mipmap.swiper1);
        sPics.add(R.mipmap.swiper2);
        sPics.add(R.mipmap.swiper3);
    }

    private Handler mHandler;
    private boolean mIsTouch = false;
    private LinearLayout mPointContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        // 准备颜色
//        Random random = new Random();
//        for (int i = 0; i < 5; i++) {
//            sColors.add(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
//        }
        // 给适配器设置数据
//        mLooperPagerAdapter.setData(sColors);
//        mLooperPagerAdapter.notifyDataSetChanged();
        mHandler = new Handler();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mHandler.post(mLoopPagerTask);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHandler.removeCallbacks(mLoopPagerTask);
    }

    private Runnable mLoopPagerTask = new Runnable() {
        @Override
        public void run() {
            if (!mIsTouch) {
                // 切换viewPager的图片到下一张
                int currentItem = mLoopPager.getCurrentItem();
                mLoopPager.setCurrentItem(++currentItem, true);
            }
            mHandler.postDelayed(this, 3000);
        }
    };

    private void initView() {
        mLoopPager = (MyViewPager) this.findViewById(R.id.looper_pager);
        // 设置适配器
        mLooperPagerAdapter = new LooperPagerAdapter();
        mLooperPagerAdapter.setData(sPics);
        mLoopPager.setAdapter(mLooperPagerAdapter);
        mLoopPager.addOnPageChangeListener(this);
        mLoopPager.setOnViewPagerTouchListener(this);
        // 根据图片的个数 去添加点的个数
        mPointContainer = (LinearLayout) this.findViewById(R.id.points_container);
        insertPoint();
        mLoopPager.setCurrentItem(mLooperPagerAdapter.getDataRealSize() * 100, false);
    }

    private void insertPoint() {
        for (int i = 0; i < sPics.size(); i++) {
            View point = new View(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(40, 40);
            layoutParams.leftMargin = 20;
            point.setBackground(getResources().getDrawable(R.drawable.shape_point_normal));
            point.setLayoutParams(layoutParams);
            mPointContainer.addView(point);
        }
    }

    @Override
    public void onPagerTouch(boolean isTouch) {
        mIsTouch = isTouch;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        // 这个方法的调用 其实是viewPager停下来以后选中的位置
        int realPosition;
        if (mLooperPagerAdapter.getDataRealSize() != 0) {
            realPosition = position % mLooperPagerAdapter.getDataRealSize();
        } else {
            realPosition = 0;
        }
        setSelectPoint(realPosition);
    }

    private void setSelectPoint(int realPosition) {
        for (int i = 0; i < mPointContainer.getChildCount(); i++) {
            View point = mPointContainer.getChildAt(i);
            if (i != realPosition) {
                point.setBackgroundResource(R.drawable.shape_point_normal);
            } else {
                point.setBackgroundResource(R.drawable.shape_point_selected);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}