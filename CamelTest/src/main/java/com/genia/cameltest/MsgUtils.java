package com.genia.cameltest;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.springframework.beans.factory.annotation.Autowired;

public class MsgUtils {
	
	@Autowired
	NewsRepository repository;
	
	public void checkExistance(String body, Exchange ex) {
		NewsEntity entity = ex.getIn().getBody(NewsEntity.class);
		boolean notExist = repository.findByTitleAndDate(entity.getTitle(), entity.getPubDate()) == null;
		ex.getIn().setHeader("persist", notExist);
	}
	
	public void updateStatus(String body, @Header("toSendIds") List<Long> IDs) {
		if(IDs == null)
			return;
		for (Long id : IDs) {
			NewsEntity entity = repository.findOne(id);
			entity.setStatus("sent");
			repository.save(entity);
		}
	}
}
