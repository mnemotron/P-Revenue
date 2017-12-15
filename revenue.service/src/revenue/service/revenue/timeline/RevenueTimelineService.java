package revenue.service.revenue.timeline;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.query.Query;

import revenue.core.timeline.Timeline;
import revenue.core.timeline.entity.TimelineDepot;
import revenue.core.timeline.entity.TimelinePortfolio;
import revenue.entity.BondHeader;
import revenue.entity.Depot;
import revenue.entity.Portfolio;
import revenue.hibernate.SessionManager;
import revenue.service.revenue.timeline.entity.ReqDepot;
import revenue.service.revenue.timeline.entity.ReqRevenueTimeline;
import revenue.service.revenue.timeline.entity.ResDepot;
import revenue.service.revenue.timeline.entity.ResRevenueTimeline;

@Path("/service")
public class RevenueTimelineService
{

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getRevenueTimeline")
	public ResRevenueTimeline getRevenueTimeline(ReqRevenueTimeline reqRevenueTimeline) throws Exception
	{
		SessionManager.initSession();
		
		ResRevenueTimeline locResRevenueTimeline = new ResRevenueTimeline();

		try
		{
			
			// query portfolio
			Portfolio locPortfolio = queryPortfolio(reqRevenueTimeline.getPortfolioId());
			
			// query depots
			if ((reqRevenueTimeline.getDepotList() != null) && !(reqRevenueTimeline.getDepotList().isEmpty()))
			{
				ArrayList<Depot> locDepotList = getDepot(locPortfolio, reqRevenueTimeline.getDepotList());
				locPortfolio.setDepot(locDepotList);
			}
			
			// calculate timeline
			Timeline locTimeline = new Timeline(locPortfolio);
			
			locTimeline.calTimeline();
			
			TimelinePortfolio locTimelinePortfolio = locTimeline.getResult();
			
			// build response
			locResRevenueTimeline = buildResponse(locTimelinePortfolio);
			
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			SessionManager.closeSession();
		}

		return locResRevenueTimeline;
	}

	private ArrayList<Depot> getDepot(Portfolio portfolio, ArrayList<ReqDepot> reqDepotList) throws Exception {

		// query depot
		ArrayList<Depot> locDepotList = new ArrayList<Depot>();

		// each depot
		for (ReqDepot reqDepot : reqDepotList) {
			
			// query depot
			Depot locDepot = queryDepot(portfolio.getId(), reqDepot.getDepotId());
			
			ArrayList<BondHeader> locBondHeaderList = new ArrayList<BondHeader>();

			// whole depot
			if((reqDepot.getBondIdList() == null) || (reqDepot.getBondIdList().isEmpty()))
			{
				locBondHeaderList = queryBondList(portfolio.getId(), locDepot.getId());
			}
			else
			{
			
				// bond
				if ((reqDepot.getBondIdList() != null) && !(reqDepot.getBondIdList().isEmpty()))
				{
					locBondHeaderList = queryBondList(portfolio.getId(), locDepot.getId(), reqDepot.getBondIdList());
				}
			
			}
			
			locDepot.setBondHeader(locBondHeaderList);
			
			locDepotList.add(locDepot);
		}
		
		return locDepotList;
	}
	
	private ResRevenueTimeline buildResponse(TimelinePortfolio timelinePortfolio)
	{
		ResRevenueTimeline locResRevenueTimeline = new ResRevenueTimeline();
		
		//map portfolio
		locResRevenueTimeline.setPortfolioId(timelinePortfolio.getPortfolio().getId());
		
		//map depot
		ArrayList<ResDepot> locResDepotList = new ArrayList<ResDepot>();
		
		for (TimelineDepot timelineDepot : timelinePortfolio.getBondDepotResult()) {
			
			ResDepot locResDepot = new ResDepot();
			
			locResDepot.setDepotId(timelineDepot.getDepot().getId());
			locResDepot.setDepotName(timelineDepot.getDepot().getName());
			
//			locResDepot.setBondList();
			
			locResDepotList.add(locResDepot);
		}


//		ArrayList<BondHeaderResult> locBondHeaderResultList = locBond.getResult();
//
//		// return result
//		ArrayList<ResBondHeader> locResBondList = new ArrayList<ResBondHeader>();
//
//		for (BondHeaderResult bondHeaderResult : locBondHeaderResultList)
//		{
//
//			ArrayList<ResBondItemBuy> locResBondItemBuyList = new ArrayList<ResBondItemBuy>();
//			ArrayList<ResBondItemInterestRevenue> locResBondTotalInterestList = new ArrayList<ResBondItemInterestRevenue>();
//
//			ArrayList<BondItemResult> locBondItemResult = bondHeaderResult.getBondItemsResult();
//
//			for (BondItemResult bondItemResult : locBondItemResult)
//			{
//				ArrayList<ResBondItemInterestRevenue> locResBondItemInterestRevenueList = new ArrayList<ResBondItemInterestRevenue>();
//
//				ArrayList<BondInterestResult> locBondInterestResult = bondItemResult.getBondInterestDates();
//
//				for (BondInterestResult bondInterestResult : locBondInterestResult)
//				{
//					ResBondItemInterestRevenue locBondItemInterestRevenue = new ResBondItemInterestRevenue();
//					locBondItemInterestRevenue.setInterestDate(bondInterestResult.getInterestDate().toInstant().toString());
//					locBondItemInterestRevenue.setInterestRevenue(bondInterestResult.getInterest());
//					locResBondItemInterestRevenueList.add(locBondItemInterestRevenue);
//				}
//
//				ResBondItemBuy locResBondItemBuy = new ResBondItemBuy();
//				locResBondItemBuy.setBondItemBuyId(bondItemResult.getBondItemBuy().getId());
//				locResBondItemBuy.setBondItemInterestRevenueList(locResBondItemInterestRevenueList);
//				locResBondItemBuyList.add(locResBondItemBuy);
//			}
//
//			// total interest result
//			for (BondInterestResult bondTotalInterestResult : bondHeaderResult.getBondTotalInterestResult())
//			{
//				ResBondItemInterestRevenue locBondTIR = new ResBondItemInterestRevenue();
//				locBondTIR.setInterestDate(bondTotalInterestResult.getInterestDate().toInstant().toString());
//				locBondTIR.setInterestRevenue(bondTotalInterestResult.getInterest());
//				locResBondTotalInterestList.add(locBondTIR);
//			}
//
//			ResBondHeader locResBond = new ResBondHeader();
//			locResBond.setBondId(bondHeaderResult.getBondHeader().getId());
//			locResBond.setBondName(bondHeaderResult.getBondHeader().getName());
//			locResBond.setBondItemBuyList(locResBondItemBuyList);
//			locResBond.setBondTotalInterestResultList(locResBondTotalInterestList);
//			locResBond.setStartDate(bondHeaderResult.getStartDate().toInstant().toString());
//			locResBond.setEndDate(bondHeaderResult.getEndDate().toInstant().toString());
//			locResBondList.add(locResBond);
//
//		}
//
//		ArrayList<ResDepot> locResDepotList = new ArrayList<ResDepot>();
//		ResDepot locResDepot = new ResDepot();
//		locResDepot.setDepotId(reqRevenueTimeline.getDepotId());
//		locResDepot.setDepotName(depot.getName());
//		locResDepot.setBondList(locResBondList);
//		locResDepotList.add(locResDepot);
//
//		locResRevenueTimeline.setPortfolioId(reqRevenueTimeline.getPortfolioId());
//		locResRevenueTimeline.setDepotList(locResDepotList);

		return locResRevenueTimeline;
	}
	
	private Portfolio queryPortfolio(long portfolioId) throws Exception
	{
		Session locSession = SessionManager.getSession();

		Portfolio locPortfolio = null;

		Query locQuery = locSession.createQuery("FROM Portfolio WHERE portfolio_id = " + portfolioId);

		locPortfolio = (Portfolio) locQuery.getSingleResult();

		return locPortfolio;
	}

	private Depot queryDepot(long portfolioId, long depotId) throws Exception
	{
		Session locSession = SessionManager.getSession();

		Depot locDepot = null;

		Query locQuery = locSession.createQuery("FROM Depot WHERE portfolio_id = " + portfolioId + "AND id = " + depotId);

		locDepot = (Depot) locQuery.getSingleResult();

		return locDepot;
	}

	private ArrayList<BondHeader> queryBondList(long portfolioId, long depotId) throws Exception
	{
		Session locSession = SessionManager.getSession();

		ArrayList<BondHeader> locBondHeaderList = null;

		Query locQuery = locSession.createQuery("FROM BondHeader WHERE portfolio_id = " + portfolioId + "AND depot_id = " + depotId);

		locBondHeaderList = (ArrayList<BondHeader>) locQuery.getResultList();

		return locBondHeaderList;
	}

	private ArrayList<BondHeader> queryBondList(long portfolioId, long depotId, ArrayList<Long> bondIdList) throws Exception
	{
		Session locSession = SessionManager.getSession();

		ArrayList<BondHeader> locBondHeaderList = null;

		Query locQuery = locSession.createQuery("FROM BondHeader WHERE portfolio_id = " + portfolioId + "AND depot_id = " + depotId + "AND id IN (:BondIdList)");

		locQuery.setParameterList("BondIdList", bondIdList);

		locBondHeaderList = (ArrayList<BondHeader>) locQuery.getResultList();

		return locBondHeaderList;
	}
}
