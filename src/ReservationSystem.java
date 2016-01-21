


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;


/**
 * @author Soneer
 *Loads/ creates a file calls methods fro airplane class, talks with user
 */
public class ReservationSystem {
	public static void main(String arg[]) throws IOException{

		Airplane currentPlane = new Airplane();
		String path = arg[0];
		
		File f = new File(path);
		if(f.exists() && !f.isDirectory()) { 
			SeatObject[][] economySeats = currentPlane.returnEconomyClassSeats();
			SeatObject[][] firstSeats = currentPlane.returnFirstClassSeats();
			FileReader fr = new FileReader(path); 
			BufferedReader br = new BufferedReader(fr); 
			String s;
			while((s = br.readLine()) != null) 
			{
				List<String> lines = Arrays.asList(s.split(", "));
				String passengerName="";
				String groupName="";
				String passengerClass="";
				String numberOfSeat= lines.get(0);
				String groupOrIndividual= lines.get(1);
				if(groupOrIndividual.equalsIgnoreCase("G")){
					groupName= lines.get(2);
					passengerName= lines.get(3);
				}
				else
				{
					passengerName = lines.get(2);
				}

				int Valj = currentPlane.seatColumnCalculator(numberOfSeat);
				int Vali = Integer.parseInt(numberOfSeat.replaceAll("[^\\d.]", ""));
				if(Vali == 1 || Vali ==2)
				{
					passengerClass = "First";
				}
				else
				{
					passengerClass = "Economy";
				}
				String seatPref = currentPlane.getSeatPreference(Valj, passengerClass);
				SeatObject tempSeat = new SeatObject(passengerName,groupName,groupOrIndividual, passengerClass, numberOfSeat,seatPref, true, Vali,Valj);
				if(passengerClass.equals("First"))
				{
					firstSeats[Vali-1][Valj] = tempSeat;
				}

				else if(passengerClass.equals("Economy"))
				{
					economySeats[Vali-10][Valj] = tempSeat;
				}
			}	
			currentPlane.setEconomyClassSeats(economySeats);
			currentPlane.setFirstClassSeats(firstSeats);
		}
		else
		{
			saveFile(currentPlane, f);
		}
		boolean programRunnining = true;

		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		while(programRunnining)
		{
			System.out.println("Enter command: ");
			try{
				
				String userInput = bufferRead.readLine();
				System.out.println(userInput);
				switch (userInput)
				{
				case "P":  
					System.out.println("Please Enter Name:");
					String userName = bufferRead.readLine();
					System.out.println("Please Enter Service Class:");
					String userServiceClass = bufferRead.readLine();
					System.out.println(userServiceClass);
					System.out.println("Please Enter Seat Perference:");
					String userPerference = bufferRead.readLine();
					String results = currentPlane.addPassenger(userName, userServiceClass, userPerference, 0, 0);
					System.out.println(results);
					break;
				case "G":  
					System.out.println("Please Enter Group Name:");
					String groupName = bufferRead.readLine();
					System.out.println(groupName);
					System.out.println("Please Enter Names Of Group:");
					List<String> names = Arrays.asList(bufferRead.readLine().split(", "));
					System.out.println("Please Enter Service Class:");
					String groupClass = bufferRead.readLine();
					System.out.println(currentPlane.addGroup(groupName, names, groupClass));
					break;
				case "C":  
					System.out.println("Cancel [I]ndividual or [G]roup?");
					String cancelGroup = bufferRead.readLine();
					if(cancelGroup.equalsIgnoreCase("I"))
					{
						System.out.println("Name of Passenger?");
						String cancelName = bufferRead.readLine();
						System.out.println(currentPlane.cancelPassenger(cancelName));
					}
					else if(cancelGroup.equalsIgnoreCase("G"))
					{
						System.out.println("Name of Group?");
						String cancelName = bufferRead.readLine();
						System.out.println(currentPlane.cancelGroup(cancelName));
					}
					else
					{
						System.out.println("Input can not be understood.");
					}
					break;
				case "A":
					
					boolean input = false;
					while(!input)
					{
						System.out.println("Service class(First/Economy)");
						String serviceClass = bufferRead.readLine();
						if(serviceClass.equals("First")||serviceClass.equals("Economy"))
						{
							currentPlane.printSeating(serviceClass);
							input = true;
						}
						else
						{
							System.out.println("Input can not be understood.");
						}
					}

					break;
				case "M":  
					System.out.println("Service class(First/Economy)");
					String serviceClass = bufferRead.readLine();
					if(serviceClass.equals("First")||serviceClass.equals("Economy"))
					{
					currentPlane.printManifest(serviceClass);
					}
					else
					{
						System.out.println("Input can not be understood.");
					}
					break;
				case "Q":  
					programRunnining = false;
					saveFile(currentPlane, f);
					break;
				default:break;
				}
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * Saves filled seats to txt file
	 * @param currentPlane  current plane object
	 * @param f name of file
	 * @throws FileNotFoundException execption if file not found
	 * @throws UnsupportedEncodingException
	 */
	public static void saveFile(Airplane currentPlane, File f) throws FileNotFoundException, UnsupportedEncodingException{
		//File file = new File(path+".txt");
		SeatObject[][] economySeats = currentPlane.returnEconomyClassSeats();
		SeatObject[][] firstSeats = currentPlane.returnFirstClassSeats();
		PrintWriter writer = new PrintWriter(f, "UTF-8");
		String name ="";
		String groupName = "";
		String seatNumber = "";
		String groupOrInd = "";

		for (int i = 0; i < 2; i++)
		{
			for (int j = 0; j < 4; j++){

				if(firstSeats[i][j].getBooleanReservation() ==true)
				{
					
				name = firstSeats[i][j].getName();
				groupName = firstSeats[i][j].getGroupName();
				seatNumber = firstSeats[i][j].getSeatNumber();
				groupOrInd = firstSeats[i][j].getIndivdualOrGroup();
				
				if(groupOrInd.equalsIgnoreCase("I")){
					writer.println(seatNumber+ ", "+ groupOrInd + ", " + name );
				}
				else if (groupOrInd.equalsIgnoreCase("G")){

					writer.println(seatNumber+ ", "+ groupOrInd + ", "+ groupName+", " + name );
				}
				}
			}
		}
		for (int i = 0; i < 20; i++)
		{
			for (int j = 0; j < 6; j++)
			{

				if(economySeats[i][j].getBooleanReservation() ==true)
				{
				name = economySeats[i][j].getName();
				groupName = economySeats[i][j].getGroupName();
				seatNumber = economySeats[i][j].getSeatNumber();
				groupOrInd = economySeats[i][j].getIndivdualOrGroup();
				
				if(groupOrInd.equalsIgnoreCase("I")){
					writer.println(seatNumber+ ", "+ groupOrInd + ", " + name );
				}
				else if (groupOrInd.equalsIgnoreCase("G")){

					writer.println(seatNumber+ ", "+ groupOrInd + ", "+ groupName+", " + name );
				}
				}
			}
		}
		writer.flush();
		writer.close();
	}

}
