package Tests;


import junit.framework.*;
import main.java.headfirst.combined.djview.HeartModel;

public class SingletonHeartModelTest extends TestCase {


private HeartModel heart01, heart02;
private int i;


public void setObs() {
    
    heart01 = HeartModel.getInstancia();
    heart02 = HeartModel.getInstancia();
   
}


public void testHeartIntentos(){
	setObs();
     i = heart01.getnInstancias();
	TestCase.assertEquals(true, i>0);
}
public void testHeart() {
	
	setObs();
	TestCase.assertEquals( heart01,heart02 );
	 
   
}
	

}


