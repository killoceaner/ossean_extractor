package net.trustie.pipeline;

import javax.annotation.Resource;

import net.trustie.dao.CsdnTopic_Dao;
import net.trustie.model.CsdnTopic_Model;

import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

@Component("CsdnDaoPipeline")
public class CsdnTopic_pipeline implements PageModelPipeline<CsdnTopic_Model> {

	@Resource
	private CsdnTopic_Dao cTopic_Dao;

	public void process(CsdnTopic_Model topic_Model, Task arg1) {
		// TODO Auto-generated method stub
		cTopic_Dao.add(topic_Model);
	}
}
