package app.manaper;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;


public class CustomLookAtMe extends VideoView {
    public CustomLookAtMe(Context context) {
        super(context);
    }

    public CustomLookAtMe(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLookAtMe(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }
}