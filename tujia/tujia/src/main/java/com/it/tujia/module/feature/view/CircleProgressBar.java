package com.it.tujia.module.feature.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
/**
 * 自定义环形进度条
 *
 * Created by JunShen
 */
public class CircleProgressBar extends View {

    private int maxProgress = 100;
    private float progress = 2;
    private int progressStrokeWidth = 3;
    private int marxArcStrokeWidth = 3;

    // 画圆所在的距形区域
    RectF oval;
    Paint paint;

    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        oval = new RectF();
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = this.getWidth();
        int height = this.getHeight();

        width = (width > height) ? height : width;
        height = (width > height) ? height : width;

        paint.setAntiAlias(true); // 设置画笔抗锯齿
        paint.setColor(Color.WHITE); // 设置画笔颜色
        canvas.drawColor(Color.TRANSPARENT); // 白色背景
        paint.setStrokeWidth(progressStrokeWidth); // 线宽
        paint.setStyle(Style.STROKE);

        oval.left = marxArcStrokeWidth / 2; // 左上角x
        oval.top = marxArcStrokeWidth / 2; // 左上角y
        oval.right = width - marxArcStrokeWidth / 2; // 左下角x
        oval.bottom = height - marxArcStrokeWidth / 2; // 右下角y

        canvas.drawArc(oval, -90, 360, false, paint); // 绘制进度条背景
        paint.setColor(Color.rgb(0x57, 0x87, 0xb6));
        paint.setStrokeWidth(marxArcStrokeWidth);
        canvas.drawArc(oval, -90, ((float) progress / maxProgress) * 360,
                false, paint); // 绘制进度圆弧，蓝色

        paint.setStrokeWidth(1);
        String text = "请稍候..."; //进度文字设置
        int textHeight = height / 4;
//        paint.setTextSize(textHeight);
        paint.setTextSize(25);
        int textWidth = (int) paint.measureText(text, 0, text.length());
        paint.setStyle(Style.FILL);
        canvas.drawText(text, width / 2 - textWidth / 2, height / 2
                + textHeight / 2, paint);

    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }


    private float startProgress = 0.0f;

    /**
     * 设置无标识进度
     *
     */
    public void doDefaultProgress() {
        startProgress+=3.0f;
        if (startProgress<=100.0f){
            this.progress = startProgress;
            this.invalidate();
        }else {
            startProgress =0.0f;
            this.invalidate();
        }

    }

    /**
     * 设置无标识进度
     *
     * @param bytesWritten 已加载的数据
     * @param totalSize    数据长度
     */
    public void setProgress(long bytesWritten, long totalSize) {
        this.progress = ((float) bytesWritten / (float) totalSize) * 100.0f;
        this.invalidate();
    }


    /**
     * 设置带标识进度
     *
     * @param progress 进度百分比
     * @param view     标识进度的节点视图
     */
    public void setProgress(int progress, View view) {
        this.progress = progress;
        if (view != null) {
            view.setAnimation(pointRotationAnima(0,
                    (int) (((float) 360 / maxProgress) * progress)));
        }
        this.invalidate();
    }

    /**
     * 非ＵＩ线程调用
     */
    public void setProgressNotInUiThread(int progress, View view) {
        this.progress = progress;
        if (view != null) {
            view.setAnimation(pointRotationAnima(0,
                    (int) (((float) 360 / maxProgress) * progress)));
        }
        this.postInvalidate();
    }

    /**
     * 进度标注点的动画
     *
     * @param fromDegrees
     * @param toDegrees
     * @return
     */
    private Animation pointRotationAnima(float fromDegrees, float toDegrees) {
        int initDegress = 306;// 进度点起始位置(图片偏移约54度)
        RotateAnimation animation = new RotateAnimation(fromDegrees,
                initDegress + toDegrees, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1);// 设置动画执行时间
        animation.setRepeatCount(1);// 设置重复执行次数
        animation.setFillAfter(true);// 设置动画结束后是否停留在结束位置
        return animation;
    }

}