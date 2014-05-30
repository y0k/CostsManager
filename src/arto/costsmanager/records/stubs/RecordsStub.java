package arto.costsmanager.records.stubs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import arto.costsmanager.records.ICItem;
import arto.costsmanager.records.RType;
import arto.costsmanager.records.Record;

public class RecordsStub extends RecordsAdapter{

	private List<HashMap<String, Object>> records;
	private List<HashMap<String, Object>> costs;
	private List<HashMap<String, Object>> incomes;
	
	private List<Integer> recordIds;
	private List<Integer> costIds;
	private List<Integer> incomeIds;
	
	public RecordsStub() {
		records = new LinkedList<HashMap<String, Object>>();
		costs = new LinkedList<HashMap<String, Object>>();
		incomes = new LinkedList<HashMap<String, Object>>();
		
		recordIds = new LinkedList<Integer>();
		costIds = new LinkedList<Integer>();
		incomeIds = new LinkedList<Integer>();
		
		fillRecords();
	}

	private void fillRecords() {
		int number = 10;
		String [] itemNames = {"Еда", "Зарплата", "Развлечения", "Еда", "Авто", 
				"Гигиена", "Подарок", "Квартира", "Еда", "Развлечения"};
		double [] sums = {100, 50000, 950, 545.60, 5490, 
				300.05, 1234.56, 9876, 333.34, 999.127};
		RType [] types = {RType.COSTS, RType.INCOME,
				RType.COSTS, RType.COSTS, RType.COSTS,
				RType.COSTS, RType.INCOME, RType.COSTS,
				RType.COSTS, RType.COSTS};
		long [] dates = {100000, 200000, 300000, 400000, 500000,
						 600000, 700000, 900000, 9900000, 1200000};
		
		String [] comments = {"было вкусно", "it's a little bit funny...", 
				"", "oh, holy shit...", 
				"", "", 
				"happy birthday btch", "don't worry be happy(..",
				"", "dead clowns"};
		for (int i = 0; i < number; i++) {			
			int id;
			if (types[i] == RType.COSTS) {
				id = 1000 + costIds.size();
				recordIds.add(id);
				costIds.add(id);
				
			}
			else {
				id = 2000 + incomeIds.size();
				recordIds.add(id);
				incomeIds.add(id);
			}
			records.add(new Record(id, new ICItem(types[i], itemNames[i]), 
					sums[i], dates[i], comments[i]).getAsMap());
			
		}
		
		number = 8;
		itemNames = new String [] {"Еда", "Развлечения", "Еда", "Авто", 
				"Гигиена", "Квартира", "Еда", "Развлечения"};
		sums = new double [] {100, 950, 545.60, 5490, 
				300.05, 9876, 333.34, 999.127};
		types = new RType [] {RType.COSTS,
				RType.COSTS, RType.COSTS, RType.COSTS,
				RType.COSTS, RType.COSTS,
				RType.COSTS, RType.COSTS};
		dates = new long [] {100000, 200000, 300000, 400000, 500000,
				 600000, 700000, 900000};
		comments = new String [] {"было вкусно", 
				"", "oh, holy shit...", 
				"", "", 
				"don't worry be happy(..",
				"", "dead clowns"};
		for (int i = 0; i < number; i++) {			
			costs.add(new Record(costIds.get(i), new ICItem(types[i], itemNames[i]), 
					sums[i], dates[i], comments[i]).getAsMap());
		}
		
		
		number = 2;
		itemNames = new String [] {"Зарплата",  "Подарок"};
		sums = new double [] {50000, 1234.56};
		types = new RType [] {RType.INCOME, RType.INCOME,};
		dates = new long [] {100000, 200000};
		comments = new String [] {"it's a little bit funny...", 
				"happy birthday btch",};
		for (int i = 0; i < number; i++) {			
			incomes.add(new Record(incomeIds.get(i), new ICItem(types[i], itemNames[i]), 
					sums[i], dates[i], comments[i]).getAsMap());
		}
		
		
	}

	@Override
	public List<HashMap<String, Object>> getAllRecordsAsMapList() {
		return records;
	}
	

	
	@Override
	public List<HashMap<String, Object>> getAllCostsAsMapList() {
		return costs;		
	};
	
	@Override
	public List<HashMap<String, Object>> getAllIncomesAsMapList() {
		return incomes;
	}
	
	@Override
	public int addNewRecord(RType type, String state, double sum, long date,
			String comment) {
		Record record = new Record(new ICItem(type, state), sum, date, comment);
		return addNewRecord(record);
	}
	
	@Override
	public int addNewRecord(Record record) {		
		int id;
		if (record.getType() == RType.INCOME) {
			incomes.add(record.getAsMap());
			id = 1000 + incomeIds.size();
			incomeIds.add(id);			
		}
		else {
			costs.add(record.getAsMap());
			id = 2000 + costIds.size();
			costIds.add(id);
		}
		recordIds.add(id);
		records.add(new Record(record, id).getAsMap());
		
		return id;
	}
	
	@Override
	public List<String> getAllCostItemNames() {
		return Arrays.asList(new String [] {"food", "car", "house", "movie",
				"hygiene", "studies", "travels"});
				
	}

	@Override
	public List<String> getAllIncomeItemNames() {
		return Arrays.asList(new String [] {"salary", "gift", "other"});
	}
	
	@Override
	public int [] getAllRecordsIds() {
		int [] result = new int [recordIds.size()];
		Iterator<Integer> iter = recordIds.iterator();
		for (int i = 0; i < recordIds.size(); i++) {
			result[i] = iter.next();
		}
		return result;
	}
	
	@Override
	public int [] getAllCostsIds() {
		int [] result = new int [costIds.size()];
		Iterator<Integer> iter = costIds.iterator();
		for (int i = 0; i < costIds.size(); i++) {
			result[i] = iter.next();
		}
		return result;
	}
	
	@Override
	public int[] getAllIncomesIds() {
		int [] result = new int [incomeIds.size()];
		Iterator<Integer> iter = incomeIds.iterator();
		for (int i = 0; i < incomeIds.size(); i++) {
			result[i] = iter.next();
		}
		return result;
	}
	
	
	@Override
	public void deleteRecord(int id) {
		
		Iterator<HashMap<String, Object>> iter = records.iterator();
		boolean isCost = false;
		while (iter.hasNext()) {
			HashMap<String, Object> map = iter.next();
			int currId = (int) map.get(Record.KEY_ID); 
			if (currId == id) {
				if (((RType) map.get(Record.KEY_TYPE)) == RType.COSTS) 
						isCost = true;				
				iter.remove();
				break;
			}
		}
		if (isCost) iter = costs.iterator();
		else iter = incomes.iterator(); 
		while (iter.hasNext()) {
			if ((int) iter.next().get(Record.KEY_ID) == id) {
				iter.remove();
				break;
			}
		}
	}
	

}
