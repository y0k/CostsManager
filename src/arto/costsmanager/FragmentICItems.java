package arto.costsmanager;

import java.util.List;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentICItems extends ListFragment {
	
	private List<String> list;
	private ArrayAdapter<String> adapter;
	
	public void setItemsList(List<String> list) {
		this.list = list;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.view_icitems_narrow_list, 
				container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		adapter = new ArrayAdapter<String>(
				getActivity(), 
				android.R.layout.simple_list_item_1,
				list);
		ListView listView = (ListView) getActivity().
				findViewById(android.R.id.list);
		listView.setAdapter(adapter);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
	}
	
}
