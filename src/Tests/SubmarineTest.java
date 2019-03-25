package Tests;
import junit.framework.*;

import main.java.headfirst.combined.djview.SubmarineModel;
import main.java.headfirst.combined.djview.BeatModel;


public class SubmarineTest extends TestCase {
	
	
private int posX, posY, i,d;
private SubmarineModel model;
private  BeatModel modelb ;



public void GetSubPos(){
	model = new SubmarineModel();
	posX = model.getX();
	posY = model.getY();
}

public void SetSubPos(){
	
	double nAleatorio = (Math.random() * 570) ;			
	i = (int) nAleatorio;
	model.setX( i);								//intenta setear la coordenada x dentro del rango posible
	double nAleatorio2 = (Math.random() * 570) +190;
	d = (int) nAleatorio2;
	model.setY(d);								//intenta setear la coordenada y dentro del rango posible
}


//Prueba si la posicion es (0,190) ubicación donde el submarino debería aparecer en la ventana
public void testInitialPos(){
	GetSubPos();
	TestCase.assertEquals(true, posX == 0);
	TestCase.assertEquals(true,posY == 190);
}

//Prueba si Se producen los cambios de coordenadas
public void testChangePos(){
	GetSubPos();
	SetSubPos();
	TestCase.assertEquals(true, model.getX() == i);
	TestCase.assertEquals(true , model.getY() == d);
	
}
//Prueba si cambia el valor de la variable vivo 
public void testEndGame(){
	GetSubPos();
	model.terminarJuego();
	TestCase.assertEquals(false,model.getVivo());
}

//Prueba si el Objeto BeatModel es seteado correctamente
public void testBeatSubmarine(){
	GetSubPos();
	model.PasarBeatModel(modelb);
	TestCase.assertEquals(modelb, model.getBeatModel()) ;
			}

//Prueba si la variable pausar es seteada correctamente
public void testSetPause(){
	GetSubPos();
	model.setPausar(true);
	TestCase.assertEquals(true,model.getPausar());
	model.setPausar(false);
	TestCase.assertEquals(false,model.getPausar());
	
}
//Prueba si la variable contar es seteada correctamente
public void testSetCont(){
	GetSubPos();
	model.setContar(true);
	TestCase.assertEquals(true,model.getContar());
	model.setContar(false);
	TestCase.assertEquals(false,model.getContar());
	
}
//Prueba si la variable profundidad es seteada correctamente
public void testSetProf(){
	GetSubPos();
	double p = (Math.random() * 10000) ;			
	model.setProfundidad(p);
	TestCase.assertEquals(true,p == model.getProfundidad());
	
}

}
