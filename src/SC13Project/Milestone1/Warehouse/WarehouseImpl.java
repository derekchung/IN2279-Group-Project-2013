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
