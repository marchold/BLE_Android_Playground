package com.catglo.tiblesimpleconnect;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
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

    //In this sample we hard code the device to connect to name, no use in building a UI for this just now.
    static final public String DEVICE_TO_CONNECT_TO_NAME = "SensorTag";

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
                    public void run(){
                        Log.i(TAG,"LeScanCallback:: device name ="+device.getName()+"    rssi="+rssi+"      scanRecord"+scanRecord);
                        if (device.getName().equalsIgnoreCase(DEVICE_TO_CONNECT_TO_NAME)){
                            Log.i(TAG,"LeScanCallback:: attempting connect");

                            device.connectGatt(getApplicationContext(), true, mGattCallback);
                        }
                    }
                });
            }
        };


    public final static String ACTION_GATT_CONNECTED =           "com.example.bluetooth.le.ACTION_GATT_CONNECTED";
    public final static String ACTION_GATT_DISCONNECTED =        "com.example.bluetooth.le.ACTION_GATT_DISCONNECTED";
    public final static String ACTION_GATT_SERVICES_DISCOVERED = "com.example.bluetooth.le.ACTION_GATT_SERVICES_DISCOVERED";
    public final static String ACTION_DATA_AVAILABLE =           "com.example.bluetooth.le.ACTION_DATA_AVAILABLE";
    public final static String EXTRA_DATA =                      "com.example.bluetooth.le.EXTRA_DATA";

    private int mConnectionState = STATE_DISCONNECTED;
    private static final int STATE_DISCONNECTED = 0;
    private static final int STATE_CONNECTING = 1;
    private static final int STATE_CONNECTED = 2;

    // Various callback methods defined by the BLE API.
    private final BluetoothGattCallback mGattCallback =
            new BluetoothGattCallback() {
                @Override
                public void onConnectionStateChange(BluetoothGatt gatt, int status,
                                                    int newState) {
                    String intentAction;
                    if (newState == BluetoothProfile.STATE_CONNECTED) {
                        intentAction = ACTION_GATT_CONNECTED;
                        mConnectionState = STATE_CONNECTED;
                        //broadcastUpdate(intentAction);

                        Log.i(TAG, "Connected to GATT server.");
                        Log.i(TAG, "Attempting to start service discovery:" +
                                gatt.discoverServices());

                    } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                        intentAction = ACTION_GATT_DISCONNECTED;
                        mConnectionState = STATE_DISCONNECTED;
                        Log.i(TAG, "Disconnected from GATT server.");
                       // broadcastUpdate(intentAction);
                    }
                }

                @Override
                // New services discovered
                public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                    if (status == BluetoothGatt.GATT_SUCCESS) {
                      //  broadcastUpdate(ACTION_GATT_SERVICES_DISCOVERED);
                        Log.i(TAG,"New Services Discovered ");
                        List<BluetoothGattService> bluetoothServices = gatt.getServices();
                        for (BluetoothGattService bluetoothGattService : bluetoothServices){
                            List<BluetoothGattCharacteristic> characteristics = bluetoothGattService.getCharacteristics();
                            Log.i(TAG,"Found bluetooth service "+bluetoothGattService.getUuid()+" with "+characteristics.size()+" characteristics");
                            for (BluetoothGattCharacteristic characteristic : characteristics){
                                Log.i(TAG,"  Has charicteristic "+characteristic.getUuid());
                            }
                        }
                    } else {
                        Log.w(TAG, "onServicesDiscovered received: " + status);
                    }
                }

                @Override
                // Result of a characteristic read operation
                public void onCharacteristicRead(BluetoothGatt gatt,
                                                 BluetoothGattCharacteristic characteristic,
                                                 int status) {
                    if (status == BluetoothGatt.GATT_SUCCESS) {
                        //broadcastUpdate(ACTION_DATA_AVAILABLE, characteristic);

                        Log.i(TAG,"onCharacteristicRead");
                    }
                }

            };
}
