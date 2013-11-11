package SC13Project.Milestone1.FlightTicket;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import SC13Project.Milestone1.FlightTicket.Database.AirlineInfo;
import SC13Project.Milestone1.FlightTicket.Database.BookingInfo;
import SC13Project.Milestone1.FlightTicket.Database.DateInfo;
import SC13Project.Milestone1.FlightTicket.Database.TicketInfo;
import SC13Project.Milestone1.FlightTicket.Database.ObjectFactory;

//Please do not change the name of the package or this interface
//Please add here your implementation
public class FlightTicketImpl implements BookingFlightWS {

	@Override
	public List<FlightInfo> getFlightInfo(String departure, String destination,
			FlightTicketDate date) {
		
		ClassLoader cl = SC13Project.Milestone1.FlightTicket.Database.ObjectFactory.class.getClassLoader();
		String packageName = FlightInfo.class.getPackage().getName();
		List<FlightInfo> allFlightsInfo = new ArrayList<FlightInfo>();
		Path test = Paths.get( System.getProperty("user.dir") + "/../datasource/ds_39_5.xml" );
		JAXBContext jc;
		

			try {
				jc = JAXBContext.newInstance(packageName, cl);
				Unmarshaller u;
				u = jc.createUnmarshaller();
				JAXBElement<TicketInfo> root;
				root = (JAXBElement<TicketInfo>)u.unmarshal(new FileInputStream(test.normalize().toString()));
				TicketInfo flight = root.getValue();
				

				List<SC13Project.Milestone1.FlightTicket.Database.AirlineInfo> airlines= flight.getAirlines().getAirline();
				List<SC13Project.Milestone1.FlightTicket.Database.BookingInfo> bookings= flight.getBookings().getBooking();
				
				for ( SC13Project.Milestone1.FlightTicket.Database.AirlineInfo a : airlines ){
					if ( allFlightsInfo.size() > 0 ) {
						
							if ( a.getDeparture().equals(departure) ) {
								if(a.getDestination().equals(destination)) {
									for ( DateInfo d : a.getDates().getDate() ) {
										if ( d.getYear() == date.getYear() && d.getMonth()==date.getMonth() && d.getDay()==date.getDate()){
											FlightInfo temp= new FlightInfo();
											temp.setDeparture(a.getDeparture());
											temp.setDestination(a.getDestination());
											temp.setFlightNo(a.getFlightNo());
											temp.setPrice(a.getPrice());
											temp.setSeats(a.getSeats());
											allFlightsInfo.add(temp);
											break;
										}
									}
								}			
							}
						}
					}
				
				for(SC13Project.Milestone1.FlightTicket.Database.BookingInfo b : bookings){
					if ( b.getDate().getYear() == date.getYear() && b.getDate().getMonth() == date.getMonth() && b.getDate().getDay() == date.getDate() ){	
						for(FlightInfo a : allFlightsInfo){
							if ( b.getFlightNo() == a.getFlightNo() ) {
								a.setSeats(a.getSeats() - b.getSeats());
								break;
							}
						}
					}	
				}
				
				for ( FlightInfo a : allFlightsInfo ){
					if ( a.getSeats() == 0 )
						allFlightsInfo.remove(a);
				}
				
				return allFlightsInfo;
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		// TODO Auto-generated method stub
		
		return null;
	}

	private FlightInfo getFlightInfobyFlightNumber(String flightNumber, FlightTicketDate date,int seats){
		
		ClassLoader cl = SC13Project.Milestone1.FlightTicket.Database.ObjectFactory.class.getClassLoader();
		String packageName = FlightInfo.class.getPackage().getName();
		List<FlightInfo> allFlightsInfo = new ArrayList<FlightInfo>();
		Path test = Paths.get( System.getProperty("user.dir") + "/../datasource/ds_39_5.xml" );
		JAXBContext jc;
		
		try {
			jc = JAXBContext.newInstance(packageName, cl);
			Unmarshaller u;
			u = jc.createUnmarshaller();
			JAXBElement<TicketInfo> root;
			root = (JAXBElement<TicketInfo>)u.unmarshal(new FileInputStream(test.normalize().toString()));
			TicketInfo flight = root.getValue();
				
			List<SC13Project.Milestone1.FlightTicket.Database.AirlineInfo> airlines= flight.getAirlines().getAirline();
				
			for ( SC13Project.Milestone1.FlightTicket.Database.AirlineInfo a : airlines ){
				if ( allFlightsInfo.size() > 0 ) {
					if ( a.getFlightNo().equals(flightNumber) ) {
							if(a.getSeats()>=seats)
								for ( DateInfo d : a.getDates().getDate() ) {
									if ( d.getYear() == date.getYear() && d.getMonth() == date.getMonth() && d.getDay() == date.getDate() ) {
										FlightInfo temp= new FlightInfo();
										temp.setDeparture(a.getDeparture());
										temp.setDestination(a.getDestination());
										temp.setPrice(a.getPrice());
										return temp;
									}
								}
							}
					}			
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	}
	
	@Override
	public String bookFlight(String flightNumber, FlightTicketDate date,
			int seats) throws FlightUnAvailableException {
	
		ClassLoader cl = SC13Project.Milestone1.FlightTicket.Database.ObjectFactory.class.getClassLoader();
		String packageName=FlightInfo.class.getPackage().getName();
		String FlightID;
		Path test = Paths.get( System.getProperty("user.dir") + "/../datasource/ds_39_5.xml" );
		try {

			JAXBContext jc = JAXBContext.newInstance(packageName, cl);
			Unmarshaller u = jc.createUnmarshaller();
			JAXBElement<TicketInfo> root = (JAXBElement<TicketInfo>)u.unmarshal(new FileInputStream(test.normalize().toString()));
			TicketInfo flight = root.getValue();
			
			// Marshell creation 
			JAXBContext context=JAXBContext.newInstance(packageName, cl);
			Marshaller m=context.createMarshaller();

			FlightInfo b=new FlightInfo();
			b=getFlightInfobyFlightNumber(flightNumber,date,seats);
			
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ObjectFactory obf = new ObjectFactory();
			JAXBElement<TicketInfo> output= obf.createTickets(flight);
					
			BookingInfo temp = obf.createBookingInfo();
			long seconds = System.currentTimeMillis();
			DateInfo d = new DateInfo();
			d.setYear(date.getYear());
			d.setMonth(date.getMonth());
			d.setDay(date.getDate());
			
			FlightID = String.valueOf(seconds);
			
			temp.setBookingID(FlightID + flightNumber);
			temp.setDate(d);
			temp.setFlightNo(flightNumber);
			temp.setSeats(seats);
			
			flight.getBookings().getBooking().add(temp);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancelBooking(String bookingID) {
		// TODO Auto-generated method stub
		
	}

}
