package revenue.service.depot;

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
import revenue.hibernate.SessionManager;
import revenue.service.depot.entity.ReqDepot;
import revenue.service.depot.entity.ResDepot;

@Path("/service")
public class DepotService
{

	// HTTP-GET: read, query
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getDepotList")
	public ArrayList<ResDepot> getDepotList(@QueryParam("portfolioId") long portfolioId)
	{
		ArrayList<ResDepot> locResDepotList = new ArrayList<ResDepot>();
		
		SessionManager.initSession();
		
		try
		{
			Session locSession = SessionManager.getSession();
			
			Query locQuery = locSession.createQuery("from Depot where portfolio_id = " + portfolioId);

			ArrayList<Depot> locDepotList = (ArrayList<Depot>) locQuery.list();
			
			locResDepotList = new ArrayList<ResDepot>();
			
			for (Depot depot : locDepotList)
			{
				ResDepot locResDepot = new ResDepot();
				
				locResDepot.setId(depot.getId());
				locResDepot.setName(depot.getName());
				locResDepot.setNumber(depot.getNumber());
				locResDepot.setPortfolioId(depot.getPortfolio().getId());
				
				locResDepotList.add(locResDepot);
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

		return locResDepotList;
	}

	// HTTP-POST: create
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/createDepot")
	public void createDepot(ReqDepot reqDepot)
	{
		SessionManager.initSession();
		
		try
		{		
			Depot locDepot = new Depot();
			
			locDepot.setName(reqDepot.getName());
			locDepot.setNumber(reqDepot.getNumber());
			
			Portfolio p = new Portfolio();
			p.setId(reqDepot.getPortfolioId());
			
			locDepot.setPortfolio(p);

			SessionManager.getSession().save(locDepot);
			
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

	// HTTP-PUT: update

	// HTP-DELETE: delete
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/deleteDepot")
	public void deleteDepot(@QueryParam("portfolioId") long portfolioId, @QueryParam("id") long depotId)
	{
		SessionManager.initSession();
		
		try
		{					
			Query locQuery = SessionManager.getSession().createQuery("from Depot where portfolio_id = " + portfolioId + " and id = " + depotId);
			
			Depot locDepot = (Depot) locQuery.getSingleResult();

			SessionManager.getSession().delete(locDepot);
			
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
