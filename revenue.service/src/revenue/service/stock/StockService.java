package revenue.service.stock;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

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
public class StockService
{

	public static final String API_YAHOO_FINANCE = "YF";
	public static final String API_DUMMY = "DY";
	
	public static final String INTERVAL_DAY_1 = "1D";
	public static final String INTERVAL_MONTH_1 = "1M";
	public static final String INTERVAL_WEEK_1 = "1W";
	
	public static final String TIME_PERIOD_USER_DEFINED = "UD";
	public static final String TIME_PERIOD_DAY_1 = "1D";
	public static final String TIME_PERIOD_WEEK_1 = "1W";
	public static final String TIME_PERIOD_MONTH_1 = "1M";
	public static final String TIME_PERIOD_MONTH_6 = "6M";
	public static final String TIME_PERIOD_YEAR_1 = "1Y";
	public static final String TIME_PERIOD_YEAR_3 = "3Y";
	public static final String TIME_PERIOD_YEAR_5 = "5Y";

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getHistoricalQuotes")
	public ResHistoricalQuotes getHistoricalQuotes(@QueryParam("api") String api, @QueryParam("tickerId") String tickerId, @QueryParam("interval") String interval,
			@QueryParam("timePeriod") String timePeriod, @QueryParam("fromDate") String fromDate, @QueryParam("toDate") String toDate) throws Exception
	{
		ResHistoricalQuotes locResHQ = new ResHistoricalQuotes();

		locResHQ.setApi(api);
		locResHQ.setTickerId(tickerId);
		locResHQ.setInterval(interval);
		locResHQ.setTimePeriod(timePeriod);

		// calculate time period
		locResHQ = this.calTimePeriod(locResHQ);

		// get quotes
		switch (locResHQ.getApi())
		{
		case API_YAHOO_FINANCE:
			locResHQ = this.api_yahooFinance_getHistoricalQuotes(locResHQ);
			break;
		case API_DUMMY:
			locResHQ = this.api_dummy_getHistoricalQuotes(locResHQ);
			break;
		default:
			break;
		}

		return locResHQ;
	}

	private ResHistoricalQuotes calTimePeriod(ResHistoricalQuotes hq)
	{
		Calendar locFrom = Calendar.getInstance();
		Calendar locTo = Calendar.getInstance();

		switch (hq.getTimePeriod())
		{
		case TIME_PERIOD_DAY_1:
			locFrom.add(Calendar.DAY_OF_MONTH, -1);
			break;
		case TIME_PERIOD_WEEK_1:
			locFrom.add(Calendar.WEEK_OF_MONTH, -1);
			break;
		case TIME_PERIOD_MONTH_1:
			locFrom.add(Calendar.MONTH, -1);
			break;
		case TIME_PERIOD_MONTH_6:
			locFrom.add(Calendar.MONTH, -6);
			break;
		case TIME_PERIOD_YEAR_1:
			locFrom.add(Calendar.YEAR, -1);
			break;
		case TIME_PERIOD_YEAR_3:
			locFrom.add(Calendar.YEAR, -3);
			break;
		case TIME_PERIOD_YEAR_5:
			locFrom.add(Calendar.YEAR, -5);
			break;
		default:
			locFrom.add(Calendar.YEAR, -1);
			break;
		}

		hq.setFromDate(locFrom.getTime().toInstant().toString());
		hq.setToDate(locTo.getTime().toInstant().toString());

		return hq;
	}

	private ResHistoricalQuotes api_yahooFinance_getHistoricalQuotes(ResHistoricalQuotes hq) throws Exception
	{

		ArrayList<Double> locQuoteList = new ArrayList<Double>();
		ArrayList<String> locXLabelList = new ArrayList<String>();

		Interval locInterval = null;
		Calendar locFrom = Calendar.getInstance();
		Calendar locTo = Calendar.getInstance();

		Instant locInstantFrom = Instant.parse(hq.getFromDate());
		Instant locInstantTo = Instant.parse(hq.getToDate());

		Date locFromDate = Date.from(locInstantFrom);
		Date locToDate = Date.from(locInstantTo);

		locFrom.setTime(locFromDate);
		locTo.setTime(locToDate);

		// interval
		switch (hq.getInterval())
		{
		case INTERVAL_DAY_1:
			locInterval = Interval.DAILY;
			break;
		case INTERVAL_MONTH_1:
			locInterval = Interval.MONTHLY;
			break;
		case INTERVAL_WEEK_1:
			locInterval = Interval.WEEKLY;
			break;
		default:
			locInterval = Interval.DAILY;
			break;
		}

		// get historical quotes
		Stock locStock = YahooFinance.get(hq.getTickerId(), locFrom, locTo, locInterval);

		// return result
		Iterator<HistoricalQuote> locIteratorHQ = locStock.getHistory().iterator();

		while (locIteratorHQ.hasNext())
		{
			HistoricalQuote locHQ = locIteratorHQ.next();

			// set quote
			BigDecimal locClose = locHQ.getClose();

			if (locClose != null)
			{
				locQuoteList.add(new Double(locClose.doubleValue()));

				// set x-label
				int locYear = locHQ.getDate().get(Calendar.YEAR);
				locXLabelList.add(new String(new Integer(locYear).toString()));
			}
		}

		hq.setName(locStock.getName());
		hq.setQuoteList(locQuoteList);
		hq.setxLabelList(locXLabelList);

		return hq;
	}

	private ResHistoricalQuotes api_dummy_getHistoricalQuotes(ResHistoricalQuotes hq) throws Exception
	{

		ArrayList<Double> locQuoteList = new ArrayList<Double>();
		ArrayList<String> locXLabelList = new ArrayList<String>();

		Calendar locFrom = Calendar.getInstance();
		Calendar locTo = Calendar.getInstance();

		Instant locInstantFrom = Instant.parse(hq.getFromDate());
		Instant locInstantTo = Instant.parse(hq.getToDate());

		Date locFromDate = Date.from(locInstantFrom);
		Date locToDate = Date.from(locInstantTo);

		locFrom.setTime(locFromDate);
		locTo.setTime(locToDate);

		while (locFrom.before(locTo))
		{
			Random locRandom = new Random();
			locQuoteList.add(locRandom.nextDouble());

			int locYear = locFrom.get(Calendar.YEAR);
			int locMonth = locFrom.get(Calendar.MONTH);
			int locDay = locFrom.get(Calendar.DAY_OF_MONTH);
			String locLabel = new String();
			locLabel = locLabel.concat(new Integer(locYear).toString());
			// locLabel = locLabel.concat(new Integer(locMonth).toString() +
			// '/');
			// locLabel = locLabel.concat(new Integer(locDay).toString());
			locXLabelList.add(locLabel);

			switch (hq.getInterval())
			{
			case INTERVAL_DAY_1:
				locFrom.add(Calendar.DAY_OF_MONTH, 1);
				break;
			case INTERVAL_WEEK_1:
				locFrom.add(Calendar.WEEK_OF_MONTH, 1);
				break;
			case INTERVAL_MONTH_1:
				locFrom.add(Calendar.MONTH, 1);
				break;
			default:
				locFrom.add(Calendar.DAY_OF_MONTH, 1);
				break;
			}

		}

		hq.setName(hq.getTickerId());
		hq.setQuoteList(locQuoteList);
		hq.setxLabelList(locXLabelList);

		return hq;
	}

}
