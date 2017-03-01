package com.forestmonitoring.activities;



import com.forestmonitoring.activities.LoginActivity;
import com.forestmonitoring.custom.InstantAutoComplete;
import com.forestmonitoring.model.UserClass;
import com.forestmonitoring.utility.AlertDialogCallBack;
import com.forestmonitoring.utility.ServerResponseCallback;
import com.forestmonitoring.utility.Util;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.forestmonitoring.model.Nodes;
import com.forestmonitoring.adapter.AutoCompleteViewAdapter;
import com.forestmonitoring.R;
import com.forestmonitoring.dropdown.SearchableSpinner;
import com.forestmonitoring.services.VolleyTaskManager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class HomeActivity extends Activity implements ServerResponseCallback {
	
	
	private VolleyTaskManager volleyTaskManager;
	private boolean isLoginService = false,isProjectTypeService=false,isProjectService=false,isSubProjectService=false;
	private Context mcontext=HomeActivity.this;
	//protected SearchableSpinner searchable_dropDown_project;
	public PopupWindow popupWindow;
	private InstantAutoComplete dropdown_projecttype,dropdown_project,dropdown_subproject;
	
	protected ArrayList<Nodes> projectTypeList = new ArrayList<Nodes>(),projectList = new ArrayList<Nodes>(),
			subprojectList = new ArrayList<Nodes>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.home_layout);
		initview();
	}
	
	
	
	/*******initializing view************/
	private void initview() {
		//searchable_dropDown_project = (SearchableSpinner) findViewById(R.id.searchable_dropDown_project);
		dropdown_projecttype=(InstantAutoComplete)findViewById(R.id.searchable_dropDown_projecttype);
		dropdown_project=(InstantAutoComplete)findViewById(R.id.searchable_dropDown_project);
		dropdown_subproject=(InstantAutoComplete)findViewById(R.id.searchable_dropDown_subproject);
		volleyTaskManager = new VolleyTaskManager(HomeActivity.this);
		ProjectTypeWebserviceCalling(mcontext);
		
		dropdown_projecttype.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//Log.d("dalmia", "onItemClick() position " + position);
				AutoCompleteViewAdapter acv=(AutoCompleteViewAdapter)dropdown_projecttype.getAdapter();	
				dropdown_projecttype.setTag(acv.getIdOfFiltered());
				
				ProjectWebserviceCalling(mcontext,acv.getIdOfFiltered());
				
			}
		});
		
		dropdown_project.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//Log.d("dalmia", "onItemClick() position " + position);
				AutoCompleteViewAdapter acv=(AutoCompleteViewAdapter)dropdown_project.getAdapter();	
				dropdown_project.setTag(acv.getIdOfFiltered());
			}
		});
		
		dropdown_subproject.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//Log.d("dalmia", "onItemClick() position " + position);
				AutoCompleteViewAdapter acv=(AutoCompleteViewAdapter)dropdown_subproject.getAdapter();	
				dropdown_subproject.setTag(acv.getIdOfFiltered());
			}
		});
		
	}
	
	
	
	
	
	/**
	 * Option dropdown
	 * */
/*	@SuppressLint("InflateParams") @SuppressWarnings("deprecation")
	public void onOptionClick(View v) {
		if (popupWindow == null || !popupWindow.isShowing()) {
			LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
			View popupView = layoutInflater.inflate(R.layout.options_menu, null);
			popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			popupWindow.setBackgroundDrawable(new BitmapDrawable());

			TextView tv_logout = (TextView) popupView.findViewById(R.id.tv_logout);
			TextView tv_reload = (TextView) popupView.findViewById(R.id.tv_reload);
			
			tv_logout.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					popupWindow.dismiss();

					Util.showCallBackMessageWithOkCancel(mcontext, "Do you want to log out?", new AlertDialogCallBack() {

						@Override
						public void onSubmit() {
							//UserClass user = Util.fetchUserClass(mcontext);
							//user.setIsLoggedin(false);
							//Util.saveUserClass(mcontext, user);

							Intent intent = new Intent(mcontext, LoginActivity.class);
							intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivity(intent);
							finish();
						}

						@Override
						public void onCancel() {

						}
					});

				}
			});

			tv_reload.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					popupWindow.dismiss();
					Util.showCallBackMessageWithOkCancel(mcontext, "Do you want to change password?", new AlertDialogCallBack() {

						@Override
						public void onSubmit() {

						}

						@Override
						public void onCancel() {

						}
					});

				}
			});

			popupWindow.setOutsideTouchable(false);
			popupWindow.showAsDropDown(v, 100, 0);
		} else {
			popupWindow.dismiss();
		}

	}*/
	
	/********************* get all the Project type ************************/

	protected void ProjectTypeWebserviceCalling(Context context) {
		isProjectTypeService = true;
		volleyTaskManager.doGetProjectType(true);
	}
	
	/********************* get all the Project ************************/

	protected void ProjectWebserviceCalling(Context context,String projectTypeId) {
		isProjectService = true;
		String paramsMap = "ID=" + projectTypeId;
		volleyTaskManager.doGetProject(true,paramsMap);
	}
	
	/********************* get all the Sub Project ************************/

	protected void SubProjectWebserviceCalling(Context context,String projectId) {
		isSubProjectService = true;
		String paramsMap = "ID=" + projectId;
		volleyTaskManager.doGetSubProject(true,paramsMap);
	}


	// Method to populate project type  dropdown
	
		protected void populateProjectTypeDropdown() {
			//Log.e("testing", "" + projectList.size());
			/*if (projectTypeList.size() > 0) {
				searchable_dropDown_project.setEnabled(true);
				String[] corridorArray = new String[projectTypeList.size()];

				for (int i = 0; i < projectTypeList.size(); i++) {
					corridorArray[i] = projectTypeList.get(i).getNodeName();
				}
				
				searchable_dropDown_project.setItems(corridorArray);
				
			} else {
				searchable_dropDown_project.setEnabled(false);				
				
			}*/
			
			
			if (projectTypeList.size() > 0) {
				dropdown_projecttype.setEnabled(true);			

				
	            ArrayList<Nodes> dataArr=new ArrayList<Nodes>();
				for (int i = 0; i < projectTypeList.size(); i++) {
					
					Nodes node=new Nodes();
					node.setNodeName(projectTypeList.get(i).getNodeName());
					node.setNodeId(projectTypeList.get(i).getNodeId());;
					dataArr.add(node);
				}
				
				AutoCompleteViewAdapter auv=new AutoCompleteViewAdapter(mcontext,R.layout.autocomplete_item_row,projectTypeList);
				
				dropdown_projecttype.setThreshold(0);			
				dropdown_projecttype.setAdapter(auv);
				
			} else {
				dropdown_projecttype.setEnabled(false);
								
				
			}
			
			dropdown_projecttype.setTag("");
			dropdown_projecttype.setHint("Select a Project type");
			
			

		}
		
		// Method to populate project dropdown
		
		protected void populateProjectDropdown() {
			
			
			if (projectList.size() > 0) {
				dropdown_project.setEnabled(true);			

				
	            ArrayList<Nodes> dataArr=new ArrayList<Nodes>();
				for (int i = 0; i < projectList.size(); i++) {
					
					Nodes node=new Nodes();
					node.setNodeName(projectList.get(i).getNodeName());
					node.setNodeId(projectList.get(i).getNodeId());;
					dataArr.add(node);
				}
				
				AutoCompleteViewAdapter auv=new AutoCompleteViewAdapter(mcontext,R.layout.autocomplete_item_row,projectList);
				
				dropdown_project.setThreshold(0);			
				dropdown_project.setAdapter(auv);
				
			} else {
				dropdown_project.setEnabled(false);								
				
			}
			
			dropdown_project.setTag("");
			dropdown_project.setHint("Select a Project");

		}
		
		// Method to populate sub project dropdown
		
				protected void populateSubProjectDropdown() {
					
					if (subprojectList.size() > 0) {
						dropdown_subproject.setEnabled(true);			

						
			            ArrayList<Nodes> dataArr=new ArrayList<Nodes>();
						for (int i = 0; i < subprojectList.size(); i++) {
							
							Nodes node=new Nodes();
							node.setNodeName(subprojectList.get(i).getNodeName());
							node.setNodeId(subprojectList.get(i).getNodeId());;
							dataArr.add(node);
						}
						
						AutoCompleteViewAdapter auv=new AutoCompleteViewAdapter(mcontext,R.layout.autocomplete_item_row,subprojectList);
						
						dropdown_subproject.setThreshold(0);			
						dropdown_subproject.setAdapter(auv);
						
					} else {
						dropdown_subproject.setEnabled(false);								
						
					}
					
					dropdown_subproject.setTag("");
					dropdown_subproject.setHint("Select a Project");

				}


	@Override
	public void onSuccess(JSONObject resultJsonObject) {
		// TODO Auto-generated method stub
		
		if(isProjectTypeService)
		{
			isProjectTypeService=false;
			projectTypeList.clear();
			JSONArray fDGetprojectTypeResultJsonArray = resultJsonObject.optJSONArray("details");
			if (fDGetprojectTypeResultJsonArray != null) {
				for (int i = 0; i < fDGetprojectTypeResultJsonArray.length(); i++) {
					JSONObject projectTypeJsonObject = fDGetprojectTypeResultJsonArray.optJSONObject(i);

					Nodes projecttype = new Nodes();
					projecttype.setNodeId(projectTypeJsonObject.optString("id"));
					projecttype.setNodeName(projectTypeJsonObject.optString("name"));

					projectTypeList.add(projecttype);
					
					
				}
			}
			if (projectTypeList.size() == 0)
				Toast.makeText(mcontext, "No Project Type list found!", Toast.LENGTH_SHORT).show();
			populateProjectTypeDropdown();
			
		}
		else if(isProjectService)
		{
			isProjectService=false;
			projectList.clear();
			JSONArray fDGetprojectResultJsonArray = resultJsonObject.optJSONArray("details");
			if (fDGetprojectResultJsonArray != null) {
				for (int i = 0; i < fDGetprojectResultJsonArray.length(); i++) {
					JSONObject projectJsonObject = fDGetprojectResultJsonArray.optJSONObject(i);

					Nodes projecttype = new Nodes();
					projecttype.setNodeId(projectJsonObject.optString("id"));
					projecttype.setNodeName(projectJsonObject.optString("name"));

					projectList.add(projecttype);
					
					
				}
			}
			if (projectList.size() == 0)
				Toast.makeText(mcontext, "No Project list found!", Toast.LENGTH_SHORT).show();
			populateProjectDropdown();
			
		}
		else if(isSubProjectService)
		{
			isSubProjectService=false;
			subprojectList.clear();
			JSONArray fDGetsubprojectResultJsonArray = resultJsonObject.optJSONArray("details");
			if (fDGetsubprojectResultJsonArray != null) {
				for (int i = 0; i < fDGetsubprojectResultJsonArray.length(); i++) {
					JSONObject subprojectJsonObject = fDGetsubprojectResultJsonArray.optJSONObject(i);

					Nodes projecttype = new Nodes();
					projecttype.setNodeId(subprojectJsonObject.optString("id"));
					projecttype.setNodeName(subprojectJsonObject.optString("name"));

					subprojectList.add(projecttype);
					
					
				}
			}
			if (subprojectList.size() == 0)
				Toast.makeText(mcontext, "No Sub Project list found!", Toast.LENGTH_SHORT).show();
			populateSubProjectDropdown();
			
		}
		
	}



	@Override
	public void onError() {
		// TODO Auto-generated method stub
		
	}

}
