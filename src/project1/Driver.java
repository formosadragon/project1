//Mei-Ying Croddy
//CS141 
//Project 1
//4/24/18

package project1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.*;
import java.io.*;

public class Driver {

	// Add method to read the temps file
	 

	private static ArrayList<Record> recordList;
	private static PrintWriter outFile;
	private static Scanner readQuery;
	
	private static double sum;
	private static double average;
	private static int counter;
	private static int mode;
	private static ArrayList<Integer> getMode;
	private static ArrayList<ModeKeeper> findingMode;
	
	public static void readQueryFile(String queryFilePath) throws IOException
	{
		File outputFile = new File("results.dat");
		outFile = new PrintWriter(outputFile);
		ArrayList<Record> records = readTempFile(); // call temp file reading method
		sortRecordsList(records);
		// read your query file line by line and use the records to find the AVG or MODE
		
		getMode = new ArrayList<Integer>();
		findingMode = new ArrayList<ModeKeeper>();
		
		
		File queryFile = new File(queryFilePath);
		readQuery = new Scanner(queryFile);
		while(readQuery.hasNext()) 
		{
			sum = 0;
			counter = 0;
		String queryLine = readQuery.nextLine();
		String[] splitQuery = queryLine.split(" ");

		
	
		if(splitQuery[1].equals("AVG"))
		{
			//check for the average query
			outFile.print(splitQuery[0]); //prints
			outFile.print(" " + splitQuery[2] + " " + splitQuery[3] + " " + splitQuery[1]);
			for(int i = 0; i < records.size(); i++) 
			{
			
					if(Integer.parseInt(splitQuery[0]) == records.get(i).getStationID()) //if the station ids are the same
					{
						if(Integer.parseInt(splitQuery[2]) <= records.get(i).getYear() && Integer.parseInt(splitQuery[3]) >= records.get(i).getYear()) //checks if year is within timeframe
						{
							
							if(records.get(i).getTemperature() <= 50 && records.get(i).getTemperature() >= -50) //bounds acceptable temps
							{
								sum = sum + records.get(i).getTemperature();
								counter++;
							}
						}
					}

		}
			average = sum/counter;
			average = (Math.round(average*100))/100.0;
			//checks if there is a value
			if(counter == 0 )
			{
				outFile.print(" unknown");
			}
			else
			{
				outFile.print(" " + average);
			}
		} else if(splitQuery[1].equals("MODE"))
		{
			//check for Mode query 
			outFile.print(splitQuery[0]); //prints
			outFile.print(" " + splitQuery[2] + " " + splitQuery[3] + " " + splitQuery[1]);  //prints
			for(int i = 0; i < records.size(); i++) 
			{
			
					if(Integer.parseInt(splitQuery[0]) == records.get(i).getStationID()) //if the station ids are the same
					{
						if(Integer.parseInt(splitQuery[2]) <= records.get(i).getYear() && Integer.parseInt(splitQuery[3]) >= records.get(i).getYear()) //checks if year is within timeframe
						{
							
							if(records.get(i).getTemperature() <= 50 && records.get(i).getTemperature() >= -50) //bounds acceptable temps
							{
								int roundedTemp = (int)Math.round(records.get(i).getTemperature());
								int temp = roundedTemp;
								ModeKeeper m1 = new ModeKeeper(temp);
								findingMode.add(m1);
							}
						}
						
					}

					
			}
			
			for(int i = 0; i < findingMode.size(); i++)
			{
				int temporary = findingMode.get(i).getMode();
				for(int j = 0; j < findingMode.size(); j++)
				{
					if(findingMode.get(j).getMode() == temporary)
						findingMode.get(i).addToCount();
				}
				
				//System.out.println(findingMode.get(i).getMode());     test
				//System.out.println(findingMode.get(i).getCount());    test
			}
			int max = 0;
			mode = -51; //sentinel value
			
			for(int i = 0; i < findingMode.size(); i++)
			{
				
				if(findingMode.get(i).getCount() > max)
				{
					max = findingMode.get(i).getCount();
					mode = findingMode.get(i).getMode();
				//	System.out.print(max + " "+ mode);
				}
				
			}
			
			if(mode == -51 || max == 2)
			{
				outFile.print(" unknown");
			}
			else
			{
				outFile.print(" " + mode);
			}
			
			
		}
		
		outFile.println(); //prints
		}
		
	
		
		
		outFile.close();
	}
		
	
	public static ArrayList<Record> readTempFile() throws IOException
	{
	
		String fileName = "temps-70s.dat";
		File inputFile = new File(fileName);
		
		Scanner readFile = new Scanner(inputFile);
	

		
		while(readFile.hasNext())
		{ //pass file name directly; just pick one
			String line = readFile.nextLine();
			String[] splitLine = line.split(" ");
			Record r1 = new Record(Integer.parseInt(splitLine[0]),Integer.parseInt(splitLine[1]),Integer.parseInt(splitLine[2]),Double.parseDouble(splitLine[3]));
		//check contents
		//	outFile.println(r1.getStationID());
		//	outFile.println(r1.getYear());
		//	outFile.println(r1.getMonth());
		//	outFile.println(r1.getTemperature());
			
			
			if(recordList == null)
				recordList = new ArrayList<Record>();
			
			recordList.add(r1);
		
		}

		
		return recordList;

		/*
		String split example: 
		String string = "004-034556";
	String[] parts = string.split("-");
	String part1 = parts[0]; // 004
	String part2 = parts[1]; // 034556 
		 
		 */
		
	}
	
	
	// Add method to read the queries file

	
	// Add method to write to a results.dat file 
	
	public static void sortRecordsList(ArrayList<Record> recordArray){
		Collections.sort(recordArray,new Comparator<Record>() {
				public int compare(Record one, Record other){
					return new Integer(one.getStationID()).compareTo(other.getStationID()); //getters and setters, dont touch
				}
		});
		
		Collections.sort(recordArray,new Comparator<Record>() {
				public int compare(Record one, Record other){
					return new Integer(one.getYear()).compareTo(other.getYear());
				}
		});	
			
	}
	
	

	
	public static void main(String[] args) throws IOException {
		// ArrayList<Record> recordList = readTemperatureFile(); test code
	
	//	readTempFile();
		readQueryFile("queries-70s.dat");
		
		sortRecordsList(recordList);
		
		
	/* test	
		for(int i = 0; i< recordList.size(); i++)
		{
			System.out.println(recordList.get(i).getMonth());
		}
		*/
	}

}


//how to access a single variable in em:
//	System.out.println(recordList.get(i).getMonth());

/*
public static void readQueryFile(String queryFilePath)
	{
		ArrayList<Record> records = readTempFile("temps-7lines.dat"); // call temp file reading method
		sortRecordsList(records);
		// read your query file line by line and use the records to find the AVG or MODE
		
	}	
	
	public static ArrayList<Record> readTempFile(String tempFilePath)
	{
		ArrayList<Record> records = new ArrayList<>();
                // implement how to read file and add records to this arraylist
		return records;
	}
 */



 
 


 


//string split, string array --> constructor