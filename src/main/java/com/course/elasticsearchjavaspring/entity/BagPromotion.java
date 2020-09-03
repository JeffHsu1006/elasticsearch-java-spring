package com.course.elasticsearchjavaspring.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "elasticsearch-java-spring-2")
public class BagPromotion {

	private String description;
	@Id
	private String Id;

	private String type;

	public String getDescription() {
		return description;
	}

	public String getId() {
		return Id;
	}

	public String getType() {
		return type;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(String id) {
		Id = id;
	}

	public void setType(String type) {
		this.type = type;
	}
}
