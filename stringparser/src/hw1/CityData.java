package hw1;

import java.util.ArrayList;

public class CityData {
	protected String city;
	protected int currTemp, dayLow, dayHigh, humidity, windDir;
	protected float pressure, visibility, windSpeed;
	protected ArrayList<String> condDescription;
/*	city – String
	currentTemperature – int (measured in degrees Fahrenheit)
	dayLow – int (measured in degrees Fahrenheit)
	dayHigh – int (measured in degrees Fahrenheit)
	humidity – int (measured as a percentage)
	pressure – float (measured in Inch Hg)
	visibility – float (measured in miles)
	windSpeed – float (measured in miles/hour)
	windDirection – int (measured in degrees)
	conditionDescription - String
*/
	public CityData(String city1, int cT, int dL, int dH, int h,
			float p, float v, float wS, int wD, ArrayList<String> cD) {
		city = city1;
		currTemp = cT;
		dayLow = dL;
		dayHigh = dH;
		humidity = h;
		pressure = p;
		visibility = v;
		windDir = wD;
		windSpeed = wS;
		condDescription = new ArrayList<String>(cD);
	}
	
//convenience checking print method
	public String toString(){
	    String sto = ": " +
	    		"\n City Name: " + city + 
	    		"\n Current Temperature: " + currTemp + 
	    		"\n Day Low: " + dayLow
	    		+ "\n Day High: "+ dayHigh 
	    		+ "\n Humidity: " + humidity
	    		+ "\n Pressure: " + pressure 
	    		+ "\n Visibility: " + visibility 
	    		+ "\n Wind Direction: " + windDir
	    		+ "\n Wind Speed: " + windSpeed
	    		+ "\n Condition Description: " + condDescription.toString();
	    return sto;
    }


//print methods
	public String printCurrTemp() {
		String tempData = "\nThe temperature in " + city + " is " + currTemp + " degrees Fahrenheit.";
		return tempData;
	}
	public String printDayHighLow() {
		String hlTemp = "\nThe high temperature in " + city + " is " + dayHigh + " degrees Fahrenheit." + 
				"\nThe low temperature in " + city + " is " + dayLow + " degrees Fahrenheit.";
		return hlTemp;
	}
	public String printHumidity() {
		String hum = "\nThe humidity in " + city + " is " + humidity + "%.";
		return hum;
	}
	public String printPressure() {
		String pres = "\nThe pressure in " + city + " is " + pressure + " Inch Hg.";
		return pres;
	}
	public String printVisibility() {
		String vis = "\nThe visibility in " + city + " is " + visibility + " miles.";
		return vis;
	}
	public String printWindSpeedDir() {
		String sdWind = "\nThe wind speed in " + city + " is " + windSpeed + " miles/hour." + 
				"\nThe wind direction in " + city + " is " + windDir + " degrees.";
		return sdWind;
	}
	public String printCondDescription() {
		String cDes = "\n" + city + " weather can be described as ";
		if (condDescription.size() == 1) {
			cDes += condDescription.get(0) + ".";
		}
		else if (condDescription.size() == 2) {
			cDes += condDescription.get(0) + " and " + condDescription.get(1) + ".";
		}
		else {
			int tempSize = condDescription.size()-1;
			int count = 0;
			while(count<tempSize-1) {
				cDes += condDescription.get(count) + ", ";
				count++;
			}
			cDes += condDescription.get(tempSize-1) + " and " + condDescription.get(tempSize) + ".";
		}
		return cDes;
	}
	public String printAll(){
	    String sto =  
	    		printCurrTemp() + printDayHighLow() + printHumidity() + 
	    		printPressure() + printVisibility() + printWindSpeedDir() + 
	    		printCondDescription();
	    return sto;
    }
	
// getter methods to access data
	public String getCity() {
		return city;
	}
	public int getCurrTemp() {
		return currTemp;
	}
	public int getDayLow() {
		return dayLow;
	}
	public int getDayHigh() {
		return dayHigh;
	}
	public int getHumidity() {
		return humidity;
	}
	public float getPressure() {
		return pressure;
	}
	public float getVisibility() {
		return visibility;
	}
	public float getWindSpeed() {
		return windSpeed;
	}
	public int getWindDirection() {
		return windDir;
	}
	public ArrayList<String> getCondDescription(){
		return condDescription;
	}	
}