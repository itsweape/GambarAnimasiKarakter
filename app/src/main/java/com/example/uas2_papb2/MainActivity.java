package com.example.uas2_papb2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView mImgView;
    Bitmap mBitmap;
    Canvas mCanvas;
    private int mColorBackground;
    Paint mCirclePaint = new Paint();
    Paint mHeadPaint = new Paint();
    int vWidth, vHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImgView = findViewById(R.id.my_img_view);
        mCirclePaint.setColor(getResources().getColor(R.color.black));
        mHeadPaint.setColor(getResources().getColor(R.color.white));

        mImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator showAnimator = ObjectAnimator.ofFloat(mImgView, "alpha", 1);
                showAnimator.setDuration(2000);
                showAnimator.start();

                ObjectAnimator rotationAnimation = ObjectAnimator.ofFloat(mImgView, "rotationY", 180);
                rotationAnimation.setStartDelay(2000);
                rotationAnimation.setDuration(2000);
                rotationAnimation.start();

                ObjectAnimator hiddenAnimation = ObjectAnimator.ofFloat(mImgView, "alpha", 0);
                hiddenAnimation.setStartDelay(4000);
                hiddenAnimation.setDuration(2000);
                hiddenAnimation.start();
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        vWidth = mImgView.getWidth();
        vHeight = mImgView.getHeight();

        mBitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);
        mImgView.setImageBitmap(mBitmap);
        mColorBackground = ResourcesCompat.getColor(getResources(), R.color.yellow, null);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(mColorBackground);

        drawHead();
        drawRightEye();
        drawLeftEye();
        drawEyeConnector();
    }

    private void drawHead() {
        mCanvas.drawOval(new RectF(vWidth / 2 - 230, vHeight / 2 - 170,
                vWidth / 2 + 230, vHeight / 2 + 170), mHeadPaint);
    }

    private void drawRightEye() {
        mCanvas.drawCircle(vWidth / 2 + 120, vHeight / 2,
                vHeight / 40, mCirclePaint);
    }

    private void drawLeftEye() {
        mCanvas.drawCircle(vWidth / 2 - 120, vHeight / 2,
                vHeight / 40, mCirclePaint);
    }

    private void drawEyeConnector() {
        mCanvas.drawRect(vWidth / 2 - 100, vHeight / 2 - 8,
                vWidth / 2 + 100, vHeight / 2 + 8, mCirclePaint);
    }
}