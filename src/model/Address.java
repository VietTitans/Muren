package model;

public class Address {
private String streetName; 
private String buildingNo; 
private int floor; 
private String zipCode;
private String city; 
private String country;

public Address(String streetName, String buildingNo, int floor, String zipCode, String city, String country) {
	this.streetName = streetName;
	this.buildingNo = buildingNo;
	this.floor = floor;
	this.zipCode = zipCode;
	this.city = city;
	this.country = country;
}

public String getStreetName() {
	return streetName;
}

public String getBuildingNo() {
	return buildingNo;
}

public int getFloor() {
	return floor;
}

public String getZipCode() {
	return zipCode;
}

public String getCity() {
	return city;
}

public String getCountry() {
	return country;
}

public void setStreetName(String streetName) {
	this.streetName = streetName;
}

public void setBuildingNo(String buildingNo) {
	this.buildingNo = buildingNo;
}

public void setFloor(int floor) {
	this.floor = floor;
}

public void setZipCode(String zipCode) {
	this.zipCode = zipCode;
}

public void setCity(String city) {
	this.city = city;
}

public void setCountry(String country) {
	this.country = country;
} 


}
