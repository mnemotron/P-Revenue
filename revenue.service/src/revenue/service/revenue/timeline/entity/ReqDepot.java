package revenue.service.revenue.timeline.entity;

import java.util.ArrayList;

public class ReqDepot {

	private long depotId;

	private ArrayList<Long> bondIdList = new ArrayList<Long>();
	
	public ReqDepot() {

	}

	public long getDepotId() {
		return depotId;
	}

	public void setDepotId(long depotId) {
		this.depotId = depotId;
	}

	public ArrayList<Long> getBondIdList() {
		return bondIdList;
	}

	public void setBondIdList(ArrayList<Long> bondIdList) {
		this.bondIdList = bondIdList;
	}
	
}
