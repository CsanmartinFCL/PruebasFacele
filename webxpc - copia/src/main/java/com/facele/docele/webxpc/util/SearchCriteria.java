package com.facele.docele.webxpc.util;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class SearchCriteria {

	private String key;
	private OPERATION operation;
	private Object value;
	private Object value2;

	public static enum OPERATION {
		like, equal, lessThanOrEqualTo, greaterThanOrEqualTo, in, between;
	}

}
