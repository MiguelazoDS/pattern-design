package main.java.headfirst.combined.djview;

public class HeartAdapter implements BeatModelInterface {
	HeartModelInterface heart;
	private int nInstancia;
 
	public HeartAdapter(HeartModelInterface heart) {
		this.heart = heart;
	}

    public void initialize() {}
  
    public void on() {}
  
    public void off() {}
   
	public int getBPM() {
		return heart.getHeartRate();
	}
  
    public void setBPM(int bpm) {}
   
	public void registerObserver(BeatObserver o) {
		heart.registerObserver(o);
	}
    
	public void removeObserver(BeatObserver o) {
		heart.removeObserver(o);
	}
     
	public void registerObserver(BPMObserver o) {
		heart.registerObserver(o);
	}
  
	public void removeObserver(BPMObserver o) {
		heart.removeObserver(o);
	}
	
 	public int getnInstancia()
 	{
 		return nInstancia;
 	}
 	
 	public void setnInstancia(int n)
	{
		nInstancia = n;
	}

	@Override
	public void notifyBeatObservers() {
		// TODO Auto-generated method stub
		
	}
}
