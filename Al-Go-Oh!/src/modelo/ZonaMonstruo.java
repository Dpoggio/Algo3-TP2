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
public class ZonaMonstruo extends Zona{
    
    public ZonaMonstruo(){
    
       this.casilleros = new ArrayList<Casillero>();
        this.crearCasilleros();
    }


    public boolean declararAtaque(Monstruo atacante, Monstruo defensor) {
        ZonaMonstruo zonaOponente = Juego.getJuego().getJugadorOponente().obtenerCampo().getZonaMonstruo();
        if (defensor == null && zonaOponente.cantidadCartas() == 0)
            atacante.atacarPuntosDeVida();
        else if (defensor != null && zonaOponente.contains(defensor))
            atacante.atacarMounstro(defensor);
        else
            return false;
        return true;
    }

    public void sacrificarMonstruo() {
        Juego.getJuego().getJugadorActivo().obtenerCampo().matarMounstro((Monstruo)this.casilleros.get(0).obtenerCarta());
    }

    public void eliminar(Monstruo monstruo) {
        for (Casillero casillero: casilleros) {
            if(casillero.contiene(monstruo)) {
                casilleros.remove(casillero);
                return;
            }

        }
    }
}
