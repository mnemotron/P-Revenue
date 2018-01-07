package revenue.service.bond;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.hibernate.Session;
import org.hibernate.query.Query;

import revenue.entity.BondHeader;
import revenue.entity.BondItemBuy;
import revenue.entity.Depot;
import revenue.entity.Interest;
import revenue.entity.Portfolio;
import revenue.hibernate.SessionManager;
import revenue.service.bond.entity.ReqBondHeader;
import revenue.service.bond.entity.ReqBondItemBuy;
import revenue.service.bond.entity.ResBondHeader;
import revenue.service.bond.entity.ResBondItemBuy;

@Path("/service")
public class BondService
{

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getBondList")
	public ArrayList<ResBondHeader> getBondList(@QueryParam("portfolioId") long portfolioId, @QueryParam("depotId") long depotId) throws Exception
	{
		ArrayList<ResBondHeader> locResBondHeaderList = new ArrayList<ResBondHeader>();

		SessionManager.initSession();

		try
		{
			Session locSession = SessionManager.getSession();

			Query locQuery = locSession.createQuery("from BondHeader where portfolio_id = " + portfolioId + "and depot_id = " + depotId);

			ArrayList<BondHeader> locBondHeaderList = (ArrayList<BondHeader>) locQuery.list();

			for (BondHeader bondheader : locBondHeaderList)
			{
				ResBondHeader locResBondHeader = new ResBondHeader();

				locResBondHeader.setId(bondheader.getId());
				locResBondHeader.setName(bondheader.getName());
				locResBondHeader.setArea(bondheader.getArea());
				locResBondHeader.setIsin(bondheader.getIsin());
				locResBondHeader.setWkn(bondheader.getWkn());

				locResBondHeader.setDueDate(bondheader.getDueDate().toInstant().toString());
				locResBondHeader.setInterestDate(bondheader.getInterestDate().toInstant().toString());

				locResBondHeader.setDepotId(bondheader.getDepot().getId());
				locResBondHeader.setPortfolioId(bondheader.getPortfolio().getId());
				locResBondHeader.setInterestIntervall(bondheader.getInterestIntervall());

				ArrayList<Interest> locInterestList = new ArrayList<Interest>(bondheader.getInterest());
				for (Interest interest : locInterestList)
				{
					locResBondHeader.setInterest(interest.getInterest());
					break;
				}

				locResBondHeaderList.add(locResBondHeader);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			SessionManager.closeSession();
		}

		return locResBondHeaderList;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getBondItemBuyList")
	public ArrayList<ResBondItemBuy> getBondItemBuyList(@QueryParam("portfolioId") long portfolioId, @QueryParam("depotId") long depotId, @QueryParam("bondId") long bondId) throws Exception
	{

		ArrayList<ResBondItemBuy> locResBondItemBuyList = new ArrayList<ResBondItemBuy>();

		SessionManager.initSession();

		try
		{
			Session locSession = SessionManager.getSession();

			Query locQuery = locSession.createQuery("from BondItemBuy where portfolio_id = " + portfolioId + "and depot_id = " + depotId + "and bondheader_id = " + bondId);

			ArrayList<BondItemBuy> locBondItemBuyList = (ArrayList<BondItemBuy>) locQuery.list();

			for (BondItemBuy bonditembuy : locBondItemBuyList)
			{
				ResBondItemBuy locResBondItemBuy = new ResBondItemBuy();

				locResBondItemBuy.setId(bonditembuy.getId());
				locResBondItemBuy.setBondId(bonditembuy.getId());
				locResBondItemBuy.setPortfolioId(bonditembuy.getPortfolio().getId());
				locResBondItemBuy.setDepotId(bonditembuy.getDepot().getId());
				locResBondItemBuy.setNominalValue(bonditembuy.getNominalValue());
				locResBondItemBuy.setBuyDate(bonditembuy.getBuyDate().toInstant().toString());
				locResBondItemBuy.setBuyPercent(bonditembuy.getBuyPercent());

				locResBondItemBuyList.add(locResBondItemBuy);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			SessionManager.closeSession();
		}

		return locResBondItemBuyList;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/createBond")
	public void createBond(ReqBondHeader reqBondHeader) throws Exception
	{
		SessionManager.initSession();

		try
		{
			BondHeader locBondHeader = new BondHeader();

			locBondHeader.setArea(reqBondHeader.getArea());
			locBondHeader.setWkn(reqBondHeader.getWkn());
			locBondHeader.setIsin(reqBondHeader.getIsin());
			locBondHeader.setName(reqBondHeader.getName());
			locBondHeader.setDueDate(reqBondHeader.getDueDate());
			locBondHeader.setInterestDate(reqBondHeader.getInterestDate());
			locBondHeader.setInterestIntervall(reqBondHeader.getInterestIntervall());

			Depot locDepot = new Depot();
			locDepot.setId(reqBondHeader.getDepotId());
			locBondHeader.setDepot(locDepot);

			Portfolio locPortfolio = new Portfolio();
			locPortfolio.setId(reqBondHeader.getPortfolioId());
			locBondHeader.setPortfolio(locPortfolio);

			ArrayList<Interest> locInterestList = new ArrayList<Interest>();
			Interest locInterest = new Interest();
			locInterest.setPortfolio(locPortfolio);
			locInterest.setDepot(locDepot);
			locInterest.setBondHeader(locBondHeader);
			locInterest.setInterest(reqBondHeader.getInterest());
			locInterestList.add(locInterest);
			Collection<Interest> locInterestCol = new HashSet<Interest>(locInterestList);

			locBondHeader.setInterest(locInterestCol);

			SessionManager.getSession().save(locBondHeader);

			SessionManager.commit();
		}
		catch (Exception e)
		{
			if (SessionManager.getTransaction() != null)
			{
				SessionManager.getTransaction().rollback();
			}

			throw e;
		}
		finally
		{
			SessionManager.closeSession();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/createBondItemBuy")
	public void createBondItemBuy(ReqBondItemBuy reqBondItemBuy) throws Exception
	{
		SessionManager.initSession();

		try
		{
			BondItemBuy locBondItemBuy = new BondItemBuy();

			locBondItemBuy.setNominalValue(reqBondItemBuy.getNominalValue());
			locBondItemBuy.setBuyPercent(reqBondItemBuy.getBuyPercent());
			locBondItemBuy.setBuyDate(reqBondItemBuy.getBuyDate());

			BondHeader locBondHeader = new BondHeader();
			locBondHeader.setId(reqBondItemBuy.getBondId());
			locBondItemBuy.setBondHeader(locBondHeader);

			Depot locDepot = new Depot();
			locDepot.setId(reqBondItemBuy.getDepotId());
			locBondItemBuy.setDepot(locDepot);

			Portfolio locPortfolio = new Portfolio();
			locPortfolio.setId(reqBondItemBuy.getPortfolioId());
			locBondItemBuy.setPortfolio(locPortfolio);

			SessionManager.getSession().save(locBondItemBuy);

			SessionManager.commit();
		}
		catch (Exception e)
		{
			if (SessionManager.getTransaction() != null)
			{
				SessionManager.getTransaction().rollback();
			}

			throw e;
		}
		finally
		{
			SessionManager.closeSession();
		}
	}

	@DELETE
	@Path("/deleteBondItemBuy")
	public void deleteBondItemBuy(@QueryParam("bondItemBuyId") long bondItemBuyId) throws Exception
	{
		SessionManager.initSession();

		try
		{
			Query locQuery = SessionManager.getSession().createQuery("from BondItemBuy where id = " + bondItemBuyId);

			BondItemBuy locBondItemBuy = (BondItemBuy) locQuery.getSingleResult();

			SessionManager.getSession().delete(locBondItemBuy);

			SessionManager.commit();
		}
		catch (Exception e)
		{
			if (SessionManager.getTransaction() != null)
			{
				SessionManager.getTransaction().rollback();
			}

			throw e;
		}
		finally
		{
			SessionManager.closeSession();
		}
	}

	@DELETE
	@Path("/deleteBond")
	public void deleteBond(@QueryParam("bondId") long bondId) throws Exception
	{
		SessionManager.initSession();

		try
		{
			Query locQuery = SessionManager.getSession().createQuery("from BondHeader where id = " + bondId);

			BondHeader locBond = (BondHeader) locQuery.getSingleResult();

			SessionManager.getSession().delete(locBond);

			SessionManager.commit();
		}
		catch (Exception e)
		{
			if (SessionManager.getTransaction() != null)
			{
				SessionManager.getTransaction().rollback();
			}

			throw e;
		}
		finally
		{
			SessionManager.closeSession();
		}
	}

}
