package main.java.headfirst.combined.djview;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Ventana extends JFrame {
	SubmarineView vistaS;
	
	public Ventana(SubmarineView vistaS)
	{
		this.vistaS=vistaS;
		setTitle("SubmarineModel");
		setSize(900,720);
		setLocation(363,0);
		add(this.vistaS);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);		
	}

}