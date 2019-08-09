package app.manaper.base;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

import app.manaper.base.constantsutils.Params;

public class DateEditText extends CustomEditText {
    DatePickerDialog datePickerDialog;

    public DateEditText(Context context) {
        super(context);
        init();
    }

    public DateEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DateEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        setOnClickListener(v -> showDateDialog());
        Log.i("init", "init: clicked");
    }

    private void showDateDialog() {
        Params params = new Params();
        if (datePickerDialog == null) {
            Calendar mcurrentTime = Calendar.getInstance();
            int year = mcurrentTime.get(Calendar.YEAR);
            int month = mcurrentTime.get(Calendar.MONTH) + 1;
            datePickerDialog
                    = new DatePickerDialog(getContext(), (datePicker, i, i1, i2) -> {
                String selectedDate = i + "-" + (++i1) + "-" + i2;
//                Log.i("showDateDialog", "showDateDialog: " + params.from_step);
//                if (params.step == 1) {
//                    params.arrDate = selectedDate;
//                } else if (params.step == 2) {
//                    if (params.from_step == 1) {
//                        params.firDateFrom = selectedDate;
//                     } else
//                        params.firDateTo = selectedDate;
//                } else if (params.step == 3) {
//                    if (params.to_step == 1) {
//                        params.secDateFrom = selectedDate;
//                        params.to_step = 2;
//                    } else
//                        params.secDateto = selectedDate;
//                } else if (params.step == 4) {
//                    params.depDate = selectedDate;
//                }
//                String result = MovementManager.dateValidation(selectedDate);
//                if (result != null) {
//                    setText("");
//                    Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
//                } else {
                setText(selectedDate);

//                }

//                Log.i("showDateDialog", "showDateDialog: " + result);
            }, year, month, 0);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        }
        datePickerDialog.show();

    }

    public DatePickerDialog getDatePickerDialog() {
        return datePickerDialog;
    }
}
