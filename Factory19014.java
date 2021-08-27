package demo19014;
import base.*;
import java.util.ArrayList;


public class Factory19014 extends Factory {

		 public Network createNetwork()
		 {
		 	Network net = new Network19014();
		 	return net;
		 }
		 public Highway createHighway()
		 {
		 	Highway hi = new Highway19014();
		 	return hi;
		 }
		 public Hub createHub(Location location)
		 {
		 	Hub h  = new Hub19014(location);
		 	return h;
		 }
		 public Truck createTruck()
		 {
		 	Truck t = new Truck19014();
		 	return t;
		 }
	
}
