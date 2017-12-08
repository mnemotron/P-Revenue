package revenue.service.config;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.query.Query;

import revenue.entity.Config;
import revenue.hibernate.SessionManager;
import revenue.service.config.entity.ReqConfig;
import revenue.service.config.entity.ResConfig;
import revenue.service.entity.Response;

@Path("/service")
public class ConfigService
{

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getConfig")
	public ArrayList<ResConfig> getConfig()
	{
		SessionManager.initSession();

		ArrayList<ResConfig> locResConfigList = new ArrayList<ResConfig>();

		try
		{
			ArrayList<Config> locConfigList = queryConfig();

			for (Config config : locConfigList)
			{
				ResConfig locResConfig = new ResConfig();
				locResConfig.setKey(config.getKey());
				locResConfig.setValue(config.getValue());
				locResConfigList.add(locResConfig);
			}
		}
		catch (Exception e)
		{

		}
		finally
		{
			SessionManager.closeSession();
		}

		return locResConfigList;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/updateConfig")
	public Response updateConfig(ArrayList<ReqConfig> reqConfigList)
	{
		Response locResponse = new Response();

		SessionManager.initSession();

		try
		{
			ArrayList<Config> locConfigList = queryConfig();

			for (ReqConfig reqConfig : reqConfigList)
			{
				for (Config config : locConfigList)
				{					
					if ((reqConfig.getKey().equals(config.getKey())) && !(reqConfig.getValue().equals(config.getValue())))
					{
						config.setValue(reqConfig.getValue());

						SessionManager.getSession().saveOrUpdate(config);

						break;
					}
				}
			}

			SessionManager.commit();
		}
		catch (Exception e)
		{
			if (SessionManager.getTransaction() != null)
			{
				SessionManager.getTransaction().rollback();
			}
		}
		finally
		{
			SessionManager.closeSession();
		}

		return locResponse;
	}

	private ArrayList<Config> queryConfig() throws RuntimeException
	{
		Session locSession = SessionManager.getSession();

		ArrayList<Config> locConfigList = null;

		Query locQuery = locSession.createQuery("FROM Config");

		locConfigList = (ArrayList<Config>) locQuery.getResultList();

		return locConfigList;
	}

}
