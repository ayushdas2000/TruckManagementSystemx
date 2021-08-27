package demo19014;
import base.*;

import java.util.ArrayList;

// We assume only one instance of Network will be created for a program
public  class Network19014 extends Network {
	ArrayList<Hub> listofhbs = new ArrayList<>();
	ArrayList<Highway> listofhghways = new ArrayList<>();
	ArrayList<Truck> listoftrcks = new ArrayList<>();
	
	// keep track of the following entities
	public void add(Hub hub)
	{
		listofhbs.add(hub);
	}
	public void add(Highway hwy)
	{
		listofhghways.add(hwy);
	}
	public void add(Truck truck)
	{
		listoftrcks.add(truck);
	}
	

	// finds the nearest Hub to a given location
	// Made static so that other classes don't need to keep track of Network instance

	
	// start the simulation
	// derived class calls start on each of the Hubs and Trucks
	public void start()
	{
		for(Truck t:listoftrcks)
		{
			t.start();
		}
		for(Hub h:listofhbs)
		{
			h.start();
		}
	}



	// derived class calls draw on each hub, highway, and truck
	// passing in display
	public void redisplay(Display disp)
	{
		for(Truck t:listoftrcks)
		{
			t.draw(disp);
		}
		for(Hub h:listofhbs)
		{
			h.draw(disp);
		}	
		for(Highway hg:listofhghways)
		{
			hg.draw(disp);
		}
	}
	
	protected  Hub findNearestHubForLoc(Location loc)
	{
		ArrayList<Double> distance =new ArrayList<>();
		for(Hub h : listofhbs)
		{
			distance.add(Double.valueOf(((loc.getX()-h.getLoc().getX())*(loc.getX()-h.getLoc().getX()))+((loc.getY()-h.getLoc().getY())*(loc.getY()-h.getLoc().getY()))));
		}
		double min =distance.get(0);
		int counter =0;
		for(int i=1;i<distance.size();i++)
		{
			if(distance.get(i)<min)
			{
				min = distance.get(i);
				counter =i;
			}
		}
		return listofhbs.get(counter);

	}
	
	static Network theNet;
}
