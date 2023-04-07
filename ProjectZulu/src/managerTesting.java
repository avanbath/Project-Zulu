import java.util.List;

import java.util.Iterator;

public class managerTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		
		ControllerIMPL c = new ControllerIMPL();
		VisualMaker fc = new VisualMaker();
		
		OpFacadeInter operFacade = new OperationFacade();
		c.setOpFacade(operFacade);
		
		TableManager tableMan = new TableManager();
		c.setTableMan(tableMan);
		tableMan.addSubscriber(fc);
		DatabaseAdapter dA = new DatabaseAdapter();
		tableMan.setAdapter(dA);
		
		
		fc.setCreatorLine();
		
		c.setStatTest();
		
		c.addRegion("Ontario");
		c.addRegion("Manitoba");
		//c.addRegion("Alberta");
		
		//c.addTimeSeries("2000-1", "2002-2");
		c.addTimeSeries("2003-1", "2010-2");
		c.addTimeSeries("2005-1", "2017-2");
		List<String> result = c.execute();
		
		Iterator<String> i = result.iterator();
		System.out.println("Now for the results");
		while (i.hasNext()) {
			System.out.println("The first result is " + i.next());
		}
	}

}
