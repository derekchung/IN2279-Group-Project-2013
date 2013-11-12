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
