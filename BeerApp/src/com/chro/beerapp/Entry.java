package com.chro.beerapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class Entry {
	private String category;
	private String productName;
	private float price;
	private int quantity;
	private String contactDetails;
	public float latitude;
	public float longtitude;
	public TimeZone tz;
	public Calendar beginTime;
	public Calendar endTime;
	public int userId;
	public boolean active;
	public int id;

	public int getUser_id() {
		return userId;
	}

	public void setUser_id(int userId) {
		this.userId = userId;
	}

	public Entry(int id, String category, String productName, float price, int quantity,
			String contactDetails, float latitude, float longtitude,
			Calendar beginTime, Calendar endTime, int userId, boolean active,boolean retry) {
		this.id=id;
		this.category = category;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.contactDetails = contactDetails;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.userId = userId;
		this.active = active;
	}

	// constructor with Times as String for SQLiteDBHandler
	public Entry(int id, String category, String productName, float price, int quantity,
			String contactDetails, float latitude, float longtitude,
			String beginTimeString, String endTimeString,int user_id,boolean active,boolean retry) {
		this.id = id;
		this.category = category;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.contactDetails = contactDetails;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.setBeginTimeFromString(beginTimeString);
		this.setEndTimeFromString(endTimeString);
		this.userId = user_id;
		this.active = active;
	}
	public Entry(String productName) {
		this.productName = productName;
	}

	public Calendar getBeginTime() {
		return beginTime;
	}
	public int getID()
	{
		return id;
	}

	public void setBeginTime(Calendar beginTime) {
		this.beginTime = beginTime;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public String getBeginTimeAsString() {
		return this.getTimeAsString(this.beginTime);
	}

	public String getEndTimeAsString() {
		return this.getTimeAsString(this.endTime);
	}

	public void setBeginTimeFromString(String timeString) {
		this.beginTime = this.getTimeFromString(timeString);
	}

	public void setEndTimeFromString(String timeString) {
		this.endTime = this.getTimeFromString(timeString);
	}

	private String getTimeAsString(Calendar calendar) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm",
				Locale.US);
		return sdf.format(calendar.getTime());
	}

	private Calendar getTimeFromString(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm",
				Locale.US);
		Calendar cal = Calendar.getInstance();
		//try {
			String[] f = dateString.split(" ");
			String[] s;
			if(f.length > 1)
			{
				String[] l =f[0].split("-");
				cal.set(Calendar.YEAR, Integer.valueOf(l[0]));
				cal.set(Calendar.MONTH, Integer.valueOf(l[1]));
				cal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(l[2]));
				s = f[1].split(":");
			}else{
				s = dateString.split(":");
			}
			cal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(s[0]));
			cal.set(Calendar.MINUTE, Integer.valueOf(s[1]));
			//cal.setTime(sdf.parse(dateString));
		//} catch (ParseException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		return cal;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}

	/*
	 * @Note you are an idiot don't put that here it has to go to the
	 * CreateActivty public void SetUpTimeEssentials() { TimeZone tz =
	 * TimeZone.getDefault(); // et Timezone Calendar cal = new
	 * GregorianCalendar(tz); // initialize Calendar Date d = new Date();
	 * cal.setTime(d); // Set time to a new time @Note is it set to now? }
	 */
	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(float longtitude) {
		this.longtitude = longtitude;
	}
	public boolean getActive(){
		return active;
	}
	public void setActive(boolean b)
	{
		active = b;
	}
	public String getCategory(){
		return category;
	}
	public String getCategoryAsString() {  
        String s = String.valueOf(category); 
		return (s);
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}
}
