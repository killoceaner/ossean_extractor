package net.trustie.pipeline;

import javax.annotation.Resource;

import net.trustie.dao.CNblogsNews_Dao;
import net.trustie.model.CNblogsNews_Model;

import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;
@Component("CNblogsnewsPipeline")
public class CNblogsNews_pipeline implements PageModelPipeline<CNblogsNews_Model>{
	@Resource
	private  CNblogsNews_Dao cnblogsNews_Dao;
	@Override
	public void process(CNblogsNews_Model  cnblogsnews_Model,Task args1){
		
		cnblogsNews_Dao.add(cnblogsnews_Model);
		}
}
