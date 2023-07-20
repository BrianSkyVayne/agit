package com.technical.agit.book.util;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Configuration
public class CommonUtil {
	public static <T> T constructData(T valueData, T newMap) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		mapper.map(valueData, newMap);
		return newMap;
	}
}
