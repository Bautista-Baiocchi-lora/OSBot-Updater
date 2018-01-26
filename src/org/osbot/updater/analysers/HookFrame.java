package org.osbot.updater.analysers;

import org.json.simple.JSONObject;

/**
 * Created by Ethan on 1/25/2018.
 */
public class HookFrame {
	private String key;
	private String clazz;
	private String field;
	private String returnType;
	private int paramCount;

	public HookFrame(String key, String clazz, String field, String returnType, int paramCount) {
		this.key = key;
		this.clazz = clazz;
		this.field = field;
		this.returnType = returnType;
		this.paramCount = paramCount;
	}

	public String getKey() {
		return key;
	}

	public String getClazz() {
		return clazz;
	}

	public String getField() {
		return field;
	}

	public String getReturnType() {
		return returnType;
	}

	public int getParamCount() {
		return paramCount;
	}

	public JSONObject toJson() {
		JSONObject hookJson = new JSONObject();
		hookJson.put("Key", getKey());
		hookJson.put("class", getClazz());
		hookJson.put("target", getField());
		hookJson.put("return type", getReturnType());
		hookJson.put("parameter count", getParamCount());
		return hookJson;
	}
}
