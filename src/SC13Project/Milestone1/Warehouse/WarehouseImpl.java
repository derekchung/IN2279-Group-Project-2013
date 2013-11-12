package SC13Project.Milestone1.Warehouse;

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

import SC13Project.Milestone1.Warehouse.Database.ObjectFactory;
import SC13Project.Milestone1.Warehouse.Database.ItemInfo;
import SC13Project.Milestone1.Warehouse.Database.WareHouse;
import SC13Project.Milestone1.Warehouse.Database.HoldingRequestInfo;

//Please do not change the name of the package or this interface
//Please add here your implementation
public class WarehouseImpl implements WarehouseWS {

	@Override
	public int query(String resourceID) {
		// TODO Auto-generated method stub
		ClassLoader cl = SC13Project.Milestone1.Warehouse.Database.ObjectFactory.class.getClassLoader();
		String packageName = WareHouse.class.getPackage().getName();
		Path test = Paths.get( System.getProperty("user.dir") + "/../datasource/ds_39_2.xml" );
		
		try {
			JAXBContext jc = JAXBContext.newInstance(packageName, cl);
			Unmarshaller u = jc.createUnmarshaller();
			JAXBElement<WareHouse> root = (JAXBElement<WareHouse>)u.unmarshal(new FileInputStream(test.normalize().toString()));
			WareHouse wareHouse = root.getValue();
			
			
			for ( ItemInfo i : wareHouse.getItems().getItem() ){
					if ( i.getResourceID().equals(resourceID) ) {
						int amount = i.getAmount();
						for ( HoldingRequestInfo h : wareHouse.getHoldingRequests().getRequest() ) {
							if ( h.getItem().getResourceID().equals(resourceID) ) {
								amount -= h.getItem().getAmount();
								if ( amount <= 0 )
									return 0;
							}
						}
						return amount;
					}
			}
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		
		return -1;
	}

	@Override
	public boolean pickupItems(String resourceID, int amount)
			throws NotEnoughItemException {
		// TODO Auto-generated method stub
		
		int q = this.query(resourceID);
		
		if ( q == -1 ) {
			return false;
		}
		else if ( q < amount ) {
			throw new NotEnoughItemException();
		} else {
			ClassLoader cl = SC13Project.Milestone1.Warehouse.Database.ObjectFactory.class.getClassLoader();
			String packageName = WareHouse.class.getPackage().getName();
			Path test = Paths.get( System.getProperty("user.dir") + "/../datasource/ds_39_2.xml" );
			
			
			JAXBContext jc = null;
			
			try {
				jc = JAXBContext.newInstance(packageName, cl);
				Unmarshaller u = jc.createUnmarshaller();
				JAXBElement<WareHouse> root = (JAXBElement<WareHouse>)u.unmarshal(new FileInputStream(test.normalize().toString()));
				WareHouse wareHouse = root.getValue();
				
				// Marshell creation 
				JAXBContext context=JAXBContext.newInstance(packageName, cl);
				Marshaller m=context.createMarshaller();
				
				for ( ItemInfo i : wareHouse.getItems().getItem()) {
					if ( i.getResourceID().equals(resourceID) ) {
						if ( i.getAmount() - amount > 0 )
							i.setAmount(i.getAmount() - amount);
						else
							wareHouse.getItems().getItem().remove(i);
						
						break;
					}
				}
				
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				ObjectFactory obf = new ObjectFactory();
				JAXBElement<WareHouse> output= obf.createWarehouse(wareHouse);
				
				m.marshal(output,new FileOutputStream(test.normalize().toString()));
				
				return true;
				
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return false;
	}

	@Override
	public int complementStock(String resourceID, int amount) {
		// TODO Auto-generated method stub
		ClassLoader cl = SC13Project.Milestone1.Warehouse.Database.ObjectFactory.class.getClassLoader();
		String packageName = WareHouse.class.getPackage().getName();
		Path test = Paths.get( System.getProperty("user.dir") + "/../datasource/ds_39_2.xml" );
		
		
		JAXBContext jc = null;
		
		try {
			jc = JAXBContext.newInstance(packageName, cl);
			Unmarshaller u = jc.createUnmarshaller();
			JAXBElement<WareHouse> root = (JAXBElement<WareHouse>)u.unmarshal(new FileInputStream(test.normalize().toString()));
			WareHouse wareHouse = root.getValue();

			// Marshell creation 
			JAXBContext context=JAXBContext.newInstance(packageName, cl);
			Marshaller m=context.createMarshaller();
			
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ObjectFactory obf = new ObjectFactory();
			JAXBElement<WareHouse> output= obf.createWarehouse(wareHouse);
			
			for ( ItemInfo i : wareHouse.getItems().getItem()) {
				if ( i.getResourceID().equals(resourceID) ) {
					i.setAmount(i.getAmount() + amount);
					m.marshal(output,new FileOutputStream(test.normalize().toString()));
					return i.getAmount();
				}
			}
			
			ItemInfo temp = obf.createItemInfo();
			temp.setResourceID(resourceID);
			temp.setAmount(amount);
			
			m.marshal(output,new FileOutputStream(test.normalize().toString()));
			
			return amount;
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public String holdItems(String resourceID, int amount)
			throws NotEnoughItemException {
		// TODO Auto-generated method stub
		
		int q = this.query(resourceID);
		
		if ( q < amount ) {
			throw new NotEnoughItemException();
		} else {
			ClassLoader cl = SC13Project.Milestone1.Warehouse.Database.ObjectFactory.class.getClassLoader();
			String packageName = WareHouse.class.getPackage().getName();
			Path test = Paths.get( System.getProperty("user.dir") + "/../datasource/ds_39_2.xml" );
			
			JAXBContext jc = null;
			
			try {
				jc = JAXBContext.newInstance(packageName, cl);
				Unmarshaller u = jc.createUnmarshaller();
				JAXBElement<WareHouse> root = (JAXBElement<WareHouse>)u.unmarshal(new FileInputStream(test.normalize().toString()));
				WareHouse wareHouse = root.getValue();
				
				// Marshell creation 
				JAXBContext context=JAXBContext.newInstance(packageName, cl);
				Marshaller m=context.createMarshaller();
				
				String requestID = String.valueOf(System.currentTimeMillis()) + resourceID;
				
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				ObjectFactory obf = new ObjectFactory();
				JAXBElement<WareHouse> output= obf.createWarehouse(wareHouse);
				
				HoldingRequestInfo temp = obf.createHoldingRequestInfo();
				ItemInfo tempItem = obf.createItemInfo();
				
				tempItem.setResourceID(resourceID);
				tempItem.setAmount(amount);
				
				temp.setRequestID(requestID);
				temp.setItem(tempItem);
				
				m.marshal(output,new FileOutputStream(test.normalize().toString()));
				
				return requestID;
				
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public void cancelHoldingItems(String holdingID) {
		// TODO Auto-generated method stub
		
		ClassLoader cl = SC13Project.Milestone1.Warehouse.Database.ObjectFactory.class.getClassLoader();
		String packageName = WareHouse.class.getPackage().getName();
		Path test = Paths.get( System.getProperty("user.dir") + "/../datasource/ds_39_2.xml" );
		
		JAXBContext jc = null;
		
		try {
			jc = JAXBContext.newInstance(packageName, cl);
			Unmarshaller u = jc.createUnmarshaller();
			JAXBElement<WareHouse> root = (JAXBElement<WareHouse>)u.unmarshal(new FileInputStream(test.normalize().toString()));
			WareHouse wareHouse = root.getValue();
			
			// Marshell creation 
			JAXBContext context=JAXBContext.newInstance(packageName, cl);
			Marshaller m=context.createMarshaller();
			
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ObjectFactory obf = new ObjectFactory();
			JAXBElement<WareHouse> output= obf.createWarehouse(wareHouse);
			
			for ( HoldingRequestInfo h : wareHouse.getHoldingRequests().getRequest() ) {
				if ( h.getRequestID().equals(holdingID) ) {
					wareHouse.getHoldingRequests().getRequest().remove(h);
					m.marshal(output,new FileOutputStream(test.normalize().toString()));		
					return;
				}
			}
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean pickupHoldingItems(String holdingID)
			throws InvalidHoldingIDException {
		// TODO Auto-generated method stub
		
		ClassLoader cl = SC13Project.Milestone1.Warehouse.Database.ObjectFactory.class.getClassLoader();
		String packageName = WareHouse.class.getPackage().getName();
		Path test = Paths.get( System.getProperty("user.dir") + "/../datasource/ds_39_2.xml" );
		JAXBContext jc = null;
		
		try {
			jc = JAXBContext.newInstance(packageName, cl);
			Unmarshaller u = jc.createUnmarshaller();
			JAXBElement<WareHouse> root = (JAXBElement<WareHouse>)u.unmarshal(new FileInputStream(test.normalize().toString()));
			WareHouse wareHouse = root.getValue();
			
			// Marshell creation 
			JAXBContext context=JAXBContext.newInstance(packageName, cl);
			Marshaller m=context.createMarshaller();
			
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ObjectFactory obf = new ObjectFactory();
			JAXBElement<WareHouse> output= obf.createWarehouse(wareHouse);
			
			for ( HoldingRequestInfo h : wareHouse.getHoldingRequests().getRequest() ) {
			
				if ( h.getRequestID().equals(holdingID) ) {
					for ( ItemInfo i : wareHouse.getItems().getItem() ) {
						
						if ( i.getResourceID().equals(h.getItem().getResourceID()) ) {
							
							if ( i.getAmount() < h.getItem().getAmount() )
								return false;
							
							i.setAmount(i.getAmount() - h.getItem().getAmount());
							
							if ( i.getAmount() == 0 )
								wareHouse.getItems().getItem().remove(i);
							
							wareHouse.getHoldingRequests().getRequest().remove(h);
							return true;
						}		
					}
				}
			}
			
			throw new InvalidHoldingIDException();
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
