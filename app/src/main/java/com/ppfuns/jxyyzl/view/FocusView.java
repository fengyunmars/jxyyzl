package com.ppfuns.jxyyzl.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ppfuns.jxyyzl.R;

/**
 * Created by flyzebra on 17-12-11.
 */

public class FocusView extends FrameLayout{
    private ImageView imageView;
    public FocusView(@NonNull Context context) {
        this(context,null);
    }

    public FocusView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FocusView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(R.drawable.mainfocus);
        addView(imageView);
        imageView.setVisibility(INVISIBLE);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        if(gainFocus){
            animate().scaleX(1.1f).scaleY(1.1f).setDuration(300).start();
            imageView.setVisibility(VISIBLE);
        }else {
            animate().scaleX(1.0f).scaleY(1.0f).setDuration(300).start();
            imageView.setVisibility(INVISIBLE);
        }

    }
}
