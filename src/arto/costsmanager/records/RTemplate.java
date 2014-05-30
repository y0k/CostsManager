package arto.costsmanager.records;

import java.util.HashMap;

public class RTemplate {

	public static String KEY_ITEMNAME = "ITEMNAME";
	public static String KEY_SUM = "SUM";
	public static String KEY_COMMENT = "COMMENT"; 
	public static String KEY_TYPE = "TYPE";
	
	private String name;
	private double sum;
	private ICItem item;
	private String comment;
	
	private int id;
	
	private HashMap<String, Object> hashMap;
	
	public RTemplate(String name, double sum, ICItem item, String comment,
			int id) {
		this.name = name;
		this.sum = sum;
		this.item = item;
		this.comment = comment;
		this.id = id;
		createHashMap();
	}

	public RTemplate(String name, double sum, ICItem item, String comment) {
		this.name = name;
		this.sum = sum;
		this.item = item;
		this.comment = comment;
		id = -1;
		createHashMap();
	}
	
	private void createHashMap() {
		hashMap = new HashMap<String, Object>();
		hashMap.put(KEY_ITEMNAME, item.getName());
		hashMap.put(KEY_SUM, sum);
		hashMap.put(KEY_COMMENT, comment);
		hashMap.put(KEY_TYPE, item.getType());
	}

	public RType getType() {
		return item.getType();
	}

	public String getName() {
		return name;
	}

	public double getSum() {
		return sum;
	}

	public ICItem getItem() {
		return item;
	}

	public String getComment() {
		return comment;
	}
	
	public int getId() {
		return id;
	}
	
	public HashMap<String, Object> getAsMap() {
		return hashMap;
	}	
	
}
