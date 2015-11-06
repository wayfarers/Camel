package com.genia.cameltest;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.genia.rssnews.NewsItem;
import org.springframework.beans.factory.annotation.Autowired;

public class Aggregator implements AggregationStrategy {

	@Autowired
	NewsRepository repository;

	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		NewsItem item = newExchange.getIn().getBody(NewsItem.class);
		NewsEntity entity = repository.findByTitleAndDate(item.getTitle(), item.getDate());
		String newBody = item.getTitle();
		if (oldExchange == null) {
			List<Long> toSendIds = new ArrayList<Long>();
			if (entity.getStatus().equals("waiting for sending")) {
				newExchange.getIn().setBody(newBody);
				toSendIds.add(entity.getId());
			} else {
				newExchange.getIn().setBody("");
			}
			newExchange.getIn().setHeader("toSendIds", toSendIds);
			return newExchange;
		}

		if (entity.getStatus().equals("waiting for sending")) {
			String oldBody = oldExchange.getIn().getBody(String.class);
			oldExchange.getIn().setBody(oldBody + "\n" + newBody);
			List<Long> ids = oldExchange.getIn().getHeader("toSendIds", ArrayList.class);
			ids.add(entity.getId());
			oldExchange.getIn().setHeader("toSendIds", ids);
		}

		return oldExchange;
	}

}
