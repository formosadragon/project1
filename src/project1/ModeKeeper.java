//Mei-Ying Croddy
//Project 1
//4/24/18

package project1;
import java.util.*;
//class to store information about mode 
public class ModeKeeper {

	private int mode;
	private int count;

	
	
	public ModeKeeper(int value)
	{
		mode = value;
		count = 1;
	}
	
	public void setMode(int i)
	{
		mode = i;
	}
	
	public int getMode()
	{
		return mode;
		
	}

	
	public void addToCount()
	{
		count = count + 1;
	}
	
	public int getCount()
	{
		return count;
	}
}
