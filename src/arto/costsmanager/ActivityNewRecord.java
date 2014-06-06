package arto.costsmanager;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
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
	
	public static final String KEY_NUMBER = "NUMBER";
	
	private TextView icitemLabel;
	private TextView icitem;
	private TextView date;
	private EditText sum;
	private EditText comment;
	
	private RadioButton rbtnCost;
	private RadioButton rbtnIncome;

	private String [] incomeItems;
	private String [] costItems;
	private Record editRecord;
	
	private boolean isEdit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_record);

		costItems = getIntent().getExtras().getStringArray(ICItem.KEY_COSTS);
		incomeItems = getIntent().getExtras().getStringArray(ICItem.KEY_INCOMES);
		
		editRecord = getIntent().getExtras().getParcelable(Record.KEY_RECORD);
		if (editRecord != null) isEdit = true;
				
		icitemLabel = (TextView) findViewById(R.id.textView_icitem_type);
		icitem = (TextView) findViewById(R.id.textView_icitem);
		date = (TextView) findViewById(R.id.textView_date_value);
		sum = (EditText) findViewById(R.id.editText_sum);
		comment = (EditText) findViewById(R.id.editText_comment);
		rbtnCost = (RadioButton) findViewById(R.id.radio_new_record_rtype_cost);
		rbtnIncome = (RadioButton) findViewById(R.id.radio_new_record_rtype_income);
		
		rbtnCost.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, 
					boolean isChecked) {
				if (isChecked) {
					icitem.setText(costItems[0]);
					icitemLabel.setText(getResources().getString(
							R.string.citem_cost));					
				} else {
					icitem.setText(incomeItems[0]);
					icitemLabel.setText(getResources().getString(
							R.string.citem_income));
				}
			}
		});
		
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
		
		final Date curDate;
		
		if (isEdit) {
			setTitle(R.string.edit_record);
			if (editRecord.getType() == RType.COSTS) rbtnCost.setChecked(true);
			else rbtnIncome.setChecked(true);
				
			curDate = new Date(editRecord.getDate());
			date.setText(editRecord.getDateString());
			icitem.setText(editRecord.getItemName());
			sum.setText(Double.toString(editRecord.getSum()));
			comment.setText(editRecord.getComment());
		} else {
			rbtnCost.setChecked(true);
			if (rbtnCost.isChecked()) icitem.setText(costItems[0]);
			else icitem.setText(incomeItems[0]);
			curDate = new Date();
			date.setText(SimpleDateFormat.getDateTimeInstance().format(curDate));
		}
		
		Button btnOK = (Button) findViewById(R.id.button_new_record_ok);
		btnOK.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				RType type;
				if (rbtnCost.isChecked()) type = RType.COSTS;
				else type = RType.INCOME;
				double sumValue;
				try {
					sumValue = Double.parseDouble(sum.getText().toString());
				} catch (NumberFormatException e) {
					sumValue = 0;
				}
				
				
				String icitemName = icitem.getText().toString();
				int icitemNum = 0;
				String [] tmp;
				if (type.isCost()) tmp = costItems;
				else tmp = incomeItems;
				for (int i = 0; i < tmp.length; i++) {
					if (icitemName.equals(tmp[i])) {
						icitemNum = i;
						break;
					}
				}
				
				Intent answer = new Intent();
				answer.putExtra(Record.KEY_TYPE, type.number);
				answer.putExtra(KEY_NUMBER, icitemNum);
				answer.putExtra(Record.KEY_SUM, sumValue);
				answer.putExtra(Record.KEY_COMMENT, comment.getText().toString());
				if (isEdit) {
					answer.putExtra(Record.KEY_DATE, editRecord.getDate());
					answer.putExtra(Record.KEY_ID, editRecord.getId());
				} else {
					answer.putExtra(Record.KEY_DATE, curDate.getTime());
				}				
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
