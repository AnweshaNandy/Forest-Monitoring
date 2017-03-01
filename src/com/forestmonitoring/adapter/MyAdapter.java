/**
 * 
 */
package com.forestmonitoring.adapter;

import com.forestmonitoring.utility.Util;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;

/**
 * @author Rupanwita Bhattacharya
 *
 */
public class MyAdapter extends BaseAdapter{

	private String[] data;
	private int type;
	private Context context;
	private int mSelectedPosition = -1;
	 private RadioButton mSelectedRB;
	 private SparseBooleanArray checked=new SparseBooleanArray();
	/**
	 * 
	 */
	public MyAdapter(String[] data,int type,Context context) {
		this.data=data;
		this.type=type;
		this.context=context;
	}
	
	public  SparseBooleanArray getChecked()
	{
		return checked;
	}
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		
		return data.length;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if(convertView == null)
		{
			switch (type) 
			{
		case 0:
			CheckBox cb=new CheckBox(context);
			cb.setText(data[position]);
			cb.setTextColor(Color.parseColor("#606060"));
			cb.setTypeface(Util.changeFont(context,  "font/OpenSans-Regular.ttf"));
			convertView = cb;
			cb.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					if (((CheckBox) v).isChecked()) {
						((CheckBox) v).setChecked(true);
						checked.put(position, true);
					} else {

						((CheckBox) v).setChecked(false);
						checked.put(position, false);
					}
				}
			});
			break;
		case 1:
			RadioButton rb=new RadioButton(context);
			rb.setText(data[position]);
			rb.setTypeface(Util.changeFont(context,  "font/OpenSans-Regular.ttf"));
			rb.setTextColor(Color.parseColor("#606060"));
			convertView =rb;
			//radioButton only one can be selected within the group
			rb.setOnClickListener(new View.OnClickListener() {

	            @Override
	            public void onClick(View v) {

	                if ((position != mSelectedPosition && mSelectedRB != null)) {
	                    mSelectedRB.setChecked(false);
	                }

	                mSelectedPosition = position;
	                mSelectedRB = (RadioButton) v;
	            }
	        });

	        if (mSelectedPosition != position) {
	           rb.setChecked(false);
	        } else {
	        	rb.setChecked(true);
	            if (mSelectedRB != null && rb != mSelectedRB) {
	                mSelectedRB = rb;
	            }
	        }
			break;
		}}
		return convertView;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
	
		return 0;
	}
	

}
