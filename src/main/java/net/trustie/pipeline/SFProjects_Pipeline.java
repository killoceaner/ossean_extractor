package net.trustie.pipeline;
import javax.annotation.Resource;
import net.trustie.model.SFProject;
import net.trustie.dao.SFProjects_Dao;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

@Component("SFProjectsDaoPipeline")
public class SFProjects_Pipeline implements PageModelPipeline<SFProject> {
	@Resource 
		private SFProjects_Dao SFProjects_Dao;
		@Override
		public void process(SFProject sfp,Task  arg1){
			SFProjects_Dao.add(sfp);
			}

}
