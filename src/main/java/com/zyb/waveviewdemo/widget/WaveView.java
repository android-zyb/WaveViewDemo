package com.zyb.waveviewdemo.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.zyb.waveviewdemo.R;

/**
 *  @Description: 
 *  @Author:  zyb
 *  @Date:  2017/12/14
 *  @Version: 1.0
 */
public class WaveView extends View {
    
    private Path xPathMain;
    private Path xPathMinor;
    private Paint xPaintMain;    // 淡化的主线条
    private Paint xPaintMinor;   // 主线条
    private int orginY;
    
    private int alphaMain = 255; // 透明值
    private int alphaMinor = 255; // 透明值
    private int mWaveWidth; //波浪X轴偏移量
    private int mWaveHeight; //波浪Y轴偏移量
    private int offsetAnimator; //动画偏移量
    private int paddingBottom = 30;  // 距离父容器底部的距离
    private int durationTime = 4000; //动画播放时间，时间越长波动越慢
    private ValueAnimator mAnimator;
    
    public WaveView (Context context) {
        super(context);
    }
    
    public WaveView (Context context, AttributeSet attrs) {
        super(context, attrs);
        xPaintMain = new Paint();
        xPathMain = new Path();
        
        xPaintMinor = new Paint();
        xPathMinor = new Path();
        
        xPaintMain.setStrokeWidth(10);
        xPaintMinor.setStrokeWidth(2);
        xPaintMain.setAntiAlias(true);//抗锯齿功能
        xPaintMinor.setAntiAlias(true);//抗锯齿功能
        
        xPaintMain.setStyle(Paint.Style.FILL_AND_STROKE);
        xPaintMinor.setStyle(Paint.Style.FILL_AND_STROKE);
        
        //淡化的次线条设置为透明的波浪
        xPaintMain.setColor(Color.parseColor("#f1c351"));
        //主线条设置为背景色
        xPaintMinor.setColor(Color.parseColor("#eba703"));
        
        xPaintMain.setAlpha(alphaMain);
        xPaintMinor.setAlpha(alphaMinor);
        setBackgroundColor(Color.parseColor("#ffffff"));
        
        mWaveWidth = 1800;
        mWaveHeight = 50;
        orginY = 5;
        offsetAnimator = 0;
    }
    
    /**
     * 设置波浪的颜色透明度 ， 范围为0-255 否则无效
     *
     * @param alphaMinor
     */
    public void setAlphaMinor (int alphaMinor) {
        this.alphaMinor = alphaMinor;
        if (alphaMinor >= 0 && alphaMinor <= 255) {
            xPaintMinor.setAlpha(alphaMinor);
        }
    }
    
    /**
     * 设置淡化的主波浪的颜色透明度 ，范围为0-255 否则无效
     *
     * @param alphaMain
     */
    public void setAlphaMain (int alphaMain) {
        this.alphaMain = alphaMain;
        if (alphaMain >= 0 && alphaMain <= 255) {
            xPaintMain.setAlpha(alphaMain);
        }
    }
    
    /**
     * 同时设置主次波浪的颜色的透明度 ，范围为0-255 否则无效
     *
     * @param alphaMain 主波浪的颜色透明度
     * @param alphaMinor 次波浪的颜色透明度
     */
    public void setAlphaWave (int alphaMain, int alphaMinor) {
        this.alphaMinor = alphaMinor;
        this.alphaMain = alphaMain;
        if (alphaMinor >= 0 && alphaMinor <= 255) {
            xPaintMinor.setAlpha(alphaMinor);
        }
        if (alphaMain >= 0 && alphaMain <= 255) {
            xPaintMain.setAlpha(alphaMain);
        }
    }
    
    /**
     * 设置主次波浪颜色
     *
     * @param mainColor 主波浪颜色
     * @param minorColor 次波浪颜色
     */
    public void setxColor (int mainColor, int minorColor) {
        xPaintMain.setColor(mainColor);
        xPaintMinor.setColor(minorColor);
    }
    
    /**
     * 设置波浪的x轴幅度
     * 一上一下两个弧度的宽度 （默认为1800）
     *
     * @param waveWidth
     */
    public void setWaveWidth (int waveWidth) {
        this.mWaveWidth = waveWidth;
    }
    
    public int getWaveHeight () {
        return mWaveHeight;
    }
    
    /**
     * 设置波浪的Y轴的波动幅度  （默认为50）
     *
     * @param waveHeight
     */
    public void setWaveHeight (int waveHeight) {
        this.mWaveHeight = waveHeight;
    }
    
    public int getDurationTime () {
        return durationTime;
    }
    
    /**
     * 设置波浪的流动时间，以毫秒为单位  值越大，变换的速度越慢
     *
     * @param durationTime
     */
    public void setDurationTime (int durationTime) {
        this.durationTime = durationTime;
    }
    
    @Override
    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);
        xPathMain.reset();
        xPathMinor.reset();
        xPathMain.moveTo(- mWaveWidth + offsetAnimator,
            getHeight() - orginY - paddingBottom); // 起始点
        xPathMinor.moveTo(- mWaveWidth + offsetAnimator,
            getHeight() - orginY - paddingBottom + 1); // 起始点
        for (int i = 0; i <= getWidth() + mWaveWidth * 2; i += mWaveWidth) {
            xPathMain.rQuadTo(mWaveWidth / 4, mWaveHeight, mWaveWidth / 2, 0);
            xPathMain.rQuadTo(mWaveWidth / 4, - mWaveHeight, mWaveWidth / 2, 0);
            
            xPathMinor.rQuadTo(mWaveWidth / 8 * 2 / 3, - mWaveHeight / 2, mWaveWidth / 4, 0);
            xPathMinor.rQuadTo(mWaveWidth / 8 * 2 / 3, mWaveHeight / 3, mWaveWidth / 4, 0);
            xPathMinor.rQuadTo(mWaveWidth / 4, - mWaveHeight, mWaveWidth / 2, 0);
        }
        
        xPathMain.lineTo(getWidth(), 0);
        xPathMain.lineTo(0, 0);
        xPathMain.close();
        
        xPathMinor.lineTo(getWidth(), 0);
        xPathMinor.lineTo(0, 0);
        xPathMinor.close();
        
        canvas.drawPath(xPathMain, xPaintMain);
        canvas.drawPath(xPathMinor, xPaintMinor);
    }
    
    /**
     * 开启动画
     */
    public void startWave () {
        mAnimator = ValueAnimator.ofInt(0, mWaveWidth);
        mAnimator.setDuration(durationTime);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            
            @Override
            public void onAnimationUpdate (ValueAnimator animation) {
                offsetAnimator = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        mAnimator.start();
    }
    
    /***
     * 停止播放动画
     */
    public void stopWave () {
        if (mAnimator != null) {
            mAnimator.cancel();
        }
    }
}
