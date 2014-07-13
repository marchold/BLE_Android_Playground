package wheresmy.catglo.com.wheresmy;

import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A placeholder fragment containing a simple view.
 */
public class BleTagListFragment extends Fragment implements AdapterView.OnItemClickListener {
    static final public String TAG = "WHERES MY";
    private static BluetoothManager bluetoothManager;
    private BluetoothAdapter bluetoothAdapter = null;
    private ListView listView;
    HashMap<String,BleTag> bleTags = new HashMap<String, BleTag>();
    ArrayList<BleTag> bleTagArray = new ArrayList<BleTag>();
    private ArrayAdapter<BleTag> adapter;

    public BleTagListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ble_tag_list_fragment, container, false);
        listView = (ListView)rootView.findViewById(R.id.listView);
          return rootView;
    }



    @Override
    public void onResume(){
        super.onResume();

        bleTags = BleTag.loadTags(getActivity());
        tagArray();
        adapter = new ArrayAdapter<BleTag>(getActivity(),android.R.layout.simple_list_item_1,bleTagArray);
        listView.setAdapter(adapter);


        // Use this check to determine whether BLE is supported on the device. Then
        // you can selectively disable BLE-related features.
        if (! getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Log.e(TAG, "System does not support BLE");
            //finish();
        }

        // Initializes a Bluetooth adapter. For API level 18 and above, get a
        // reference to BluetoothAdapter through BluetoothManager.
        bluetoothManager = (BluetoothManager) getActivity().getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();

        // Checks if Bluetooth is supported on the device.
        if (bluetoothAdapter == null) {
            Log.e(TAG, "Unable to get bluetooth adapter");
            //finish();
        }

        if (bluetoothAdapter.isEnabled() == false) {
            Log.e(TAG, "You need to enable bluetooth");
            //finish();
        }

        bluetoothAdapter.startLeScan(mLeScanCallback);

        listView.setOnItemClickListener(this);
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
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run(){

                            StringBuilder sb = new StringBuilder();
                            for (int i = 0; i < 15; i++){
                                sb.append(scanRecord[i]);
                            }

                            String key = sb.toString();
                            BleTag tag = bleTags.get(key);
                            if (tag==null){
                                tag = new BleTag();
                                tag.scanRecord = key;
                                tag.rssi = rssi;
                                bleTags.put(key,tag);
                                BleTag.saveTags(getActivity(),bleTags);

                            }
                            tag.rssi = rssi;
                            tagArray();
                            adapter = new ArrayAdapter<BleTag>(getActivity(),android.R.layout.simple_list_item_1,bleTagArray);
                            listView.setAdapter(adapter);

                            Log.i(TAG,"Found tag "+bleTags.size()+"   "+key+"   rssi="+rssi);

                        }
                    });
                }
            };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final BleTag tag = bleTagArray.get(position);
        ((BleTagListActivity)getActivity()).showTagDetails(tag);
    }


    public ArrayList<BleTag> tagArray(){
        bleTagArray = new ArrayList<BleTag>();
        for (String key : bleTags.keySet()){
            bleTagArray.add(bleTags.get(key));
        }
        return bleTagArray;
    }




}
