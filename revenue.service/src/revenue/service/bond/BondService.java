package revenue.service.bond;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import revenue.entity.BondHeader;
import revenue.entity.Depot;
import revenue.entity.Portfolio;
import revenue.hibernate.HibernateSessionFactory;
import revenue.service.entity.Response;

@Path("/service")
public class BondService
{

	// HTTP-GET: read, query
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getBondList")
	public ArrayList<BondHeader> getBondList(@QueryParam("portfolioId") long portfolioId, @QueryParam("depotId") long depotId)
	{
		Session locSession = HibernateSessionFactory.getSessionFactory().getCurrentSession();

		Transaction locTransaction = locSession.beginTransaction();

		Query locQuery = locSession.createQuery("from BondHeader where portfolio_id = " + portfolioId + "and depot_id = " + depotId);

		ArrayList<BondHeader> locBondHeaderList = (ArrayList<BondHeader>) locQuery.list();

		for (BondHeader bondHeader : locBondHeaderList)
		{
			bondHeader.setPortfolio(null);
			bondHeader.setDepot(null);
		}

		// locTransaction.commit();

		locSession.close();

		return locBondHeaderList;
	}

	// HTTP-POST: create
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/createBondHeader")
	public Response createDepot(BondHeader bondHeader)
	{
		Response locResponse = new Response();

		Session locSession = HibernateSessionFactory.getSessionFactory().getCurrentSession();

		Transaction locTransaction = locSession.beginTransaction();

		locSession.save(bondHeader);

		locTransaction.commit();

		locSession.close();

		return locResponse;
	}

	// HTTP-PUT: update

	// HTP-DELETE: delete
//	@DELETE
//	@Produces(MediaType.APPLICATION_JSON)
//	@Path("/deleteDepot")
//	public Response deleteDepot(@QueryParam("id") long depotId)
//	{
//		Response locResponse = new Response();
//		
//		Session locSession = HibernateSessionFactory.getSessionFactory().getCurrentSession();
//		
//		Transaction locTransaction = locSession.beginTransaction();
//		
//		Query locQuery = locSession.createQuery("from Depot where id = " + depotId);
//		
//		Depot locDepot = (Depot) locQuery.getSingleResult();
//
//		locSession.delete(locDepot);
//
//		locTransaction.commit();
//
//		locSession.close();
//
//		return locResponse;
//	}

}
