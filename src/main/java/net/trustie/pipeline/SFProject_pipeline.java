package net.trustie.pipeline;

import javax.annotation.Resource;
import net.trustie.dao.SFProject_Dao;
import net.trustie.model.SFProject;
import org.springframework.stereotype.Component;
import core.PageModelPipeline;
import us.codecraft.webmagic.Task;
@Component("SFProjectpipeline")
public class SFProject_pipeline  implements PageModelPipeline<SFProject>{
	@Resource 
	private SFProject_Dao sfp_Dao;
	public void process(SFProject sfp,Task arg1){
		sfp_Dao.add(sfp);
	}

}
