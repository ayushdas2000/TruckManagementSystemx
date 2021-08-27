package demo19014;
import base.*;
import java.util.ArrayList;
import java.lang.Math; 





public class Truck19014 extends Truck {
	
	int time =0;
	Hub lasthub;
	Highway currenthighway;
	private boolean onHub;
	private boolean onHighway;
	// the Hub from which it last exited. 
	@Override
	public String getTruckName() {
		return "Truck19014";
	}

	public Hub getLastHub()
	{
		return lasthub;
	}

	public void enter(Highway hwy)
	{
		currenthighway = hwy;
		lasthub = currenthighway.getStart();
		onHighway = true;
		onHub = false;
	}
	


	// called every deltaT time to update its status/position
	// If less than startTime, does nothing
	// If at a hub, tries to move on to next highway in its route
	// If on a road/highway, moves towards next Hub
	// If at dest Hub, moves towards dest




	




protected synchronized void update(int deltaT)
	{
		time = time + deltaT;
		if(time <=getStartTime())
		{
			return;		
		}


		if( getDest() == null )
		{
				return;
		}

		if( this.getDest().getX() == this.getLoc().getX() && this.getDest().getY() == this.getLoc().getY()) 
		{
			return;
		}


		if( !onHub && !onHighway) 
		{

			Hub nxtHub = Network.getNearestHub(this.getLoc());

			if( this.getLoc().getX() == nxtHub.getLoc().getX() && this.getLoc().getY() == nxtHub.getLoc().getY()) 
			{


				if(nxtHub.add(this))
				{
					onHub = true;
				}

			}
			 this.setLoc(nxtHub.getLoc());
		}


		else if(onHub) 
		{

            Hub destHub = Network.getNearestHub(this.getDest());


            if(destHub.getLoc().getX() == this.getLoc().getX() &&   destHub.getLoc().getY() == this.getLoc().getY()) 
            {

            	this.setLoc(this.getDest());


            }
		}



		else if(onHighway) 
		{


			Hub nxtHub = currenthighway.getEnd();


			if(this.getLoc().getX() == nxtHub.getLoc().getX() &&	this.getLoc().getY() == nxtHub.getLoc().getY())
			{

				if(nxtHub.add(this))
				{
					onHub = true;
					onHighway = false;
					currenthighway.remove(this);
				}
			}

			 go(nxtHub.getLoc(), deltaT);
		}


	}


	private synchronized void go(Location loc,int deltaT)
	{
		double x = this.getLoc().getX();
		double y = this.getLoc().getY();

		int newX = loc.getX();
		int newY = loc.getY();

		double p = newY - y;
		double b = newX - x;
		double h = Math.sqrt((p*p)+(b*b));
		double sin = p/h;
		double cos = b/h;
		double d = (deltaT*this.currenthighway.getMaxSpeed())/500;

		if(d >= h)
		{
			this.setLoc(loc);

			return;
		}

		double x_incremented = x + (d*cos);
		double y_incremented = y + (d*sin);
		Location l = new Location((int)x_incremented,(int)y_incremented);
		this.setLoc(l);

	}
}
