package arto.costsmanager;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import arto.costsmanager.records.ICItem;
import arto.costsmanager.records.Record;


public class MainActivity extends Activity {
		
	private static final int REQ_CODE_NEW_RECORD = 1;
	
	private DialogRecordsMore dialogRecordsMore;
	private FragmentRecords fragmentRecordList;
	//private DialogNewRecord dialogNewRecord;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); 

		ActionBar actionBar = getActionBar();		
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);		
		View viewForActionBar = LayoutInflater.from(this).inflate(
				R.layout.view_actionbar_records, null);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setCustomView(viewForActionBar);
		
		if (savedInstanceState == null) {			
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			fragmentRecordList = new FragmentRecords();
			ft.add(R.id.layout_main, fragmentRecordList);
			ft.commit();
		}
		dialogRecordsMore = new DialogRecordsMore();
		dialogRecordsMore.setDialogListener(fragmentRecordList);
		
//		dialogNewRecord = new DialogNewRecord();
//		dialogNewRecord.setNewRecordListener(fragmentRecordList);
//		dialogNewRecord.setRecordsInterface(
//				fragmentRecordList.getRecordsInterface());
		
		final ImageButton imgBtnMoreForRecords = (ImageButton) 
				findViewById(R.id.more_for_records);
		imgBtnMoreForRecords.setOnClickListener(new OnClickListener() {
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {				
				dialogRecordsMore.show(getFragmentManager(), "TAG_RECORDS_MORE");
			}
		});

		LinearLayout addRecord = (LinearLayout) findViewById(R.id.add_record);
		addRecord.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
//				activity.setRecordsInterface(
//						fragmentRecordList.getRecordsInterface());
				Intent intent = new Intent(
						MainActivity.this, ActivityNewRecord.class);
				intent.putExtra(ICItem.KEY_COSTS, 
						(String [] ) fragmentRecordList.getRecordsInterface().
						getAllCostItemNames().toArray());
				intent.putExtra(ICItem.KEY_INCOMES, 
						(String [] ) fragmentRecordList.getRecordsInterface().
						getAllIncomeItemNames().toArray());
				
				startActivityForResult(intent, REQ_CODE_NEW_RECORD);
			}
		});
		

		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQ_CODE_NEW_RECORD && resultCode == RESULT_OK) {
			fragmentRecordList.addRecord(
					(Record) data.getParcelableExtra(Record.KEY_RECORD));
		}
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.menu_records_action_more, menu);
		return true;
	}

	
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}

}
