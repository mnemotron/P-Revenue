package revenue.service.stock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import revenue.service.stock.entity.ResHistoricalQuotes;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

@Path("/service")
public class StockService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getHistoricalQuotes")
	public ResHistoricalQuotes getHistoricalQuotes(@QueryParam("tickerId") String tickerId) throws Exception 
	{
		ResHistoricalQuotes locResHQ = new ResHistoricalQuotes();
		ArrayList<Double> locQuoteList = new ArrayList<Double>();
		
		Calendar locFrom = Calendar.getInstance();
		Calendar locTo = Calendar.getInstance();
		
		locFrom.add(Calendar.YEAR, -5); // from 5 years ago

		Stock locStock = YahooFinance.get(symbol, locFrom, locTo, Interval.WEEKLY);

		Iterator<HistoricalQuote> locIteratorHQ = locStock.getHistory().iterator();
		
		while(locIteratorHQ.hasNext())
		{
			HistoricalQuote locHQ = locIteratorHQ.next();
			
			locQuoteList.add(new Double(locHQ.getClose().doubleValue()));
		}
		
		locResHQ.setQuoteList(locQuoteList);

		return locResHQ;
	}

}
