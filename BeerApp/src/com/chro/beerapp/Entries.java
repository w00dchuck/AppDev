package com.chro.beerapp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;



<<<<<<< HEAD
public class Entries {	
	public List<Entry> Entries = new LinkedList<Entry>();
	public List<Entry> LastQuery = new LinkedList<Entry>(); //Zuletzt angeforderten Daten
=======
public class Entries {
	public ArrayList<Entry> Entries = new ArrayList<Entry>();
	public ArrayList<Entry> LastQuery = new ArrayList<Entry>(); //Zuletzt angeforderten Daten
>>>>>>> a1ff0fa8423873ba60ed417ee1055a140f33ec6e
	TimeZone tz ; // et Timezone
	Calendar calBeg; // initialize Beginning Calendar
	Calendar calEnd;
	Date d = new Date();
	protected static Entries instants = null;
	protected Entries()
	{
		SetUpTimeEssentials();
		/*@TODO add the calendars here aswell */
<<<<<<< HEAD
		Entry i = new Entry(0, "Bier", 0.1f, 3, "0188888888",99.0f,27.0f,calBeg,calEnd);
		Entry i1 = new Entry(1, "Bier", 10.0f, 3, "0188888888",33.0f,27.0f,calBeg,calEnd);
		Entry i2 = new Entry(2, "Bier", 10.0f, 3, "0188888888",34.0f,23.0f,calBeg,calEnd);
		Entries.add(i);
		Entries.add(i1);
		Entries.add(i2);
=======
		int i=2;
		Entry e = new Entry(0,"Becks"+i,0.67f,4,"details",51.039926f, 13.731029f,"2014-04-23 16:29","2014-04-23 18:29");
		Entry e1 = new Entry(1,"Chips"+i,i*0.67f,5,"details",51.044055f, 13.733217f,"2014-04-23 16:29","2014-04-23 18:29");
		Entry e2 = new Entry(2,"Fanta"+i,i*0.67f,3,"details",51.044459f, 13.738882f,"2014-04-23 16:29","2014-04-23 18:29");
		Entries.add(e);
		Entries.add(e1);
		Entries.add(e2);
>>>>>>> a1ff0fa8423873ba60ed417ee1055a140f33ec6e
		LastQuery = Entries;
	}
	public void SetUpTimeEssentials()
	{
		TimeZone tz = TimeZone.getDefault(); // et Timezone
		Calendar calBeg = new GregorianCalendar(tz); // initialize Beginning Calendar
		Calendar calEnd = new GregorianCalendar(tz);
		Date d = new Date();
		calBeg.setTime(d); // Set time to cur Time
		calEnd.setTime(d);
		calBeg.set(Calendar.HOUR_OF_DAY,23);
		calEnd.set(Calendar.HOUR_OF_DAY,24);
		calBeg.set(Calendar.DAY_OF_WEEK, 0);		
		
	}
	public static Entries getInstance()
	{
		if(instants == null)
		{
			instants = new Entries();
		}
		return instants;
	}
<<<<<<< HEAD
	/*@TODO getEntrieByCategory
	 * @TODO getNameByCategory
	 * @TOdo getByUser
	 */
	public List<Entry> getEntriesByName(String Name,int Category)
=======
	public ArrayList<Entry> getEntriesByName(String Name)
>>>>>>> a1ff0fa8423873ba60ed417ee1055a140f33ec6e
	{
		LastQuery = Entries;
		return Entries;
	}
	public ArrayList<Entry> getEntriesByLocation(int Categorie,float latiude,float longitude,int Radius)
	{
		LastQuery = Entries;
		return Entries;
		
	}
	public ArrayList<Entry> getEntriesByID(int UserID)
	{
		LastQuery = Entries;
		return Entries;
		
	}
<<<<<<< HEAD
	
	public List<Entry> getLastRequest()
=======
	public ArrayList<Entry> getLastRequest()
>>>>>>> a1ff0fa8423873ba60ed417ee1055a140f33ec6e
	{
		return LastQuery;
	}
	
	
}
