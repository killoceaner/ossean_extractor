package core;

import us.codecraft.webmagic.Task;

/**
 * Implements PageModelPipeline to persistent your page model.
 * 
 * @author code4crafter@gmail.com <br>
 * @since 0.2.0
 */
public interface PageModelPipeline<T> {   //P312，PageModelPipeline<T>,使用PageModelPipeline<T>接口时会传入实际类型

	public  void process(T t, Task task);

}
