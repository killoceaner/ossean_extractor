package net.trustie.pipeline;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Task;
import net.trustie.dao.CsdnQ_Dao;
import net.trustie.model.CsdnQ_Model;
import core.PageModelPipeline;

@Component("csdnQPipeline")
public class CsdnQ_pipeline implements PageModelPipeline<CsdnQ_Model> {
	@Resource
	private CsdnQ_Dao csdnQ_Dao;

	@Override
	public void process(CsdnQ_Model csdnQ_Model, Task task) {
		csdnQ_Dao.add(csdnQ_Model);
	}

}
