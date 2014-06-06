package arto.costsmanager.records;

public class RSettings {

	public static final int TYPE_ALL = 0;
	public static final int TYPE_COSTS = 1;
	public static final int TYPE_INCOMES = 2;
	
	static boolean newRecordsAbove;
	static int showRecordsType;
	static int showItemsType;
	
	private static boolean justInitialized;
	
	public RSettings() {
		
		if (justInitialized) return;
		
		newRecordsAbove = true;
		showRecordsType = TYPE_ALL;
		showItemsType = TYPE_ALL;
		
		justInitialized = true;
	}
	
	public boolean isNewRecordsAbove() {
		return newRecordsAbove;
	}

	public void setNewRecordsAbove(boolean newRecordsAbove) {
		this.newRecordsAbove = newRecordsAbove;
	}

	public int getShowRecordsType() {
		return showRecordsType;
	}

	public void setShowRecordsType(int showRecordsType) {
		this.showRecordsType = showRecordsType;
	}

	public int getShowItemsType() {
		return showItemsType;
	}

	public void setShowItemsType(int showItemsType) {
		this.showItemsType = showItemsType;
	}
	
}
