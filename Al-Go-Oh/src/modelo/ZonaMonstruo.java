/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


import modelo.Monstruos.MonstruoGenerico;

import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author mramundo
 */
public class ZonaMonstruo extends Zona{
    
    public ZonaMonstruo(){
    
        super();
    }

/*
    public boolean declararAtaque(Monstruo atacante, Monstruo defensor) {
        ZonaMonstruo zonaOponente = Juego.getJuego().getJugadorOponente().obtenerCampo().getZonaMonstruo();
        if (defensor == null && zonaOponente.cantidadCartas() == 0)
            atacante.atacarPuntosDeVida();
        else if (defensor != null && zonaOponente.contains(defensor))
            atacante.atacarMonstruo(defensor);
        else
            return false;
        return true;
    }
*/

    public void destruirZona(){
        for (Casillero key :casilleros.keySet()){
            casilleros.get(key).destruir();
            //casilleros.remove(key);
        }
    }
    
    public void aplicarCartaCampo(int adicional, boolean ataque){
        for (Casillero casillero: Casillero.values()) {
            if (casilleros.containsKey(casillero)) {
                Monstruo monstruo = (Monstruo) casilleros.get(casillero); //Obtengo la carta monstruo
                if(ataque == true)monstruo.sumarAdicionalAlataque(adicional);       //Le sumo los adicionales que viene de la cartaCampo
                else monstruo.sumarAdicionalAlaDefensa(adicional);
            }
        }
    }

    public void revertirCartaCampo(){
        for (Casillero casillero: Casillero.values()) {
            if (casilleros.containsKey(casillero)) {
                Monstruo monstruo = (Monstruo) casilleros.get(casillero); //Obtengo la carta monstruo
                monstruo.ponerAdicionalesEnCero();
            }
        }
    }

    
    public Monstruo destruirMonstruoConMenorAtaque() {
        Monstruo monstruoConMenorAtaque = new MonstruoGenerico("mayorAtaque",8000,0,0);
        for (Casillero casillero: Casillero.values()) {
            if (casilleros.containsKey(casillero)) {
                Monstruo monstruo = (Monstruo) casilleros.get(casillero); //Obtengo la carta monstruo
                if(monstruoConMenorAtaque.esMenorElAtaque(monstruo)){ //Comparo los ataques de los dos Monstruos
                    monstruoConMenorAtaque = monstruo;
                } 
            }
        }
          
       return monstruoConMenorAtaque;
       
    }

    
    
    
}
