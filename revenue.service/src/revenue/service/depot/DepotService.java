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
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import revenue.entity.Depot;
import revenue.entity.Portfolio;
import revenue.hibernate.HibernateSessionFactory;
import revenue.service.depot.entity.ReqDepot;
import revenue.service.depot.entity.ResDepot;
import revenue.service.entity.Response;

@Path("/service")
public class DepotService
{

	// HTTP-GET: read, query
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getDepotList")
	public ArrayList<ResDepot> getDepotList(@QueryParam("id") long portfolioId)
	{
		Session locSession = HibernateSessionFactory.getSessionFactory().getCurrentSession();

		Transaction locTransaction = locSession.beginTransaction();

		Query locQuery = locSession.createQuery("from Depot where portfolio_id = " + portfolioId);

		ArrayList<Depot> locDepotList = (ArrayList<Depot>) locQuery.list();
		
		ArrayList<ResDepot> locResDepotList = new ArrayList<ResDepot>();
		
		for (Depot depot : locDepotList)
		{
			ResDepot locResDepot = new ResDepot();
			
			locResDepot.setId(depot.getId());
			locResDepot.setName(depot.getName());
			locResDepot.setPortfolioId(depot.getPortfolio().getId());
			
			locResDepotList.add(locResDepot);
		}
		
		locSession.close();

		return locResDepotList;
	}

	// HTTP-POST: create
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/createDepot")
	public Response createDepot(ReqDepot reqDepot)
	{
		Response locResponse = new Response();
		
		Depot locDepot = new Depot();
		
		locDepot.setName(reqDepot.getName());
		
		Portfolio p = new Portfolio();
		p.setId(reqDepot.getPortfolioId());
		
		locDepot.setPortfolio(p);

		Session locSession = HibernateSessionFactory.getSessionFactory().getCurrentSession();

		Transaction locTransaction = locSession.beginTransaction();

		locSession.save(locDepot);

		locTransaction.commit();

		locSession.close();

		return locResponse;
	}

	// HTTP-PUT: update

	// HTP-DELETE: delete
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/deleteDepot")
	public Response deleteDepot(@QueryParam("id") long depotId)
	{
		Response locResponse = new Response();
		
		Session locSession = HibernateSessionFactory.getSessionFactory().getCurrentSession();
		
		Transaction locTransaction = locSession.beginTransaction();
		
		Query locQuery = locSession.createQuery("from Depot where id = " + depotId);
		
		Depot locDepot = (Depot) locQuery.getSingleResult();

		locSession.delete(locDepot);

		locTransaction.commit();

		locSession.close();

		return locResponse;
	}

}
