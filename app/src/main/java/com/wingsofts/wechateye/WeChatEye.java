package com.wingsofts.wechateye;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2016/1/12.
 */
public class WeChatEye extends View {

    private Paint mPaint;

    private RectF mRectF;

    //底部的path
    private Path mPath;
    private Path mTopPath;
    //控制全局percent
    private int mPercent;

    public WeChatEye(Context context) {
        this(context,null);
    }

    public WeChatEye(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WeChatEye(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();

        mRectF = new RectF(200,200,250,250);
        mPath = new Path();
        mTopPath = new Path();
    }


    @Override
    protected void onDraw(Canvas canvas) {

        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);


        //小的
        if(mPercent<33) {

            float stroke = mPercent/3f;
            Log.e("wing","st" + stroke);
            if(stroke == 0.0){
                mPaint.setColor(Color.BLACK);
            }else {
                mPaint.setColor(Color.GRAY);
            }
            mPaint.setStrokeWidth(stroke);
            canvas.drawArc(mRectF, 180, 10, false, mPaint);


            canvas.drawArc(mRectF, 205, 25, false, mPaint);
        }else if(mPercent < 66) {

            //画内部
            mPaint.setStrokeWidth(10);
            canvas.drawArc(mRectF, 180, 10, false, mPaint);
            canvas.drawArc(mRectF, 205, 25, false, mPaint);

            //画圆圈
            mPaint.setStrokeWidth(2);
            int alpha = (int) ((mPercent - 33f) / 33f * 255);

            mPaint.setAlpha(alpha);
            canvas.drawCircle(225, 225, 40, mPaint);

        }else {


// 画内部


            //取圆边界-40 +40
//        canvas.drawLine(145,225,305,225,mPaint);

//            画静态上边线
//            mPath.moveTo(145, 225);
//            mPath.quadTo(225, 125, 305, 225);
//            mPath.reset();
//
            mPaint.setStrokeWidth(10);
            canvas.drawArc(mRectF, 180, 10, false, mPaint);
            canvas.drawArc(mRectF, 205, 25, false, mPaint);

            //画圆圈
            mPaint.setStrokeWidth(2);
            canvas.drawCircle(225, 225, 40, mPaint);
            canvas.drawPath(mPath, mPaint);

            float percent = (mPercent-66)*3f/100;
            Log.e("wing",percent+"");

            if(percent<0.3){
                mPaint.setAlpha(0);

            }else if(percent<0.99){
                mPaint.setAlpha((int) (255*percent));
            }

            else {
                mPaint.setColor(Color.WHITE);
                mPaint.setStrokeWidth(10);
                canvas.drawArc(mRectF, 180, 10, false, mPaint);
                canvas.drawArc(mRectF, 205, 25, false, mPaint);

                //画圆圈
                mPaint.setStrokeWidth(2);
                canvas.drawCircle(225, 225, 40, mPaint);
                canvas.drawPath(mPath, mPaint);
            }




//
            float mStartX = 225 -(225-115)*percent;
            float mEndX = 225+ (335-225)*percent;
            mTopPath.moveTo(mStartX ,175+(225-175)*percent);
//            Log.e("wing","start:"+(225 -(225-145)*percent) + " y:"+125+(225-125)*percent);
            mTopPath.quadTo(225, 175-50*percent, mEndX, 175+(225-175)*percent );

            canvas.drawPath(mTopPath, mPaint);
            mTopPath.reset();
//
//            //画静态下边线
////            mPath.moveTo(145, 225);
////            mPath.quadTo(225, 325, 305, 225);
////            canvas.drawPath(mPath, mPaint);
//
            mPath.moveTo(mStartX ,275-(275-225)*percent);
            mPath.quadTo(225,  275+50*percent, mEndX , 275-(275-225)*percent );
            canvas.drawPath(mPath,mPaint);

            mPath.reset();
////

        }
    }

    public void setPercent(int percent){
        mPercent = percent;
        invalidate();
    }
}
