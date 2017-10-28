package revenue.service.bond;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

import revenue.entity.BondHeader;
import revenue.entity.BondItemBuy;
import revenue.entity.Depot;
import revenue.entity.Portfolio;
import revenue.hibernate.HibernateSessionFactory;
import revenue.service.bond.entity.ReqBondHeader;
import revenue.service.bond.entity.ResBondHeader;
import revenue.service.bond.entity.ResBondItemBuy;
import revenue.service.entity.Response;

@Path("/service")
public class BondService
{

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

			locResBondHeader.setId(bondheader.getId());
			locResBondHeader.setName(bondheader.getName());
			locResBondHeader.setArea(bondheader.getArea());
			// locResBondHeader.setDueDate(bondheader.getDueDate());

			locResBondHeaderList.add(locResBondHeader);
		}

		locSession.close();

		return locResBondHeaderList;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getBondItemBuyList")
	public ArrayList<ResBondItemBuy> getBondItemBuyList(@QueryParam("portfolioId") long portfolioId, @QueryParam("depotId") long depotId, @QueryParam("bondId") long bondId)
	{
		Session locSession = HibernateSessionFactory.getSessionFactory().getCurrentSession();

		Transaction locTransaction = locSession.beginTransaction();

		Query locQuery = locSession.createQuery("from BondItemBuy where portfolio_id = " + portfolioId + "and depot_id = " + depotId + "and bondheader_id = " + bondId);

		ArrayList<BondItemBuy> locBondItemBuyList = (ArrayList<BondItemBuy>) locQuery.list();

		ArrayList<ResBondItemBuy> locResBondItemBuyList = new ArrayList<ResBondItemBuy>();

		for (BondItemBuy bonditembuy : locBondItemBuyList)
		{
			ResBondItemBuy locResBondItemBuy = new ResBondItemBuy();

			locResBondItemBuy.setId(bonditembuy.getId());
			locResBondItemBuy.setBondId(bonditembuy.getId());
			locResBondItemBuy.setPortfolioId(bonditembuy.getPortfolio().getId());
			locResBondItemBuy.setDepotId(bonditembuy.getDepot().getId());
			locResBondItemBuy.setNominalValue(bonditembuy.getNominalValue());
			locResBondItemBuy.setBuyDate(bonditembuy.getBuyDate());
			locResBondItemBuy.setBuyPercent(bonditembuy.getBuyPercent());

			locResBondItemBuyList.add(locResBondItemBuy);
		}

		locSession.close();

		return locResBondItemBuyList;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/createBond")
	public Response createBond(ReqBondHeader reqBondHeader)
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

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/deleteBond")
	public Response deleteDepot(@QueryParam("bondId") long bondId)
	{
		Response locResponse = new Response();

		Session locSession = HibernateSessionFactory.getSessionFactory().getCurrentSession();

		Transaction locTransaction = locSession.beginTransaction();

		Query locQuery = locSession.createQuery("from BondHeader where id = " + bondId);

		BondHeader locBond = (BondHeader) locQuery.getSingleResult();

		locSession.delete(locBond);

		locTransaction.commit();

		locSession.close();

		return locResponse;
	}

}