package revenue.core.timeline.entity;

import java.util.ArrayList;

import revenue.entity.Depot;

public class TimelineDepot {
	
	private Depot depot;

	private ArrayList<TimelineBondHeader> bondHeaderResult;

	public ArrayList<TimelineBondHeader> getBondHeaderResult() {
		return bondHeaderResult;
	}

	public void setBondHeaderResult(ArrayList<TimelineBondHeader> bondHeaderResult) {
		this.bondHeaderResult = bondHeaderResult;
	}

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}

}
