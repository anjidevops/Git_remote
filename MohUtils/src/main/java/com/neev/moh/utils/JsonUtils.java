package com.neev.moh.utils;

import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class JsonUtils {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(JsonUtils.class.getName());

	public static String objectToJson(Object object) {
		String json = null;
		json = new Gson().toJson(object);
		return json;
	}

	public static <T> T jsonToObject(String json, Class<T> klass) {
		T object = null;
		try {
			object = new Gson().fromJson(json, klass);
		} catch (JsonSyntaxException e) {
			logger.log(MohLogger.ERROR, "" + e);
		}
		return object;
	}
}
