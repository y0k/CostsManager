package arto.costsmanager;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import arto.costsmanager.records.ICItem;
import arto.costsmanager.records.RType;
import arto.costsmanager.records.Record;

public class ActivityNewRecord extends Activity 
		implements DialogICItems.ICItemReceiveListener {
	
	private TextView icitemLabel;
	private TextView icitem;
	private TextView date;
	private EditText sum;
	private EditText comment;
	
	private RadioButton rbtnCost;
//	private RadioButton rbtnIncome;

	private String [] incomeItems;
	private String [] costItems;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_record);

		costItems = getIntent().getExtras().getStringArray(ICItem.KEY_COSTS);
		incomeItems = getIntent().getExtras().getStringArray(ICItem.KEY_INCOMES);
		
		icitemLabel = (TextView) findViewById(R.id.textView_icitem_type);
		icitem = (TextView) findViewById(R.id.textView_icitem);
		date = (TextView) findViewById(R.id.textView_date_value);
		sum = (EditText) findViewById(R.id.editText_sum);
		comment = (EditText) findViewById(R.id.editText_comment);
		rbtnCost = (RadioButton) findViewById(R.id.radio_new_record_rtype_cost);
//		rbtnIncome = (RadioButton) findViewById(R.id.radio_new_record_rtype_income);
		
		rbtnCost.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, 
					boolean isChecked) {
				if (isChecked) {
					icitem.setText(costItems[0]);
					icitemLabel.setText(getResources().getString(
							R.string.citem_cost));					
				}
				else {
					icitem.setText(incomeItems[0]);
					icitemLabel.setText(getResources().getString(
							R.string.citem_income));
				}
			}
		});
		
		
		final Date curDate = new Date();
		date.setText(SimpleDateFormat.getDateTimeInstance().format(curDate));
		
		if (rbtnCost.isChecked()) icitem.setText(costItems[0]);
		else icitem.setText(incomeItems[0]);
		icitem.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogICItems dialog = new DialogICItems();
				if (rbtnCost.isChecked()) dialog.setICItems(
						costItems,
						getResources().getString(R.string.select_cost_item));
				else dialog.setICItems(incomeItems, 
						getResources().getString(R.string.select_income_item));
				dialog.setICItemReceiveListener(ActivityNewRecord.this);
				dialog.show(getFragmentManager(), "TAG_SELECT_ICITEM");
			}
		});
		
		Button btnOK = (Button) findViewById(R.id.button_new_record_ok);
		btnOK.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				RType type;
				if (rbtnCost.isChecked()) type = RType.COSTS;
				else type= RType.INCOME;
				double sumValue;
				try {
					sumValue = Double.parseDouble(sum.getText().toString());
				} catch (NumberFormatException e) {
					sumValue = 0;
				}
				
				Record record = new Record(
						new ICItem(type, icitem.getText().toString()),
						sumValue,
					curDate.getTime(),
					comment.getText().toString());
				Intent answer = new Intent();
				answer = answer.putExtra(Record.KEY_RECORD, 
						(Parcelable) record);
				answer.putExtra("OLOLO", "answerstring");
				setResult(RESULT_OK, answer);
				finish();
				
			}
		});
		
		Button btnCancel = (Button) findViewById(R.id.button_new_record_cancel);
		btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		
		LayoutParams params = getWindow().getAttributes();
        params.width = LayoutParams.MATCH_PARENT;
        getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, 
				WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
	}

	@Override
	public void onItemReceived(String itemName) {
		icitem.setText(itemName);		
	}
}
