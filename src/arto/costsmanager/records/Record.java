package arto.costsmanager.records;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import android.os.Parcel;
import android.os.Parcelable;

public class Record implements Parcelable{

	public static String KEY_ITEMNAME = "ITEMNAME";
	public static String KEY_SUM = "SUM";
	public static String KEY_DATE = "DATE";
	public static String KEY_COMMENT = "COMMENT"; 
	public static String KEY_TYPE = "TYPE";
	public static String KEY_DATE_STRING = "DATE_STRING";
	public static String KEY_ID = "ID";
	
	public static String KEY_RECORD = "RECORD";
	
	private int id;
	
	private ICItem item;
	private double sum;
	private long date;
	private String comment;	
	private String dateString;
	
	private HashMap<String, Object> hashMap;
	
	public Record(Record r, int id) {
		this.id = id;
		this.item = r.item;
		this.sum = r.sum;
		this.date = r.date;
		this.comment = r.comment;
		setDateString();
		createHashMap();
	}
	
	public Record(ICItem item, double sum, long date, String comment) {		
		this.item = item;
		this.sum = sum;
		this.date = date;
		this.comment = comment;
		setDateString();
		id = -1;
		
		createHashMap();
	}
	
	
	public Record(int id, ICItem item, double sum, long date, 
			String comment) {
		this.item = item;
		this.sum = sum;
		this.date = date;
		this.comment = comment;
		this.id = id;
		setDateString();		
		createHashMap();
	}
	

	private void createHashMap() {
		hashMap = new HashMap<String, Object>();
		hashMap.put(KEY_ITEMNAME, item.getName());
		hashMap.put(KEY_SUM, sum);
		hashMap.put(KEY_DATE, date);
		hashMap.put(KEY_DATE_STRING, dateString);
		hashMap.put(KEY_COMMENT, comment);
		hashMap.put(KEY_TYPE, item.getType());
		hashMap.put(KEY_ID, id);
	}
	
	public String getItemName() {
		return item.getName();
	}
	
	public ICItem getICItem() {
		return item;
	}
	public double getSum() {
		return sum;
	}
	public long getDate() {
		return date;
	}
	public String getComment() {
		return comment;
	}
	public int getId() {
		return id;
	}
	public RType getType() {
		return item.getType();
	}
	
	public HashMap<String, Object> getAsMap() {
		return hashMap;
	}	
	
	public String getDateString() {
		return dateString;
	}
	
	private void setDateString() {
		dateString = SimpleDateFormat.getDateTimeInstance().
				format(new Date(date));
	}


	@Override
	public int describeContents() {
		return 0;
	}


	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(item, 0);
		dest.writeDouble(sum);
		dest.writeString(comment);
		dest.writeLong(date);
		dest.writeInt(id);
	}
	
	public static final Parcelable.Creator<Record> CREATOR = 
			new Parcelable.Creator<Record>() {
				@Override
				public Record createFromParcel(Parcel source) {
					return new Record(source);
				}

				@Override
				public Record[] newArray(int size) {
					return new Record[size];
				}
	};
	
	
	private Record(Parcel parcel) {
		item = parcel.readParcelable(ICItem.class.getClassLoader());
		sum = parcel.readDouble();
		comment = parcel.readString();
		date = parcel.readLong();
		id = parcel.readInt();
		setDateString();
		createHashMap();
	}
	
	
	
	
	
	
	
	
	
}
