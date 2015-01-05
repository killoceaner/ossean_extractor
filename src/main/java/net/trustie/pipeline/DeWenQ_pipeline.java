package net.trustie.pipeline;

import javax.annotation.Resource;

import net.trustie.dao.DeWenQ_Dao;
import net.trustie.model.DeWenQ_Model;

import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

@Component("deWenQpipeline")
public class DeWenQ_pipeline  implements PageModelPipeline<DeWenQ_Model> {
	@Resource
	DeWenQ_Dao deWenQ_Dao;
	@Override
	public void process(DeWenQ_Model deWenQ_Model, Task arg1){
		deWenQ_Dao.add(deWenQ_Model);
		}
	

}
