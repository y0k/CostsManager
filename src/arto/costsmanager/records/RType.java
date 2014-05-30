package arto.costsmanager.records;

public enum RType {
	INCOME (0),
	COSTS (1);
	
	public final int number;
	
	private RType(int number) {
		this.number = number; 
	}
}
