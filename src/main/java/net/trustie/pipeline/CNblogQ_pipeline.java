package net.trustie.pipeline;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import core.PageModelPipeline;

import us.codecraft.webmagic.Task;
import net.trustie.dao.CNblogsQ_Dao;
import net.trustie.model.CNblogsQ_Model;

@Component("cnblogQPipeline")
public class CNblogQ_pipeline implements PageModelPipeline<CNblogsQ_Model> {

	@Resource
	CNblogsQ_Dao cNblogsQ_Dao;

	public void process(CNblogsQ_Model cQ_Model, Task arg1) {
		// TODO Auto-generated method stub
		cNblogsQ_Dao.add(cQ_Model);
	}

}
