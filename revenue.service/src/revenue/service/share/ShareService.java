package revenue.service.share;

import java.util.ArrayList;

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

import revenue.entity.Depot;
import revenue.entity.Portfolio;
import revenue.entity.ShareHeader;
import revenue.hibernate.SessionManager;
import revenue.service.share.entity.ReqShareHeader;
import revenue.service.share.entity.ResShareHeader;

@Path("/service")
public class ShareService
{

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getShareList")
	public ArrayList<ResShareHeader> getShareList(@QueryParam("portfolioId") long portfolioId, @QueryParam("depotId") long depotId) throws Exception
	{

		ArrayList<ResShareHeader> locResShareHeaderList = new ArrayList<ResShareHeader>();

		SessionManager.initSession();

		try
		{
			Session locSession = SessionManager.getSession();

			Query locQuery = locSession.createQuery("from ShareHeader where portfolio_id = " + portfolioId + "and depot_id = " + depotId);

			ArrayList<ShareHeader> locShareHeaderList = (ArrayList<ShareHeader>) locQuery.list();

			for (ShareHeader shareHeader : locShareHeaderList)
			{
				ResShareHeader locResShareHeader = new ResShareHeader();

				locResShareHeader.setId(shareHeader.getId());
				locResShareHeader.setPortfolioId(shareHeader.getPortfolio().getId());
				locResShareHeader.setDepotId(shareHeader.getDepot().getId());
				locResShareHeader.setName(shareHeader.getName());
				locResShareHeader.setIsin(shareHeader.getIsin());
				locResShareHeader.setWkn(shareHeader.getWkn());
				locResShareHeader.setArea(shareHeader.getArea());

				locResShareHeaderList.add(locResShareHeader);
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

		return locResShareHeaderList;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/createShare")
	public void createShare(ReqShareHeader reqShareHeader) throws Exception
	{
		SessionManager.initSession();

		try
		{
			ShareHeader locShareHeader = new ShareHeader();

			locShareHeader.setArea(reqShareHeader.getArea());
			locShareHeader.setWkn(reqShareHeader.getWkn());
			locShareHeader.setIsin(reqShareHeader.getIsin());
			locShareHeader.setName(reqShareHeader.getName());

			Depot locDepot = new Depot();
			locDepot.setId(reqShareHeader.getDepotId());
			locShareHeader.setDepot(locDepot);

			Portfolio locPortfolio = new Portfolio();
			locPortfolio.setId(reqShareHeader.getPortfolioId());
			locShareHeader.setPortfolio(locPortfolio);

			SessionManager.getSession().save(locShareHeader);

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
	@Path("/deleteShare")
	public void deleteShare(@QueryParam("shareId") long shareId) throws Exception
	{
		SessionManager.initSession();

		try
		{
			Query locQuery = SessionManager.getSession().createQuery("from ShareHeader where id = " + shareId);

			ShareHeader locShare = (ShareHeader) locQuery.getSingleResult();

			SessionManager.getSession().delete(locShare);

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
