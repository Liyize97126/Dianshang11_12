package com.bawei.dianshang11_12;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //定义
    private ImageView dong_animation,dong_animator;
    private HorizontalScrollView hs;
    private Button daojishi;
    private boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dong_animation = findViewById(R.id.dong_animation);
        dong_animator = findViewById(R.id.dong_animator);
        hs = findViewById(R.id.hs);
        daojishi = findViewById(R.id.daojishi);
        //垂直方向的水平滚动条是否显示
        hs.setVerticalScrollBarEnabled(false);
        //水平方向的水平滚动条是否显示
        hs.setHorizontalScrollBarEnabled(false);
        //添加点击事件
        dong_animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this, "提示：这是一个青苹果！", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER,0,-460);
                toast.show();
            }
        });
        dong_animator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this, "提示：这是一个青苹果！", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER,0,-20);
                toast.show();
            }
        });
        //设置点击事件
        daojishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){
                    flag = false;
                    //ValueAnimator：只改变具体的数值，需要配合监听器使用
                    ValueAnimator valueAnimator = ValueAnimator.ofInt(60, 0);
                    //设置持续时间
                    valueAnimator.setDuration(60*1000);
                    //匀速差值器
                    valueAnimator.setInterpolator(new LinearInterpolator());
                    //设置监听事件
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            int animatedValue = (int) animation.getAnimatedValue();
                            if(animatedValue == 0){
                                flag = true;
                                daojishi.setText("开始倒计时");
                            } else {
                                daojishi.setText(animatedValue + " S");
                            }
                        }
                    });
                    //执行
                    valueAnimator.start();
                } else {
                    Toast.makeText(MainActivity.this,"正在倒计时，请等待倒计时结束...",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    //Animation平移动画，不会改变实际位置，点击触摸事件依旧在原始位置上
    public void event01(View view) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 425, 0, 65);
        translateAnimation.setDuration(4000);
        //让动画停留在最后位置
        translateAnimation.setFillEnabled(true);
        translateAnimation.setFillAfter(true);
        dong_animation.startAnimation(translateAnimation);
    }
    /*Animator属性动画：控件改变则只能进行四种动画，使用控件点出四种方法，然后属性拿出来进行改变。
    平移 translationX,translationY,translationZ
    缩放 scaleX,scaleY
    旋转 rotationX,rotationY,rotation
    透明度 alpha*/
    //Animator平移动画，会改变控件实际位置，点击触摸事件在当前控件所在位置
    public void event02(View view) {
        //X轴平移   animatorBtn.setTranslationX();
        ObjectAnimator translationX = ObjectAnimator.ofFloat(dong_animator, "translationX", 0, 425);
        //设置播放时间
        translationX.setDuration(4000);
        //开始播放
        translationX.start();
        //Y轴平移   animatorBtn.setTranslationY();
        ObjectAnimator translationY = ObjectAnimator.ofFloat(dong_animator, "translationY", 0, -65);
        translationY.setDuration(4000);
        translationY.start();
    }
    //Animator旋转动画
    public void event03(View view) {
        //X轴旋转
        ObjectAnimator rotationX = ObjectAnimator.ofFloat(dong_animator, "rotationX", 0, 360);
        rotationX.setDuration(10000);
        rotationX.start();
        //Y轴旋转
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(dong_animator, "rotationY", 0, 360);
        rotationY.setDuration(7500);
        rotationY.start();
    }
    //平移+旋转
    public void event04(View view) {
        //平移
        ObjectAnimator translationX = ObjectAnimator.ofFloat(dong_animator, "translationX", 0, 425);
        translationX.setDuration(4000);
        translationX.start();
        //旋转
        ObjectAnimator rotation = ObjectAnimator.ofFloat(dong_animator, "rotation", 0, 360);
        rotation.setDuration(4000);
        rotation.start();
    }
    //动画集合
    public void event05(View view) {
        ObjectAnimator a01 = ObjectAnimator.ofFloat(dong_animator, "translationX", 0, 235);
        a01.setDuration(3000);
        ObjectAnimator a02 = ObjectAnimator.ofFloat(dong_animator, "rotationX", 0, 360);
        a02.setDuration(6000);
        ObjectAnimator a03 = ObjectAnimator.ofFloat(dong_animator, "translationY", 0, 125,-125,0);
        a03.setDuration(6000);
        //核心：动画集合
        AnimatorSet animatorSet = new AnimatorSet();
        //时间设置（每段动画统一时间设定，如需要不同执行时间，则需要单独设置）
        //animatorSet.setDuration(8000);
        //设置顺序
        animatorSet.play(a02).after(a01).with(a03);
        animatorSet.start();
    }
    //差值器
    /*LinearInterpolator：匀速插值器
    AccelerateInterpolator：加速度差值器
    DecelerateInterpolator：减速度差值器
    AccelerateDecelerateInterpolator：加速减速差值器*/
    public void event06(View view) {
        ObjectAnimator a01 = ObjectAnimator.ofFloat(dong_animator, "translationX", 0, 425, 235);
        a01.setDuration(4000);
        //设置加速差值器
        a01.setInterpolator(new AccelerateInterpolator());
        ObjectAnimator a02 = ObjectAnimator.ofFloat(dong_animator, "rotationX", 0, 360);
        a02.setDuration(8000);
        //设置减速差值器
        a02.setInterpolator(new DecelerateInterpolator());
        ObjectAnimator a03 = ObjectAnimator.ofFloat(dong_animator, "translationY", 0, 125,-125,0);
        a03.setDuration(8000);
        //设置减速差值器
        a03.setInterpolator(new DecelerateInterpolator());
        //核心：动画集合
        AnimatorSet animatorSet = new AnimatorSet();
        //时间设置（每段动画统一时间设定，如需要不同执行时间，则需要单独设置）
        //animatorSet.setDuration(8000);
        //设置顺序
        animatorSet.play(a02).after(a01).with(a03);
        animatorSet.start();
    }
    //去往坐标图Activity
    public void goToZBT(View view) {
        //完成界面切换
        Intent intent = new Intent(this, ZBTActivity.class);
        startActivity(intent);
    }
}
