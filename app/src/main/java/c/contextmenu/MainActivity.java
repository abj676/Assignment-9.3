package c.contextmenu;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //declaration of list view
    ListView listView;
  //string files for list view
    String[] names=
            {"ABC","DEF","GHI","JKL","MNO","PQR","STU","VWX","YZ1"};
    String[] contactNo=
            {"123","234","345","456","567","678","789","890","012"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=(ListView)findViewById(R.id.listview);

        CustomAdapter customAdapter= new CustomAdapter();
        listView.setAdapter(customAdapter);

//        Assigining ContextMenu to the listView
       registerForContextMenu(listView);

    }
    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            //attaching row.xml to list view in mainactivity.xml

            view=getLayoutInflater().inflate(R.layout.row,null);

            TextView nameview, numberview;

            nameview=view.findViewById(R.id.name);
            numberview=view.findViewById(R.id.phnumber);

            nameview.setText(names[i]);
            numberview.setText(contactNo[i]);


            return view;
        }
    }

    //creating conext menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Select Action");

        // using:  menu.add(groupId,itemId, itemPosition, itemName);
        menu.add(0,1,0,"CALL");
        menu.add(0,2,1,"SMS");

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                 (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int  position = info.position;

        //providing action to the menu item of context menu
        switch (item.getItemId())
        {
            case 1:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+contactNo[position]));
                startActivity(intent);

                break;
            case 2:
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("sms:"));
                i.putExtra("address",contactNo[position]);
                i.putExtra("sms_body","HI!! " + names[position]);
                startActivity(i);
                break;
        }

        return super.onContextItemSelected(item);
    }
}
