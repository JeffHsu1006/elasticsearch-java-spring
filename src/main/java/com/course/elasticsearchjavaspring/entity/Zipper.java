package com.course.elasticsearchjavaspring.entity;

public class Zipper {

	private String manufacturer;

	private int price;

	private int size;

	public Zipper() {

	}

	public Zipper(String manufacturer, int size, int price) {
		super();
		this.manufacturer = manufacturer;
		this.size = size;
		this.price = price;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public int getPrice() {
		return price;
	}

	public int getSize() {
		return size;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "Zipper [manufacturer=" + manufacturer + ", size=" + size + ", price=" + price + "]";
	}
}
