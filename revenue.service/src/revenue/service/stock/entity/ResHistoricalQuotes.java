package revenue.service.stock.entity;

import java.util.ArrayList;

public class ResHistoricalQuotes {

	private ArrayList<Double> quoteList = new ArrayList<Double>();

	public ResHistoricalQuotes() {

	}

	public ArrayList<Double> getQuoteList() {
		return quoteList;
	}

	public void setQuoteList(ArrayList<Double> quoteList) {
		this.quoteList = quoteList;
	}

}
