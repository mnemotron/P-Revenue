package revenue.core.depot;

import java.util.ArrayList;

import revenue.core.bond.Bond;

public class Depot {

	private ArrayList<Bond> bondList;

	public Depot() {
		this.bondList = new ArrayList<Bond>();
	}

	public Depot(Bond bond) {
		this.managerBond = bond;
	}

	public void calReturnTimeLine() {
		
		//bond
		this.calReturnTimeLineBond();
	}
	
	private void calReturnTimeLineBond()
	{
		for (Bond bond : bondList) {
			bond.calReturnTimeline();
		}
	}

}
