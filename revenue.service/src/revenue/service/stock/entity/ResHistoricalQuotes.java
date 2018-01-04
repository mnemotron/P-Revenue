package revenue.service.stock.entity;

import java.util.ArrayList;

public class ResHistoricalQuotes {

	private String api;
	private String tickerId;
	private String name;
	private String interval;
	private String timePeriod;
	private String fromDate;
	private String toDate;	
	private ArrayList<Double> quoteList = new ArrayList<Double>();
	private ArrayList<String> xLabelList = new ArrayList<String>();

	public ResHistoricalQuotes() {

	}

	public ArrayList<Double> getQuoteList() {
		return quoteList;
	}

	public void setQuoteList(ArrayList<Double> quoteList) {
		this.quoteList = quoteList;
	}

	public String getTickerId() {
		return tickerId;
	}

	public void setTickerId(String tickerId) {
		this.tickerId = tickerId;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public String getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getxLabelList() {
		return xLabelList;
	}

	public void setxLabelList(ArrayList<String> xLabelList) {
		this.xLabelList = xLabelList;
	}

}
