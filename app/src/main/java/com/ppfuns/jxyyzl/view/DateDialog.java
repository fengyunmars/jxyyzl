package com.ppfuns.jxyyzl.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Author: Fly
 * Time: 17-12-31 下午2:19.
 * Discription: This is DateDialog
 */

public class DateDialog extends Dialog {
    private SelectDateView selectDateView;

    public DateDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectDateView = new SelectDateView(getContext());
        setContentView(selectDateView);
    }
}
