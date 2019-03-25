package main.java.headfirst.combined.djview;

public class SubmarineTestDrive {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SubmarineModelInterface modelS = new SubmarineModel();
		ControllerInterface model = new SubmarineController(modelS);
	}

}