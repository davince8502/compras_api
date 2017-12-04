package com.compras.commons.util;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class JsonConverterUtil {
	
	public static <T> T deserializeObject (String target, Class<T> clazz) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
	    return gson.fromJson(target, clazz);
	}

	
	public static <T> List<T> deserializeList(String jsonString, Class<T> clazz) {
	    GsonBuilder builder = new GsonBuilder();
	    Gson gson = builder.create();

	    return gson.fromJson(jsonString, new ListOfSomething<T>(clazz));
	}
	
	
	public static String serializeObject (Object target) {
		GsonBuilder builder = new GsonBuilder();
	    Gson gson = builder.create();
	    return gson.toJson(target);
	}
	
	static class ListOfSomething<X> implements ParameterizedType {

		private Class<?> wrapped;

		public ListOfSomething(Class<X> wrapped) {
			this.wrapped = wrapped;
		}

		@Override
		public Type[] getActualTypeArguments() {
			return new Type[] { wrapped };
		}

		@Override
		public Type getRawType() {
			return List.class;
		}

		@Override
		public Type getOwnerType() {
			return null;
		}

	}
	

}
