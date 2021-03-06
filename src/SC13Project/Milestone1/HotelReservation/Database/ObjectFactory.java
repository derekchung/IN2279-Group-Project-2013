//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.11.06 um 09:45:49 PM CET 
//


package SC13Project.Milestone1.HotelReservation.Database;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the SC13Project.Milestone1.HotelReservation.Database package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Hotel_QNAME = new QName("http://www.example.org/HotelDB", "hotel");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: SC13Project.Milestone1.HotelReservation.Database
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link HotelInfo }
     * 
     */
    public HotelInfo createHotelInfo() {
        return new HotelInfo();
    }

    /**
     * Create an instance of {@link ReservationDateInfo }
     * 
     */
    public ReservationDateInfo createReservationDateInfo() {
        return new ReservationDateInfo();
    }

    /**
     * Create an instance of {@link BookingInfo }
     * 
     */
    public BookingInfo createBookingInfo() {
        return new BookingInfo();
    }

    /**
     * Create an instance of {@link BookingList }
     * 
     */
    public BookingList createBookingList() {
        return new BookingList();
    }

    /**
     * Create an instance of {@link RoomList }
     * 
     */
    public RoomList createRoomList() {
        return new RoomList();
    }

    /**
     * Create an instance of {@link StayPeriodType }
     * 
     */
    public StayPeriodType createStayPeriodType() {
        return new StayPeriodType();
    }

    /**
     * Create an instance of {@link RoomInfo }
     * 
     */
    public RoomInfo createRoomInfo() {
        return new RoomInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HotelInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/HotelDB", name = "hotel")
    public JAXBElement<HotelInfo> createHotel(HotelInfo value) {
        return new JAXBElement<HotelInfo>(_Hotel_QNAME, HotelInfo.class, null, value);
    }

}
