package main.java.headfirst.combined.djview;
  
public class BeatController implements ControllerInterface {
	BeatModelInterface model;
	HeartModelInterface model2;
	DJView view;
   
	public BeatController(BeatModelInterface model) {
		this.model = model;
		view = new DJView(this, model);
        view.createView();
        view.createControls();
		view.disableStopMenuItem();
		view.enableStartMenuItem();
		view.setVistaPosicion(200, 170);
		view.setControlPosicion(200, 0);
		model.initialize();
	}
  
	public void start() {
		model.on();
		view.disableStartMenuItem();
		view.enableStopMenuItem();
	}
  
	public void stop() {
		model.off();
		view.disableStopMenuItem();
		view.enableStartMenuItem();
	}
    
	public void increaseBPM() {
		model2 = HeartModel.getInstancia();// intento de creacion de nueva instancia de un HeartModel
        int bpm = model.getBPM();
        int nInstancia = model2.getnInstancias();
        model.setnInstancia(nInstancia);
        model.setBPM(bpm + 1);
	}
    
	public void decreaseBPM() {
        int bpm = model.getBPM();
        model.setBPM(bpm - 1);
  	}
  
 	public void setBPM(int bpm) {
		model.setBPM(bpm);
	}
}
