package de.paluno.dox.parser;

public class CompactDisc {
	private String title;
	private String artist;
	private String country;
	private String company;
	private double price;
	private int year;

	public CompactDisc(){}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "CompactDisc [title=" + title + ", artist=" + artist
				+ ", country=" + country + ", company=" + company + ", price="
				+ price + ", year=" + year + "]";
	}
}
