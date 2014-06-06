package arto.costsmanager;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import arto.costsmanager.records.ICItem;

public class ActivityItems extends Activity {

	private static final int REQ_CODE_NEW_ITEM = 4;
	
	private DialogItemsMore dialogItemsMore;
	private FragmentItems fragmentItemList;
	
	//private RecordsInterface
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_items);
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);		
		View viewForActionBar = LayoutInflater.from(this).inflate(
				R.layout.view_actionbar_items, null);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setCustomView(viewForActionBar);
		
		if (savedInstanceState == null) {
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			fragmentItemList = new FragmentItems();
			ft.add(R.id.layout_items, fragmentItemList);
			ft.commit();
		}
		dialogItemsMore = new DialogItemsMore();
		dialogItemsMore.setDialogListener(fragmentItemList);
		
		final ImageButton imgBtnMore = (ImageButton)
				findViewById(R.id.more_for_items);
		imgBtnMore.setOnClickListener(new OnClickListener() {
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {				
				dialogItemsMore.show(getFragmentManager(), "TAG_ITEMS_MORE");
			}
		});
		
		LinearLayout addItem = (LinearLayout) findViewById(R.id.add_item);
		addItem.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						ActivityNewItem.class);
				startActivityForResult(intent, REQ_CODE_NEW_ITEM);
			}
		});
		
		ImageButton imgBtnToItems = (ImageButton) 
				findViewById(R.id.to_records);
		imgBtnToItems.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
//				Intent intent = new Intent(getApplicationContext(), 
//						MainActivity.class);
//				startActivity(intent);
			}
		});
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQ_CODE_NEW_ITEM && resultCode == RESULT_OK) {
			fragmentItemList.addItem(
					(ICItem) data.getParcelableExtra(ICItem.KEY_ICITEM));
		}
	}
	
	
	
	
	
}
