package revenue.service.account;

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

import revenue.entity.AccountHeader;
import revenue.entity.Portfolio;
import revenue.hibernate.SessionManager;
import revenue.service.account.entity.ReqAccountHeader;
import revenue.service.account.entity.ResAccountHeader;

@Path("/service")
public class AccountService
{

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAccountList")
	public ArrayList<ResAccountHeader> getAccountList(@QueryParam("portfolioId") long portfolioId) throws Exception
	{
		ArrayList<ResAccountHeader> locResAccountHeaderList = new ArrayList<ResAccountHeader>();

		SessionManager.initSession();

		try
		{
			Session locSession = SessionManager.getSession();

			Query locQuery = locSession.createQuery("from AccountHeader where portfolio_id = " + portfolioId);

			ArrayList<AccountHeader> locAccountHeaderList = (ArrayList<AccountHeader>) locQuery.list();

			for (AccountHeader accountheader : locAccountHeaderList)
			{
				ResAccountHeader locResAccountHeader = new ResAccountHeader();

				locResAccountHeader.setId(accountheader.getId());
				locResAccountHeader.setName(accountheader.getName());
				locResAccountHeader.setNumber(accountheader.getNumber());

				locResAccountHeader.setPortfolioId(accountheader.getPortfolio().getId());

				locResAccountHeaderList.add(locResAccountHeader);
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

		return locResAccountHeaderList;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/createAccount")
	public void createAccount(ReqAccountHeader reqAccountHeader) throws Exception
	{
		SessionManager.initSession();

		try
		{
			AccountHeader locAccountHeader = new AccountHeader();

			locAccountHeader.setName(reqAccountHeader.getName());
			locAccountHeader.setNumber(reqAccountHeader.getNumber());

			Portfolio locPortfolio = new Portfolio();
			locPortfolio.setId(reqAccountHeader.getPortfolioId());
			locAccountHeader.setPortfolio(locPortfolio);

			SessionManager.getSession().save(locAccountHeader);

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
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/deleteAccount")
	public void deleteAccount(@QueryParam("portfolioId") long portfolioId, @QueryParam("id") long accountId) throws Exception
	{
		SessionManager.initSession();

		try
		{
			Query locQuery = SessionManager.getSession().createQuery("from AccountHeader where portfolio_id = " + portfolioId + " and id = " + accountId);

			AccountHeader locAccountHeader = (AccountHeader) locQuery.getSingleResult();

			SessionManager.getSession().delete(locAccountHeader);

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
