package com.genia.cameltest.converter;

import org.genia.rssnews.NewsItem;
import org.springframework.core.convert.converter.Converter;

import com.genia.cameltest.NewsEntity;

public class EntityToNewsItem implements Converter<NewsEntity, NewsItem>{

	@Override
	public NewsItem convert(NewsEntity in) {
		NewsItem item = new NewsItem();
		item.setTitle(in.getTitle());
		item.setDescription(in.getDescription());
		item.setDate(in.getPubDate());
		item.setUrl(in.getUrl());
		return item;
	}

}
