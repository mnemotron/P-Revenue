package revenue.service.bond;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
import revenue.service.bond.entity.ReqBondHeader;
import revenue.service.bond.entity.ResBondHeader;
import revenue.service.entity.Response;

@Path("/service")
public class BondService
{

	// HTTP-GET: read, query
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getBondList")
	public ArrayList<ResBondHeader> getBondList(@QueryParam("portfolioId") long portfolioId, @QueryParam("depotId") long depotId)
	{
		Session locSession = HibernateSessionFactory.getSessionFactory().getCurrentSession();

		Transaction locTransaction = locSession.beginTransaction();

		Query locQuery = locSession.createQuery("from BondHeader where portfolio_id = " + portfolioId + "and depot_id = " + depotId);

		ArrayList<BondHeader> locBondHeaderList = (ArrayList<BondHeader>) locQuery.list();

		ArrayList<ResBondHeader> locResBondHeaderList = new ArrayList<ResBondHeader>();

		for (BondHeader bondheader : locBondHeaderList)
		{
			ResBondHeader locResBondHeader = new ResBondHeader();

			locResBondHeader.setName(bondheader.getName());
			locResBondHeader.setArea(bondheader.getArea());
			// locResBondHeader.setDueDate(bondheader.getDueDate());

			locResBondHeaderList.add(locResBondHeader);
		}

		locSession.close();

		return locResBondHeaderList;
	}

	// HTTP-POST: create
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/createBondHeader")
	public Response createBondHeader(ReqBondHeader reqBondHeader)
	{
		Response locResponse = new Response();

		BondHeader locBondHeader = new BondHeader();

		locBondHeader.setArea(reqBondHeader.getArea());
		locBondHeader.setWkn(reqBondHeader.getWkn());
		locBondHeader.setIsin(reqBondHeader.getIsin());
		locBondHeader.setName(reqBondHeader.getName());

		SimpleDateFormat locFormatter = new SimpleDateFormat("dd.MM.yyyy");

		try
		{
			Date locDueDate = locFormatter.parse(reqBondHeader.getDueDate());
			locBondHeader.setDueDate(locDueDate);
		}
		catch (ParseException e)
		{
			locResponse.setMessage(e.getMessage());
		}

		Depot locDepot = new Depot();
		locDepot.setId(reqBondHeader.getDepotId());
		locBondHeader.setDepot(locDepot);

		Portfolio locPortfolio = new Portfolio();
		locPortfolio.setId(reqBondHeader.getPortfolioId());
		locBondHeader.setPortfolio(locPortfolio);

		Session locSession = HibernateSessionFactory.getSessionFactory().getCurrentSession();

		Transaction locTransaction = locSession.beginTransaction();

		locSession.save(locBondHeader);

		locTransaction.commit();

		locSession.close();

		return locResponse;
	}

	// HTTP-PUT: update

	// HTP-DELETE: delete
	// @DELETE
	// @Produces(MediaType.APPLICATION_JSON)
	// @Path("/deleteDepot")
	// public Response deleteDepot(@QueryParam("id") long depotId)
	// {
	// Response locResponse = new Response();
	//
	// Session locSession =
	// HibernateSessionFactory.getSessionFactory().getCurrentSession();
	//
	// Transaction locTransaction = locSession.beginTransaction();
	//
	// Query locQuery = locSession.createQuery("from Depot where id = " +
	// depotId);
	//
	// Depot locDepot = (Depot) locQuery.getSingleResult();
	//
	// locSession.delete(locDepot);
	//
	// locTransaction.commit();
	//
	// locSession.close();
	//
	// return locResponse;
	// }

}
