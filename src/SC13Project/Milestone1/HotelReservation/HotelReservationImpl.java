package SC13Project.Milestone1.HotelReservation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.xml.bind.*;

import SC13Project.Milestone1.HotelReservation.Database.BookingInfo;
import SC13Project.Milestone1.HotelReservation.Database.BookingList;
import SC13Project.Milestone1.HotelReservation.Database.HotelInfo;
import SC13Project.Milestone1.HotelReservation.Database.ObjectFactory;
import SC13Project.Milestone1.HotelReservation.Database.ReservationDateInfo;
import SC13Project.Milestone1.HotelReservation.Database.StayPeriodType;

//Please do not change the name of the package or this interface
//Please add here your implementation
//chris comment
public class HotelReservationImpl implements HotelReservationWS{

/*	private boolean IsStayPeriodOverlap( StayPeriod x, StayPeriod y ) {
		
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
	}*/
	
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
				if ( x.getCheckin().getDate() >= y.getCheckout().getDate() )
					return false;
			}
		}
		
		return true;
	}
	
/*	private boolean IsStayPeriodOverlap( StayPeriod x, SC13Project.Milestone1.HotelReservation.Database.StayPeriodType y ) {
		
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
	}*/
	
	@Override
	public List<RoomInfo> getAvailableRooms(StayPeriod period) {
		// TODO Auto-generated method stub
		ClassLoader cl = SC13Project.Milestone1.HotelReservation.Database.ObjectFactory.class.getClassLoader();
		String packageName = HotelInfo.class.getPackage().getName();
		List<RoomInfo> allRoomsInfo = new ArrayList<RoomInfo>();
		List<RoomInfo> availableRoomsInfo = new ArrayList<RoomInfo>();
		Path test = Paths.get( System.getProperty("user.dir") + "/../datasource/ds_39_4.xml" );
				
		try {
			JAXBContext jc = JAXBContext.newInstance(packageName, cl);
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
		
		return null;
	}

	@Override
	public String bookRoom(String type, int amount, StayPeriod period)
	throws UnAvailableException {
		ClassLoader cl = SC13Project.Milestone1.HotelReservation.Database.ObjectFactory.class.getClassLoader();
		String packageName=HotelInfo.class.getPackage().getName();
		String bookID;
		Path test = Paths.get( System.getProperty("user.dir") + "/../datasource/ds_39_4.xml" );
		
		try {
			
			JAXBContext jc = JAXBContext.newInstance(packageName, cl);
			Unmarshaller u = jc.createUnmarshaller();
			JAXBElement<HotelInfo> root = (JAXBElement<HotelInfo>)u.unmarshal(new FileInputStream(test.normalize().toString()));
			HotelInfo hotel = root.getValue();
			
			// Marshell creation 
			JAXBContext context=JAXBContext.newInstance(packageName, cl);
			Marshaller m=context.createMarshaller();

			//get the list with available rooms
			List <RoomInfo> availableRoom = getAvailableRooms(period);
			
			//check if any of the available rooms fits
			for (RoomInfo a : availableRoom){
				if ( a.getType().equals(type) ){
					if ( a.getVacancies() >= amount ) {
						m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
						ObjectFactory obf = new ObjectFactory();
						JAXBElement<HotelInfo> output= obf.createHotel(hotel);
						
						BookingInfo temp = obf.createBookingInfo();
						long seconds = System.currentTimeMillis();
						bookID = String.valueOf(seconds) + type + String.valueOf(amount);
						temp.setBookingID( bookID );
						temp.setAmount(amount);
						temp.setStayPeriod(this.convertStayPeriodToStayPeriodType(period));
						temp.setType(type);
						//List<BookingInfo> writingList = hotel.getBookings().getBooking();
						hotel.getBookings().getBooking().add(temp);
						//writingList.add(temp);
						
						m.marshal(output,new FileOutputStream(test.normalize().toString()));
						return bookID;
					}
				}
			}
			
			throw new UnAvailableException();
			
		} catch (FileNotFoundException | JAXBException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private StayPeriodType convertStayPeriodToStayPeriodType( StayPeriod x ){
		
		StayPeriodType temp = new StayPeriodType();
		ReservationDateInfo checkin = new ReservationDateInfo();
		ReservationDateInfo checkout = new ReservationDateInfo();
		
		checkin.setYear(x.getCheckin().getYear());
		checkin.setMonth(x.getCheckin().getMonth());
		checkin.setDate(x.getCheckin().getDate());
		
		checkout.setYear(x.getCheckout().getYear());
		checkout.setMonth(x.getCheckout().getMonth());
		checkout.setDate(x.getCheckout().getDate());
		
		temp.setCheckin(checkin);
		temp.setCheckout(checkout);
		
		return temp;
	}

	@Override
	public void cancelBooking(String bookingID) {
		// TODO Auto-generated method stub
		ClassLoader cl = SC13Project.Milestone1.HotelReservation.Database.ObjectFactory.class.getClassLoader();
		String packageName=HotelInfo.class.getPackage().getName();
		Path test = Paths.get( System.getProperty("user.dir") + "/../datasource/ds_39_4.xml" );
		boolean noThisBookingID = true;
		
		try {
			
			JAXBContext jc = JAXBContext.newInstance(packageName, cl);
			Unmarshaller u = jc.createUnmarshaller();
			JAXBElement<HotelInfo> root = (JAXBElement<HotelInfo>)u.unmarshal(new FileInputStream(test.normalize().toString()));
			HotelInfo hotel = root.getValue();
			
			// Marshell creation 
			JAXBContext context=JAXBContext.newInstance(packageName, cl);
			Marshaller m=context.createMarshaller();
			
			for ( BookingInfo b : hotel.getBookings().getBooking() ) {
				if ( b.getBookingID().equals(bookingID) ) {
					hotel.getBookings().getBooking().remove(b);
					noThisBookingID = false;
					break;
				}
			}
			
			if ( noThisBookingID )
				return;
			
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ObjectFactory obf = new ObjectFactory();
			JAXBElement<HotelInfo> output= obf.createHotel(hotel);
			
			m.marshal(output,new FileOutputStream(test.normalize().toString()));
		
		} catch (FileNotFoundException | JAXBException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
