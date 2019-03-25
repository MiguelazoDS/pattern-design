package main.java.headfirst.combined.djview;

import java.util.*;

public class HeartModel implements HeartModelInterface, Runnable {
	ArrayList beatObservers = new ArrayList();
	ArrayList bpmObservers = new ArrayList();
	int time = 1000;
    int bpm = 90;
	Random random = new Random(System.currentTimeMillis());
	Thread thread;
	
/* Para el funcionamiento del patron Singleton debemos tener en cuenta:
 1 Agergar la variable privada, static y del mismo tipo de la clase que lo aplica
 2 El constructor debera ser privado
 3 Agregamos el metodo getInstancia() para la primera y unica creacion del singleton
*/ 
	private static HeartModel instanciaUnica;
	private static int nInstancias = 0;
	
	public static HeartModel getInstancia()
	{
		nInstancias= nInstancias + 1;
		if(instanciaUnica == null)
		{
			instanciaUnica= new HeartModel();
			nInstancias=0;
		}
		
		return instanciaUnica;
	}
	
	public int getnInstancias()
	{
		return nInstancias;
	}

	private HeartModel() {
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		int lastrate = -1;

		for(;;) {
			int change = random.nextInt(10);
			if (random.nextInt(2) == 0) {
				change = 0 - change;
			}
			int rate = 60000/(time + change);
			if (rate < 120 && rate > 50) {
				time += change;
				notifyBeatObservers();
				if (rate != lastrate) {
					lastrate = rate;
					notifyBPMObservers();
					//System.out.println(bpmObservers.size());
				}
			}
			try {
				Thread.sleep(time);
			} catch (Exception e) {}
		}
	}
	public int getHeartRate() {
		return 60000/time;
	}

	public void registerObserver(BeatObserver o) {
		beatObservers.add(o);
	}

	public void removeObserver(BeatObserver o) {
		int i = beatObservers.indexOf(o);
		if (i >= 0) {
			beatObservers.remove(i);
		}
	}

	public void notifyBeatObservers() {
		for(int i = 0; i < beatObservers.size(); i++) {
			BeatObserver observer = (BeatObserver)beatObservers.get(i);
			observer.updateBeat();
		}
	}

	public void registerObserver(BPMObserver o) {
		bpmObservers.add(o);
	}

	public void removeObserver(BPMObserver o) {
		int i = bpmObservers.indexOf(o);
		if (i >= 0) {
			bpmObservers.remove(i);
		}
	}

	public void notifyBPMObservers() {
		for(int i = 0; i < bpmObservers.size(); i++) {
			BPMObserver observer = (BPMObserver)bpmObservers.get(i);
			observer.updateBPM();
		}
	}
}
