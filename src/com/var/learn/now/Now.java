package com.var.learn.now;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Now extends Activity {
	List<Restaurant> model = new ArrayList<Restaurant>(); // create a new instance of class
	RestaurantAdapter adapter = null;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button save = (Button)findViewById(R.id.save);
        
        save.setOnClickListener(onSave);
        
        ListView list = (ListView)findViewById(R.id.restaurants);
        
        adapter = new RestaurantAdapter();
        list.setAdapter(adapter);
    }
    
    private View.OnClickListener onSave = new View.OnClickListener() {
		
		public void onClick(View v) {
			
			Restaurant r = new Restaurant();
			EditText name = (EditText)findViewById(R.id.name);
			EditText address = (EditText)findViewById(R.id.addr);
			
			r.setName(name.getText().toString());
			r.setAddress(address.getText().toString());
			
			RadioGroup types = (RadioGroup)findViewById(R.id.types);
			
			switch (types.getCheckedRadioButtonId()){
				case R.id.sit_down:
					r.setType("sit_down");
					break;
				case R.id.take_out:
					r.setType("take_out");
					break;
				case R.id.delivery:
					r.setType("delivery");
					break;
			}
			adapter.add(r);
		}
	};
	class RestaurantAdapter extends ArrayAdapter<Restaurant>{
		RestaurantAdapter(){
			super(Now.this,
					android.R.layout.simple_expandable_list_item_1,
					model);
		}
		public View getView(int position,View convertView,ViewGroup parent){
			View row = convertView;
			
			if(row == null){
				LayoutInflater inflater = getLayoutInflater();
				
				row = inflater.inflate(R.layout.row, null);
			}
			
			Restaurant r = model.get(position);
			
			((TextView)row.findViewById(R.id.title)).setText(r.getName());
			((TextView)row.findViewById(R.id.address)).setText(r.getaddress());
			
			ImageView icon = (ImageView)row.findViewById(R.id.icon);
			
			
		}
	}
}