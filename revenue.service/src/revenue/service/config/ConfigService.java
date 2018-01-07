package revenue.service.config;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.query.Query;

import revenue.entity.Config;
import revenue.hibernate.SessionManager;
import revenue.service.config.entity.ReqConfig;
import revenue.service.config.entity.ResConfig;

@Path("/service")
public class ConfigService
{

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getConfigSingle")
	public ResConfig getConfigSingle(@QueryParam("key") String key) throws Exception
	{
		SessionManager.initSession();

		ResConfig locResConfig = new ResConfig();

		try
		{
			Config locConfig = queryConfig(key);

			locResConfig.setKey(locConfig.getKey());
			locResConfig.setValue(locConfig.getValue());

		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			SessionManager.closeSession();
		}

		return locResConfig;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getConfig")
	public ArrayList<ResConfig> getConfig() throws Exception
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
			throw e;
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
	public void updateConfig(ArrayList<ReqConfig> reqConfigList) throws Exception
	{
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

			throw e;
		}
		finally
		{
			SessionManager.closeSession();
		}

	}

	private Config queryConfig(String key) throws RuntimeException
	{
		Session locSession = SessionManager.getSession();

		Config locConfig = null;

		Query locQuery = locSession.createQuery("FROM Config WHERE key = '" + key + "'");

		locConfig = (Config) locQuery.getSingleResult();

		return locConfig;
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
