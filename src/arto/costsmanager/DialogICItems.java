package arto.costsmanager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class DialogICItems extends DialogFragment {
	
	private String [] items;
	private String title;
	
	private ICItemReceiveListener listener;
	
	public void setICItemReceiveListener(ICItemReceiveListener listener) {
		this.listener = listener;
	}
	
	public void setICItems(String [] items, String title) {
		this.items = items;
		this.title = title;
	}
	
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		builder.setItems(items, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						listener.onItemReceived(items[which]);
					}
				});
		builder.setTitle(title);
		
		return builder.create();
		
		
	}
	
	public static interface ICItemReceiveListener {
		void onItemReceived(String itemName);
	}
	
}
