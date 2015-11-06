package com.genia.cameltest.server;

import java.util.List;

import org.genia.rssnews.NewsList;
import org.genia.rssnews.RSSNewsService;
import org.springframework.beans.factory.annotation.Autowired;

import com.genia.cameltest.NewsEntity;
import com.genia.cameltest.NewsRepository;
import com.genia.cameltest.converter.EntityToNewsItem;

public class RSSNewsServiceImpl implements RSSNewsService {
	
	@Autowired
	private NewsRepository newsRepository;
	
	@Override
	public NewsList getFreshNews() {
		EntityToNewsItem converter = new EntityToNewsItem();
		List<NewsEntity> items = (List<NewsEntity>) newsRepository.findAll();
		NewsList news = new NewsList();
		for (NewsEntity newsEntity : items) {
			news.getNewsItem().add(converter.convert(newsEntity));
		}
		
		return news;
	}


}
