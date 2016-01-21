

/**
 * @author Soneer
 *
 */
public class SeatObject {

	
	private String passengerName;
	private String groupName;
	private String flightClass;
	private String seatNumber;
	private String seatPref;
	private String indivdualOrGroup;
	private boolean reserved;
	int i;
	int j;
	
	/**
	 * Constructs Seat Object
	 * 
	 * @param name Name Of Passenger In Seat
	 * @param gName Group Name Of Passenger In Seat, Null If No Group
	 * @param iOrG Is Passenger In Seat In A Group Or Individual
	 * @param passengerClass Class Of Passenger, First Or Economy
	 * @param numberOfSeat Seat Number ie.(1A)
	 * @param seatPerference Seat Preference Of Passenger, W, A, Or C
	 * @param bool Is Seat Occupied
	 * @param arrayValuei Seat Value In The Array (Row)
	 * @param arrayValuej Seat Value In The Array (Column)
	 */
	public SeatObject(String name, String gName,String iOrG, String passengerClass, String numberOfSeat,String seatPerference, boolean bool, int arrayValuei, int arrayValuej) {
		passengerName = name;
		groupName = gName;
		flightClass = passengerClass;
		seatNumber = numberOfSeat;
		seatPref = seatPerference;
		reserved = bool;
		indivdualOrGroup = iOrG;
		i = arrayValuei;
		j = arrayValuej;
	}
	/**
	 * Returns passenger name
	 * @return name of passenger
	 */
	public String getName()
	{
		return passengerName;
	}
	/**
	 * Returns seat preference of passenger
	 * @return passenger seat pref.
	 */
	public String getSeatPreference()
	{
		return seatPref;
	}
	/**
	 * @return If passenger is in group or alone
	 */
	public String getIndivdualOrGroup()
	{
		return indivdualOrGroup;
	}
	/**
	 * @return name of the group passenger is in, null if not in group
	 */
	public String getGroupName()
	{
		return groupName;
	}
	
	/**
	 * @return flight class of passenger
	 */
	public String getFlightClass()
	{
		return flightClass;
	}
	
	/**
	 * @return the seat passenger is in
	 */
	public String getSeatNumber()
	{
		return seatNumber;
	}
	
	/**
	 * @return true is seat is occupied false if seat is empty
	 */
	public boolean getBooleanReservation()
	{
		return reserved;
	}
	
	/**
	 * @return Postion in 2d array for row
	 */
	public int getI()
	{
		return i;
	}
	
	/**
	 * @return Position for 2d array in col.
	 */
	public int getJ()
	{
		return j;
	}
}
