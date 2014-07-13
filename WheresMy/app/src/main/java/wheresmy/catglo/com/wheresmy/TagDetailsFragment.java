package wheresmy.catglo.com.wheresmy;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;

import java.util.HashMap;


public class TagDetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "tag";


    private BleTag tag;
    private EditText editText;
    private CheckBox playAlarmCheckbox;
    private NumberPicker distanceNumberPicker;
    private HashMap<String, BleTag> tags;


    public TagDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            BleTag tag = (BleTag)getArguments().getSerializable(ARG_PARAM1);
            tags = BleTag.loadTags(getActivity());
            for (String key : tags.keySet()){
                BleTag t = tags.get(key);
                if (t.scanRecord.equalsIgnoreCase(tag.scanRecord)){
                    this.tag = t;
                    break;
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tag_details_fragment, container, false);
        editText = (EditText)view.findViewById(R.id.tagName);
        playAlarmCheckbox = (CheckBox)view.findViewById(R.id.playAlarm);
        distanceNumberPicker = (NumberPicker)view.findViewById(R.id.distance);
        distanceNumberPicker.setMaxValue(200);
        distanceNumberPicker.setMinValue(0);
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        editText.setText(tag.assignedName);
        playAlarmCheckbox.setChecked(tag.playAlarm);
        distanceNumberPicker.setValue(tag.distance);
    }

    @Override
    public void onPause(){
        tag.assignedName = editText.getText().toString();
        tag.playAlarm = playAlarmCheckbox.isChecked();
        tag.distance = distanceNumberPicker.getValue();
        BleTag.saveTags(getActivity(),tags);
        super.onPause();
    }

}
