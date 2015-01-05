package net.trustie.pipeline;
import javax.annotation.Resource;

import net.trustie.dao.IteyeAsk_Dao;
import net.trustie.model.IteyeAsk_Model;

import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

@Component("IteyeAskDaoPipeline")
public class IteyeAsk_pipeline implements PageModelPipeline<IteyeAsk_Model>{
	@Resource
	private IteyeAsk_Dao iteyeask_Dao;
	@Override
	public void process(IteyeAsk_Model ask_Model,Task arg1){
		iteyeask_Dao.add(ask_Model);
	}
}
