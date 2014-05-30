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
	
	private RType type;
	private String name;
	private ICItem parent;
	
	public ICItem(RType type, String name, ICItem parent) {
		this.type = type;
		this.name = name;
		this.parent = parent;
	}

	public ICItem(RType type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public RType getType() {
		return type;
	}

	public String getName() {
		return name;
	}
	
	public ICItem getParent() {
		return parent;
	}
	
	public boolean hasParent() {
		if (parent == null) return false;
		else return true;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(type.number);
		dest.writeString(name);
		dest.writeParcelable(parent, 0);
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
		if (parcel.readInt() == RType.COSTS.number) type = RType.COSTS;
		else type = RType.INCOME;
		name = parcel.readString();
		parent = parcel.readParcelable(ICItem.class.getClassLoader());
	}
			
			
	
}
