package wheresmy.catglo.com.wheresmy;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

/**
* Created by goblets on 7/12/14.
*/
public class BleTag implements Serializable {
    public String assignedName;
    public String scanRecord;
    public int rssi;
    public boolean playAlarm;
    public int distance;

    static final String fileName = "BLE_TAGS";


    @Override
    public String toString(){
        if (assignedName==null){
            return "Unknown"+scanRecord+" "+rssi;
        }
        return assignedName+" "+rssi;
    }

    public static void saveTags(Context context, HashMap<String,BleTag> bleTags){
        FileOutputStream fos = null;
        try {
            fos =  context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(bleTags);
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static HashMap<String,BleTag> loadTags(Context context){
        FileInputStream fis = null;
        HashMap<String,BleTag> bleTags = new HashMap<String,BleTag>();
        try {
            fis =  context.openFileInput(fileName);
            ObjectInputStream is = new ObjectInputStream(fis);
            bleTags = (HashMap<String,BleTag>) is.readObject();
            is.close();
        } catch (Exception e) {
            Log.i("BLE", "Failed to load tags");
        }
        Log.i("BLE","Loaded "+bleTags.size()+" saved tags");
        return bleTags;
    }

}
