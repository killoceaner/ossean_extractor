package net.trustie.pipeline;

import javax.annotation.Resource;

import net.trustie.dao.OschinaQuestion_Dao;
import net.trustie.model.OSChinaQuestion_Model;
import org.springframework.stereotype.Component;
import core.PageModelPipeline;
import us.codecraft.webmagic.Task;

@Component("OSChinaQuestionPipeline")
public class OSChinaQuestion_pipeline implements PageModelPipeline<OSChinaQuestion_Model> {

	@Resource
	private OschinaQuestion_Dao oschian_Dao;

	public void process(OSChinaQuestion_Model model, Task arg1) {
		// TODO Auto-generated method stub
		oschian_Dao.add(model);
	}
}
