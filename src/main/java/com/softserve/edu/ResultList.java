package com.softserve.edu;

import java.util.ArrayList;
import java.util.List;

public class ResultList {
	private final List<String> content;

	public ResultList() {
		this.content = new ArrayList<String>();
	}

	public List<String> getContent() {
		return content;
	}

	public ResultList addText(String text) {
		content.add(text);
		return this;
	}

}
