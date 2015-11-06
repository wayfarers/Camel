package com.genia.cameltest;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
	@Query("FROM NewsEntity n where n.title = ?1 and n.pubDate = ?2")
	NewsEntity findByTitleAndDate(String title, Date pubDate);
}