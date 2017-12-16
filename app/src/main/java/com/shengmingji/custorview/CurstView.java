package com.shengmingji.custorview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class CurstView extends View {
    private String mExampleString; // TODO: use a default from R.string...
    private int mExampleColor = Color.RED; // TODO: use a default from R.color...
    private float mExampleDimension = 0; // TODO: use a default from R.dimen...

    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;
    private Drawable drawable;
    private int height;
    private int width;
    private Paint paint;

    public CurstView(Context context) {
        super(context);
        init(null, 0);
    }

    public CurstView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CurstView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.CurstView, defStyle, 0);

        mExampleString = a.getString(
                R.styleable.CurstView_exampleString);
        mExampleColor = a.getColor(
                R.styleable.CurstView_exampleColor,
                mExampleColor);
        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        mExampleDimension = a.getDimension(
                R.styleable.CurstView_exampleDimension,
                mExampleDimension);

        a.recycle();

        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();


        paint = new Paint();

        TypedArray typedArray = getContext()
                .obtainStyledAttributes(attrs, R.styleable.CurstImageView, defStyle, 0);
        drawable = typedArray.getDrawable(R.styleable.CurstImageView_src);
        measureDrawable();
        typedArray.recycle();
    }

    private void measureDrawable() {
        height = drawable.getIntrinsicHeight();
        width = drawable.getIntrinsicWidth();
    }

    private void invalidateTextPaintAndMeasurements() {
        mTextPaint.setTextSize(mExampleDimension);
        mTextPaint.setColor(mExampleColor);
        mTextWidth = mTextPaint.measureText(mExampleString);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widMode = MeasureSpec.getMode(widthMeasureSpec);
        int widSize = MeasureSpec.getSize(widthMeasureSpec);
        int heiMode = MeasureSpec.getMode(heightMeasureSpec);
        int heiSize = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(measureWid(widMode, widSize), measureHei(heiMode, heiSize));
    }

    private int measureHei(int heiMode, int heiSize) {
        switch (heiMode) {
            case MeasureSpec.EXACTLY:
                height = heiSize;
                break;
            default:
                break;
        }
        return height;
    }

    private int measureWid(int widMode, int widSize) {
        switch (widMode) {
            case MeasureSpec.EXACTLY:
                width = widSize;
                break;
            default:
                break;
        }
        return width;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        // Draw the text.
//        canvas.drawText(mExampleString,
//                paddingLeft + (contentWidth - mTextWidth) / 2,
//                paddingTop + (contentHeight + mTextHeight) / 2,
//                mTextPaint);
        BitmapDrawable bd = (BitmapDrawable) drawable;
        paint.setAntiAlias(true);

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bd.getBitmap(), getMeasuredWidth(), getMeasuredHeight(), true);
        canvas.drawBitmap(scaledBitmap, getLeft(), getTop(), paint);

        canvas.save();
        canvas.rotate(90);
        paint.setColor(Color.GREEN);
        paint.setTextSize(50);
        canvas.drawText("hahahhshshsaha",200,-300,paint);
        canvas.restore();
    }

    /**
     * Gets the example string attribute value.
     *
     * @return The example string attribute value.
     */
    public String getExampleString() {
        return mExampleString;
    }

    /**
     * Sets the view's example string attribute value. In the example view, this string
     * is the text to draw.
     *
     * @param exampleString The example string attribute value to use.
     */
    public void setExampleString(String exampleString) {
        mExampleString = exampleString;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example color attribute value.
     *
     * @return The example color attribute value.
     */
    public int getExampleColor() {
        return mExampleColor;
    }

    /**
     * Sets the view's example color attribute value. In the example view, this color
     * is the font color.
     *
     * @param exampleColor The example color attribute value to use.
     */
    public void setExampleColor(int exampleColor) {
        mExampleColor = exampleColor;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example dimension attribute value.
     *
     * @return The example dimension attribute value.
     */
    public float getExampleDimension() {
        return mExampleDimension;
    }

    /**
     * Sets the view's example dimension attribute value. In the example view, this dimension
     * is the font size.
     *
     * @param exampleDimension The example dimension attribute value to use.
     */
    public void setExampleDimension(float exampleDimension) {
        mExampleDimension = exampleDimension;
        invalidateTextPaintAndMeasurements();
    }

}
