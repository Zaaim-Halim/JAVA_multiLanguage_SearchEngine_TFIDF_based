package com.halim.utils;

import java.util.Map;
public class SearchResponseModel {
	
	private Map<String,String> lines;

	public SearchResponseModel(Map<String,String> lines) {
		super();
		this.lines = lines;
	}

	public Map<String,String> getLines() {
		return lines;
	}

	public void setLines(Map<String,String> lines) {
		this.lines = lines;
	}
	
}
