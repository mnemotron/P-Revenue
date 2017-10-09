package revenue.hibernate;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateListener implements ServletContextListener
{
	@Override
	public void contextInitialized(ServletContextEvent event)
	{
		HibernateSessionFactory.getSessionFactory();
	}

	@Override
	public void contextDestroyed(ServletContextEvent event)
	{
		HibernateSessionFactory.shutdown();
	}
}
