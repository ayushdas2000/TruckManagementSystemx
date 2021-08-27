package demo19014;
import base.*;

import java.util.*;

public  class Hub19014 extends Hub {

	public Hub19014(Location loc)
	{
		super(loc);
		listoftrucks = new ArrayList<>();
	}

	ArrayList<Truck> listoftrucks ;
	
	// add a Truck to the queue of the Hub.
	// returns false if the Q is full and add fails
	public boolean add(Truck truck)
	{
		if(this.listoftrucks.size()<this.getCapacity())
		{
			this.listoftrucks.add(truck);
			return true;
		}
		else
		{
			return false;		
		}
	}

	protected void remove(Truck truck)
	{
		this.listoftrucks.remove(truck);
	}



	// dfs(Hub curr_hub,Hub dest_hub)
	// {
	// 	ArrayList<Hub> stack = new ArrayList<>();
	// 	ArrayList<Hub> visited = new ArrayList<>();
	// 	Hub temp = curr_hub.getHighways().get(0).getEnd();
	// 	if(temp.getStart() == dest_hub.getStart() && temp.getEnd() == dest_hub.getEnd())
	// 	{

         
	// 	}
	// 	if(curr_hub.getHighways().size()>1)
	// 	{
	// 		stack.add(curr_hub);
	// 	}
	// 	while(stack.size()!= 0 || (curr_hub.getLoc().getX() == dest_hub.getLoc().getX() && curr_hub.getLoc().getY() == dest_hub.getLoc().getY() ))
	// 	{
	// 		for(int i =0;i<curr_hub.getHighways().size();i++)
	// 		{
	// 			if((stack.contains(curr_hub.getHighways().get(i).getEnd())!=true) && (curr_hub.getHighways().get(i).getEnd().getLoc().getX() == dest_hub.getLoc().getX() && curr_hub.getHighways().get(i).getEnd().getLoc().getY() == dest_hub.getLoc().getY()) )
	// 			{
	// 				curr_hub = curr_hub.getHighways().get(i).getEnd()
	// 				dfs(curr_hub,dest_hub);
	// 			}
	// 		}

	// 	}
	// }



	// Highway dfs(Hub curr_hub,Hub dest_hub)
	// {
	// 	// ArrayList<Hub> stack = new ArrayList<>();
	// 	// ArrayList<Hub> visited = new ArrayList<>();

	// 	// stack.add(curr_hub);
	// 	// visited.add(stack.get(stack.size()-1));
	// 	// stack.remove(stack.size()-1)
	// 	Hub temp;
	// 	for(int i = 0;i<curr_hub.getHighways().size();i++)
	// 	{
	// 		temp = curr_hub.getHighways().get(i).getEnd()
	// 		ArrayList<Hub> stack = new ArrayList<>();
	// 		ArrayList<Hub> visited = new ArrayList<>();

			
	// 		visited.add(temp);
	// 		stack.add(stack.size(),temp);
	// 		while(stack.size()!=0)
	// 		{
	// 			Hub top = stack.get(stack.size()-1);
	// 			Hub unvisited;
	// 			int counter = 0;
	// 			for(int j =0;j<top.getHighways.size();j++)
	// 			{
	// 				if(visited.contains(top.getHighways().get(i).getEnd())==false)
	// 				{
	// 					unvisited = top.getHighways().get(i).getEnd();
	// 					break;
	// 				}
	// 				else
	// 				{
	// 					counter++;
	// 				}
	// 			}
	// 			if(counter!=top.getHighways.size())
	// 			{
	// 				visited.add(unvisited);
	// 				stack.add(stack.size(),unvisited);
	// 			}
	// 			if(counter == top.getHighways.size())
	// 			{
	// 				visited.add(stack.get(stack.size()-1));
	// 				stack.remove(stack.size()-1);

	// 			}

	// 		}
	// 		if(visited.contains(dest_hub)==true)
	// 		{
	// 			return curr_hub.getHighways().get(i);
	// 		}		
	// 	}
	// }




	// provides routing information
	public synchronized Highway getNextHighway(Hub last, Hub dest)
	{


		for( Highway h: getHighways())
		{
			HashMap<Hub,Boolean> visitedlist = new HashMap<>();


			visitedlist.put(this,true);

			
			dfs(h.getEnd(),visitedlist);

			if(visitedlist.getOrDefault(dest,false))
			{
				return h;
			}
		}

		return null;

		


	}

	public synchronized void dfs(Hub hub,HashMap<Hub,Boolean> visitedlst)
	{
		visitedlst.put(hub,true);



		for(Highway hgw : hub.getHighways())
		{
			if(!visitedlst.getOrDefault(hgw.getEnd(),false))
			{
				dfs(hgw.getEnd(),visitedlst);
			}
		}
	}
	
	// draws a circle at its location. Override id needed

	

	


	// to be implemented in derived classes. Called at each time step


protected synchronized void processQ(int deltaT) {

        for (Truck t : listoftrucks)
        {


            Hub dest_Hub = Network.getNearestHub(t.getDest());


            if(dest_Hub != this)
            {


            	Highway nextHighway = getNextHighway(this, dest_Hub);

                if(nextHighway.add(t))
                {
                    this.remove(t);

                    // System.out.println("addonhighway");
                    t.enter(nextHighway);
                }
            }
            else
            {
 

                this.remove(t);


            }
        }
    }

	

}
