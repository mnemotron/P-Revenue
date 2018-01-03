package revenue.service.api.yahooFinance;

import java.io.IOException;
import java.util.Calendar;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.Interval;

@Path("/service")
public class APIYahooFinance {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getHistoricalQuotes")
	public void getHistoricalQuotes() throws Exception 
	{
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		from.add(Calendar.YEAR, -5); // from 5 years ago

		Stock locStock = YahooFinance.get("GOOG", from, to, Interval.WEEKLY);
		
		locStock.getHistory();
	}

}
