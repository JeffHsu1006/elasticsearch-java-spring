package com.course.elasticsearchjavaspring.entity;

import java.time.LocalDate;
import java.util.List;

public class Bag {

	private List<String> additionFeatures;

	private boolean available;

	private String brand;

	private String color;

	private LocalDate firstReleaseDate;

	private Material material;

	private int price;

	private String type;

	private List<Zipper> zippers;

	public Bag() {

	}

	public Bag(String brand, String color, String type, int price, boolean available, LocalDate firstReleaseDate) {
		super();
		this.brand = brand;
		this.color = color;
		this.type = type;
		this.price = price;
		this.available = available;
		this.firstReleaseDate = firstReleaseDate;
	}

	public List<String> getAdditionFeatures() {
		return additionFeatures;
	}

	public String getBrand() {
		return brand;
	}

	public String getColor() {
		return color;
	}

	public LocalDate getFirstReleaseDate() {
		return firstReleaseDate;
	}

	public Material getMaterial() {
		return material;
	}

	public int getPrice() {
		return price;
	}

	public String getType() {
		return type;
	}

	public List<Zipper> getZippers() {
		return zippers;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAdditionFeatures(List<String> additionFeatures) {
		this.additionFeatures = additionFeatures;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setFirstReleaseDate(LocalDate firstReleaseDate) {
		this.firstReleaseDate = firstReleaseDate;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setZippers(List<Zipper> zippers) {
		this.zippers = zippers;
	}

	@Override
	public String toString() {
		return "Bag [brand=" + brand + ", color=" + color + ", type=" + type + ", price=" + price + ", available="
				+ available + ", firstReleaseDate=" + firstReleaseDate + "]";
	}
}
