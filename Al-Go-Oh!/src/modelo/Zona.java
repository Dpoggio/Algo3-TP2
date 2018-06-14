/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author mramundo
 */
public abstract class Zona {
    ArrayList<Casillero> casilleros; 


    protected void crearCasilleros(){  
        for (int i= 0; i< 5; i++){
            Casillero casillero = new Casillero (); 
            casilleros.add(casillero);
        }
    }
    
    
    public boolean casilleroDisponible() {
         for(Casillero casillero : this.casilleros){
            if(casillero.estaVacio())return true; 
        }  
        return false;
    }   
    
    public boolean tirarCarta(Carta carta) {
        for(Casillero casillero : this.casilleros){
            if(casillero.estaVacio()){
                return casillero.agregarCarta(carta);     
            }
        }  
        return false;   
     }

    protected boolean contains(Carta carta) {
        for (Casillero casillero:casilleros) {
            if(casillero.contiene(carta))
                return true;
        }
        return false;
    }

    public int cantidadCartas(){
        int count = 0;
        for (Casillero casillero: casilleros) {
            if (!casillero.estaVacio()){
                count++;
            }

        }
        return count;
    };

    public ArrayList<Carta> cartasBocaAbajo(){
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        for (Casillero casillero: casilleros){
            Carta carta = casillero.obtenerCarta();
            if(carta != null && carta.estaBocaAbajo())
                cartas.add(carta);
        }
        return cartas;
    }
}
