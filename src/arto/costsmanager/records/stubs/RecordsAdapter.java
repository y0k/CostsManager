package arto.costsmanager.records.stubs;

import java.util.HashMap;
import java.util.List;

import arto.costsmanager.records.ICItem;
import arto.costsmanager.records.RTemplate;
import arto.costsmanager.records.RType;
import arto.costsmanager.records.Record;
import arto.costsmanager.records.RecordsInterface;
import arto.costsmanager.records.User;

public class RecordsAdapter implements RecordsInterface {

	@Override
	public int addRecord(int itemId, double sum, long date, String comment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addRecord(Record newRecord) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Record getRecord(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean editRecord(int recordId, int itemId, double sum, long date,
			String comment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editRecord(Record editedRecord) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteRecord(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Record> getRecords(int itemId) {
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
	public List<Record> getRecords(int[] itemIds, long dateFrom, long dateTo) {
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
	public List<Record> getIncomes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, Object>> getIncomesAsMapList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Record> getCosts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, Object>> getCostsAsMapList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getAllRecordsIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getCostsIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getIncomesIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addICItem(RType type, String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addICItem(ICItem newItem) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ICItem getICItem(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean editICItem(int id, RType newType, String newName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editICItem(ICItem editedItem) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteICItem(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ICItem> getAllItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ICItem> getIncomeItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ICItem> getCostItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getAllItemIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getIncomeItemIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getCostItemIds() {
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
	public boolean editTemplate(int id, String newName, int istemId,
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
	public String[] getIncomeItemNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getCostItemNames() {
		// TODO Auto-generated method stub
		return null;
	}



}