//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.11.06 um 09:45:49 PM CET 
//

//chris comment here
package SC13Project.Milestone1.HotelReservation.Database;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für BookingInfo complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="BookingInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bookingID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="stayPeriod" type="{http://www.example.org/HotelDB}stayPeriodType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
//chris comment here
//chris second comment here
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BookingInfo", propOrder = {
    "bookingID",
    "type",
    "amount",
    "stayPeriod"
})
public class BookingInfo {

    @XmlElement(required = true)
    protected String bookingID;
    @XmlElement(required = true)
    protected String type;
    protected int amount;
    @XmlElement(required = true)
    protected StayPeriodType stayPeriod;

    /**
     * Ruft den Wert der bookingID-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookingID() {
        return bookingID;
    }

    /**
     * Legt den Wert der bookingID-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookingID(String value) {
        this.bookingID = value;
    }

    /**
     * Ruft den Wert der type-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Legt den Wert der type-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Ruft den Wert der amount-Eigenschaft ab.
     * 
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Legt den Wert der amount-Eigenschaft fest.
     * 
     */
    public void setAmount(int value) {
        this.amount = value;
    }

    /**
     * Ruft den Wert der stayPeriod-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link StayPeriodType }
     *     
     */
    public StayPeriodType getStayPeriod() {
        return stayPeriod;
    }

    /**
     * Legt den Wert der stayPeriod-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link StayPeriodType }
     *     
     */
    public void setStayPeriod(StayPeriodType value) {
        this.stayPeriod = value;
    }

}
