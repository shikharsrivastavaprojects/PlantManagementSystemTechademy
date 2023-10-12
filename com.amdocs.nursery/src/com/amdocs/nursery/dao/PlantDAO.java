package com.amdocs.nursery.dao;
import java.util.*;
import com.amdocs.nursery.pojo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.amdocs.nursery.pojo.*;

public class PlantDAO {
	PreparedStatement pst;
	//establishing the connection using the PlantConnection class.
	PlantConnection plantConnection = new PlantConnection();
	Connection con = plantConnection.getConnection();
	
	//Method which returns the plant ids already present in the table.
	public HashSet<Integer> getIdsPresent() {
		HashSet<Integer> temp = new HashSet<>();
		try {
			pst=con.prepareStatement("select plantId from nursery");
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				temp.add(rs.getInt(1));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return temp;
	}

	//Method for adding a plant into the database
	public int addPlant(Plant p) {
		try {
			pst = con.prepareStatement(
					"insert into nursery (plantId,plantName,originCountryName,sunlightRequired,waterSupplyRequired,plantType,costPlant) values (?,?,?,?,?,?,?)");
			pst.setInt(1, p.getPlantID());
			pst.setString(2, p.getPlantName());
			pst.setString(3, p.getOriginCountryName());
			pst.setInt(4, p.isSunlightRequired() ? 1 : 0);
			pst.setString(5, p.getWaterSupplyFrequency());
			pst.setString(6, p.getPlantType());
			pst.setDouble(7, p.getCost());
			int rs = pst.executeUpdate();
			con.close();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	//Method for deleting a plant from the database with the given id.
	public int deletePlant(int id) {
		try {
			pst = con.prepareStatement("delete from nursery where plantId=?");
			pst.setInt(1, id);
			int rs = pst.executeUpdate();
			con.close();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	//Method for updating the cost of the plant with the given id.
	public boolean updatePlantCost(int id, double cost) {
		try {
			pst = con.prepareStatement("update nursery set costPlant = ? where plantId=? ");
			pst.setDouble(1, cost);
			pst.setInt(2, id);
			pst.executeQuery();
			con.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	//Method for displaying all plants present in the database and displaying all the values associated with them.
	public ArrayList<Plant> showAllPlants() {
		ArrayList<Plant> names = new ArrayList<>();
		try {
			pst = con.prepareStatement("select * from nursery");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				names.add(new Plant(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getString(5),
						rs.getString(6), rs.getDouble(7)));
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return names;

	}

	//Method for searching plant with given country of origin.
	public ArrayList<Plant> searchByOriginCountryName(String country) {
		ArrayList<Plant> names = new ArrayList<>();
		try {
			pst = con.prepareStatement("select * from nursery where originCountryName=?");
			pst.setString(1, country);

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				names.add(new Plant(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getString(5),
						rs.getString(6), rs.getDouble(7)));
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return names;
	}

	//Method for searching for outdoor plants which require sunlight.
	public ArrayList<Plant> searchOutdoorPlantsWithSunlight() {
		ArrayList<Plant> names = new ArrayList<>();
		try {
			pst = con.prepareStatement("select * from nursery where (sunlightRequired=? AND plantType=?)");
			pst.setInt(1, 1);
			pst.setString(2, "Outdoor");

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				names.add(new Plant(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getString(5),
						rs.getString(6), rs.getDouble(7)));
			}
			con.close();
			return names;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return names;

	}

	//Method for counting the plants with given water supply frequency.
	public int countPlantsByWaterSupplyFrequency(String supplyFrequency) {
		try {
			pst = con.prepareStatement("select plantId from nursery where waterSupplyRequired=?");
			pst.setString(1, supplyFrequency);
			ResultSet rs = pst.executeQuery();
			//Variable for counting plants on the basis for water supply
			int count = 0;
			while (rs.next())
				count++;
			con.close();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
