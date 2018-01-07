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
import org.hibernate.query.Query;

import revenue.entity.Portfolio;
import revenue.hibernate.SessionManager;
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
	public ArrayList<ResPortfolio> getPortfolioList() throws Exception
	{

		ArrayList<ResPortfolio> locResPortfolioList = new ArrayList<ResPortfolio>();

		SessionManager.initSession();

		try
		{
			Session locSession = SessionManager.getSession();

			Query locQuery = locSession.createQuery("from Portfolio");

			ArrayList<Portfolio> locPortfolioList = (ArrayList<Portfolio>) locQuery.getResultList();

			locResPortfolioList = new ArrayList<ResPortfolio>();

			for (Portfolio portfolio : locPortfolioList)
			{
				ResPortfolio locResPortfolio = new ResPortfolio();

				locResPortfolio.setId(portfolio.getId());
				locResPortfolio.setName(portfolio.getName());
				locResPortfolio.setDescription(portfolio.getDescription());

				locResPortfolioList.add(locResPortfolio);
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

		return locResPortfolioList;
	}

	// HTTP-POST: create
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/createPortfolio")
	public void createPortfolio(ReqPortfolio reqPortfolio) throws Exception
	{

		SessionManager.initSession();

		try
		{
			Portfolio locPortfolio = new Portfolio();

			locPortfolio.setName(reqPortfolio.getName());
			locPortfolio.setDescription(reqPortfolio.getDescription());
			locPortfolio.setCreationDate(new Date());

			SessionManager.getSession().save(locPortfolio);

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
	@Path("/deletePortfolio")
	public void deletePortfolio(@QueryParam("id") long portfolioId) throws Exception
	{
		SessionManager.initSession();

		try
		{

			Query locQuery = SessionManager.getSession().createQuery("from Portfolio where id = " + portfolioId);

			Portfolio locPortfolio = (Portfolio) locQuery.getSingleResult();

			SessionManager.getSession().delete(locPortfolio);

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
