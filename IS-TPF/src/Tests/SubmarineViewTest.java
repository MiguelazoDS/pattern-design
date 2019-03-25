package Tests;

import main.java.headfirst.combined.djview.SubmarineView;
import main.java.headfirst.combined.djview.SubmarineModelInterface;
import main.java.headfirst.combined.djview.SubmarineController;
import main.java.headfirst.combined.djview.ControllerInterface;
import main.java.headfirst.combined.djview.SubmarineModel;
import junit.framework.*;


public class SubmarineViewTest extends TestCase {

	private SubmarineModelInterface model = new SubmarineModel();
	private  ControllerInterface controller = new SubmarineController(model);
	private SubmarineView view = new SubmarineView(controller , model);
	

	
	public void testSetPausar(){
		
		view.setPausar(true);
		TestCase.assertEquals(true, view.getPausar());
		view.setPausar(false);
		TestCase.assertEquals(false,view.getPausar());
		
	}
}
	
	
