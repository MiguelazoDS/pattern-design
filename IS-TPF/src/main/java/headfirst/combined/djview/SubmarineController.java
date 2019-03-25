package main.java.headfirst.combined.djview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class SubmarineController implements ControllerInterface{
	
	SubmarineModelInterface modelS;
	BeatModelInterface modelB;
	HeartModelInterface modelH;
	DJView view;
	SubmarineView vistaS;
	
    JFrame ventana;
    
    boolean pausa;
	
	public SubmarineController(SubmarineModelInterface model_1)
	{
		pausa=false;
		modelS=model_1;
		vistaS= new SubmarineView(this,modelS);
		ventana= new Ventana(vistaS);
		vistaS.PasarFrame(ventana);
		
		modelB = new BeatModel();
		
		view = new DJView(this, modelB);
        view.createView();
        view.createControls();
		view.disableStopMenuItem();
		view.enableStartMenuItem();
		view.setVistaPosicion(200, 170);
		view.setControlPosicion(200, 0);
		modelB.initialize();
		
		modelS.PasarBeatModel(modelB);
		        
	}
	
	public void start()
	{
		vistaS.setVisibleInicial(false);
		vistaS.setSubmarinePos();
		modelS.setX(0);
		modelS.setY(190);
		modelS.setProfundidad(0);
		modelS.setContar(true);
		view.disableStartMenuItem();
		view.enableStopMenuItem();
	}
	public void stop()
	{
		inverso(pausa);
		
		if(pausa==true)
		{
			vistaS.setPausar(true);
			modelB.off();
			modelB.initialize();
		}
		else
		{
			vistaS.setPausar(false);
			if(modelS.getY() > 500)
			{
				modelB.on();
				modelB.setBPM(90);
			}
		}
	}
	public SubmarineView getVistaS(){
		return vistaS ;
	}
	public boolean getPausa(){
		return pausa;
	}
	public void inverso(boolean b)
	{
		if(b==true){pausa=false;}
		else{pausa=true;};
	}
	
	public void increaseBPM(){}
	public void decreaseBPM(){}
	public void setBPM(int bpm){}


}