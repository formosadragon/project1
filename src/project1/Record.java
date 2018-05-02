//Mei-Ying Croddy
//CS141 
//Project 1
//4/24/18

package project1;
import java.io.File;
import java.io.IOException;
import java.util.*;
public class Record implements Comparable<Record>{
	private int stationID;
	private int year;
	private int month;
	private double temperature;



	
	public int compareTo(Record other){
		return new Integer(year).compareTo(other.year);
	}
	 // getters and setters for data fields 1, constructor 2; nothing else in here
	
	public Record(int id, int yr, int mon, double temp) 
	{
		stationID = id;
		year = yr;
		month = mon;
		temperature = temp;
	}
	
	public void setStationID(int i) 
	{
		stationID = i;
	}
	
	public int getStationID() 
	{
		return stationID;
	}
	
	public void setYear(int i)
	{
		year = i;
	}
	
	public int getYear() 
	{
		return year;
	}
	
	public void setMonth(int i)
	{
		month = i;
	}
	
	public int getMonth() 
	{
		return month;
	}
	
	public void setTemperature(double d)
	{
	temperature = d;
	}
	
	public double getTemperature()
	{
		return temperature;
	}
	
	
}
// throws IOException 