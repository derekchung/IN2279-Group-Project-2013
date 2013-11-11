package SC13Project.Milestone1.FlightTicket;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

//Please do not change the name of the package or this interface
//Please add here your implementation
public class FlightTicketImpl implements BookingFlightWS {

	@Override
	public List<FlightInfo> getFlightInfo(String departure, String destination,
			FlightTicketDate date) {
		
		String packageName = FlightInfo.class.getPackage().getName();
		List<FlightInfo> allFlightInfo = new ArrayList<FlightInfo>();
		List<FlightInfo> availableFlightInfo = new ArrayList<FlightInfo>();
		Path test = Paths.get( System.getProperty("user.dir") + "/../datasource/ds_39_5.xml" );
		JAXBContext jc;
		

			try {
				jc = JAXBContext.newInstance(packageName);
				Unmarshaller u;
				u = jc.createUnmarshaller();
				JAXBElement<FlightInfo> root;
				root = (JAXBElement<FlightInfo>)u.unmarshal(new FileInputStream(test.normalize().toString()));
				FlightInfo Flight = root.getValue();
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
	public String bookFlight(String flightNumber, FlightTicketDate date,
			int seats) throws FlightUnAvailableException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancelBooking(String bookingID) {
		// TODO Auto-generated method stub
		
	}

}
