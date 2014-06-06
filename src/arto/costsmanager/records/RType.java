package arto.costsmanager.records;

public enum RType {
	INCOME (0),
	COSTS (1);
	
	public final int number;
	
	private RType(int number) {
		this.number = number; 
	}
	
	public static RType getType(int number) {
		if (number == INCOME.number) return INCOME;
		else if (number == COSTS.number) return COSTS;
		else return null;
	}
	
	public boolean isCost() {
		if (this == COSTS) return true;
		else return false;
	}
}
