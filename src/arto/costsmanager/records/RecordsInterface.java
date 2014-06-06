package arto.costsmanager.records;

import java.util.HashMap;
import java.util.List;

public interface RecordsInterface {

	//Records
	
	
	int addRecord(int itemId, double sum, long date, String comment);
	int addRecord(Record newRecord); //returns ID of this new record
	Record getRecord(int id);
	boolean editRecord(int recordId, int itemId, double sum, long date, 
			String comment);
	boolean editRecord(Record editedRecord);
	void deleteRecord(int id);

	List<Record> getRecords(int itemId);
	List<Record> getRecords(long dateFrom, long dateTo);
	List<Record> getRecords(RType type, long dateFrom, long dateTo);
	List<Record> getRecords(int [] itemIds, long dateFrom, long dateTo);
		
	List<Record> getAllRecords();
	List<Record> getIncomes();
	List<Record> getCosts();

	List<HashMap<String, Object>> getAllRecordsAsMapList();
	List<HashMap<String, Object>> getIncomesAsMapList();
	List<HashMap<String, Object>> getCostsAsMapList();
	
	int [] getAllRecordsIds();
	int [] getIncomesIds();
	int [] getCostsIds();
	
	//Income/Cost Items
	int addICItem(RType type, String name); 
	int addICItem(ICItem newItem);
	ICItem getICItem(int id);
	boolean editICItem(int id, RType newType, String newName);
	boolean editICItem(ICItem editedItem);
	void deleteICItem(int id);
	
	List<ICItem> getAllItems();
	List<ICItem> getIncomeItems();
	List<ICItem> getCostItems();
	
	String [] getIncomeItemNames();
	String [] getCostItemNames();
	
	int [] getAllItemIds();
	int [] getIncomeItemIds();
	int [] getCostItemIds();
	
	
	
	//For templates
	int addTemplate(RTemplate newTemplate); //return ID of this new template
	int addTemplate(String name, ICItem item, double sum, String comment);
	
	void deleteTemplate(int id);
	boolean editTemplate(int id, String newName, int istemId, 
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
