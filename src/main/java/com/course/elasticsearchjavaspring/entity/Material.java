package com.course.elasticsearchjavaspring.entity;

public class Material {

	private int thickness;

	private String type;

	public Material() {

	}

	public Material(String type, int thickness) {
		super();
		this.type = type;
		this.thickness = thickness;
	}

	public int getThickness() {
		return thickness;
	}

	public String getType() {
		return type;
	}

	public void setThickness(int thickness) {
		this.thickness = thickness;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Material [type=" + type + ", thickness=" + thickness + "]";
	}

}
