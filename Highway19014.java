package demo19014;

import base.*;

import java.util.ArrayList;


// Highways are unidirectional

public  class Highway19014 extends Highway {
	

	ArrayList<Truck> listoftrucksonhighway = new ArrayList<>();
	// returns true if Highway is not full
	// i.e. number of trucks is below capacity
	public boolean hasCapacity()
	{
		if(this.listoftrucksonhighway.size()<this.getCapacity())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	// fails if already at capacity
	public boolean add(Truck truck)
	{
		if(this.hasCapacity() == true)
		{
			listoftrucksonhighway.add(truck);
			return true;
		}
		else
		{
			return false;
		}
	}
	public void remove(Truck truck)
	{	
		listoftrucksonhighway.remove(truck);

	}
		

}
	
