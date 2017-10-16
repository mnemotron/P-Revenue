package revenue.service.depot;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import revenue.entity.Depot;
import revenue.entity.Portfolio;
import revenue.hibernate.HibernateSessionFactory;
import revenue.service.entity.Response;

@Path("/service")
public class DepotService
{

	// HTTP-GET: read, query
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getDepotList")
	public ArrayList<Depot> getDepotList(@QueryParam("id") long portfolioId)
	{
		Session locSession = HibernateSessionFactory.getSessionFactory().getCurrentSession();

		Transaction locTransaction = locSession.beginTransaction();

		Query locQuery = locSession.createQuery("from Depot where portfolio_id = " + portfolioId);

		// locQuery.setParameter(":portfolio_id", portfolioId);

		ArrayList<Depot> locDepotList = (ArrayList<Depot>) locQuery.list();

		for (Depot depot : locDepotList)
		{
			depot.setPortfolio(null);
		}

		// locTransaction.commit();

		locSession.close();

		return locDepotList;
	}

	// HTTP-POST: create
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/createDepot")
	public Response createDepot(Depot depot)
	{
		Response locResponse = new Response();

		Session locSession = HibernateSessionFactory.getSessionFactory().getCurrentSession();

		Transaction locTransaction = locSession.beginTransaction();

		locSession.save(depot);

		locTransaction.commit();

		locSession.close();

		return locResponse;
	}

	// HTTP-PUT: update

	// HTP-DELETE: delete

}
