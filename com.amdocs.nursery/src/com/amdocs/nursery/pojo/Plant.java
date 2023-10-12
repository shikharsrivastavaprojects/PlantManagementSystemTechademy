package com.amdocs.nursery.pojo;

public class Plant {
	private int plantID;
	private String plantName;
	private String originCountryName;
	private boolean sunlightRequired;
	private String waterSupplyFrequency;
	private String plantType;
	private double cost;

	@Override
	public String toString() {
		return "Plant:" + "\n" + "Plant Id: " + plantID + "\n" + "Plant Name: " + plantName + "\n"
				+ "Country of Origin: " + originCountryName + "\n" + "Sunlight Required: " + sunlightRequired + "\n"
				+ "Frequency of water supply: " + waterSupplyFrequency + "\n" + "Type: " + plantType + "\n"
				+ "Cost of the plant: " + cost + "\n";
//		return "Plant [plantID=" + plantID + ", plantName=" + plantName + ", originCountryName=" + originCountryName
//				+ ", sunlightRequired=" + sunlightRequired + ", waterSupplyFrequency=" + waterSupplyFrequency
//				+ ", plantType=" + plantType + ", cost=" + cost + "]";
	}

	public Plant(int plantID, String plantName, String originCountryName, boolean sunlightRequired,
			String waterSupplyFrequency, String plantType, double cost) {
		super();
		this.plantID = plantID;
		this.plantName = plantName;
		this.originCountryName = originCountryName;
		this.sunlightRequired = sunlightRequired;
		this.waterSupplyFrequency = waterSupplyFrequency;
		this.plantType = plantType;
		this.cost = cost;
	}

	public int getPlantID() {
		return plantID;
	}

	private void setPlantID(int plantID) {
		this.plantID = plantID;
	}

	public String getPlantName() {
		return plantName;
	}

	private void setPlantName(String plantName) {
		this.plantName = plantName;
	}

	public String getOriginCountryName() {
		return originCountryName;
	}

	private void setOriginCountryName(String originCountryName) {
		this.originCountryName = originCountryName;
	}

	public boolean isSunlightRequired() {
		return sunlightRequired;
	}

	private void setSunlightRequired(boolean sunlightRequired) {
		this.sunlightRequired = sunlightRequired;
	}

	public String getWaterSupplyFrequency() {
		return waterSupplyFrequency;
	}

	private void setWaterSupplyFrequency(String waterSupplyFrequency) {
		this.waterSupplyFrequency = waterSupplyFrequency;
	}

	public String getPlantType() {
		return plantType;
	}

	private void setPlantType(String plantType) {
		this.plantType = plantType;
	}

	public double getCost() {
		return cost;
	}

	private void setCost(double cost) {
		this.cost = cost;
	}

}
