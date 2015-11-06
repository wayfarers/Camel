package com.genia.cameltest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.xml.XPathBuilder;
import org.genia.rssnews.NewsItem;

public class MessageToItem implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		NewsItem item = new NewsItem();
		String dateStr = exchange.getIn().getHeader("pubDate", String.class);
		String fullText = XPathBuilder.xpath("item/fulltext/text()").evaluate(exchange, String.class).replaceAll("<[^>]*>", "").replaceAll("\\s+", " ").trim();
		item.setTitle(exchange.getIn().getHeader("newsTitle", String.class));
		item.setDate(parseDate(dateStr));
		item.setUrl(XPathBuilder.xpath("item/link/text()").evaluate(exchange, String.class));
		item.setDescription(fullText);
		exchange.getIn().setBody(item);
	}
	
	public Date parseDate(String dateStr) throws ParseException {
		DateFormat dateParser = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
		return dateParser.parse(dateStr);
		
	}

}
