package SC13Project.Milestone1.HotelReservation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.*;

import SC13Project.Milestone1.HotelReservation.Database.HotelInfo;

//Please do not change the name of the package or this interface
//Please add here your implementation
//chris comment
public class HotelReservationImpl implements HotelReservationWS{

	private boolean IsStayPeriodOverlap( StayPeriod x, StayPeriod y ) {
		
		if ( x.getCheckout().getYear() < y.getCheckin().getYear() ){
			return false;
		} else if ( x.getCheckout().getYear() == y.getCheckin().getYear() ) {
			if ( x.getCheckout().getMonth() < y.getCheckin().getMonth() ){
				return false;
			} else if ( x.getCheckout().getMonth() == y.getCheckin().getMonth() ) {
				if ( x.getCheckout().getDate() <= y.getCheckin().getDate() )
					return false;
			}
		}
		
		if ( x.getCheckin().getYear() > y.getCheckout().getYear() ) {
			return false;
		} else if ( x.getCheckin().getYear() == y.getCheckout().getYear() ) {
			if ( x.getCheckin().getMonth() > y.getCheckout().getMonth() ) {
				return false;
			} else if ( x.getCheckin().getMonth() == y.getCheckout().getMonth() ) {
				if ( x.getCheckout().getDate() >= y.getCheckin().getDate() )
					return false;
			}
		}
		
		return true;
	}
	
	private boolean IsStayPeriodOverlap( SC13Project.Milestone1.HotelReservation.Database.StayPeriodType x, StayPeriod y ) {
		
		if ( x.getCheckout().getYear() < y.getCheckin().getYear() ){
			return false;
		} else if ( x.getCheckout().getYear() == y.getCheckin().getYear() ) {
			if ( x.getCheckout().getMonth() < y.getCheckin().getMonth() ){
				return false;
			} else if ( x.getCheckout().getMonth() == y.getCheckin().getMonth() ) {
				if ( x.getCheckout().getDate() <= y.getCheckin().getDate() )
					return false;
			}
		}
		
		if ( x.getCheckin().getYear() > y.getCheckout().getYear() ) {
			return false;
		} else if ( x.getCheckin().getYear() == y.getCheckout().getYear() ) {
			if ( x.getCheckin().getMonth() > y.getCheckout().getMonth() ) {
				return false;
			} else if ( x.getCheckin().getMonth() == y.getCheckout().getMonth() ) {
				if ( x.getCheckout().getDate() >= y.getCheckin().getDate() )
					return false;
			}
		}
		
		return true;
	}
	
	private boolean IsStayPeriodOverlap( SC13Project.Milestone1.HotelReservation.Database.StayPeriodType x, SC13Project.Milestone1.HotelReservation.Database.StayPeriodType y ) {
		
		if ( x.getCheckout().getYear() < y.getCheckin().getYear() ){
			return false;
		} else if ( x.getCheckout().getYear() == y.getCheckin().getYear() ) {
			if ( x.getCheckout().getMonth() < y.getCheckin().getMonth() ){
				return false;
			} else if ( x.getCheckout().getMonth() == y.getCheckin().getMonth() ) {
				if ( x.getCheckout().getDate() <= y.getCheckin().getDate() )
					return false;
			}
		}
		
		if ( x.getCheckin().getYear() > y.getCheckout().getYear() ) {
			return false;
		} else if ( x.getCheckin().getYear() == y.getCheckout().getYear() ) {
			if ( x.getCheckin().getMonth() > y.getCheckout().getMonth() ) {
				return false;
			} else if ( x.getCheckin().getMonth() == y.getCheckout().getMonth() ) {
				if ( x.getCheckout().getDate() >= y.getCheckin().getDate() )
					return false;
			}
		}
		
		return true;
	}
	
	private boolean IsStayPeriodOverlap( StayPeriod x, SC13Project.Milestone1.HotelReservation.Database.StayPeriodType y ) {
		
		if ( x.getCheckout().getYear() < y.getCheckin().getYear() ){
			return false;
		} else if ( x.getCheckout().getYear() == y.getCheckin().getYear() ) {
			if ( x.getCheckout().getMonth() < y.getCheckin().getMonth() ){
				return false;
			} else if ( x.getCheckout().getMonth() == y.getCheckin().getMonth() ) {
				if ( x.getCheckout().getDate() <= y.getCheckin().getDate() )
					return false;
			}
		}
		
		if ( x.getCheckin().getYear() > y.getCheckout().getYear() ) {
			return false;
		} else if ( x.getCheckin().getYear() == y.getCheckout().getYear() ) {
			if ( x.getCheckin().getMonth() > y.getCheckout().getMonth() ) {
				return false;
			} else if ( x.getCheckin().getMonth() == y.getCheckout().getMonth() ) {
				if ( x.getCheckout().getDate() >= y.getCheckin().getDate() )
					return false;
			}
		}
		
		return true;
	}
	
	@Override
	public List<RoomInfo> getAvailableRooms(StayPeriod period) {
		// TODO Auto-generated method stub
		String packageName = HotelInfo.class.getPackage().getName();
		List<RoomInfo> allRoomsInfo = new ArrayList<RoomInfo>();
		List<RoomInfo> availableRoomsInfo = new ArrayList<RoomInfo>();
		
		try {
			JAXBContext jc = JAXBContext.newInstance(packageName);
			Unmarshaller u = jc.createUnmarshaller();
			JAXBElement<HotelInfo> root = (JAXBElement<HotelInfo>)u.unmarshal(new FileInputStream(""));
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
				return allRoomsInfo;
			
			for ( SC13Project.Milestone1.HotelReservation.Database.BookingInfo b : bookings) {
				for ( RoomInfo a : allRoomsInfo ) {
					if ( b.getType().equals(a.getType()) &&
							IsStayPeriodOverlap(b.getStayPeriod(), period) ) {
						a.setVacancies(a.getVacancies() - b.getAmount());
					}
				}
			}
			
			for ( RoomInfo a : allRoomsInfo ){
				if ( a.getVacancies() > 0 ) 
					availableRoomsInfo.add(a);
			}
			
			return availableRoomsInfo;
			
		} catch (JAXBException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String bookRoom(String type, int amount, StayPeriod period)
	throws UnAvailableException {
	String packageName=HotelInfo.class.getPackage().getName();

	try {
	// Unmarshell creation 
	JAXBContext jc=JAXBContext.newInstance(packageName);
	Unmarshaller u=jc.createUnmarshaller();
	// Marshell creation 
	JAXBContext context=JAXBContext.newInstance(packageName);
	Marshaller m=context.createMarshaller();
	JAXBElement<HotelInfo>root= (JAXBElement<HotelInfo>)u.unmarshal(new FileInputStream("HotelDB.xml"));
	HotelInfo hotel=root.getValue();
	//get the list with available rooms
	List <RoomInfo> availableRoom = new ArrayList<RoomInfo>();
	availableRoom=getAvailableRooms(period);
	//check if any of the available rooms fits
	for(int i = 0; i < availableRoom.size(); i++){
	if( availableRoom.get(i).getType().equals(type)){
	if((availableRoom.get(i).getRate())<=(amount)){
	availableRoom.get(i).setVacancies(i);
	m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	//ObjectFactory obf=new ObjectFactory();
	//JAXBElement<HotelInfo> output= obf.createHotel(hotel);
	//JAXBElement<BookingInfo> output= obf.createBookingInfo();
	//output.setValue(type);
//	m.marshal(output,new FileOutputStream("HotelDB.xml"));
	}
	}
	}
	} catch (FileNotFoundException | JAXBException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	return null;
	}


	@Override
	public void cancelBooking(String bookingID) {
		// TODO Auto-generated method stub
		
	}

}
