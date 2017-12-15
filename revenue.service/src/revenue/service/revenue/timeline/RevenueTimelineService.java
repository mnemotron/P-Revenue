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
import revenue.core.timeline.entity.TimelineBondHeader;
import revenue.core.timeline.entity.TimelineBondInterest;
import revenue.core.timeline.entity.TimelineDepot;
import revenue.core.timeline.entity.TimelinePortfolio;
import revenue.entity.BondHeader;
import revenue.entity.Depot;
import revenue.entity.Portfolio;
import revenue.hibernate.SessionManager;
import revenue.service.revenue.timeline.entity.ReqDepot;
import revenue.service.revenue.timeline.entity.ReqRevenueTimeline;
import revenue.service.revenue.timeline.entity.ResBondHeader;
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

	private ArrayList<Depot> getDepot(Portfolio portfolio, ArrayList<ReqDepot> reqDepotList) throws Exception
	{

		// query depot
		ArrayList<Depot> locDepotList = new ArrayList<Depot>();

		// each depot
		for (ReqDepot reqDepot : reqDepotList)
		{

			// query depot
			Depot locDepot = queryDepot(portfolio.getId(), reqDepot.getDepotId());

			ArrayList<BondHeader> locBondHeaderList = new ArrayList<BondHeader>();

			// whole depot
			if ((reqDepot.getBondIdList() == null) || (reqDepot.getBondIdList().isEmpty()))
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

		// map portfolio
		locResRevenueTimeline.setPortfolioId(timelinePortfolio.getPortfolio().getId());

		// map depot
		ArrayList<ResDepot> locResDepotList = new ArrayList<ResDepot>();

		for (TimelineDepot timelineDepot : timelinePortfolio.getBondDepotResult())
		{
			ResDepot locResDepot = new ResDepot();

			// map bond header
			ArrayList<ResBondHeader> locResBondHeaderList = new ArrayList<ResBondHeader>();

			for (TimelineBondHeader timelineBondHeader : timelineDepot.getBondHeaderResult())
			{
				ResBondHeader locBondHeader = new ResBondHeader();

				// map bond item interest result
				ArrayList<ResBondItemInterestRevenue> locResBondItemInterestRevenueList = new ArrayList<ResBondItemInterestRevenue>();

				for (TimelineBondInterest timelineBondInterest : timelineBondHeader.getBondTotalInterestResult())
				{
					ResBondItemInterestRevenue locResBondItemInterestRevenue = new ResBondItemInterestRevenue();

					locResBondItemInterestRevenue.setInterestDate(timelineBondInterest.getInterestDate().toInstant().toString());
					locResBondItemInterestRevenue.setInterestRevenue(timelineBondInterest.getInterest());

					locResBondItemInterestRevenueList.add(locResBondItemInterestRevenue);
				}

				locBondHeader.setBondId(timelineBondHeader.getBondHeader().getId());
				locBondHeader.setBondName(timelineBondHeader.getBondHeader().getName());
				// locBondHeader.setBondItemBuyList();
				locBondHeader.setBondTotalInterestResultList(locResBondItemInterestRevenueList);
				locBondHeader.setStartDate(timelineBondHeader.getStartDate().toInstant().toString());
				locBondHeader.setEndDate(timelineBondHeader.getEndDate().toInstant().toString());

				locResBondHeaderList.add(locBondHeader);
			}

			locResDepot.setDepotId(timelineDepot.getDepot().getId());
			locResDepot.setDepotName(timelineDepot.getDepot().getName());
			locResDepot.setBondList(locResBondHeaderList);
			locResDepot.setStartDate(timelineDepot.getStartDate().toInstant().toString());
			locResDepot.setEndDate(timelineDepot.getEndDate().toInstant().toString());

			locResDepotList.add(locResDepot);
		}

		locResRevenueTimeline.setDepotList(locResDepotList);
		locResRevenueTimeline.setStartDate(timelinePortfolio.getStartDate().toInstant().toString());
		locResRevenueTimeline.setEndDate(timelinePortfolio.getEndDate().toInstant().toString());
		
		return locResRevenueTimeline;
	}

	private Portfolio queryPortfolio(long portfolioId) throws Exception
	{
		Session locSession = SessionManager.getSession();

		Portfolio locPortfolio = null;

		Query locQuery = locSession.createQuery("FROM Portfolio WHERE id = " + portfolioId);

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
