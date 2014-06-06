package arto.costsmanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import arto.costsmanager.records.ICItem;
import arto.costsmanager.records.RSettings;
import arto.costsmanager.records.RType;
import arto.costsmanager.records.Record;
import arto.costsmanager.records.RecordsInterface;
import arto.costsmanager.records.stubs.RecordsStub;

public class FragmentRecords extends ListFragment 
		implements DialogRecordsMore.DialogRecordsMoreListener {

	private static final int REQ_CODE_EDIT_RECORD = 2;
	
	private RecordsInterface recordsInterface;
	private SimpleAdapter listAdapter; 
	private RSettings settings;
	private int [] recordsIds;
	
	public FragmentRecords() {
		recordsInterface = new RecordsStub();
		settings = new RSettings();
	}
	
	
	public RecordsInterface getRecordsInterface() {
		return recordsInterface;
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_records_list, container, 
				false);
		
		
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		refreshList(recordsInterface.getAllRecordsAsMapList());
		recordsIds = recordsInterface.getAllRecordsIds();
	}
	
	@Override
	public void onSettingsChanged(int newType, boolean newAbove) {
		
		refreshList(getRecordsList(newType, newAbove));		
		
		settings.setShowRecordsType(newType);
		settings.setNewRecordsAbove(newAbove);
	}

	
	private List<HashMap<String, Object>>  getRecordsList() {
		return getRecordsList(settings.getShowRecordsType(), 
				settings.isNewRecordsAbove());
	}
	
	private List<HashMap<String, Object>>  getRecordsList(
			int newType, boolean newAbove) {
		
		List<HashMap<String, Object>> list;
		switch (newType) {
		case RSettings.TYPE_ALL:
			list = recordsInterface.getAllRecordsAsMapList();
			recordsIds = recordsInterface.getAllRecordsIds();
			break;
		case RSettings.TYPE_COSTS:
			list = recordsInterface.getCostsAsMapList();
			recordsIds = recordsInterface.getCostsIds();
			break;
		case RSettings.TYPE_INCOMES:
			list = recordsInterface.getIncomesAsMapList();
			recordsIds = recordsInterface.getIncomesIds();
			break;
		default:
			return null;
		}
		
		List<HashMap<String, Object>> newList = 
				new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < list.size(); i++) 
				newList.add(new HashMap<String, Object>());
		
		Collections.copy(newList, list);
		if (newAbove) {
			Collections.reverse(newList);
			
			int [] tmp = new int [recordsIds.length];
			for (int i = 0, j = recordsIds.length - 1;
					i < recordsIds.length; 
					i++, j--) tmp[i] = recordsIds[j];
			recordsIds = tmp;
		}
		return newList;
	}
	
	private void refreshList(final List<HashMap<String, Object>> newList) {
		listAdapter = new SimpleAdapter(getActivity().getApplicationContext(), 
				newList,
				R.layout.list_item_record,
				new String [] {Record.KEY_ITEMNAME, Record.KEY_SUM, 
				Record.KEY_DATE_STRING, Record.KEY_COMMENT},
				new int [] {R.id.textView_list_item_record_citem,
				R.id.textView_list_item_record_sum,
				R.id.textView_list_item_record_date,
				R.id.textView_list_item_record_comment}) {

			@Override
			public View getView(int position, View convertView,
					ViewGroup parent) {
				View view = super.getView(position, convertView, parent);
				
				RType type = (RType) newList.get(position).get(Record.KEY_TYPE);
				if (type == RType.INCOME) {
					TextView textItem = (TextView) 
							view.findViewById(R.id.textView_list_item_record_citem);
					TextView textSum = (TextView) 
							view.findViewById(R.id.textView_list_item_record_sum);
					textItem.setTextColor(getResources().getColor(R.color.income));
					textSum.setTextColor(getResources().getColor(R.color.income));
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
			public void onDestroyActionMode(ActionMode mode) {}
			
			@Override
			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				MenuInflater inflater = mode.getMenuInflater();
				inflater.inflate(R.menu.menu_record, menu);
				return true;
			}
			
			@Override
			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				switch (item.getItemId()) {
				case R.id.menu_item_record_delete:
					if (numbers.size() > 1) {
						Iterator<Integer> iter = numbers.iterator();
						while (iter.hasNext()) {
							recordsInterface.deleteRecord(recordsIds[iter.next()]);
						}
					} else {
						recordsInterface.deleteRecord(
								recordsIds[number]);
					}					
					refreshList(getRecordsList());					
					break;
				case R.id.menu_item_record_edit:
					Record record = recordsInterface.getRecord(recordsIds[number]);
					Intent intent = new Intent(getActivity(), 
							ActivityNewRecord.class);
					intent.putExtra(Record.KEY_RECORD, record);
					intent.putExtra(ICItem.KEY_COSTS, 
							recordsInterface.getCostItemNames());
					intent.putExtra(ICItem.KEY_INCOMES, 
							recordsInterface.getIncomeItemNames());
					startActivityForResult(intent, REQ_CODE_EDIT_RECORD);					
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
		
//		getListView().setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				
//				
//				
//				// TODO Auto-generated method stub
//				
//			}
//		});
		
//		getListView().setOnItemLongClickListener(new OnItemLongClickListener() {
//			@Override
//			public boolean onItemLongClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				
//				PopupMenu menu = new PopupMenu(getActivity(), view); 
//				MenuInflater inflater = getActivity().getMenuInflater();
//				inflater.inflate(R.menu.menu_record, menu.getMenu());
//				menu.show();
//				return true;
//			}
//		});
	}

	public void addRecord(Intent data) {	
		
		int typeNum = data.getExtras().getInt(Record.KEY_TYPE);
		RType type = RType.getType(typeNum);
		int icitemNum = data.getExtras().getInt(ActivityNewRecord.KEY_NUMBER);
		int icitemId;
		if (type.isCost()) {
			icitemId = recordsInterface.getCostItemIds()[icitemNum];
		} else {
			icitemId = recordsInterface.getIncomeItemIds()[icitemNum];
		}
		
		double sum = data.getExtras().getInt(Record.KEY_SUM);
		long date = data.getExtras().getInt(Record.KEY_DATE);
		String comment = data.getExtras().getString(Record.KEY_COMMENT);
		
		recordsInterface.addRecord(icitemId, sum, date, comment);
		refreshList(getRecordsList());
	}
	
	
	
		
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQ_CODE_EDIT_RECORD 
				&& resultCode == Activity.RESULT_OK) {
			
			int typeNum = data.getExtras().getInt(Record.KEY_TYPE);
			RType type = RType.getType(typeNum);
			int icitemNum = data.getExtras().getInt(ActivityNewRecord.KEY_NUMBER);
			
			int icitemId;
			if (type.isCost()) {
				icitemId = recordsInterface.getCostItemIds()[icitemNum];
			} else {
				icitemId = recordsInterface.getIncomeItemIds()[icitemNum];
			}
			
			int id = data.getExtras().getInt(Record.KEY_ID);
			double sum = data.getExtras().getInt(Record.KEY_SUM);
			long date = data.getExtras().getInt(Record.KEY_DATE);
			String comment = data.getExtras().getString(Record.KEY_COMMENT);
			
			recordsInterface.editRecord(id, icitemId, sum, date, comment);
			refreshList(getRecordsList());
		}
	
	
	}
	
		
	

	
	
	
}
