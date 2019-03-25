import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
//import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Panel extends JPanel implements Runnable, KeyListener{

	private Image Oceano;
	private Thread hilo;
	private Timer timer, tiempo;
	private Image Submarino;
	private int x, y, contador, segundo, centesima, descargas;
	private boolean dibujar, vivo, keyboard_on;

	public Panel() {
		contador = 0;
		segundo = 0;
		descargas =10;
		vivo = true;
		setFocusable(true);
		Oceano = new ImageIcon("Imagenes/Background.gif").getImage();
		Submarino = new ImageIcon("Imagenes/Submarino_fx.png").getImage();
		this.addKeyListener(this);
		dibujar = false;
		x=0;
		y=250;
		keyboard_on=true;
	}

	public void addNotify() {
		super.addNotify();
		hilo = new Thread(this);
		hilo.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(Oceano, 0, 0, null);		
		g2.drawImage(Submarino, x, y, null);
		g2.setFont(new Font("digital display tfb", Font.ITALIC, 75));
		g2.setColor(Color.red.darker());
		g2.drawString(""+segundo+"."+centesima,30,75);		
		
	}

	public void run() {
		tiempo = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centesima++;
			}
		});
		

		while (vivo)
		{
			if(y>500)
			{
				tiempo.start();
				if(centesima==10)
				{
					centesima=0;
					segundo+=1;
				}
				if(segundo==60)
				{
					segundo=0;
					centesima=0;
				}
			}
			else
			{
				tiempo.stop();
				centesima=0;
				segundo=0;
			}
			repaint();			
		}
	}


	
	public void print(String cadena)
	{
		System.out.println(cadena);
	}


	public void terminarJuego() {
		vivo = false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int c = e.getKeyCode();
		String aux = "";
		if(keyboard_on)
		{
			if (c==KeyEvent.VK_UP&&y>190)
			{			
				y-=2;
				Submarino = new ImageIcon("Imagenes/Submarino_fx_up.png").getImage();
			}
			
			if (c==KeyEvent.VK_DOWN&&y<570)
			{
				y+=2;
				Submarino = new ImageIcon("Imagenes/Submarino_fx_down.png").getImage();
			}
			
			if (c==KeyEvent.VK_RIGHT&&x<570)
			{
				x+=2;
				Submarino = new ImageIcon("Imagenes/Submarino_fx.png").getImage();
			}
			
			if (c==KeyEvent.VK_LEFT&&x>0)
			{
				x-=2;
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
}
