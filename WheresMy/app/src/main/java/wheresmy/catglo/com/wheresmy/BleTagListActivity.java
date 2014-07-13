package wheresmy.catglo.com.wheresmy;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class BleTagListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ble_tag_list_activity);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new BleTagListFragment())
                    .commit();
        }
    }

    public void showTagDetails(BleTag tag){
        Bundle args = new Bundle();
        args.putSerializable("tag",tag);
        TagDetailsFragment tagDetails = new TagDetailsFragment();
        tagDetails.setArguments(args);
        getFragmentManager().beginTransaction()
                .replace(R.id.container, tagDetails)
                .addToBackStack(tag.assignedName)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ble_tag_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
