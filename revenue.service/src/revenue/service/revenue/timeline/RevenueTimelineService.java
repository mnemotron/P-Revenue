package revenue.service.revenue.timeline;

import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import revenue.entity.BondHeader;
import revenue.hibernate.HibernateSessionFactory;
import revenue.service.revenue.timeline.entity.ReqRevenueTimeline;
import revenue.service.revenue.timeline.entity.ResRevenueTimeline;

@Path("/service")
public class RevenueTimelineService
{

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getRevenueTimeline")
	public ResRevenueTimeline getRevenueTimeline(ReqRevenueTimeline reqRevenueTimeline)
	{
		Session locSession = HibernateSessionFactory.getSessionFactory().getCurrentSession();

		Transaction locTransaction = locSession.beginTransaction();

		ResRevenueTimeline locResRevenueTimeline = new ResRevenueTimeline();

		if (reqRevenueTimeline.getPortfolioId() > 0 && reqRevenueTimeline.getDepotId() > 0 && reqRevenueTimeline.getBondIdList().isEmpty() == false)
		{

			ArrayList<Long> locBondIdList = reqRevenueTimeline.getBondIdList();

			Query locQuery = locSession.createQuery("FROM BondHeader WHERE portfolio_id = " + reqRevenueTimeline.getPortfolioId() + "AND depot_id = " + reqRevenueTimeline.getDepotId()
					+ "AND id IN (:BondIdList)");

			locQuery.setParameterList("BondIdList", locBondIdList);

			ArrayList<BondHeader> locBondHeaderList = (ArrayList<BondHeader>) locQuery.getResultList();
			
			//Revenue Berechnung pro Bond
			
			//Map to result

		}
		else if (reqRevenueTimeline.getPortfolioId() > 0 && reqRevenueTimeline.getDepotId() > 0)
		{

		}
		else if (reqRevenueTimeline.getPortfolioId() > 0)
		{

		}

		locSession.close();

		return locResRevenueTimeline;
	}
}

//// ITEMS
//// nominal value: 2000, buy date: 01.02.2017, buy value: 100%
// BondItemBuy locBondItem1 = new BondItemBuy(2000, new Date(117, 1, 1), 100,
//// null, null);
//
// ArrayList<BondItemBuy> locBondItemsBuy = new ArrayList<BondItemBuy>();
// locBondItemsBuy.add(locBondItem1);
//
//// HEADER
//// interest date: 21.05., due date: 21.05.2020
// BondHeader locBondHeader = new BondHeader("JUNG, DMS ANL.15/20", "Insurance",
//// "A14J9D", "DE000A14J9D9", 6.0, BondHeader.INTEREST_INTERVALL_YEARLY, new
//// Date(117, 4, 21), new Date(120, 4, 21), locBondItemsBuy);
//
// Bond bond = new Bond(locBondHeader);
//
// bond.calReturnTimeline();
