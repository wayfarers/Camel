package com.genia.cameltest.server;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.genia.rssnews.RSSNewsService;

public class RssProcessor implements Processor {
	
	RSSNewsService rssService;

	@Override
	public void process(Exchange exchange) throws Exception {
		exchange.getOut().setBody(rssService.getFreshNews());
	}

	public void setRssService(RSSNewsService rssService) {
		this.rssService = rssService;
	}
	
}
