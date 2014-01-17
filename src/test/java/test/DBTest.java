package test;

import junit.framework.TestCase;
import dbController.DBConnection;
import dbController.DBControlPojazd;
import entities.Pojazd;

public class DBTest extends TestCase {
	
    @org.junit.Test
	public void test() {
			
        Pojazd testowy = DBControlPojazd.getPojazdbyID(1);
        
        assertNotNull(testowy.getRocznik());
        assertTrue(testowy.getRocznik().equals(2003));
        assertFalse(testowy.getZajetosc());
        assertEquals(testowy.getMarka(), "SAAB");
        assertEquals(testowy.getModel(),"9-5");
     
        DBConnection.killConnection();	
	}
}