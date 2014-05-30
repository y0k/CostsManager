package arto.costsmanager.records;

public class RSettings {

	public static final int TYPE_ALL = 0;
	public static final int TYPE_COSTS = 1;
	public static final int TYPE_INCOMES = 2;
	
	boolean newRecordsAbove;
	int showRecordsType;
	
	public RSettings() {
		newRecordsAbove = true;
		showRecordsType = TYPE_ALL;
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

	
	
}
