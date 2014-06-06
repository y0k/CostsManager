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

public class DialogItemsMore extends DialogFragment {

	private RadioButton rbtnAll;
	private RadioButton rbtnCosts;
	private RadioButton rbtnIncomes;
	
	private static int type = -1;
	
	private DialogItemsMoreListener listener;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.view_items_more_options, null);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.icitems_options)
				.setPositiveButton(R.string.ok,
						new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						int oldType = type;
						if (rbtnAll.isChecked()) type = RSettings.TYPE_ALL;
						else if (rbtnCosts.isChecked()) type = RSettings.TYPE_COSTS;
						else type = RSettings.TYPE_INCOMES;
						if (oldType != type) listener.onSettingsChaged(type);
					}
				})
				.setNegativeButton(R.string.cancel, null)						
				.setView(view);
		
		rbtnAll = (RadioButton) view.findViewById(R.id.radio_items_type_all);
		rbtnCosts = (RadioButton) view.findViewById(R.id.radio_items_type_costs);
		rbtnIncomes = (RadioButton) view.findViewById(R.id.radio_items_type_incomes);
		
		if (type == -1) {
			if (rbtnAll.isChecked()) type = RSettings.TYPE_ALL;
			else if (rbtnCosts.isChecked()) type = RSettings.TYPE_COSTS;
			else type = RSettings.TYPE_INCOMES;
		} else {
			if (type == RSettings.TYPE_ALL) rbtnAll.setChecked(true);
			else if (type == RSettings.TYPE_COSTS) rbtnCosts.setChecked(true);
			else rbtnIncomes.setChecked(true);
		}
		
		return builder.create();
	}
	
	

	public void setDialogListener(DialogItemsMoreListener listener) {
		this.listener = listener;
	}
	
	public static interface DialogItemsMoreListener {
		void onSettingsChaged(int newType);
	}
}
