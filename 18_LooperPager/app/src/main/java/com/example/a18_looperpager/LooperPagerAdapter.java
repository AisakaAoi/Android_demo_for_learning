package com.example.a18_looperpager;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * <pre>
 * author : zongnan.chen
 * time : 2021/08/04
 * </pre>
 */
public class LooperPagerAdapter extends PagerAdapter {

    private List<Integer> mPics = null;

    @Override
    public int getCount() {
        if (mPics != null) {
            return Integer.MAX_VALUE;
        }
        return 0;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int realPosition = position % mPics.size();
        ImageView imageView = new ImageView(container.getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//        imageView.setBackgroundColor(mColors.get(position));
        imageView.setImageResource(mPics.get(realPosition));
        // 设置完数据后 就添加到容器里
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public void setData(List<Integer> colors) {
        this.mPics = colors;
    }

    public int getDataRealSize() {
        if (mPics != null) {
            return mPics.size();
        }
        return 0;
    }
}
