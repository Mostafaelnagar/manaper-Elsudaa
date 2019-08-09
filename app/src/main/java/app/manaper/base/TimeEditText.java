package app.manaper.base;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.util.AttributeSet;

import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

public class TimeEditText extends CustomEditText {
    TimePickerDialog timePickerDialog;

    public TimeEditText(Context context) {
        super(context);
        init();
    }

    public TimeEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TimeEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        setOnClickListener(v -> showDateDialog());
    }

    private void showDateDialog() {
        Calendar c = Calendar.getInstance();
//        int year = mcurrentTime.get(Calendar.YEAR);
//        int month = mcurrentTime.get(Calendar.MONTH) + 1;
//        final TimePickerDialog timePickerDialog
//                = new TimePickerDialog(getContext(), (timePickerDialog, i, i1, i2) -> {
//            String selectedDate = i + "-" + (++i1) + "-" + i2;
//            setText(selectedDate);
//        }, year, month, 0);
//        timePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
//        timePickerDialog.show();

        TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(new com.wdullaer.materialdatetimepicker.time.TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(com.wdullaer.materialdatetimepicker.time.TimePickerDialog view, int hourOfDay, int minute, int second) {
                setText(hourOfDay + ":" + minute);

            }
        }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true);
        timePickerDialog.show(getActivity().getFragmentManager(), "Timepickerdialog"); //show time picker dialog

    }

    public TimePickerDialog getTimePickerDialog() {
        return timePickerDialog;
    }
// TintContextWrapper exc
    private Activity getActivity() {
        Context context = getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }
}
