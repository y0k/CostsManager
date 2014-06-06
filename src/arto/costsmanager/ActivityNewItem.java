package arto.costsmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import arto.costsmanager.records.ICItem;
import arto.costsmanager.records.RType;

public class ActivityNewItem extends Activity{
	
	private EditText name;
	private RadioButton rbtnCost;
	private RadioButton rbtnIncome;
	
	private ICItem editItem;
	private boolean isEdit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_item);
		
		if (getIntent().getExtras() == null) isEdit = false;
		else {
			editItem = getIntent().getExtras().getParcelable(ICItem.KEY_ICITEM);
			isEdit = true;
		}
		
		name = (EditText) findViewById(R.id.editText_item_name);
		rbtnCost = (RadioButton) findViewById(R.id.radio_new_item_rtype_cost);
		rbtnIncome = (RadioButton) findViewById(R.id.radio_new_item_rtype_income);
		
		if (isEdit) {
			if (editItem.getType() == RType.COSTS) rbtnCost.setChecked(true);
			else rbtnIncome.setChecked(true);
			name.setText(editItem.getName());
			setTitle(R.string.edit_icitem);
		} else {
			rbtnCost.setChecked(true);
		}
		
		Button btnOK = (Button) findViewById(R.id.button_new_item_ok);
		btnOK.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				RType type;
				if (rbtnCost.isChecked()) type = RType.COSTS;
				else type = RType.INCOME;
				ICItem newItem;
				if (isEdit) {
					newItem = new ICItem(editItem.getId(), 
							type, name.getText().toString());
				} else {
					newItem = new ICItem(type, name.getText().toString());
				}
				
				Intent answer = new Intent();
				answer.putExtra(ICItem.KEY_ICITEM, newItem);
				setResult(RESULT_OK, answer);
				finish();
			}
		});
		
		Button btnCancel = (Button) findViewById(R.id.button_new_item_cancel);
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

}
