package SC13Project.Milestone1.Warehouse;

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
public class WarehouseImpl implements WarehouseWS {

	@Override
	public int query(String resourceID) {
		// TODO Auto-generated method stub
		String packageName = HotelInfo.class.getPackage().getName();
		List<RoomInfo> allRoomsInfo = new ArrayList<RoomInfo>();
		List<RoomInfo> availableRoomsInfo = new ArrayList<RoomInfo>();
		Path test = Paths.get( System.getProperty("user.dir") + "/../datasource/ds_39_4.xml" );
				
		try {
			JAXBContext jc = JAXBContext.newInstance(packageName);
			Unmarshaller u = jc.createUnmarshaller();
			JAXBElement<HotelInfo> root = (JAXBElement<HotelInfo>)u.unmarshal(new FileInputStream(test.normalize().toString()));
			HotelInfo hotel = root.getValue();
			
			List<SC13Project.Milestone1.HotelReservation.Database.RoomInfo> rooms = hotel.getRooms().getRoom();
			List<SC13Project.Milestone1.HotelReservation.Database.BookingInfo> bookings = hotel.getBookings().getBooking();
			
			for ( SC13Project.Milestone1.HotelReservation.Database.RoomInfo a : rooms ){
				if ( allRoomsInfo.size() > 0 ) {
					boolean cangonext = false;
					
					for ( RoomInfo b : allRoomsInfo ) {
						if ( a.getType().equals(b.getType()) ) {
							b.setVacancies(b.getVacancies() + a.getTotalAmount());
							cangonext = true;
							break;
						}
					}
					
					if (!cangonext) {
						RoomInfo temp = new RoomInfo();
						temp.setType(a.getType());
						temp.setRate(a.getRate());
						temp.setVacancies(a.getTotalAmount());
						allRoomsInfo.add(temp);
					}
				}
				else {
					RoomInfo temp = new RoomInfo();
					temp.setType(a.getType());
					temp.setRate(a.getRate());
					temp.setVacancies(a.getTotalAmount());
					allRoomsInfo.add(temp);
				}
			}
			
			if ( allRoomsInfo.size() == 0 )
				return null;
			
			for ( SC13Project.Milestone1.HotelReservation.Database.BookingInfo b : bookings) {
				for ( RoomInfo a : allRoomsInfo ) {
					if ( b.getType().equals(a.getType()) &&
							IsStayPeriodOverlap(b.getStayPeriod(), convertStayPeriodToStayPeriodType(period)) ) {
						a.setVacancies(a.getVacancies() - b.getAmount());
					}
				}
			}
			
			for ( RoomInfo a : allRoomsInfo ){
				if ( a.getVacancies() > 0 ) 
					availableRoomsInfo.add(a);
			}
			
			if ( availableRoomsInfo.size() == 0 )
				return null;
			
			return availableRoomsInfo;
			
		} catch (JAXBException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public boolean pickupItems(String resourceID, int amount)
			throws NotEnoughItemException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int complementStock(String resourceID, int amount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String holdItems(String resourceID, int amount)
			throws NotEnoughItemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancelHoldingItems(String holdingID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean pickupHoldingItems(String holdingID)
			throws InvalidHoldingIDException {
		// TODO Auto-generated method stub
		return false;
	}

}
