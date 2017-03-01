/**
 * 
 */
package com.forestmonitoring.adapter;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.forestmonitoring.R;
//import com.forestmonitoring.model.DataBean;
import com.forestmonitoring.model.Nodes;

public class AutoCompleteViewAdapter extends ArrayAdapter<Nodes> {
	
	
    private String id;
    private ArrayList<Nodes> items;
    private ArrayList<Nodes> itemsAll;
    private ArrayList<Nodes> suggestions;
    private int viewResourceId;

    @SuppressWarnings("unchecked")
	public AutoCompleteViewAdapter(Context context, int viewResourceId, ArrayList<Nodes> items) {
        super(context, viewResourceId, items);
        this.items = items;      
        this.itemsAll = (ArrayList<Nodes>) items.clone();
        this.suggestions = new ArrayList<Nodes>();
        this.viewResourceId = viewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(viewResourceId, null);
        }
        Nodes data = items.get(position);
        if (data != null) {
            TextView customerNameLabel = (TextView) v.findViewById(R.id.tv_fileName);
            if (customerNameLabel != null) {
//              Log.i(MY_DEBUG_TAG, "getView Customer Name:"+customer.getName());
                customerNameLabel.setText(Html.fromHtml(data.getNodeName()));
            }
            
          
        }
        return v;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

  
    Filter nameFilter = new Filter() {
        @Override
        public String convertResultToString(Object resultValue) {
            String str = ((Nodes)(resultValue)).getNodeName(); 
            return str;
        }
        @SuppressLint("DefaultLocale")
		@Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if(constraint != null) {
                suggestions.clear();
                for (Nodes data : itemsAll) {
                    if(data.getNodeName().toLowerCase().startsWith(constraint.toString().toLowerCase())){
                        suggestions.add(data);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            @SuppressWarnings("unchecked")
			ArrayList<Nodes> filteredList = (ArrayList<Nodes>) results.values;
            if(results != null && results.count > 0) {
                clear();
                for (Nodes c : filteredList) {
                    add(c);
                }
                notifyDataSetChanged();
            }
        }
    };

    @Override
    public boolean isEnabled(int position) {
    	Nodes data = items.get(position);
    	   if(data.getNodeName() != null &&data.getNodeName().length()>0)
			{
				if(data.getNodeName()!= null && data.getNodeName().trim().contains("<b>"))
					return false;           
				
			}
    	  // Log.d("id---", data.getId());
    	   this.id=data.getNodeId();
    	return true;
    };
    
    /**
	 * Method to return the id of selected from the list
	 */
    public String getIdOfFiltered() {
		return id;
	

	}
}