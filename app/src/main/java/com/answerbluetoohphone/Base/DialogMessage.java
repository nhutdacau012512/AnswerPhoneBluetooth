package com.answerbluetoohphone.Base;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;


import com.answerbluetoohphone.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Minh Nhut on 8/29/2016.
 */
public class DialogMessage extends DialogFragment {

    private boolean isOneButton;
    private boolean isClickOK;
    private String TAG = "DialogMessage";

    @BindView(R.id.dialogMessage_TextView_Title)
    TextView tvTitle;
    @BindView(R.id.dialogMessage_TextView_message)
    TextView tvMessage;
    @BindView(R.id.dialogMessage_Button_Cancel)
    TextView btnCancel;

    @OnClick({R.id.dialogMessage_Button_OK, R.id.dialogMessage_Button_Cancel})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialogMessage_Button_Cancel:
                isClickOK = false;
                break;
            case R.id.dialogMessage_Button_OK:
                isClickOK = true;
                break;
        }
        dismiss();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_message, container, false);
        ButterKnife.bind(this, view);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);

        Bundle bundle = getArguments();
        String message = bundle.getString("message");
        String title = bundle.getString("title");
        isOneButton = bundle.getBoolean("isOneButton");

        tvMessage.setText(message);
        tvTitle.setText(title);
        if (isOneButton) btnCancel.setVisibility(View.GONE);
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new Dialog(getActivity(), getTheme()){
            @Override
            public void onBackPressed() {
                //do your stuff
            }
        };
    }

    @Override
    public void dismiss() {
        super.dismiss();
        Intent intent = new Intent();
        if (!isOneButton) intent.putExtra("ClickOK", isClickOK);
        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
    }
}
