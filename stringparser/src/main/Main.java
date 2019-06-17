package hw1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Hw1 {
	public static void parseLine(String fileName, String inputLine,
		String[] data, int errCount, Map<String, CityData> citymap) {
		data = inputLine.split("\\|");
		if(data.length<10) {
			System.out.println("This file "+fileName+" is not formatted properly.");
			System.out.println("There are not enough parameters on line ‘"+inputLine+"’.\n");
			throw new IllegalArgumentException();
		}
		String city1; int cT; int dL; int dH; int h;
		float p; float v; float wS; int wD; ArrayList<String> cD;
		try {
			city1 = data[0]; errCount ++;
			cT = Integer.parseInt(data[1]); errCount ++;
			dL = Integer.parseInt(data[2]); errCount ++;
			dH = Integer.parseInt(data[3]); errCount ++;
			h = Integer.parseInt(data[4]); errCount ++;
			p = Float.parseFloat(data[5]); errCount ++;
			v = Float.parseFloat(data[6]); errCount ++;
			wS = Float.parseFloat(data[7]); errCount ++;
			wD = Integer.parseInt(data[8]); errCount ++;
			cD = new ArrayList<String>();
			for(int i=9; i<data.length; i++) {
				cD.add(data[i]); errCount ++;
			}
		}
		catch (NumberFormatException e) {
			System.out.println("This file "+fileName+" is not formatted properly.");
			String errType;
			if(errCount == 0 || errCount >= 9) {
				errType = "string";
			}
			else if(errCount > 0 && errCount <= 4 || errCount == 8) {
				errType = "integer";
			}
			else {
				errType = "float";
			}
			System.out.println("Unable to convert '"+data[errCount]+"' to a "+errType+".\n");
			throw new IllegalArgumentException();
		}
		CityData cityInfo = new CityData(city1, cT, dL, dH, h, p, v, wS, wD, cD);
		String cityKey = city1.toLowerCase();
		citymap.put(cityKey, cityInfo);
	}
	
	public static void printMenu() {
		System.out.println("\t 1) Temperature");
		System.out.println("\t 2) High and low temperature today");
		System.out.println("\t 3) Humidity");
		System.out.println("\t 4) Pressure");
		System.out.println("\t 5) Visibility");
		System.out.println("\t 6) Wind speed and direction");
		System.out.println("\t 7) Descriptions of weather conditions");
		System.out.println("\t 8) Everything");
		System.out.println("\t 9) Enter a different city");
	}
	
	
	public static void main(String [] args) {
		Scanner input = new Scanner(System.in);
		Map<String, CityData> citymap = new LinkedHashMap<>();

		while(true) {
			//holds line-parsing info
			String[] data = null;
			//keeps track of arrayList position where error when line-parsing
			int errCount = 0;
			
			// reads in input file name	
			System.out.print("What is the name of the weather file? ");
			String fileName = input.nextLine();
			//start parsing file
			FileReader fr = null;
			BufferedReader br = null;
			try {
				fr = new FileReader(fileName);
				br = new BufferedReader(fr);
				String line = br.readLine();
				while (line != null) {
					parseLine(fileName, line, data, errCount, citymap);
					line = br.readLine();
				}
				break;
			}
			catch (FileNotFoundException fnfe) {
				System.out.println("The file "+fileName+" could not be found.\n");
			}
			catch (IOException ioe) {
				System.out.println(ioe.getMessage());
			}
			catch (IllegalArgumentException e) {
				//do nothing
			}
			finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException ioe) {
						System.out.print(ioe.getMessage());
					}
				}
				if (fr != null) {
					try {
						fr.close();
					} catch (IOException ioe) {
						System.out.print(ioe.getMessage());
					}
				}
			}
		}
		
		while(true) {
			System.out.print("\nFor what city would you like weather information? ");
			
			String cName = input.nextLine(); //Read user input 
			String cityName = cName.toLowerCase();
			
			//if wrong city name entered			
			if(!citymap.containsKey(cityName) && !cityName.equals("all") && !cityName.equals("exit") ){
				System.out.print("\nUnable to find city "+cityName+".\n");
			}
			//info for all cities
			else if (cityName.equals("all")) {
				System.out.println("\nI do have information about the weather in all cities.\n");
				printMenu();
				while(true) {
					try {
						System.out.print("\nWhat information would you like to know? ");
						int option = input.nextInt();
						input.nextLine();
						if(option != 1 && option != 2 && option != 3 &&
								option != 4 && option != 5 && option != 6 &&
								option != 7 && option != 8 && option != 9 ) {
							System.out.println("\nThat is not a valid option.");
						}
						else if(option == 1) {
							Iterator<String> cityIt2 = citymap.keySet().iterator();
							while (cityIt2.hasNext()) {
							    String country = cityIt2.next();
							    System.out.print( citymap.get(country).printCurrTemp() );
							}
							System.out.print("\n");
						}
						else if(option == 2) {
							Iterator<String> cityIt2 = citymap.keySet().iterator();
							while (cityIt2.hasNext()) {
							    String country = cityIt2.next();
							    System.out.print( citymap.get(country).printDayHighLow() );
							}
							System.out.print("\n");
						}
						else if(option == 3) {
							Iterator<String> cityIt2 = citymap.keySet().iterator();
							while (cityIt2.hasNext()) {
							    String country = cityIt2.next();
							    System.out.print( citymap.get(country).printHumidity() );
							}
							System.out.print("\n");
						}
						else if(option == 4) {
							Iterator<String> cityIt2 = citymap.keySet().iterator();
							while (cityIt2.hasNext()) {
							    String country = cityIt2.next();
							    System.out.print( citymap.get(country).printPressure() );
							}
							System.out.print("\n");
						}
						else if(option == 5) {
							Iterator<String> cityIt2 = citymap.keySet().iterator();
							while (cityIt2.hasNext()) {
							    String country = cityIt2.next();
							    System.out.print( citymap.get(country).printVisibility() );
							}
							System.out.print("\n");
						}
						else if(option == 6) {
							Iterator<String> cityIt2 = citymap.keySet().iterator();
							while (cityIt2.hasNext()) {
							    String country = cityIt2.next();
							    System.out.print( citymap.get(country).printWindSpeedDir() );
							}
							System.out.print("\n");
						}
						else if(option == 7) {
							Iterator<String> cityIt2 = citymap.keySet().iterator();
							while (cityIt2.hasNext()) {
							    String country = cityIt2.next();
							    System.out.print( citymap.get(country).printCondDescription() );
							}
							System.out.print("\n");
						}
						else if(option == 8) {
							Iterator<String> cityIt2 = citymap.keySet().iterator();
							while (cityIt2.hasNext()) {
							    String country = cityIt2.next();
							    System.out.print( citymap.get(country).printAll() );
							    System.out.print("\n");
							}
						}
						else if(option == 9) {
							break;
						}
					}
					catch(InputMismatchException ime) {
						System.out.println("\nThat is not a valid option.");
						input.nextLine();
					}
				}
			}
			//terminate program
			else if (cityName.equals("exit")) {
				System.out.println("\nThank you for using my weather program.");
				break;
			}
			//chose info for one city
			else {
				System.out.println("\nI do have information about the weather in "+citymap.get(cityName).getCity()+".\n");
				printMenu();
				while(true) {
					try {
						System.out.print("\nWhat information would you like to know? ");
						int option = input.nextInt();
						input.nextLine();
						if(option != 1 && option != 2 && option != 3 &&
								option != 4 && option != 5 && option != 6 &&
								option != 7 && option != 8 && option != 9 ) {
							System.out.println("\nThat is not a valid option.\n");
						}
						else if(option == 1) {
							System.out.print( citymap.get(cityName).printCurrTemp() );
							System.out.print("\n");
						}
						else if(option == 2) {
							System.out.print( citymap.get(cityName).printDayHighLow() );
							System.out.print("\n");
						}
						else if(option == 3) {
							System.out.print( citymap.get(cityName).printHumidity() );
							System.out.print("\n");
						}
						else if(option == 4) {
							System.out.print( citymap.get(cityName).printPressure() );
							System.out.print("\n");
						}
						else if(option == 5) {
							System.out.print( citymap.get(cityName).printVisibility() );
							System.out.print("\n");
						}
						else if(option == 6) {
							System.out.print( citymap.get(cityName).printWindSpeedDir() );
							System.out.print("\n");
						}
						else if(option == 7) {
							System.out.print( citymap.get(cityName).printCondDescription() );
							System.out.print("\n");
						}
						else if(option == 8) {
							System.out.print( citymap.get(cityName).printAll() );
							System.out.print("\n");
						}
						else if(option == 9) {
							break;
						}
					}
					catch(InputMismatchException ime) {
						System.out.println("\nThat is not a valid option.\n");
						input.nextLine();
					}
				}
			}

		}
		
		input.close();
		
	}
}
