package com.genia.cameltest.converter;

import org.apache.camel.Converter;
import org.apache.camel.TypeConverters;
import org.genia.rssnews.NewsItem;

import com.genia.cameltest.NewsEntity;

public class CamelTypeConverters  implements TypeConverters {
	
	@Converter
	public NewsItem toNewsItem(NewsEntity in) {
		NewsItem item = new NewsItem();
		item.setTitle(in.getTitle());
		item.setDescription(in.getDescription());
		item.setDate(in.getPubDate());
		item.setUrl(in.getUrl());
		return item;
	}
	
	@Converter
	public NewsEntity toNewsEntity(NewsItem in) {
		NewsEntity entity = new NewsEntity();
		entity.setTitle(in.getTitle());
		entity.setDescription(in.getDescription());
		entity.setPubDate(in.getDate());
		entity.setUrl(in.getUrl());
		return entity;
	}
}
