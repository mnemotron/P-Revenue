package revenue.service.revenue.timeline;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import revenue.core.bond.Bond;
import revenue.core.bond.entity.BondHeaderResult;
import revenue.core.bond.entity.BondInterestResult;
import revenue.core.bond.entity.BondItemResult;
import revenue.entity.BondHeader;
import revenue.hibernate.HibernateSessionFactory;
import revenue.service.revenue.timeline.entity.ReqRevenueTimeline;
import revenue.service.revenue.timeline.entity.ResBond;
import revenue.service.revenue.timeline.entity.ResBondItemBuy;
import revenue.service.revenue.timeline.entity.ResBondItemInterestRevenue;
import revenue.service.revenue.timeline.entity.ResDepot;
import revenue.service.revenue.timeline.entity.ResRevenueTimeline;

@Path("/service")
public class RevenueTimelineService
{

	@POST
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

			locResRevenueTimeline = calTimelineBond(reqRevenueTimeline, locBondHeaderList);

		}
		else if (reqRevenueTimeline.getPortfolioId() > 0 && reqRevenueTimeline.getDepotId() > 0)
		{

		}
		else if (reqRevenueTimeline.getPortfolioId() > 0)
		{

		}

		locTransaction.commit();
		
		locSession.close();

		return locResRevenueTimeline;
	}

	private ResRevenueTimeline calTimelineBond(ReqRevenueTimeline reqRevenueTimeline, ArrayList<BondHeader> bondHeaderList)
	{
		ResRevenueTimeline locResRevenueTimeline = new ResRevenueTimeline();

		Bond locBond = new Bond();

		// calculate revenue timeline
		for (BondHeader bondHeader : bondHeaderList)
		{
			locBond.addBond(bondHeader);
		}

		locBond.calReturnTimeline();

		ArrayList<BondHeaderResult> locBondHeaderResultList = locBond.getResult();

		// return result
		ArrayList<ResBond> locResBondList = new ArrayList<ResBond>();

		for (BondHeaderResult bondHeaderResult : locBondHeaderResultList)
		{

			ArrayList<ResBondItemBuy> locResBondItemBuyList = new ArrayList<ResBondItemBuy>();

			ArrayList<BondItemResult> locBondItemResult = bondHeaderResult.getBondItemsResult();

			for (BondItemResult bondItemResult : locBondItemResult)
			{
				ArrayList<ResBondItemInterestRevenue> locResBondItemInterestRevenueList = new ArrayList<ResBondItemInterestRevenue>();

				ArrayList<BondInterestResult> locBondInterestResult = bondItemResult.getBondInterestDates();

				for (BondInterestResult bondInterestResult : locBondInterestResult)
				{
					ResBondItemInterestRevenue locBondItemInterestRevenue = new ResBondItemInterestRevenue();
					locBondItemInterestRevenue.setInterestDate(bondInterestResult.getInterestDate().toInstant().toString());
					locBondItemInterestRevenue.setInterestRevenue(bondInterestResult.getInterest());
					locResBondItemInterestRevenueList.add(locBondItemInterestRevenue);
				}

				ResBondItemBuy locResBondItemBuy = new ResBondItemBuy();
				locResBondItemBuy.setBondItemBuyId(bondItemResult.getBondItemBuy().getId());
				locResBondItemBuy.setBondItemInterestRevenueList(locResBondItemInterestRevenueList);
				locResBondItemBuyList.add(locResBondItemBuy);
			}

			ResBond locResBond = new ResBond();
			locResBond.setBondId(bondHeaderResult.getBondHeader().getId());
			locResBond.setBondItemBuyList(locResBondItemBuyList);
			locResBondList.add(locResBond);

		}

		ArrayList<ResDepot> locResDepotList = new ArrayList<ResDepot>();
		ResDepot locResDepot = new ResDepot();
		locResDepot.setDepotId(reqRevenueTimeline.getDepotId());
		locResDepot.setBondList(locResBondList);
		locResDepotList.add(locResDepot);

		locResRevenueTimeline.setPortfolioId(reqRevenueTimeline.getPortfolioId());
		locResRevenueTimeline.setDepotList(locResDepotList);

		return locResRevenueTimeline;
	}
}