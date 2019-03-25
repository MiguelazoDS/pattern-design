package Tests;

import junit.framework.*;
import main.java.headfirst.combined.djview.SubmarineModel;
import main.java.headfirst.combined.djview.SubmarineModelInterface;
import main.java.headfirst.combined.djview.SubmarineView;
import main.java.headfirst.combined.djview.SubmarineController;
public class SubmarineControllerTest extends TestCase {
	
	private SubmarineModel model;
	private SubmarineController controller;
	private SubmarineView view;
	
	
	
	public void setSub(){
		model = new SubmarineModel();
		controller = new SubmarineController (model);
		view = controller.getVistaS();
		
	}
	
	
	public void testStopController(){
		
		setSub();
		controller.stop();
		TestCase.assertEquals(true, view.getPausar());
		controller.stop();
		TestCase.assertEquals(false, view.getPausar());
		
		
	}
	
	public void testInverso(){
		
		setSub();
		controller.inverso(true);
		TestCase.assertEquals(false, controller.getPausa());
		controller.inverso(false);
		TestCase.assertEquals(true, controller.getPausa());
		
		
		
	}
}





