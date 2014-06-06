package arto.costsmanager.records;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Income/cost item
 * @author Arto
 *
 */
public class ICItem implements Parcelable {
	
	public static final String KEY_INCOMES = "Incomes";
	public static final String KEY_COSTS = "Costs";
	public static final String KEY_ICITEM = "ICItem";
	
	
	private RType type;
	private String name;
	private int parentId;
	private int id;
	
	public ICItem(int id, RType type, String name, int parent) {
		this.type = type;
		this.name = name;
		this.parentId = parent;
		this.id = id;
	}

	public ICItem(int id, RType type, String name) {
		this.type = type;
		this.name = name;
		this.id = id;
		parentId = -1;
	}

	public ICItem(RType type, String name, int parent) {
		this.type = type;
		this.name = name;
		this.parentId = parent;
		id = -1;
	}

	public ICItem(RType type, String name) {
		this.type = type;
		this.name = name;
		id = -1;
		parentId = -1;
	}
	
	public int getId() {
		return id;
	}
	
	public RType getType() {
		return type;
	}

	public String getName() {
		return name;
	}
	
	public int getParent() {
		return parentId;
	}
	
	public boolean hasParent() {
		if (parentId != -1) return true;
		else return false;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeIntArray(new int [] {id, type.number, parentId});
		dest.writeString(name);
	}
	
	public static final Parcelable.Creator<ICItem> CREATOR = 
			new Parcelable.Creator<ICItem>() {
				@Override
				public ICItem createFromParcel(Parcel source) {
					return new ICItem(source);
				}

				@Override
				public ICItem[] newArray(int size) {
					return new ICItem[size]; 
				}
	};

	private ICItem(Parcel parcel) {
		int [] ints = new int [3];
		parcel.readIntArray(ints);
		id = ints[0];
		if (ints[1] == RType.COSTS.number) type = RType.COSTS;
		else type = RType.INCOME;
		name = parcel.readString();
		parentId = ints[2];
	}
			
			
	
}
