package arto.costsmanager.records.stubs;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import arto.costsmanager.records.ICItem;
import arto.costsmanager.records.RType;
import arto.costsmanager.records.Record;

public class RecordsStub extends RecordsAdapter{

	private static List<Record> records;
	private static List<Integer> ids;
	private static List<HashMap<String, Object>> maps;
	
	private static int numC = 0;
	private static int numI = 0;
	
	private static List<ICItem> items;
	private static List<Integer> itemIds;
	private static int numItemC = 0;
	private static int numItemI = 0;
	
	private static Random rand;
	
	private static boolean justInitialized;
	
	public RecordsStub() {
		
		if (justInitialized) return;
		
		rand = new Random();
		
		records = new LinkedList<Record>(); 
		ids = new LinkedList<Integer>();
		maps = new LinkedList<HashMap<String, Object>>();
		items = new LinkedList<ICItem>();
		itemIds = new LinkedList<Integer>();
		
		fillItems();
		fillRecords();
		
		justInitialized = true;
	}
		
	private int makeId() {
		
		int id;
		boolean was = false;
		do {
			id = rand.nextInt();
			
			Iterator<Integer> iter = ids.iterator();
			while (iter.hasNext()) {
				if (id == iter.next()) {
					was = true;
					break;
				}
			}
			iter = itemIds.iterator();
			while (iter.hasNext()) {
				if (id == iter.next()) {
					was = true;
					break;
				}
			}
			
		} while (was);
		return id;
	}
	
	private void fillItems() {
		String [] costNames = {"food", "car", "house", "movie", "hygiene",
							   "studies", "travels", "clothes", "shoes", 
							   "medicine", "drugs", "alcohol"};
		numItemC = costNames.length;
		
		String [] incomeNames = {"salary", "gift", "bonus"};
		numItemI = incomeNames.length;
		
		for (int i = 0; i < numItemC; i++) {
			int id = makeId();
			itemIds.add(id);
			items.add(new ICItem(id, RType.COSTS, costNames[i]));
		}
		for (int i = 0; i < numItemI; i++) {
			int id = makeId();
			itemIds.add(id);
			items.add(new ICItem(id, RType.INCOME, incomeNames[i]));
		}
	}
		
	private void fillRecords() {
		int number = 10;
				
		double [] sums = {100, 50000, 950, 545.60, 5490, 
				300.05, 1234.56, 9876, 333.34, 999.127};
		
		long currDate = new Date().getTime();
		
		long [] dates = {10000000, 5000000, 3000000, 2000000, 1000000,
						 900000, 800000, 700000, 600000, 400000};
		for (int i = 0; i < number; i++) dates[i] += currDate;		
		
		String [] comments = {"mmm... delicious", "it's a little bit funny...", 
				"", "oh, holy shit...", 
				"", "", 
				"happy birthday btch", "don't worry be happy(..",
				"", "dead clowns"};
		
		int [] icitemIds = new int [] {
				itemIds.get(0), itemIds.get(numItemC),
				itemIds.get(2), itemIds.get(5),
				itemIds.get(10), itemIds.get(1),
				itemIds.get(numItemC + 1), itemIds.get(3),
				itemIds.get(2), itemIds.get(6)};
		
		for (int i = 0; i < number; i++) {
			int id = makeId();
			ICItem icitem = getICItem(icitemIds[i]);
			Record r = new Record(id, icitem, 
					sums[i], dates[i], comments[i]);
			ids.add(id);
			records.add(r);
			maps.add(r.getAsMap());
			if (icitem.getType().isCost()) numC++;
			else numI++;
		}
		
	}
	
	@Override
	public int addICItem(ICItem newItem) {
		return addICItem(newItem.getType(), newItem.getName());
	}

	@Override
	public int addICItem(RType type, String name) {
		int id = makeId();
		itemIds.add(id);
		items.add(new ICItem(id, type, name));
		
		if (type.isCost()) numItemC++;
		else numItemI++;
		
		return id;
	}
	
	@Override
	public ICItem getICItem(int id) {
		Iterator<ICItem> iter = items.iterator();
		while (iter.hasNext()) {
			ICItem item = iter.next();
			if (item.getId() == id) return item;
		}
		return null;
	}
	
	@Override
	public boolean editICItem(ICItem editedItem) {
		return editICItem(editedItem.getId(), editedItem.getType(),
				editedItem.getName());
	}
	
	@Override
	public boolean editICItem(int id, RType newType, String newName) {
		ListIterator<ICItem> iter = items.listIterator();
		boolean found = false;
		while (iter.hasNext()) {
			ICItem item = iter.next();
			if (item.getId() == id) {
				found = true;
				
				RType oldType = item.getType();
				if (oldType != newType) {
					if (oldType.isCost()) {
						numItemC--;
						numItemI++;
					} else {
						numItemC++;
						numItemI--;
					}
				}
				
				iter.remove();
				iter.add(new ICItem(id, newType, newName));
				break;
			}
		}
		return found;
	}
	
	@Override
	public void deleteICItem(int id) {
		RType type = null;		
		boolean found = false;
		Iterator<ICItem> iter = items.iterator();
		while (iter.hasNext()) {
			ICItem item = iter.next();
			if (item.getId() == id) {
				found = true;
				type = item.getType();
				iter.remove();
				break;
			}
		}
		if (!found) return;
		Iterator<Integer> iterId = itemIds.iterator();
		while (iterId.hasNext()) {
			if (iterId.next() == id) {
				iterId.remove();
				break;
			}
		}
		Iterator<HashMap<String, Object>> iterMap = maps.iterator();
		while (iterMap.hasNext()) {
			HashMap<String, Object> map = iterMap.next();
			if ((int) map.get(Record.KEY_ID) == id) {
				iterMap.remove();
				break;
			}
		}
		
		if (type.isCost()) numItemC--;
		else numItemI--;
		
	}
	
	@Override
	public List<ICItem> getAllItems() {
		return items;
	}
	
	@Override
	public List<ICItem> getIncomeItems() {
		List<ICItem> incomes = new LinkedList<ICItem>();
		Iterator<ICItem> iter = items.iterator();
		while (iter.hasNext()) {
			ICItem item = iter.next();
			if (!item.getType().isCost()) incomes.add(item); 
		}
		return incomes;
	}
	
	@Override
	public List<ICItem> getCostItems() {
		List<ICItem> costs = new LinkedList<ICItem>();
		Iterator<ICItem> iter = items.iterator();
		while (iter.hasNext()) {
			ICItem item = iter.next();
			if (item.getType().isCost()) costs.add(item); 
		}
		return costs;
	}
	
	@Override
	public String[] getIncomeItemNames() {
		String [] names = new String [numItemI];
		Iterator<ICItem> iter = items.iterator();
		int i = 0;
		while (iter.hasNext()) {
			ICItem item = iter.next();
			if (!item.getType().isCost()) {
				names[i] = item.getName();
				i++;				
			}
		}
		return names;
	}
	
	@Override
	public String[] getCostItemNames() {
		String [] names = new String [numItemC];
		Iterator<ICItem> iter = items.iterator();
		int i = 0;
		while (iter.hasNext()) {
			ICItem item = iter.next();
			if (item.getType().isCost()) {
				names[i] = item.getName();
				i++;				
			}
		}
		return names;
	}
	
	@Override
	public int[] getAllItemIds() {
		int [] ids = new int [items.size()];
		Iterator<ICItem> iter = items.iterator();
		int i = 0;
		while (iter.hasNext()) {
			ids[i] = iter.next().getId();
			i++;
		}
		return ids;
	}
	
	@Override
	public int[] getIncomeItemIds() {
		int [] ids = new int [numItemI];
		Iterator<ICItem> iter = items.iterator();
		int i = 0;
		while (iter.hasNext()) {
			ICItem item = iter.next();
			if (!item.getType().isCost()) {
				ids[i] = item.getId();
				i++;
			}
		}
		return ids;
	}
	
	@Override
	public int[] getCostItemIds() {
		int [] ids = new int [numItemC];
		Iterator<ICItem> iter = items.iterator();
		int i = 0;
		while (iter.hasNext()) {
			ICItem item = iter.next();
			if (item.getType().isCost()) {
				ids[i] = item.getId();
				i++;
			}
		}
		return ids;
	}
	
	
	@Override
	public int addRecord(Record newRecord) {
		int id = makeId();
		Record r = new Record(id, newRecord.getICItem(), newRecord.getSum(), 
				newRecord.getDate(), newRecord.getComment());
		ids.add(id);
		records.add(r);
		maps.add(r.getAsMap());
		if (newRecord.getICItem().getType().isCost()) numC++;
		else numI++;
		return id;
	}
	
	@Override
	public int addRecord(int itemId, double sum, long date, String comment) {
		int id = makeId();
		ICItem icitem = getICItem(itemId);
		Record r = new Record(id, icitem, sum, date, comment);
		ids.add(id);
		records.add(r);
		maps.add(r.getAsMap());
		if (icitem.getType().isCost()) numC++;
		else numI++;
		return id;
	}
	
	@Override
	public Record getRecord(int id) {
		Iterator<Record> iter = records.iterator();
		while (iter.hasNext()) {
			Record r = iter.next();
			if (r.getId() == id) return r;
		}
		return null;
	}
	
	@Override
	public boolean editRecord(Record editedRecord) {
		int id = editedRecord.getId();
		boolean found = false;
		ListIterator<Record> iter = records.listIterator();
		while (iter.hasNext()) {
			Record r = iter.next();
			if (r.getId() == id) {
				found = true;
				RType oldType = r.getType();
				if (oldType != editedRecord.getICItem().getType()) {
					if (oldType.isCost()) {
						numC--;
						numI++;
					} else {
						numC++;
						numI--;
					}
				}
				iter.remove();
				iter.add(editedRecord);
				break;
			}
		}
		if (!found) return false;
		
		ListIterator<HashMap<String, Object>> iterMap = maps.listIterator();
		while (iterMap.hasNext()) {
			HashMap<String, Object> map = iterMap.next();
			if ((int) map.get(Record.KEY_ID) == id) {
				iterMap.remove();
				iterMap.add(editedRecord.getAsMap());
				break;
			}
		}
		return true;
	}
	
	
	@Override
	public boolean editRecord(int recordId, int itemId, double sum, long date,
			String comment) {
		boolean found = false;
		Record newR = null;
		ListIterator<Record> iter = records.listIterator();
		while (iter.hasNext()) {
			Record r = iter.next();
			if (r.getId() == recordId) {
				found = true;
				ICItem icitem = getICItem(itemId);
				newR = new Record(recordId, icitem, sum, date, comment);

				RType oldType = r.getType();
				if (oldType != icitem.getType()) {
					if (oldType.isCost()) {
						numC--;
						numI++;
					} else {
						numC++;
						numI--;
					}
				}
				iter.remove();
				iter.add(newR);
				break;
			}
		}
		if (!found) return false;
		
		ListIterator<HashMap<String, Object>> iterMap = maps.listIterator();
		while (iterMap.hasNext()) {
			HashMap<String, Object> map = iterMap.next();
			if ((int) map.get(Record.KEY_ID) == recordId) {
				iterMap.remove();
				iterMap.add(newR.getAsMap());
				break;
			}
		}
		return true;
	}
	
	@Override
	public void deleteRecord(int id) {
		Iterator<Record> iter = records.iterator();
		while (iter.hasNext()) {
			Record r = iter.next();
			if (r.getId() == id) {
				if (r.getType().isCost()) numC--;
				else numI--;
				iter.remove();
				break;
			}
		}
		
		Iterator<HashMap<String, Object>> iterMap = maps.iterator();
		while (iterMap.hasNext()) {
			HashMap<String, Object> map = iterMap.next();
			if ((int) map.get(Record.KEY_ID) == id) {
				iterMap.remove();
				break;
			}
		}
	
		Iterator<Integer> iterId = ids.iterator();
		while (iterId.hasNext()) {
			if (iterId.next() == id) {
				iterId.remove();
				break;
			}
		}
	}
	
	@Override
	public List<Record> getAllRecords() {
		return records;
	}
	
	@Override
	public List<Record> getIncomes() {
		List<Record> incomes = new LinkedList<Record>();
		Iterator<Record> iter = records.iterator();
		while (iter.hasNext()) {
			Record r = iter.next();
			if (!r.getType().isCost()) incomes.add(r);
		}
		return incomes;
	}
	
	@Override
	public List<Record> getCosts() {
		List<Record> costs = new LinkedList<Record>();
		Iterator<Record> iter = records.iterator();
		while (iter.hasNext()) {
			Record r = iter.next();
			if (r.getType().isCost()) costs.add(r);
		}
		return costs;
	}
	
	@Override
	public List<HashMap<String, Object>> getAllRecordsAsMapList() {
		return maps;
	}
	
	@Override
	public List<HashMap<String, Object>> getIncomesAsMapList() {
		List<HashMap<String, Object>> incomes = 
				new LinkedList<HashMap<String, Object>>();
		Iterator<Record> iter = records.iterator();
		while (iter.hasNext()) {
			Record r = iter.next();
			if (!r.getType().isCost()) incomes.add(r.getAsMap());
		}
		return incomes;	
	}
	
	@Override
	public List<HashMap<String, Object>> getCostsAsMapList() {
		List<HashMap<String, Object>> costs =
				new LinkedList<HashMap<String, Object>>();
		Iterator<Record> iter = records.iterator();
		while (iter.hasNext()) {
			Record r = iter.next();
			if (r.getType().isCost()) costs.add(r.getAsMap());
		}
		return costs;		
	}
	
	@Override
	public int [] getAllRecordsIds() {
		int [] result = new int [ids.size()];
		Iterator<Integer> iter = ids.iterator();
		int i = 0;
		while (iter.hasNext()) {
			result[i] = iter.next();
		}
		return result;
	}
	
	@Override
	public int [] getCostsIds() {
		int [] result = new int [numC];
		int pointer = 0;
		Iterator<Record> iter = records.iterator();
		while (iter.hasNext()) {
			Record r = iter.next();
			if (r.getType().isCost()) {
				result[pointer] = r.getId();
				pointer++;
			}
		}
		return result;
	}
	
	@Override
	public int[] getIncomesIds() {
		int [] result = new int [numI];
		int pointer = 0;
		Iterator<Record> iter = records.iterator();
		while (iter.hasNext()) {
			Record r = iter.next();
			if (!r.getType().isCost()) {
				result[pointer] = r.getId();
				pointer++;
			}
		}
		return result;
	}
	

			
//	private Record createRecordFromHashMap(HashMap<String, Object> map) {
//		return new Record(
//				(int) map.get(Record.KEY_ID),
//				new ICItem(
//						(RType) map.get(Record.KEY_TYPE),
//						(String) map.get(Record.KEY_ITEMNAME)),
//				(double) map.get(Record.KEY_SUM),
//				(long) map.get(Record.KEY_DATE),
//				(String) map.get(Record.KEY_COMMENT));
//	}

}
