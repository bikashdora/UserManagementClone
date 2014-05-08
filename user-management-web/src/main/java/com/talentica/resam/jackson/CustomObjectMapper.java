package com.talentica.resam.jackson;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.PropertyNamingStrategy;

public class CustomObjectMapper extends ObjectMapper {

	public CustomObjectMapper() {
		super();
		setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
	}
}
