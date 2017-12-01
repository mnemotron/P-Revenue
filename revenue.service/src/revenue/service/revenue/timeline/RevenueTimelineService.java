package revenue.service.revenue.timeline;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.query.Query;

import revenue.core.bond.Bond;
import revenue.core.bond.entity.BondHeaderResult;
import revenue.core.bond.entity.BondInterestResult;
import revenue.core.bond.entity.BondItemResult;
import revenue.entity.BondHeader;
import revenue.entity.Depot;
import revenue.hibernate.SessionManager;
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
		SessionManager.initSession();

		ResRevenueTimeline locResRevenueTimeline = new ResRevenueTimeline();

		try
		{

			if (reqRevenueTimeline.getPortfolioId() > 0 && reqRevenueTimeline.getDepotId() > 0 && reqRevenueTimeline.getBondIdList().isEmpty() == false)
			{

				ArrayList<Long> locBondIdList = reqRevenueTimeline.getBondIdList();

				Depot locDepot = queryDepot(reqRevenueTimeline.getPortfolioId(), reqRevenueTimeline.getDepotId());

				ArrayList<BondHeader> locBondHeaderList = queryBondList(reqRevenueTimeline.getPortfolioId(), reqRevenueTimeline.getDepotId(), locBondIdList);

				locResRevenueTimeline = calTimelineBond(reqRevenueTimeline, locDepot, locBondHeaderList);

			}
			else if (reqRevenueTimeline.getPortfolioId() > 0 && reqRevenueTimeline.getDepotId() > 0)
			{

				Depot locDepot = queryDepot(reqRevenueTimeline.getPortfolioId(), reqRevenueTimeline.getDepotId());

				ArrayList<BondHeader> locBondHeaderList = queryBondList(reqRevenueTimeline.getPortfolioId(), reqRevenueTimeline.getDepotId());

				locResRevenueTimeline = calTimelineBond(reqRevenueTimeline, locDepot, locBondHeaderList);

			}
			else if (reqRevenueTimeline.getPortfolioId() > 0)
			{

			}

		}
		catch (Exception e)
		{
			// if (locTransaction != null)
			// {
			// locTransaction.rollback();
			// }
		}
		finally
		{
			SessionManager.closeSession();
		}

		return locResRevenueTimeline;
	}

	private ResRevenueTimeline calTimelineBond(ReqRevenueTimeline reqRevenueTimeline, Depot depot, ArrayList<BondHeader> bondHeaderList)
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
			ArrayList<ResBondItemInterestRevenue> locResBondTotalInterestList = new ArrayList<ResBondItemInterestRevenue>();

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

			// total interest result
			for (BondInterestResult bondTotalInterestResult : bondHeaderResult.getBondTotalInterestResult())
			{
				ResBondItemInterestRevenue locBondTIR = new ResBondItemInterestRevenue();
				locBondTIR.setInterestDate(bondTotalInterestResult.getInterestDate().toInstant().toString());
				locBondTIR.setInterestRevenue(bondTotalInterestResult.getInterest());
				locResBondTotalInterestList.add(locBondTIR);
			}

			ResBond locResBond = new ResBond();
			locResBond.setBondId(bondHeaderResult.getBondHeader().getId());
			locResBond.setBondName(bondHeaderResult.getBondHeader().getName());
			locResBond.setBondItemBuyList(locResBondItemBuyList);
			locResBond.setBondTotalInterestResultList(locResBondTotalInterestList);
			locResBondList.add(locResBond);

		}

		ArrayList<ResDepot> locResDepotList = new ArrayList<ResDepot>();
		ResDepot locResDepot = new ResDepot();
		locResDepot.setDepotId(reqRevenueTimeline.getDepotId());
		locResDepot.setDepotName(depot.getName());
		locResDepot.setBondList(locResBondList);
		locResDepotList.add(locResDepot);

		locResRevenueTimeline.setPortfolioId(reqRevenueTimeline.getPortfolioId());
		locResRevenueTimeline.setDepotList(locResDepotList);

		return locResRevenueTimeline;
	}

	private Depot queryDepot(long portfolioId, long depotId) throws RuntimeException
	{
		Session locSession = SessionManager.getSession();

		Depot locDepot = null;

		Query locQuery = locSession.createQuery("FROM Depot WHERE portfolio_id = " + portfolioId + "AND id = " + depotId);

		locDepot = (Depot) locQuery.getSingleResult();

		return locDepot;
	}

	private ArrayList<BondHeader> queryBondList(long portfolioId, long depotId) throws RuntimeException
	{
		Session locSession = SessionManager.getSession();

		ArrayList<BondHeader> locBondHeaderList = null;

		Query locQuery = locSession.createQuery("FROM BondHeader WHERE portfolio_id = " + portfolioId + "AND depot_id = " + depotId);

		locBondHeaderList = (ArrayList<BondHeader>) locQuery.getResultList();

		return locBondHeaderList;
	}

	private ArrayList<BondHeader> queryBondList(long portfolioId, long depotId, ArrayList<Long> bondIdList) throws RuntimeException
	{
		Session locSession = SessionManager.getSession();

		ArrayList<BondHeader> locBondHeaderList = null;

		Query locQuery = locSession.createQuery("FROM BondHeader WHERE portfolio_id = " + portfolioId + "AND depot_id = " + depotId + "AND id IN (:BondIdList)");

		locQuery.setParameterList("BondIdList", bondIdList);

		locBondHeaderList = (ArrayList<BondHeader>) locQuery.getResultList();

		return locBondHeaderList;
	}
}