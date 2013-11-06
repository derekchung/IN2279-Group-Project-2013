//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.11.06 um 09:45:49 PM CET 
//


package SC13Project.Milestone1.HotelReservation.Database;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für HotelInfo complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="HotelInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rooms" type="{http://www.example.org/HotelDB}RoomList"/>
 *         &lt;element name="bookings" type="{http://www.example.org/HotelDB}BookingList"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HotelInfo", propOrder = {
    "rooms",
    "bookings"
})
public class HotelInfo {

    @XmlElement(required = true)
    protected RoomList rooms;
    @XmlElement(required = true)
    protected BookingList bookings;

    /**
     * Ruft den Wert der rooms-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link RoomList }
     *     
     */
    public RoomList getRooms() {
        return rooms;
    }

    /**
     * Legt den Wert der rooms-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link RoomList }
     *     
     */
    public void setRooms(RoomList value) {
        this.rooms = value;
    }

    /**
     * Ruft den Wert der bookings-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BookingList }
     *     
     */
    public BookingList getBookings() {
        return bookings;
    }

    /**
     * Legt den Wert der bookings-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BookingList }
     *     
     */
    public void setBookings(BookingList value) {
        this.bookings = value;
    }

}
