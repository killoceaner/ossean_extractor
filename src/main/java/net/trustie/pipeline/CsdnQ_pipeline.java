package net.trustie.pipeline;

import javax.annotation.Resource;

import net.trustie.dao.CsdnQ_Dao;
import net.trustie.model.CsdnQ_Model;

import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;
@Component("CsdnDaoPipeline")
public class CsdnQ_pipeline  implements PageModelPipeline<CsdnQ_Model>{
	@Resource 
	private CsdnQ_Dao cQ_Dao;
	@Override
	public void process(CsdnQ_Model csdnq_Model,Task  arg1){
		cQ_Dao.add(csdnq_Model);
		}
	}
