package com.amdocs.nursery.main;

import com.amdocs.nursery.dao.*;
import com.amdocs.nursery.pojo.*;
import com.amdocs.nursery.exception.*;
import java.util.*;

public class PlantMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("-------Welcome to Amdocs's Nursery-------");
			System.out.println("Press 1 for adding a new plant" + "\n" + "Press 2 for deleting a plant" + "\n"
					+ "Press 3 for updating the plant cost" + "\n" + "Press 4 for displaying all the plants" + "\n"
					+ "Press 5 for searching a plant by the origin of the country " + "\n"
					+ "Press 6 for counting plants by water supply frequency" + "\n"
					+ "Press 7 for getting plants which require sunlight and are outdoor plants" + "\n"
					+ "Press 8 for exit");
			System.out.println("Enter your choice");
			//Entering the choice of operation, which we need to perform
			int choice = sc.nextInt();
			//Making an object of class PlantDAO
			PlantDAO plantDao = new PlantDAO();
			ArrayList<Plant> plants = null;
			//Local variable to store the id of the plant, which we will input
			int id;
			//Local variable for storing the name.
			String name;
			int result = 0;
			
			//Set which will store the ids of the plant which are already present in the database
			HashSet<Integer> set = new HashSet<>();
			if (plantDao.getIdsPresent().size() > 0) {
				set = (HashSet) plantDao.getIdsPresent().clone();
			}
			
			
			switch (choice) {
			case 1:
				System.out.println("Enter the plantId");
				id = sc.nextInt();
				//Checking if the id of the plant already exists in the table and throwing an exception based on that.
				if (set.contains(id)) {
					try {
						throw new InvalidId("Id already exists, please change the id");
					} catch (InvalidId e) {
						System.out.println("Exception occured: " + e);
						break;
					}
				}
				System.out.println("Enter the name of the plant");
				name = sc.next();
				System.out.println("Enter the name of country of origin");
				String origin = sc.next();
				//Taking integer input for sunlight from the user and storing converting it to boolean
				System.out.println("Press 1 if sunlight is required else press any another number");
				int temp = sc.nextInt();
				boolean sunlight = temp == 1 ? true : false;
				System.out.println("Enter the frequency of water supply: daily, weekly or alternatively");
				String water = sc.next();
				//Checking the format of the input and if it's invalid throwing an exception
				if(!(water.equals("daily")||water.equals("alternatively")||water.equals("weekly"))) {
					try {
						throw new InvalidFormat("You have enter the wrong format of the value.Try again");
					}catch(InvalidFormat e) {
						System.out.println("Exception occured: "+e);
						break;
					}
				}
				System.out.println("Enter the type of plant: Outdoor or Indoor");
				String type = sc.next();
				//Checking the format of the input and if it's invalid throwing an exception.
				if(!(type.equals("Outdoor")||type.equals("Indoor"))) {
					try {
						throw new InvalidFormat("You have enter the wrong format of the value.Try again");
					}catch(InvalidFormat e) {
						System.out.println("Exception occured: "+e);
						break;
					}
				}
				System.out.println("Enter the cost of plant");
				double cost = sc.nextDouble();

				//Checking if the entry has been inserted in the table.
				result = plantDao.addPlant(new Plant(id, name, origin, sunlight, water, type, cost));
				if (result != 0) {
					System.out.println("Your value has been inserted");
					set.add(id);
				} else
					System.out.println("Not inserted");

				break;
			case 2:
				System.out.println("Enter the id of the plant you want to delete");
				id = sc.nextInt();
				//Checking if the id doesn't exists in the table.
				if (!set.contains(id)) {
					try {
						throw new IdDoesNotExists("Id does not exist in the table, pleace choose a vaild id");
					} catch (IdDoesNotExists e) {
						System.out.println("Exception occured: " + e);
						break;
					}
				}
				result = plantDao.deletePlant(id);
				//Checking if the deletion operation was successful
				if (result != 0) {
					System.out.println("The plant with id: " + id + " has been deleted");
					set.remove(id);
				} else
					System.out.println("The plant with id: " + id + " has not been deleted");

				break;
			case 3:
				System.out.println("Enter the plant id whose cost you want to update");
				id = sc.nextInt();
				if (!set.contains(id)) {
					try {
						throw new IdDoesNotExists("Id does not exist in the table, pleace choose a vaild id");
					} catch (IdDoesNotExists e) {
						System.out.println("Exception occured: " + e);
						break;
					}
				}
				System.out.println("Enter the new cost of the plant ");
				double newcost = sc.nextDouble();
				boolean updatedCost = plantDao.updatePlantCost(id, newcost);
				if (updatedCost)
					System.out.println("The cost of the plant has been updated");
				else
					System.out.println("Unable to update the cost");
				break;
			case 4:
				plants = plantDao.showAllPlants();
				for (Plant p : plants) {
					System.out.println(p.toString());
				}
				break;
			case 5:
				System.out.println("Enter the name of the country");
				String nameOfTheCountry = sc.next();
				plants = plantDao.searchByOriginCountryName(nameOfTheCountry);
				if (plants.size() == 0) {
					try {
						throw new CountryDoesNotExists(
								"The country: " + nameOfTheCountry + " doesn't exists in database");
					} catch (CountryDoesNotExists e) {
						System.out.println("Exception Occured: " + e);
						break;
					}
				}
				for (Plant p : plants) {
					System.out.println(p.toString());
				}
				break;
			case 6:
				System.out.println("Enter the water frequency");
				String waterFrequency = sc.next();
				Set<String> container = new HashSet<>();
				container.add("daily");
				container.add("alternately");
				container.add("weekly");
				if (!container.contains(waterFrequency)) {
					try {
						throw new InvalidFormat("You have enter the wrong format of the value.Try again");
					} catch (InvalidFormat e) {
						System.out.println("Exception Occured: " + e);
						break;
					}
				}
				int count = plantDao.countPlantsByWaterSupplyFrequency(waterFrequency);
				System.out.println("There are " + count + " plants with water frequency " + waterFrequency);
				break;
			case 7:
				plants = plantDao.searchOutdoorPlantsWithSunlight();
					
				for (Plant p : plants) {
					System.out.println(p.toString());
				}
				break;
			case 8:
				return;
			default:
				System.out.println("Invalid choice");
			}
		}

	}

}
