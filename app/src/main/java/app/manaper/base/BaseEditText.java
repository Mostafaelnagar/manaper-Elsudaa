package app.manaper.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

import app.manaper.R;

public class BaseEditText extends AppCompatEditText {


    public BaseEditText(Context context) {
        super(context);

    }

    public BaseEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public BaseEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BaseEditText, defStyleAttr, 0);
        typedArray.getString(R.styleable.BaseEditText_error);

        typedArray.recycle();


    }

    @BindingAdapter(value = "errorAttrChanged")
    public static void setListener(CustomEditText errorInputLayout, final InverseBindingListener textAttrChanged) {
        if (textAttrChanged != null) {
            errorInputLayout.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                    textAttrChanged.onChange();
                }
            });
        }
    }

    @BindingAdapter("error")
    public static void setError(CustomEditText view, String value) {
        if(value==null||value.equals("null"))value=null;
            view.setError(value);
    }

    @InverseBindingAdapter(attribute = "error")
    public static String getError(CustomEditText errorInputLayout) {
        return ""+errorInputLayout.getError();
    }
}
