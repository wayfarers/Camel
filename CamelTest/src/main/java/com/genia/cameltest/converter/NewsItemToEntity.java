package com.genia.cameltest.converter;

import org.genia.rssnews.NewsItem;
import org.springframework.core.convert.converter.Converter;

import com.genia.cameltest.NewsEntity;

public class NewsItemToEntity implements Converter<NewsItem, NewsEntity>{

	@Override
	public NewsEntity convert(NewsItem in) {
		NewsEntity entity = new NewsEntity();
		entity.setTitle(in.getTitle());
		entity.setDescription(in.getDescription());
		entity.setPubDate(in.getDate());
		entity.setUrl(in.getUrl());
		return entity;
	}
	
}
