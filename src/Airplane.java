

import java.sql.Array;
import java.util.List;

/**
 * @author Soneer
 * CLass has all functions needed by airplane.java
 */
public abstract class Airplane {

	/**
	 * Calls 2d array initalization methods
	 */
	public Airplane() {
		initalizeEconomySeats();
		initalizeFirstSeats();
	}
	SeatObject [][] Seats = new SeatObject[20][6];
	SeatObject [][] SeatsFirstClass = new SeatObject[2][4];

	/**
	 * Initalizes first class seats
	 */
	public void initalizeFirstSeats()
	{
		String seatNumber = "";
		for (int i = 0; i < 2; i++)
		{
			for (int j = 0; j < 4; j++){

				seatNumber= seatNumberCalculator(j, i, "First") +"";
				if(j == 0 || j ==3){
					SeatObject emptySeat = new SeatObject("null","null","null", "First", seatNumber,"W", false, i, j);
					SeatsFirstClass[i][j] = emptySeat;
				}
				else if (j == 1 || j ==2){
					SeatObject emptySeat = new SeatObject("null","null","null", "First",seatNumber, "A", false, i, j);
					SeatsFirstClass[i][j] = emptySeat;
				}
			}
		}
	}
	/**
	 * Initalizes economy class seats
	 */
	public void initalizeEconomySeats()
	{
		String seatNumber="";
		for (int i = 0; i < 20; i++)
		{
			for (int j = 0; j < 6; j++)
			{
				seatNumber= seatNumberCalculator(j, i, "Economy") +"";

				if(j == 0 || j ==5){
					SeatObject emptySeat = new SeatObject("null","null","null", "Economy", seatNumber, "W", false, i ,j);
					Seats[i][j] = emptySeat;
				}
				else if (j == 1 || j ==4){
					SeatObject emptySeat = new SeatObject("null","null","null", "Economy", seatNumber,"C", false,i,j);
					Seats[i][j] = emptySeat;
				}
				else if (j == 2  || j ==3){
					SeatObject emptySeat = new SeatObject("null","null","null", "Economy", seatNumber,"A", false,i,j);
					Seats[i][j] = emptySeat;
				}
			}
		}
	}

	public SeatObject[][] returnEconomyClassSeats(){
		return Seats;
	}
	public SeatObject[][] returnFirstClassSeats(){
		return SeatsFirstClass;
	}
	public void setEconomyClassSeats( SeatObject[][] array){
		Seats = array;
	}
	public void setFirstClassSeats( SeatObject[][] array){
		SeatsFirstClass = array;
	}

	/**
	 * @param name  Name of passenger provided by user
	 * @param serviceClass Name of desired service class(First/Economy)
	 * @param seatPreference Seating Preference for user (W=window, A=aisle, C=center
	 */
	public String addPassenger(String name, String serviceClass, String seatPreference, int iSpot, int jSpot) {
		String seatNumber = "Seat Not Available.";
		if(serviceClass.equals("Economy"))
		{
			mainloop:
				for (int i = 0; i < 20; i++)
				{
					for (int j = 0; j < 6; j++){
						if(Seats[i][j].getBooleanReservation()==false &&(!seatPreference.equalsIgnoreCase("W")&&!seatPreference.equalsIgnoreCase("C")&&!seatPreference.equalsIgnoreCase("A")))
						{
							System.out.println("test"+ Seats[i][j].getBooleanReservation());
							seatNumber= seatNumberCalculator(j, i, "Economy") +"";
							SeatObject seat = new SeatObject(name,seatPreference,"G", serviceClass, seatNumber, Seats[i][j].getSeatPreference(), true, i , j);
							Seats[i][j] = seat;
							break mainloop;
						}

						else if(!Seats[i][j].getBooleanReservation() && Seats[i][j].getSeatPreference().equals(seatPreference))
						{
							seatNumber= seatNumberCalculator(j, i, "Economy") +"";
							
							SeatObject seat = new SeatObject(name,"null","I", serviceClass, seatNumber,seatPreference, true, i , j);
							Seats[i][j] = seat;
							break mainloop;
						}
					}
				}
		}
		else if(serviceClass.equals("First"))
		{
			mainloop:
				for (int i = 0; i < 2; i++)
				{
					for (int j = 0; j < 4; j++){
						if(SeatsFirstClass[i][j].getBooleanReservation()==false && (!seatPreference.equalsIgnoreCase("W")&&!seatPreference.equalsIgnoreCase("C")&&!seatPreference.equalsIgnoreCase("A")))
						{
							seatNumber= seatNumberCalculator(j, i, "First") +"";
							SeatObject seat = new SeatObject(name,seatPreference,"G", serviceClass, seatNumber, SeatsFirstClass[i][j].getSeatPreference(), true, i , j);
							SeatsFirstClass[i][j] = seat;
							break mainloop;
						}

						else if(!SeatsFirstClass[i][j].getBooleanReservation() && SeatsFirstClass[i][j].getSeatPreference().equals(seatPreference))
						{
							seatNumber= seatNumberCalculator(j, i, "First") +"";
							SeatObject seat = new SeatObject(name,"null","I", serviceClass, seatNumber,seatPreference, true, i , j);
							SeatsFirstClass[i][j] = seat;
							break mainloop;
						}
					}
				}
		}
		return seatNumber;
	}

	/**
	 * @param groupName Name of the group 
	 * @param names Name of the individuals in the group
	 * @param serviceClass Name of desired service class(First/Economy)
	 */
	public String addGroup(String groupName, List<String> names, String serviceClass ) {
		int numberOfMembers = names.size();
		int totalEmpty = 0;
		int rowWithMostEmpty=0;
		int mainRow=0;
		int tempRow=0;
		if(serviceClass.equals("First"))
		{
			mainloop:
				for (int i = 0; i < 2; i++)
				{
					for (int j = 0; j < 4; j++)
					{
						if(SeatsFirstClass[i][j].getBooleanReservation()==false)
						{
							tempRow++;
							totalEmpty++;
							if(tempRow>mainRow)
							{
								mainRow=tempRow;
								rowWithMostEmpty = i;
							}

						}
					}
				}
		
		if(totalEmpty<numberOfMembers)
		{
		return "Failed, no one added";
		}
		else{
		int k =0;
		mainloop:
			while( k< numberOfMembers)
			{

				for (int j = 0; j < 4; j++)
				{
					if(k== numberOfMembers)
					{
						System.out.println(k);
						break mainloop;
					}
					else if(SeatsFirstClass[rowWithMostEmpty][j].getBooleanReservation()==false)
					{

						SeatObject tempSeat = new SeatObject(names.get(k), groupName, "G", serviceClass, SeatsFirstClass[rowWithMostEmpty][j].getSeatNumber(), SeatsFirstClass[rowWithMostEmpty][j].getSeatPreference(), true, rowWithMostEmpty, j);	
						SeatsFirstClass[rowWithMostEmpty][j] = tempSeat;
						k++;
					}
				}
					while(k<numberOfMembers)
					{
						System.out.println(names.get(k));
						addPassenger(names.get(k), serviceClass, groupName, 0, 0);
						k++;
					}
				
			}
		}
		}
		
		else{
			mainloop:
				for (int i = 0; i < 20; i++)
				{
					for (int j = 0; j < 6; j++)
					{
						if(Seats[i][j].getBooleanReservation()==false)
						{
							tempRow++;
							totalEmpty++;
							if(tempRow>mainRow)
							{
								mainRow=tempRow;
								rowWithMostEmpty = i;
							}

						}
					}
				}
		if(totalEmpty< numberOfMembers){
			return "Failed, no one added";	
		}
		else {
		int k =0;
		mainloop:
			while( k< numberOfMembers)
			{

				for (int j = 0; j < 6; j++)
				{
					if(k== numberOfMembers)
					{
						System.out.println(k);
						break mainloop;
					}
					else if(Seats[rowWithMostEmpty][j].getBooleanReservation()==false)
					{

						SeatObject tempSeat = new SeatObject(names.get(k), groupName, "G", serviceClass, Seats[rowWithMostEmpty][j].getSeatNumber(), Seats[rowWithMostEmpty][j].getSeatPreference(), true, rowWithMostEmpty, j);	
						Seats[rowWithMostEmpty][j] = tempSeat;
						k++;
					}
				}
					while(k<numberOfMembers)
					{
						System.out.println("k"+k+"   "+"numberOfMembers"+numberOfMembers);
						addPassenger(names.get(k), serviceClass, groupName, 0, 0);
						k++;
					}
				
			}
		}
		}
		return "Success!";
		
	}

	/**
	 * @param passengerName Name of the passenger who wants cancel 
	 */
	public String cancelPassenger(String passengerName) {
		boolean passengerFound = false;

		mainloop:
			for (int i = 0; i < 2; i++)
			{
				for (int j = 0; j < 4; j++)
				{
					if(SeatsFirstClass[i][j].getName().equals(passengerName) && SeatsFirstClass[i][j].getIndivdualOrGroup().equals("I"))
					{
						SeatObject tempSeat = new SeatObject("null","null","null",SeatsFirstClass[i][j].getFlightClass() , SeatsFirstClass[i][j].getSeatNumber(),SeatsFirstClass[i][j].getSeatPreference(), false, i, j);
						SeatsFirstClass[i][j] = tempSeat;
						passengerFound = true;
						break mainloop;
					}
				}
			}
		mainloop:
			if(!passengerFound){

				for (int i = 0; i < 20; i++)
				{
					for (int j = 0; j < 6; j++)
					{
						if(Seats[i][j].getName().equals(passengerName))
						{
							SeatObject tempSeat = new SeatObject("null","null", "null", Seats[i][j].getFlightClass() , Seats[i][j].getSeatNumber(),SeatsFirstClass[i][j].getSeatPreference(), false, i, j);
							Seats[i][j] = tempSeat;
							break mainloop;
						}
					}
				}
			}
			return passengerFound+"";
	}

	/**
	 * Cancels whole group of passengers
	 * @param groupName name of group
	 */
	public String cancelGroup(String groupName) {
		boolean passengerFound = false;

			for (int i = 0; i < 2; i++)
			{
				for (int j = 0; j < 4; j++)
				{
					if(SeatsFirstClass[i][j].getGroupName().equals(groupName))
					{
						SeatObject tempSeat = new SeatObject("null","null","null",SeatsFirstClass[i][j].getFlightClass() , SeatsFirstClass[i][j].getSeatNumber(),SeatsFirstClass[i][j].getSeatPreference(), false, i, j);
						SeatsFirstClass[i][j] = tempSeat;
						passengerFound = true;
					
					}
				}
			}
			if(!passengerFound){

				for (int i = 0; i < 20; i++)
				{
					for (int j = 0; j < 6; j++)
					{
						if(Seats[i][j].getGroupName().equals(groupName))
						{
							SeatObject tempSeat = new SeatObject("null","null", "null", Seats[i][j].getFlightClass() , Seats[i][j].getSeatNumber(),Seats[i][j].getSeatPreference(), false, i, j);
							Seats[i][j] = tempSeat;
							passengerFound=true;
						}
					}
				}
			}
			return passengerFound+"";
	}

	/**
	 * Returns all seat empty in a class
	 * @param serviceClass seating class
	 * @return empty seats in a seating class
	 */
	public Array printSeating(String serviceClass) {
		//Array seatsNotReserved;
		if(serviceClass.equals("Economy")){
			for (int i = 0; i < 20; i++)
			{
				for (int j = 0; j < 6; j++)
				{
					if(!Seats[i][j].getBooleanReservation() && Seats[i][j].getFlightClass().equals(serviceClass))
					{
						System.out.println(seatNumberCalculator(j, i, "Economy"));
					}
				}
			}
		}
		if(serviceClass.equals("First")){
			for (int i = 0; i < 2; i++)
			{
				for (int j = 0; j < 4; j++)
				{
					if(!SeatsFirstClass[i][j].getBooleanReservation() && SeatsFirstClass[i][j].getFlightClass().equals(serviceClass))
					{
					System.out.println(SeatsFirstClass[i][j].getSeatNumber() + "   "+ SeatsFirstClass[i][j].getName()+"  "+ SeatsFirstClass[i][j].getBooleanReservation());
					}
					}
			}
		}
		return null;
	}

	/**
	 * Returns all seats full in a class
	 * @param serviceClass flight class
	 * @return seats that are full in a class
	 */
	public Array printManifest(String serviceClass) {
		if(serviceClass.equals("Economy")){
			for (int i = 0; i < 20; i++)
			{
				for (int j = 0; j < 6; j++)
				{
					if(Seats[i][j].getBooleanReservation() && Seats[i][j].getFlightClass().equals(serviceClass))
					{
						System.out.println(seatNumberCalculator(j, i, "Economy"));
					}
				}
			}
		}
		if(serviceClass.equals("First")){
			for (int i = 0; i < 2; i++)
			{
				
				for (int j = 0; j < 4; j++)
				{
					if(SeatsFirstClass[i][j].getBooleanReservation() && SeatsFirstClass[i][j].getFlightClass().equals(serviceClass))
					{
					System.out.println(SeatsFirstClass[i][j].getSeatNumber() + "   "+ SeatsFirstClass[i][j].getName()+"  "+ SeatsFirstClass[i][j].getBooleanReservation());
				}
				}
			}
		}
		return null;
	}


	//Helper Method
	/**
	 * Calculates seat number
	 * @param i row value
	 * @param j coln. value
	 * @param serviceClass flight class
	 * @return seat number
	 */
	public String seatNumberCalculator(int i, int j,String serviceClass)
	{
		String seatName="";
		int seatNumber = j;
		if(serviceClass.equals("Economy"))
		{
			seatNumber = j +10;
		}
		else if(serviceClass.equals("First"))
		{
			seatNumber = j+1;
		}
		switch (i)
		{
		case 0:
			seatName="A";
			break;
		case 1:
			seatName="B";
			break;
		case 2:
			seatName="C";
			break;
		case 3:
			seatName="D";
			break;
		case 4:
			seatName="E";
			break;
		case 5:
			seatName="F";
			break;

		}

		return seatNumber+seatName;
	}
	/**
	 * Calc. colunm number
	 * @param seatNumber number of seat
	 * @return column number
	 */
	public int seatColumnCalculator(String seatNumber)
	{
		int colomnNumber = 0;

		if( seatNumber.contains("A"))
		{
			colomnNumber=0;
		}
		else if( seatNumber.contains("B"))
		{
			colomnNumber=1;
		}
		else if( seatNumber.contains("C"))
		{
			colomnNumber=2;
		}
		else if( seatNumber.contains("D"))
		{
			colomnNumber=3;
		}
		else if( seatNumber.contains("E"))
		{
			colomnNumber=4;
		}
		else if( seatNumber.contains("F"))
		{
			colomnNumber=5;
		}
		return colomnNumber;
	}
	/**
	 * Calc. row number
	 * @param j colunm number
	 * @param serviceClass class of flight
	 * @return row number
	 */
	public String getSeatPreference(int j, String serviceClass){

		String preference="";
		if(serviceClass.equals("Economy"))
		{
			if(j == 0 || j ==5){
				preference ="W";
			}
			else if (j == 1 || j ==4){
				preference = "C";
			}
			else if (j == 2  || j ==3){
				preference = "A";
			}
		}
		else if(serviceClass.equals("First"))
		{
			if(j == 0 || j ==3){
				preference = "W";
			}
			else if (j == 1 || j ==2){
				preference = "A";
			}
		}
		return preference;
	}
}
