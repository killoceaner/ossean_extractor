package core;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Object contains extract results.<br>
 * It is contained in Page and will be processed in pipeline.
 * 
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 * @see Page
 * @see us.codecraft.webmagic.pipeline.Pipeline
 */
public class ResultItems {

	private Map<String, Object> fields = new LinkedHashMap<String, Object>();

	private Map<String, Boolean> isFieldSkiped = new LinkedHashMap<String, Boolean>();

	private boolean skip;
	
	private String url;

	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		Object o = fields.get(key);
		if (o == null) {
			return null;
		}
		return (T) fields.get(key);
	}

	public <T> ResultItems put(String key, T value) {
		fields.put(key, value);
		return this;
	}

	public Map<String, Object> getAll() {
		return fields;
	}

	public boolean getIsSkip(String key) {
		if (isFieldSkiped.containsKey(key))
			return isFieldSkiped.get(key);
		return true;
	}

	public ResultItems putIsSkip(String key, boolean value) {
		isFieldSkiped.put(key, value);
		return this;
	}

	/**
	 * Whether to skip the result.<br>
	 * Result which is skipped will not be processed by Pipeline.
	 * 
	 * @return whether to skip the result
	 */
	public boolean isSkip() {
		return skip;
	}

	/**
	 * Set whether to skip the result.<br>
	 * Result which is skipped will not be processed by Pipeline.
	 * 
	 * @param skip
	 *            whether to skip the result
	 * @return this
	 */
	public ResultItems setSkip(boolean skip) {
		this.skip = skip;
		return this;
	}	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "ResultItems{" + "fields=" + fields + ", skip=" + skip + '}';
	}
}
