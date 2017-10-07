package revenue.service.portfolio;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.json.JsonObject;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import revenue.entity.Portfolio;
import revenue.hibernate.HibernateSessionFactory;
import revenue.service.entity.Response;

@Path("/portfolio")
public class PortfolioService
{

	// @Resource(name = "jdbc/revenue")
	// private DataSource ds;

	// HTTP-GET: read, query
	@GET
	@Produces(
	{ MediaType.APPLICATION_JSON })
	@Path("/getPortfolioList")
	public ArrayList<Portfolio> getPortfolioList()
	{
		Session locSession = HibernateSessionFactory.getSessionFactory().getCurrentSession();

		Transaction locTransaction = locSession.beginTransaction();

		Query locQuery = locSession.createQuery("from Portfolio");

		ArrayList<Portfolio> locPortfolioList = (ArrayList<Portfolio>) locQuery.list();

		// locTransaction.commit();

		locSession.close();

		return locPortfolioList;
	}

	// HTTP-POST: create
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/createPortfolio")
	public Response createPortfolio(Portfolio portfolio)
	{
		Response locResponse = new Response();

		portfolio.setCreationDate(new Date());

		Session locSession = HibernateSessionFactory.getSessionFactory().getCurrentSession();

		Transaction locTransaction = locSession.beginTransaction();

		locSession.save(portfolio);

		locTransaction.commit();
		
		locSession.close();

		return locResponse;
	}

	// HTTP-PUT: update

	// HTP-DELETE: delete

}
