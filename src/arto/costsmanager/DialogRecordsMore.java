package arto.costsmanager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import arto.costsmanager.records.RSettings;

public class DialogRecordsMore extends DialogFragment {
	
	private RadioButton rbtnAll;
	private RadioButton rbtnCosts;
	private RadioButton rbtnIncomes;
	private RadioButton rbtnNewAbove;
	private RadioButton rbtnNewBelow;	
	
	private static int type = -1;
	private static boolean newAbove;
	
	private DialogRecordsMoreListener listener;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	
		LayoutInflater inflater = getActivity().getLayoutInflater();
		
		View view = inflater.inflate(
				R.layout.view_records_more_options, null);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.records_options)
				.setPositiveButton(R.string.ok, 
						new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						int oldType = type;						
						if (rbtnAll.isChecked()) type = RSettings.TYPE_ALL;
						else if (rbtnCosts.isChecked()) type = RSettings.TYPE_COSTS;
						else type = RSettings.TYPE_INCOMES;
						
						boolean oldSort = newAbove;						
						newAbove = rbtnNewAbove.isChecked();
					
						if (newAbove != oldSort || oldType != type)
								listener.onSettingsChanged(type, newAbove);
						
					}
				})
				.setNegativeButton(R.string.cancel, null)						
				.setView(view);
		
		rbtnAll = (RadioButton) view.findViewById(R.id.radio_records_type_all);
		rbtnCosts = (RadioButton) view.findViewById(R.id.radio_records_type_costs);
		rbtnIncomes = (RadioButton) view.findViewById(R.id.radio_records_type_incomes);
		
		rbtnNewAbove = (RadioButton) view.findViewById(R.id.radio_sort_records_new_above);
		rbtnNewBelow = (RadioButton) view.findViewById(R.id.radio_sort_records_new_below);
		
		if (type == -1) {
			if (rbtnAll.isChecked()) type = RSettings.TYPE_ALL;
			else if (rbtnCosts.isChecked()) type = RSettings.TYPE_COSTS;
			else type = RSettings.TYPE_INCOMES;
			newAbove = rbtnNewAbove.isChecked();
		} else {
			if (type == RSettings.TYPE_ALL) rbtnAll.setChecked(true);
			else if (type == RSettings.TYPE_COSTS) rbtnCosts.setChecked(true);
			else rbtnIncomes.setChecked(true);
			
			if (newAbove) rbtnNewAbove.setChecked(true);
			else rbtnNewBelow.setChecked(true);
		}		
		return builder.create();
	}

	public void setDialogListener(DialogRecordsMoreListener listener) {
		this.listener = listener;
	}
	
	public interface DialogRecordsMoreListener {
		void onSettingsChanged(int newType, boolean newAbove);

	}
	
}
