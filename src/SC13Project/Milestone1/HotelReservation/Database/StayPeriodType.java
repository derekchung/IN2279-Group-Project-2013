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
 * <p>Java-Klasse für stayPeriodType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="stayPeriodType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="checkin" type="{http://www.example.org/HotelDB}ReservationDateInfo"/>
 *         &lt;element name="checkout" type="{http://www.example.org/HotelDB}ReservationDateInfo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "stayPeriodType", propOrder = {
    "checkin",
    "checkout"
})
public class StayPeriodType {

    @XmlElement(required = true)
    protected ReservationDateInfo checkin;
    @XmlElement(required = true)
    protected ReservationDateInfo checkout;

    /**
     * Ruft den Wert der checkin-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ReservationDateInfo }
     *     
     */
    public ReservationDateInfo getCheckin() {
        return checkin;
    }

    /**
     * Legt den Wert der checkin-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ReservationDateInfo }
     *     
     */
    public void setCheckin(ReservationDateInfo value) {
        this.checkin = value;
    }

    /**
     * Ruft den Wert der checkout-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ReservationDateInfo }
     *     
     */
    public ReservationDateInfo getCheckout() {
        return checkout;
    }

    /**
     * Legt den Wert der checkout-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ReservationDateInfo }
     *     
     */
    public void setCheckout(ReservationDateInfo value) {
        this.checkout = value;
    }

}
