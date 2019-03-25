package main.java.headfirst.combined.djview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class SubmarineView extends JPanel implements KeyListener, Runnable,ActionListener{
	
	ControllerInterface controlle;
	SubmarineModelInterface model;
	Thread hilo;
	
	Graphics2D g2;
	private Image Background;
	private Image explosion;
	private boolean dibujar, vivo, keyboard_on, pausar, hundir;
	private Image Oceano;
	
	private Image Submarino;
	private Image contadorFondo;
	private Image inicial;
	
	JFrame ventana;
	JMenuBar menuBar;
	JMenu menu;
    JMenuItem restartMenuItem;
    JMenuItem ayudaMenuItem;
    private Timer bajar;
	

	public SubmarineView(ControllerInterface controller, SubmarineModelInterface model) {
		
		this.controlle=controller;
		this.model=model;
		
		vivo = true;
		setFocusable(true);
		Oceano = new ImageIcon("Imagenes/Background.gif").getImage();
		Submarino = new ImageIcon("Imagenes/Submarino_fx.png").getImage();
		contadorFondo = new ImageIcon("Imagenes/ContFondo3.png").getImage();
		inicial = new ImageIcon("Imagenes/Inicial3.png").getImage();
		this.addKeyListener(this);
		dibujar = false;
		vivo = true;
		setFocusable(true);
		this.addKeyListener(this);
		Background = new ImageIcon("Data/Background2.0.jpg").getImage();
		explosion = new ImageIcon("Data/Explosion.gif").getImage();
		dibujar = false;
		keyboard_on=true;
		pausar = false;
		hundir=true;
        

	}
	
	public void run() {
		
		bajar = new Timer(10, new ActionListener(){
			public void actionPerformed(ActionEvent e){						
					model.setY(model.getY()+1);
					model.setProfundidad(model.getProfundidad()+1.29);
					Submarino = new ImageIcon("Imagenes/Submarino_fx_down.png").getImage();						
			}
		});
		
		while(model.getVivo()){
		while (pausar==false)
		{	
			if(model.getSegundos()==10&&model.getCentesimas()==0 && hundir==true)
			{
				hundir=false;
				bajar.start();				
			}
			if(model.getY()>=702 && model.getY()<712)
			{
				bajar.stop();
				hundir=true;
			}
			
			repaint();
			
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	public void setSubmarinePos()
	{
		Submarino = new ImageIcon("Imagenes/Submarino_fx.png").getImage();
	}
	
	public void setVisibleInicial(boolean b)
	{
		inicial=null;
	}
	
	public void setPausar(boolean b)
	{
		if(b == true)
		{
			pausar = true;
			model.setPausar(pausar);
		}
		else
		{
			pausar = false;
			model.setPausar(pausar);
		}
	}
	
	public void PasarFrame(JFrame vent) 
	{
		ventana = vent;
		
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JFrame.setDefaultLookAndFeelDecorated(true);
				menuBar = new JMenuBar();
		        menu = new JMenu("Menu");
		        restartMenuItem = new JMenuItem("Reiniciar");
		        menu.add(restartMenuItem);
		        restartMenuItem.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent event) {
		                controlle.start();
		            }
		        });
		        
		        ayudaMenuItem = new JMenuItem("Ayuda");
		        menu.add(ayudaMenuItem);
		        ayudaMenuItem.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent event) {
		            	try {
			       		     File path = new File ("Archivos/Enunciado.pdf");
			       		     Desktop.getDesktop().open(path);
			       		}catch (IOException ex) {
			       		     ex.printStackTrace();
			       		}
		            }
		        });
		        
		        JMenuItem exit = new JMenuItem("Salir");
		        exit.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent event) {
		                System.exit(0);
		            }
		        });

		        menu.add(exit);
		
		menuBar.add(menu);
		ventana.setJMenuBar(menuBar);
		ventana.getContentPane().add(this, BorderLayout.CENTER);
	}
	
	public void addNotify() {
		super.addNotify();
		hilo = new Thread(this);
		hilo.start();
		model.iniciarHilo();
	}
	 public boolean getPausar(){return pausar;}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		g2.drawImage(Oceano, 0, 0, null);		
		g2.drawImage(Submarino, model.getX(), model.getY(), null);
		g2.drawImage(contadorFondo, 25, 10, null);
		g2.setFont(new Font("digital display tfb", Font.ITALIC, 85));
		g2.setColor(Color.red.darker());
		g2.drawString(""+model.getSegundos()+"."+model.getCentesimas(),38,99);
		g2.setFont(new Font("Arial narrow", Font.ITALIC, 75));
		g2.setColor(Color.GRAY.darker());
		DecimalFormat formato = new DecimalFormat("#.#");
		if(model.getY()<702)
			g2.drawString("Profundidad: "+formato.format(model.getProfundidad())+"m",180,99);	
			else
			{	
				g2.setColor(Color.red);
				g2.drawString("Fuera de control!!!!",180,99);
			}
		g2.drawImage(inicial, 0, 0, null);

	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int c = e.getKeyCode();
		String aux = "";
		if(keyboard_on && model.getSegundos()<10)
		{
			if (c==KeyEvent.VK_UP&&model.getY()>190)
			{			
				int y= model.getY() - 2;
				model.setY(y);
				double p = model.getProfundidad()-2.58;
				model.setProfundidad(p);
				Submarino = new ImageIcon("Imagenes/Submarino_fx_up.png").getImage();
			}
			
			if (c==KeyEvent.VK_DOWN&&model.getY()<570)
			{
				int y= model.getY() + 2;
				double p = model.getProfundidad()+2.58;
				model.setProfundidad(p);
				model.setY(y);
				Submarino = new ImageIcon("Imagenes/Submarino_fx_down.png").getImage();
			}
			
			if (c==KeyEvent.VK_RIGHT&&model.getX()<570)
			{
				int x= model.getX() + 2;
				model.setX(x);
				Submarino = new ImageIcon("Imagenes/Submarino_fx.png").getImage();
			}
			
			if (c==KeyEvent.VK_LEFT&&model.getX()>0)
			{
				int x= model.getX() - 2;
				model.setX(x);
				Submarino = new ImageIcon("Imagenes/Submarino_fx.png").getImage();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int c = e.getKeyCode();
		String aux = "";
		
		if (c==KeyEvent.VK_UP)
		{
			//y-=1;
			Submarino = new ImageIcon("Imagenes/Submarino_fx.png").getImage();
		}
		
		if (c==KeyEvent.VK_DOWN)
		{
			//y+=1;
			Submarino = new ImageIcon("Imagenes/Submarino_fx.png").getImage();
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
