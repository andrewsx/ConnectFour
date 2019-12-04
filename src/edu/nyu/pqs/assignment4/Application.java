package edu.nyu.pqs.assignment4;

import edu.nyu.pqs.assignment4.model.ConnectFour;
import edu.nyu.pqs.assignment4.model.IConnectFour;
import edu.nyu.pqs.assignment4.view.Observer;

public class Application {

	public static void main(String[] args) {
		
		
		IConnectFour model = ConnectFour.getInstance();
		new Observer(model);
		new Observer(model);
		

	}

}
