package com.forestmonitoring.dropdown;


import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
 
import com.forestmonitoring.R;
//import com.example.manishvishwakarma.searchablespinnermine.SearchableListDialog;


import java.util.ArrayList;
import java.util.List;
 
 
public class SearchableSpinner extends Spinner implements View.OnTouchListener,
        SearchableListDialog.SearchableItem {
 
    String selectedItem;
    //this string above will store the value of selected item.
 
    public static final int NO_ITEM_SELECTED = -1;
    private Context _context;
    private List _items;
    private SearchableListDialog _searchableListDialog;
 
    private boolean _isDirty;
    private ArrayAdapter _arrayAdapter;
    private String _strHintText;
    private boolean _isFromInit;
    
    private List<PopupListItem> list;
    private float textSize = 15;
    private PopupListItemAdapter adapter;
 
    public SearchableSpinner(Context context) {
        super(context);
        this._context = context;
        init();
    }
 
    public SearchableSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        this._context = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SearchableSpinner);
        final int N = a.getIndexCount();
        for (int i = 0; i < N; ++i) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.SearchableSpinner_hintText) {
                _strHintText = a.getString(attr);
            }
        }
        a.recycle();
        init();
    }
 
    public SearchableSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this._context = context;
        init();
    }
 
    private void init() {
        _items = new ArrayList();
        _searchableListDialog = SearchableListDialog.newInstance
                (_items);
        _searchableListDialog.setOnSearchableItemClickListener(this);
        setOnTouchListener(this);
 
        _arrayAdapter = (ArrayAdapter) getAdapter();
        if (!TextUtils.isEmpty(_strHintText)) {
            ArrayAdapter arrayAdapter = new ArrayAdapter(_context, android.R.layout
                    .simple_list_item_1, new String[]{_strHintText});
            _isFromInit = true;
            setAdapter(arrayAdapter);
        }
    }
 
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
 
            if (null != _arrayAdapter) {
 
                // Refresh content #6
                // Change Start
                // Description: The items were only set initially, not reloading the data in the
                // spinner every time it is loaded with items in the adapter.
                _items.clear();
                for (int i = 0; i < _arrayAdapter.getCount(); i++) {
                    _items.add(_arrayAdapter.getItem(i));
                }
                // Change end.
 
                _searchableListDialog.show(scanForActivity(_context).getFragmentManager(), "TAG");
            }
        }
        return true;
    }
 
    @Override
    public void setAdapter(SpinnerAdapter adapter) {
 
        if (!_isFromInit) {
            _arrayAdapter = (ArrayAdapter) adapter;
            if (!TextUtils.isEmpty(_strHintText) && !_isDirty) {
                ArrayAdapter arrayAdapter = new ArrayAdapter(_context, android.R.layout
                        .simple_list_item_1, new String[]{_strHintText});
                super.setAdapter(arrayAdapter);
            } else {
                super.setAdapter(adapter);
            }
 
        } else {
            _isFromInit = false;
            super.setAdapter(adapter);
        }
    }
    //The method just below is executed  when an item in the searchlist is tapped.This is where we store the value int string called selectedItem. 
    @Override
    public void onSearchableItemClicked(Object item, int position) {
        setSelection(_items.indexOf(item));
 
        if (!_isDirty) {
            _isDirty = true;
            setAdapter(_arrayAdapter);
            setSelection(_items.indexOf(item));
        }
        selectedItem= getItemAtPosition(position).toString();
 
        Toast.makeText(getContext(),"You selected "+selectedItem,Toast.LENGTH_LONG).show();
    }
 
 
 
    private Activity scanForActivity(Context cont) {
        if (cont == null)
            return null;
        else if (cont instanceof Activity)
            return (Activity) cont;
        else if (cont instanceof ContextWrapper)
            return scanForActivity(((ContextWrapper) cont).getBaseContext());
 
        return null;
    }
 
    @Override
    public int getSelectedItemPosition() {
        if (!TextUtils.isEmpty(_strHintText) && !_isDirty) {
            return NO_ITEM_SELECTED;
        } else {
            return super.getSelectedItemPosition();
        }
    }
 
    @Override
    public Object getSelectedItem() {
        if (!TextUtils.isEmpty(_strHintText) && !_isDirty) {
            return null;
        } else {
 
            return super.getSelectedItem();
        }
    }
    
    private class PopupListItem {
        private final String text;
        private final int resId;

        public PopupListItem(String text) {
            this.text = text;
            this.resId = -1;
        }

        public PopupListItem(String text, int resId) {
            this.text = text;
            this.resId = resId;
        }

        public String getText() {
            return text;
        }

        public int getResId() {
            return resId;
        }

        @Override
        public String toString() {
            return text;
        }
    }
    
    private class PopupListItemAdapter extends ArrayAdapter<PopupListItem> {

        public PopupListItemAdapter(Context context, List<PopupListItem> objects) {
            super(context, android.R.layout.activity_list_item, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView == null) {
                convertView = newView();
                viewHolder = new ViewHolder();
                viewHolder.tvText = (TextView)convertView.findViewById(android.R.id.text1);
//              viewHolder.tvText.setTextColor(Color.BLACK);
                viewHolder.tvText.setTextColor(Color.parseColor("#333333"));
                viewHolder.tvText.setGravity(Gravity.CENTER);
               viewHolder.tvText.setPadding(10, 0, 0, 0);
                viewHolder.tvText.setSingleLine(true);
                viewHolder.tvText.setEllipsize(TruncateAt.MARQUEE);
                viewHolder.tvText.setMarqueeRepeatLimit(-1);
                viewHolder.tvText.setHorizontallyScrolling(true);
                viewHolder.tvText.setSelected(true);
                viewHolder.tvText.setHeight(getMeasuredHeight());
                viewHolder.ivIcon = (ImageView)convertView.findViewById(android.R.id.icon);
                convertView.setTag(viewHolder);
            }
            else {
                viewHolder = (ViewHolder)convertView.getTag();
            }
            viewHolder.tvText.setText(getItem(position).getText());
            int resId = getItem(position).getResId();
            if(resId != -1)
                viewHolder.ivIcon.setImageResource(resId);
            return convertView;
        }

        private class ViewHolder {
            TextView tvText;
            ImageView ivIcon;
        }

        private View newView() {
            LinearLayout ll_parent = new LinearLayout(getContext());
            ll_parent.setGravity(Gravity.CENTER_VERTICAL);
            ll_parent.setOrientation(LinearLayout.HORIZONTAL);

            ImageView ivIcon = new ImageView(getContext());
            ivIcon.setId(android.R.id.icon);
            ll_parent.addView(ivIcon);

            TextView tvText = new TextView(getContext());
            tvText.setTextColor(Color.BLACK);
            tvText.setTextSize(textSize);
            tvText.setMaxLines(2);
            tvText.setEllipsize(TruncateAt.MARQUEE);
            tvText.setMarqueeRepeatLimit(-1);
            tvText.setId(android.R.id.text1);
            ll_parent.addView(tvText);
            return ll_parent;
        }
    }
    public void setItems(String[] arr) {
        if(arr == null)
            throw new NullPointerException("Items Array is null.");
        if(list == null)
            list = new ArrayList<SearchableSpinner.PopupListItem>();
        list.clear();
        for(String text: arr)
            list.add(new PopupListItem(text));
        adapter = new PopupListItemAdapter(getContext(), list);
        setAdapter(adapter);
       // Log.d("dropDown", "setItems....");
        //setText("");
//        refreshView();
    }
}