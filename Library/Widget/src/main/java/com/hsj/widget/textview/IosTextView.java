package com.hsj.widget.textview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @Company     :  北京****科技有限公司
 * @Author      :  HSJ
 * @Version     :  Framework V1.0
 * @Date        :  2017/3/14 19:33
 * @E-mail      :  mr.ajun@foxmail.com
 * @Class       :  IOSTextView
 * @Description :  字体可以自定义风格；但是做android就用android自带字体、拒绝使用ios字体
 */
public class IosTextView extends TextView {

    private Context mContext;

    public IosTextView(Context context) {
        super(context);
        mContext = context;
        setIosTypeface();
    }

    public IosTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setIosTypeface();
    }

    public IosTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        setIosTypeface();
    }

    private void setIosTypeface() {
        Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), "fonts/ios.ttf");
        this.setTypeface(typeface);
    }
}
