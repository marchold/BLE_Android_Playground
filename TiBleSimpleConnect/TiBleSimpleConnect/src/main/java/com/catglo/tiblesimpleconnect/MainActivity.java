package com.catglo.tiblesimpleconnect;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends Activity {
    static final public String TAG = "MARC";
    private static BluetoothManager bluetoothManager;
    private BluetoothAdapter bluetoothAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Use this check to determine whether BLE is supported on the device. Then
        // you can selectively disable BLE-related features.
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Log.e(TAG, "System does not support BLE");
            finish();
        }

        // Initializes a Bluetooth adapter. For API level 18 and above, get a
        // reference to BluetoothAdapter through BluetoothManager.
        bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();

        // Checks if Bluetooth is supported on the device.
        if (bluetoothAdapter == null) {
            Log.e(TAG, "Unable to get bluetooth adapter");
            finish();
        }

        if (bluetoothAdapter.isEnabled() == false) {
            Log.e(TAG, "You need to enable bluetooth");
            finish();
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        bluetoothAdapter.startLeScan(mLeScanCallback);
    }

    @Override
    public void onPause(){
        super.onPause();
        bluetoothAdapter.stopLeScan(mLeScanCallback);
    }

    private BluetoothAdapter.LeScanCallback mLeScanCallback =
        new BluetoothAdapter.LeScanCallback() {
            @Override
            public void onLeScan(final BluetoothDevice device
                                ,final int rssi
                                ,final byte[] scanRecord) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //mLeDeviceListAdapter.addDevice(device);
                        //mLeDeviceListAdapter.notifyDataSetChanged();

                        Log.i(TAG,"LeScanCallback:: device name ="+device.getName()+"    rssi="+rssi+"      scanRecord"+scanRecord);
                    }
                });
            }
        };

}
