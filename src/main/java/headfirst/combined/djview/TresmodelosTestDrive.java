package main.java.headfirst.combined.djview;

public class TresmodelosTestDrive {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BeatModelInterface modelB = new BeatModel();
        ControllerInterface controller = new BeatController(modelB);
    	
    	HeartModelInterface modelH = HeartModel.getInstancia();
		controller = new HeartController(modelH);
		
		SubmarineModelInterface modelS = new SubmarineModel();
		ControllerInterface model = new SubmarineController(modelS);

	}

}
