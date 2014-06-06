package arto.costsmanager;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import arto.costsmanager.records.ICItem;
import arto.costsmanager.records.RSettings;
import arto.costsmanager.records.RType;
import arto.costsmanager.records.RecordsInterface;
import arto.costsmanager.records.stubs.RecordsStub;

public class FragmentItems extends ListFragment
		implements DialogItemsMore.DialogItemsMoreListener{

	private static final int REQ_CODE_EDIT_ITEM = 3;
	
	private ArrayAdapter<String> listAdapter;
	private RecordsInterface recordsInterface;
	private RSettings settings;
	private int [] itemsIds;
	
	public FragmentItems() {
		super();
		recordsInterface = new RecordsStub();
		settings = new RSettings();
		//TODO settings and recordsInterface
	}
	
//	public void setRecordsInterface(RecordsInterface recordsInterface) {
//		this.recordsInterface = recordsInterface;
//	}
//	
//	public void setRSettings(RSettings settings) {
//		this.settings = settings;
//	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_items_list, container,
				false);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		refreshList(getItemsList());
	}
	
	private List<ICItem> getItemsList() {
		return getItemsList(settings.getShowItemsType());
	}
	
	private List<ICItem> getItemsList(int type) {
		List<ICItem> list;
		switch (type) {
		case RSettings.TYPE_ALL:
			list = recordsInterface.getAllItems();
			itemsIds = recordsInterface.getAllItemIds();
			break;
		case RSettings.TYPE_COSTS:
			list = recordsInterface.getCostItems();
			itemsIds = recordsInterface.getCostItemIds();
			break;
		case RSettings.TYPE_INCOMES:
			list = recordsInterface.getIncomeItems();
			itemsIds = recordsInterface.getIncomeItemIds();
			break;
		default:
			return null;
		}
		return list;
	}
	
	
	private void refreshList(final List<ICItem> items) {
		
		String [] itemNames = new String [items.size()];
		Iterator<ICItem> iter = items.iterator();
		int i = 0;
		while (iter.hasNext()) {
			itemNames[i] = iter.next().getName();
			i++;
		}		
		
		listAdapter = new ArrayAdapter<String>(
				getActivity().getApplicationContext(),
				R.layout.list_item_icitem,
				R.id.textView_list_item_icitem_name,
				itemNames) {
			
			@Override
			public View getView(int position, View convertView,
					ViewGroup parent) {
				View view = super.getView(position, convertView, parent);
				if (items.get(position).getType() == RType.INCOME) {
					TextView textItem = (TextView) view.
							findViewById(R.id.textView_list_item_icitem_name);
					textItem.setTextColor(getResources().getColor(R.color.income));
				}
				return view;
			}
		};
		
		ListView listView = (ListView) getActivity().
				findViewById(android.R.id.list);
		listView.setAdapter(listAdapter);		
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		listView.setMultiChoiceModeListener(new MultiChoiceModeListener() {
			
			int number = -1;		
			List<Integer> numbers = new LinkedList<Integer>();			
			@Override
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				return false;
			}
			@Override
			public void onDestroyActionMode(ActionMode mode) {
			}
			
			@Override
			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				MenuInflater inflater = mode.getMenuInflater();
				inflater.inflate(R.menu.menu_item, menu);
				return true;
			}
			
			@Override
			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				switch (item.getItemId()) {
				case R.id.menu_item_icitem_delete:
					if (numbers.size() > 1) {
						Iterator<Integer> iter = numbers.iterator();
						while (iter.hasNext()) {
							recordsInterface.deleteICItem(itemsIds[iter.next()]);
						}
					} else {
						recordsInterface.deleteICItem(itemsIds[number]);
					}
					refreshList(getItemsList()); 
					break;
				case R.id.menu_item_icitem_edit:
					ICItem icitem = recordsInterface.getICItem(itemsIds[number]);
					Intent intent = new Intent(getActivity(),
							ActivityNewItem.class);
					intent.putExtra(ICItem.KEY_ICITEM, icitem);
					startActivityForResult(intent, REQ_CODE_EDIT_ITEM);
					break;
				default:
					return false;
				}
				
				
				
				return true;
			}
			
			@Override
			public void onItemCheckedStateChanged(ActionMode mode, int position,
					long id, boolean checked) {
				number = position;
				numbers.add(position);
			}
		});
		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQ_CODE_EDIT_ITEM 
				&& resultCode == Activity.RESULT_OK) {
			ICItem item = (ICItem) data.getParcelableExtra(ICItem.KEY_ICITEM);
			recordsInterface.editICItem(item);
			refreshList(getItemsList());
		}
	}

	@Override
	public void onSettingsChaged(int newType) {
		refreshList(getItemsList(newType));
		settings.setShowItemsType(newType);
	}
	
	public void addItem(ICItem item) {
		recordsInterface.addICItem(item);
		refreshList(getItemsList());
	}
	
}
