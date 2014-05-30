package arto.costsmanager.records;

import java.util.HashMap;
import java.util.List;

public interface RecordsInterface {

	//For records
	int addNewRecord(RType type, String state, double sum, long date, 
			String comment);
	int addNewRecord(Record newRecord); //returns ID of this new record
	
	Record getRecord(int id);
	
	void deleteRecord(int id);
	
	boolean changeSum(int recordId, double newSum);
	boolean changeICItem(int recordId, ICItem newItem);
	boolean changeDate(int recordId, long newDate);
	boolean changeComment(int recordId, String newComment);

	List<Record> getRecords(ICItem item);
	List<Record> getRecords(long dateFrom, long dateTo);
	List<Record> getRecords(RType type, long dateFrom, long dateTo);
	List<Record> getRecords(ICItem [] items, long dateFrom, long dateTo);
	
	List<Record> getAllRecords();
	List<HashMap<String, Object>> getAllRecordsAsMapList();
	
	List<Record> getAllIncomes();
	List<HashMap<String, Object>> getAllIncomesAsMapList();
	
	List<Record> getAllCosts();
	List<HashMap<String, Object>> getAllCostsAsMapList();
	
	int [] getAllRecordsIds();
	int [] getAllCostsIds();
	int [] getAllIncomesIds();
	
	//For items
	//Items have unique names within their type.
	boolean addItem(RType type, String name); //return false if error or 
												//such item already exists
	boolean addItem(ICItem newItem);
	
	boolean changeItem(RType type, String name, String newName);
	boolean changeItem(RType type, String name, RType newType);
	boolean changeItem(RType type, String name, RType newType, 
			String newName);
	
	void deleteItem(RType type, String name);
	void deleteItem(ICItem item);
	
	List<ICItem> getAllItems();
	List<ICItem> getAllIncomeItems();
	List<ICItem> getAllCostItems();
	
	List<String> getAllItemNames();
	List<String> getAllIncomeItemNames();
	List<String> getAllCostItemNames();
	
	//For templates
	int addTemplate(RTemplate newTemplate); //return ID of this new template
	int addTemplate(String name, ICItem item, double sum, String comment);
	
	void deleteTemplate(int id);
	
	boolean changeTemplate(int id, String newName);
	boolean changeTemplate(int id, ICItem newItem);
	boolean changeTemplate(int id, String newName, String newComment);
	boolean changeTemplate(int id, double sum);
	boolean changeTemplate(int id, String name, double sum);
	boolean changeTemplate(int id, String newName, ICItem newItem, 
			double newSum, String newComment);
	
	RTemplate getTemplate(int id);
	
	List<RTemplate> getAllTemplates();
	List<RTemplate> getAllIncomeTemplates();
	List<RTemplate> getAllCostsTemplates();
	
	List<HashMap<String, Object>> getAllTemplatesAsMapList();
	List<HashMap<String, Object>> getAllIncomeTemplatesAsMapList();
	List<HashMap<String, Object>> getAllCostTemplatesAsMapList();
	
	//For users
	User addUser(String name);
	User getUser(int id);
	List<User> getAllUsers();
	boolean changeUserName(int id);
	void deleteUser(int id);	
	
}
