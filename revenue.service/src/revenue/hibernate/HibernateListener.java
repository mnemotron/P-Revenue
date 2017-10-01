package revenue.hibernate;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateListener implements ServletContextListener
{
	public void contextInitialized(ServletContextEvent event)
	{
		HibernateSessionFactory.getSessionFactory();
	}

	public void contextDestroyed(ServletContextEvent event)
	{
		HibernateSessionFactory.shutdown();
	}
}
