package view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.xiaoyaoprefecture.firstday.R;

/**
 * Created by Administrator on 2017/3/19.
 */

public class MyView extends View implements View.OnTouchListener ,
        View.OnLongClickListener,View.OnClickListener{
    private String text;
    private String color;
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if(context instanceof MyClickLisener){
            myClickLisener= (MyClickLisener) context;
        }
        //获取自定义属性的值
        /*//先获取自定义属性的个数
        int count=attrs.getAttributeCount();
        for(int i=0;i<count;i++){
            Log.e("------名字："+attrs.getAttributeName(i),"------值："+attrs.getAttributeValue(i));

        }*/
     TypedArray typedArray= context.obtainStyledAttributes(attrs, R.styleable.xiaoyao);
        text=typedArray.getString(R.styleable.xiaoyao_xiaoyao_name);
        color=typedArray.getString(R.styleable.xiaoyao_xiaoyao_color);
        typedArray.recycle();
        setLongClickable(true);
        setOnClickListener(this);
        setOnLongClickListener(this);
        setOnTouchListener(this);
    }

    /**
     * 绘制控件的样子
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //弄个笔出来
        Paint mPaint=new Paint();
        //给笔弄点颜色
        if(color.equals("红色")){
            mPaint.setColor(Color.RED);
        }else if (color.equals("蓝色")){
            mPaint.setColor(Color.BLUE);
        }else{
            mPaint.setColor(Color.YELLOW);
        }
        //设置一下抗锯齿（让绘画的图形圆滑一点）
        mPaint.setAntiAlias(true);
        //开始画控件了
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
        //把文字弄上去
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(40);
        canvas.drawText(text,(getWidth()-(text.length()*50))/2,getHeight()/2,mPaint);
    }

    /**
     * 测量尺寸
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //首先设置默认的宽和高
        int defaultWidth=200;
        int defaultHeight=100;
        //获取测量模式
        int WidthMode=MeasureSpec.getMode(widthMeasureSpec);
        int HeightMode=MeasureSpec.getMode(heightMeasureSpec);
        //获取宽和高的值（父控件建议的）
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heigthSize=MeasureSpec.getSize(heightMeasureSpec);

        //首先给定义一个宽和高最终值得变量
        int finalWidthSize=-1;
        int finalHeightSize=-1;
        //获取宽的最终计算结果
        switch (WidthMode){
            case MeasureSpec.AT_MOST://这个表示warp_content
                finalWidthSize=Math.min(defaultWidth,widthSize);
                break;
            case MeasureSpec.EXACTLY://这个表示match_parent
                finalWidthSize=widthSize;
                break;
        }
        //然后获取高的最终计算结果
        switch (HeightMode){
            case MeasureSpec.AT_MOST:
                finalHeightSize=Math.min(defaultHeight,heigthSize);
                break;
            case MeasureSpec.EXACTLY:
                finalHeightSize=heigthSize;
                break;
        }
        int textWidth=text.length()*50;
        finalWidthSize+=textWidth;
        setMeasuredDimension(finalWidthSize,finalHeightSize);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                color="蓝色";
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                color="红色";
                invalidate();
                break;
        }

        return false;
    }
    //自定义一个回调
    public interface MyClickLisener{
        public void onClick(View v);
        public void onLongClick(View v);
    }
    MyClickLisener myClickLisener=null;
    //写个方法来注册这个回调监听
    public void setonLisener(MyClickLisener myClickLisener){
        this.myClickLisener=myClickLisener;
    }
    @Override
    public boolean onLongClick(View v) {
        myClickLisener.onLongClick(v);
        return true;
    }

    @Override
    public void onClick(View v) {
        myClickLisener.onClick(v);
    }
}
