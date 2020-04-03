package com.bawei.dianshang11_12;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 坐标图
 */
public class ZBTView extends FrameLayout {
    //定义
    private Paint linePaint;
    private Paint textPaint;
    //方法实现
    public ZBTView(@NonNull Context context) {
        super(context);
        init();
    }
    public ZBTView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public ZBTView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    //初始化
    private void init(){
        //绘制直线
        linePaint = new Paint();
        linePaint.setColor(Color.parseColor("#0000FF"));
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(5);
        //绘制文字
        textPaint = new Paint();
        textPaint.setTextSize(35);
        textPaint.setStrokeWidth(2);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setColor(Color.parseColor("#FF0000"));
    }
    //绘图方法
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画坐标轴线
        canvas.drawLine(100,1000,675,1000,linePaint);//X轴
        canvas.drawLine(100,1000,100,35,linePaint);//Y轴
        //箭头
        //Y轴
        canvas.drawLine(100,35,90,75,linePaint);
        canvas.drawLine(100,35,110,75,linePaint);
        //X轴
        canvas.drawLine(675,1000,635,990,linePaint);
        canvas.drawLine(675,1000,635,1010,linePaint);
        //画坐标点
        //Y轴
        for (int i = 0; i < 10; i++) {
            String text = String.valueOf(i * 100);
            /*开始绘制文本
            公式为：文字x轴坐标=默认坐标-文字长度-文字和坐标线的边距
            Paint可以使用measureText(文本)方法测量文本的宽度*/
            canvas.drawText(text,100-textPaint.measureText(text)-20,1000-i*100+textPaint.getTextSize()/2,textPaint);
            //短线
            canvas.drawLine(100,1000-i*100,110,1000-i*100,linePaint);
        }
        //X轴
        for (int i = 1; i < 6; i++) {
            String text = i + "月";
            //开始绘制文本
            canvas.drawText(text,100 + i*100-textPaint.measureText(text)/2,1000+textPaint.getTextSize() + 10,textPaint);
            //短线
            canvas.drawLine(100+i*100,1000,100+i*100,1000-10,linePaint);
        }
        //数据表示
        Path path = new Path();
        path.moveTo(200,700);
        path.quadTo(200,700,300,500);
        path.quadTo(300,500,400,800);
        path.quadTo(400,800,500,400);
        path.quadTo(500,400,600,350);
        canvas.drawPath(path,linePaint);
    }
}
