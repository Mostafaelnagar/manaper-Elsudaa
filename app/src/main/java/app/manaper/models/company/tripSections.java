package app.manaper.models.company;

 import com.google.gson.annotations.SerializedName;

 public class tripSections {

	@SerializedName("arrival")
	private Arrival arrival;

	@SerializedName("third_city")
	private ThirdCity thirdCity;

	@SerializedName("first_city")
	private FirstCity firstCity;

	@SerializedName("departure")
	private Departure departure;

	@SerializedName("second_city")
	private SecondCity secondCity;

	public void setArrival(Arrival arrival){
		this.arrival = arrival;
	}

	public Arrival getArrival(){
		return arrival;
	}

	public void setThirdCity(ThirdCity thirdCity){
		this.thirdCity = thirdCity;
	}

	public ThirdCity getThirdCity(){
		return thirdCity;
	}

	public void setFirstCity(FirstCity firstCity){
		this.firstCity = firstCity;
	}

	public FirstCity getFirstCity(){
		return firstCity;
	}

	public void setDeparture(Departure departure){
		this.departure = departure;
	}

	public Departure getDeparture(){
		return departure;
	}

	public void setSecondCity(SecondCity secondCity){
		this.secondCity = secondCity;
	}

	public SecondCity getSecondCity(){
		return secondCity;
	}

	@Override
 	public String toString(){
		return 
			"tripSections{" +
			"arrival = '" + arrival + '\'' + 
			",third_city = '" + thirdCity + '\'' + 
			",first_city = '" + firstCity + '\'' + 
			",departure = '" + departure + '\'' + 
			",second_city = '" + secondCity + '\'' + 
			"}";
		}
}