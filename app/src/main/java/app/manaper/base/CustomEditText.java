package app.manaper.base;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;


public class CustomEditText extends BaseEditText {


    public CustomEditText(Context context) {
        super(context);
        init();
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        init();
    }


    private void init() {


        addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence c, int start, int before, int count) {
                if (Validate.isEmpty(c.toString())) {
//                    setError("Empty");
                } else if (getInputType() == InputType.TYPE_CLASS_PHONE && !Validate.isPhone(c.toString())) {
                    setError("Wrong phone");
                } else if (getInputType() == InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS && !Validate.isMail(c.toString())) {
                    setError("Wrong email");
                } else if ((getInputType() == InputType.TYPE_TEXT_VARIATION_PASSWORD || getInputType() == InputType.TYPE_NUMBER_VARIATION_PASSWORD || getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD || getInputType() == InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD) && !Validate.isAvLen(c.toString(), 6, 25)) {
                    setError("wrong password");
                } else {
                    setError(null);
                }
            }

            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
            }

            public void afterTextChanged(Editable c) {
             }
        });
    }


}
