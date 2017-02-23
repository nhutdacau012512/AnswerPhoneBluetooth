package com.answerbluetoohphone.Base;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.answerbluetoohphone.R;

/**
 * Created by MinhNhut on 2/22/17.
 */

public class Utility {
    public static void showDialogMessage(BaseFragment fragment, String message,
                                         String title, int requestCode, boolean isOneButton) {
        DialogFragment picker = new DialogMessage();
        picker.setTargetFragment(fragment, requestCode);
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        bundle.putString("title", title);
        bundle.putBoolean("isOneButton", isOneButton);
        picker.setArguments(bundle);
        picker.show(fragment.getFragmentManager().beginTransaction(), "Popup Message");
    }
}
