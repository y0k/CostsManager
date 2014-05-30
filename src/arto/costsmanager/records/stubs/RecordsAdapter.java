package arto.costsmanager.records.stubs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import arto.costsmanager.records.ICItem;
import arto.costsmanager.records.RTemplate;
import arto.costsmanager.records.RType;
import arto.costsmanager.records.Record;
import arto.costsmanager.records.RecordsInterface;
import arto.costsmanager.records.User;

public class RecordsAdapter implements RecordsInterface {

	@Override
	public int addNewRecord(RType type, String state, double sum, long date,
			String comment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addNewRecord(Record newRecord) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Record getRecord(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRecord(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean changeSum(int recordId, double newSum) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeICItem(int recordId, ICItem newItem) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeDate(int recordId, long newDate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeComment(int recordId, String newComment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Record> getRecords(ICItem item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Record> getRecords(long dateFrom, long dateTo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Record> getRecords(RType type, long dateFrom, long dateTo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Record> getRecords(ICItem[] items, long dateFrom, long dateTo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Record> getAllRecords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, Object>> getAllRecordsAsMapList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Record> getAllIncomes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, Object>> getAllIncomesAsMapList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Record> getAllCosts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, Object>> getAllCostsAsMapList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addItem(RType type, String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addItem(ICItem newItem) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeItem(RType type, String name, String newName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeItem(RType type, String name, RType newType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeItem(RType type, String name, RType newType,
			String newName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteItem(RType type, String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteItem(ICItem item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ICItem> getAllItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ICItem> getAllIncomeItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ICItem> getAllCostItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addTemplate(RTemplate newTemplate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addTemplate(String name, ICItem item, double sum, String comment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteTemplate(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean changeTemplate(int id, String newName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeTemplate(int id, ICItem newItem) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeTemplate(int id, String newName, String newComment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeTemplate(int id, double sum) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeTemplate(int id, String name, double sum) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeTemplate(int id, String newName, ICItem newItem,
			double newSum, String newComment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RTemplate getTemplate(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RTemplate> getAllTemplates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RTemplate> getAllIncomeTemplates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RTemplate> getAllCostsTemplates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, Object>> getAllTemplatesAsMapList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, Object>> getAllIncomeTemplatesAsMapList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, Object>> getAllCostTemplatesAsMapList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User addUser(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changeUserName(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getAllItemNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllIncomeItemNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllCostItemNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getAllRecordsIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getAllCostsIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getAllIncomesIds() {
		// TODO Auto-generated method stub
		return null;
	}


}
