package main.java.headfirst.combined.djview;
  
public interface BeatModelInterface {
	void initialize();
	  
	void on();
 
	void off();
  
    void setBPM(int bpm);
  
	int getBPM();
	
	int getnInstancia();
	
	void notifyBeatObservers();
	
	void setnInstancia(int nInstancia);
  
	void registerObserver(BeatObserver o);
  
	void removeObserver(BeatObserver o);
  
	void registerObserver(BPMObserver o);
  
	void removeObserver(BPMObserver o);
}
