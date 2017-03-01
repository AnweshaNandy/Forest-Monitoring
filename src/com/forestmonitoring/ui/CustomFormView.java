/**
 * 
 */
package com.forestmonitoring.ui;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.forestmonitoring.adapter.MyAdapter;
import com.forestmonitoring.custom.InstantAutoComplete;
import com.forestmonitoring.dropdown.DropDownViewForXML;
import com.forestmonitoring.utility.Util;
import com.forestmonitoring.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * @author Rupanwita Bhattacharya
 *
 */
@SuppressLint("ResourceAsColor")
public class CustomFormView {

	/******
	 * Method for showing Labels of the field
	 * @param context
	 * @param text {@link String} the text of the label
	 * @param tag {@link String} the Id for the View
	 * *********/
	public TextView getTextViewLebel(Context context,String text,String tag)
	{
		TextView tv=new TextView(context);
		tv.setTag(tag);
		tv.setTypeface(Util.changeFont(context,  "font/OpenSans-Regular.ttf"));
		tv.setTextColor(Color.parseColor("#202020"));
		tv.setTextSize(17);    
		tv.setText(text);
		return tv;
	}
	
	/******
	 * Method for showing NumberPicker View
	 * @param context
	 * @param tag {@link String} the Id for the View
	 * *********/
	@SuppressLint("InflateParams")
	/*public View getNumberPicker(Activity activity,String tag)
	{
		View customNumberPicker=activity.getLayoutInflater().inflate(R.layout.custom_number_picker, null);
		Button btnMinus=(Button)customNumberPicker.findViewById(R.id.btnMinus);
		Button btnPlus=(Button)customNumberPicker.findViewById(R.id.btnPlus);
		final EditText etNumber=(EditText)customNumberPicker.findViewById(R.id.etNumber);	
		etNumber.setTypeface(Util.changeFont(activity,  "font/OpenSans-Regular.ttf"));
		etNumber.setTextColor(Color.parseColor("#606060"));
		etNumber.setTag(tag);

		btnMinus.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(etNumber.getText().toString() != null && !etNumber.getText().toString().trim().isEmpty())
				{
					try {

						int val=Integer.parseInt(etNumber.getText().toString().trim());
						val--;
						etNumber.setText(""+val);

					} catch (NumberFormatException e) {

					}

				}
			}
		});
		btnPlus.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(etNumber.getText().toString() != null && !etNumber.getText().toString().trim().isEmpty())
				{
					try {
						int val=Integer.parseInt(etNumber.getText().toString().trim());
						val++;
						etNumber.setText(""+val);
					}
					catch(NumberFormatException e) {

					}
				}
			}
		});
		customNumberPicker.setTag("NumberPicker");
		return customNumberPicker;
	}*/
	
	/******
	 * Method for showing Single Line EditText View
	 * @param context
	 * @param inputType {@link InputType} types of Input method for EditText
	 * @param tag {@link String} the Id for the View
	 * 
	 * *********/
	public EditText getEditText(Context context,int inputType,String tag)
	{
		EditText et=new EditText(context);
		et.setTag(tag);
		et.setTypeface(Util.changeFont(context,  "font/OpenSans-Regular.ttf"));
		et.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 55));
		et.setTextColor(Color.parseColor("#606060"));
		//et.setBackgroundResource(R.drawable.grey_border_box);
		et.setTextSize(18);    
		et.setPadding(5, 5, 5, 5);
		et.setInputType(inputType);
		return et;
	}
	
	/******
	 * Method for showing Multiple line EditText View
	 * @param context
	 * @param inputType {@link InputType} types of Input method for EditText
	 * @param tag {@link String} the Id for the View
	 * 
	 * *********/
	public EditText getTextArea(Context context,int inputType,String tag)
	{
		EditText et=new EditText(context);
		et.setTag(tag);
		et.setTypeface(Util.changeFont(context,  "font/OpenSans-Regular.ttf"));
		et.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 75));
		//et.setBackgroundResource(R.drawable.grey_border_box);
		et.setTextColor(Color.parseColor("#606060"));
		et.setSingleLine(false);
		et.setGravity(Gravity.TOP);
		et.setTextSize(17);    
		et.setPadding(5, 5, 5, 5);
		et.setInputType(inputType);
		return et;
	}
	
	public InstantAutoComplete getInstantAutoComplete(Context context)
	{
		InstantAutoComplete iAC=new InstantAutoComplete(context);
		return iAC;
		
	}
	
	
	/******
	 * Method for showing DropDown View
	 * @param context
	 * @param data {@link Array} Options for the DropDown
	 * @param tag {@link String} the Id for the View
	 * 
	 * *********/
	public DropDownViewForXML getDropDownViewForXML(Context context,final String[] data,final String tag)
	{
		final DropDownViewForXML dVX=new DropDownViewForXML(context);
		dVX.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 45));
		dVX.setPadding(5, 5, 5, 5);
		if (data.length > 0) {
			dVX.setEnabled(true);
			dVX.setItems(data);
		} else {
			dVX.setEnabled(false);
			dVX.setText("");
		}
		dVX.setHint("Select");
		dVX.setTag(tag);		
		dVX.setTextSize(18);
		//dVX.setBackgroundResource(R.drawable.grey_border_box);
		dVX.setTextColor(Color.parseColor("#606060"));
		dVX.setTypeface(Util.changeFont(context,  "font/OpenSans-Regular.ttf"));
		dVX.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				
			}
		});
		return dVX;

	}
	
	/******
	 * Method for CheckBox and RadioButton options
	 * @param context
	 * @param data {@link Array} of options
	 * @param type if the type is 0 then it is for {@link CheckBox} and if it is 1 then it is for {@link RadioButton} 
	 * @param tag {@link String} the Id for the View
	 * 
	 * *********/
	public GridView getOptionView(Context context,String[] data,int type,String tag)
	{
		GridView gridView=new GridView(context);
		
		gridView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		gridView.setNumColumns(3);
		gridView.setAdapter(new MyAdapter(data, type, context));
		gridView.setTag(tag);
		setGridViewHeightBasedOnChildren(gridView, 3);
		return gridView;
		
	}
	/******
	 * Method for showing date View
	 * @param context
	 * @param dateFormatter {@link SimpleDateFormat} The formatting structure of the date
	 * @param newCalendar {@link Calendar} The calendar object
	 * @param tag {@link String} the Id for the View
	 * 
	 * *********/
	public EditText getDate(final Context context,final SimpleDateFormat dateFormatter,final Calendar newCalendar,String tag)
	{
		final EditText et=new EditText(context);
		et.setTypeface(Util.changeFont(context,  "font/OpenSans-Regular.ttf"));
		//et.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.watch, 0);
		et.setText(dateFormatter.format(newCalendar.getTime()));
		et.setTextColor(Color.parseColor("#606060"));
		et.setTextSize(18);
		et.setTag(tag);
		
		//to make edittext working like textview
		
		et.setCursorVisible(false);
		et.setKeyListener(null);
		et.setBackgroundColor(Color.TRANSPARENT); 
		
		et.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 new DatePickerDialog(context, new OnDateSetListener() {

						public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
							Calendar newDate = Calendar.getInstance();
							newDate.set(year, monthOfYear, dayOfMonth);
							et.setText(dateFormatter.format(newDate.getTime()));
						}

					}, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH)).show();;
				
			}
		});
		return et;
		
	}
	
	/******
	 * Adapter Class 
	 * *********//*
	private class MyAdapter extends BaseAdapter
	{
		private String[] data;
		private int type;
		private Context context;
		private int mSelectedPosition = -1;
		 private RadioButton mSelectedRB;
		 private SparseBooleanArray checked;
		*//**
		 * 
		 *//*
		public MyAdapter(String[] data,int type,Context context) {
			this.data=data;
			this.type=type;
			this.context=context;
		}
		
		@SuppressWarnings("unused")
		public  SparseBooleanArray getChecked()
		{
			return checked;
		}
		 (non-Javadoc)
		 * @see android.widget.Adapter#getCount()
		 
		@Override
		public int getCount() {
			
			return data.length;
		}

		 (non-Javadoc)
		 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
		 
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			if(convertView == null)
			{
				switch (type) 
				{
			case 0:
				CheckBox cb=new CheckBox(context);
				cb.setText(data[position]);
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

		 (non-Javadoc)
		 * @see android.widget.Adapter#getItem(int)
		 
		@Override
		public Object getItem(int position) {
			
			return null;
		}

		 (non-Javadoc)
		 * @see android.widget.Adapter#getItemId(int)
		 
		@Override
		public long getItemId(int position) {
		
			return 0;
		}
		
	}*/
	
	
	public void setGridViewHeightBasedOnChildren(GridView gridView, int columns) {
        ListAdapter listAdapter = gridView.getAdapter(); 
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int items = listAdapter.getCount();
        int rows = 0;

        View listItem = listAdapter.getView(0, null, gridView);
        listItem.measure(0, 0);
        totalHeight = listItem.getMeasuredHeight();

        float x = 1;
        if( items > columns ){
            x = items/columns;
            rows = (int) (x + 1);
            totalHeight *= rows;
        }

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        gridView.setLayoutParams(params);

}
}
