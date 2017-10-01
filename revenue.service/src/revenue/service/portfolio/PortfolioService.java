package revenue.service.portfolio;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import revenue.entity.Portfolio;
import revenue.hibernate.HibernateSessionFactory;

@Path("/portfolio")
public class PortfolioService
{

	@Resource(name = "jdbc/revenue")
	private DataSource ds;

	// HTTP-GET: read, query
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/getPortfolioList")
	public ArrayList<Portfolio> getPortfolioList()
	{
		Session locSession = HibernateSessionFactory.getSessionFactory().getCurrentSession();
		
		Transaction locTransaction = locSession.beginTransaction();

		Query locQuery = locSession.createQuery("from Portfolio");

		ArrayList<Portfolio> locPortfolioList = (ArrayList<Portfolio>) locQuery.list();
		
//		locTransaction.commit();
		
		locSession.close();

		return locPortfolioList;
	}

	// HTTP-POST: create
	// @POST
	// @Produces(MediaType.TEXT_HTML)
	// @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	// public void newContact(
	// @FormParam("id") String id,
	// @FormParam("name") String name,
	// @Context HttpServletResponse servletResponse
	// ) throws IOException {
	// Contact c = new Contact(id,name,new ArrayList<Address>());
	// ContactStore.getStore().put(id, c);
	//
	// URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();
	// Response.created(uri).build();
	//
	// servletResponse.sendRedirect("../pages/new_contact.html");

	// HTTP-PUT: update

	// HTP-DELETE: delete

}
