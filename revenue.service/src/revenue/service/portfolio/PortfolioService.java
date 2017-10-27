package revenue.service.portfolio;

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

import revenue.entity.Portfolio;
import revenue.hibernate.HibernateSessionFactory;
import revenue.service.entity.Response;
import revenue.service.portfolio.entity.ReqPortfolio;
import revenue.service.portfolio.entity.ResPortfolio;

@Path("/service")
public class PortfolioService
{

	// @Resource(name = "jdbc/revenue")
	// private DataSource ds;

	// HTTP-GET: read, query
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getPortfolioList")
	public ArrayList<ResPortfolio> getPortfolioList()
	{
		Session locSession = HibernateSessionFactory.getSessionFactory().getCurrentSession();

		Transaction locTransaction = locSession.beginTransaction();

		Query locQuery = locSession.createQuery("from Portfolio");

		ArrayList<Portfolio> locPortfolioList = (ArrayList<Portfolio>) locQuery.getResultList();

		ArrayList<ResPortfolio> locResPortfolioList = new ArrayList<ResPortfolio>();

		for (Portfolio portfolio : locPortfolioList)
		{
			ResPortfolio locResPortfolio = new ResPortfolio();

			locResPortfolio.setId(portfolio.getId());
			locResPortfolio.setName(portfolio.getName());
			locResPortfolio.setDescription(portfolio.getDescription());

			locResPortfolioList.add(locResPortfolio);
		}

		locSession.close();

		return locResPortfolioList;
	}

	// HTTP-POST: create
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/createPortfolio")
	public Response createPortfolio(ReqPortfolio reqPortfolio)
	{
		Response locResponse = new Response();

		Portfolio locPortfolio = new Portfolio();

		locPortfolio.setName(reqPortfolio.getName());
		locPortfolio.setDescription(reqPortfolio.getDescription());
		locPortfolio.setCreationDate(new Date());

		Session locSession = HibernateSessionFactory.getSessionFactory().getCurrentSession();

		Transaction locTransaction = locSession.beginTransaction();

		locSession.save(locPortfolio);

		locTransaction.commit();

		locSession.close();

		return locResponse;
	}

	// HTTP-PUT: update

	// HTP-DELETE: delete
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/deletePortfolio")
	public Response deletePortfolio(@QueryParam("id") long portfolioId)
	{
		Response locResponse = new Response();

		Session locSession = HibernateSessionFactory.getSessionFactory().getCurrentSession();

		Transaction locTransaction = locSession.beginTransaction();

		Query locQuery = locSession.createQuery("from Portfolio where id = " + portfolioId);

		Portfolio locPortfolio = (Portfolio) locQuery.getSingleResult();

		locSession.delete(locPortfolio);

		locTransaction.commit();

		locSession.close();

		return locResponse;
	}

}
