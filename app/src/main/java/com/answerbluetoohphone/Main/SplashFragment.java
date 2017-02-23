package com.answerbluetoohphone.Main;

import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.answerbluetoohphone.Base.BaseFragment;
import com.answerbluetoohphone.Base.Utility;
import com.answerbluetoohphone.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * Created by MinhNhut on 2/22/17.
 *
 */

public class SplashFragment extends BaseFragment {

    private final int BLUETOOTH_NOT_SUPPORT = -1;
    private final int BLUETOOTH_OFF = 0;
    private final int BLUETOOTH_ON = 1;
    private final int OPEN_SETTING = 2;


    private String TAG = "SplashFragment";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Observable.timer(1000, TimeUnit.MILLISECONDS).subscribe(aLong -> checkBluetooth());

    }

    public void checkBluetooth() {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
            Utility.showDialogMessage(this, "Bluetooth is not support! \n Click OK to exit application!",
                    "Message", BLUETOOTH_NOT_SUPPORT, true);
        } else {
            if (!mBluetoothAdapter.isEnabled()) {
                // Bluetooth is not enable :)
                Utility.showDialogMessage(this,
                        "Bluetooth is not enable. Do you want turn on it?", "Message", BLUETOOTH_OFF, false);
            }
            else {
                Utility.showDialogMessage(this, "Bluetooth is enable.", "Message", BLUETOOTH_ON, true);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case BLUETOOTH_NOT_SUPPORT:
                Log.e(TAG, "Not support");
                getActivity().finish();
                System.exit(0);
                break;
            case BLUETOOTH_ON:
                Log.e(TAG, "Turn on");
                break;
            case BLUETOOTH_OFF:
                Log.e(TAG, "Turn off");
                boolean clickOK = false;
                if (data != null) {
                    clickOK = data.getBooleanExtra("ClickOK", false);
                }
                if (clickOK) {
                    Intent intentOpenBluetoothSettings = new Intent();
                    intentOpenBluetoothSettings.setAction(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS);
                    startActivityForResult(intentOpenBluetoothSettings, OPEN_SETTING);
                } else {
                    Observable.timer(1000, TimeUnit.MILLISECONDS).subscribe(aLong -> checkBluetooth());
                }

                break;
            default:
                Log.e(TAG, "DEFAULT");
                Observable.timer(1000, TimeUnit.MILLISECONDS).subscribe(aLong -> checkBluetooth());
                break;
        }
    }

}
