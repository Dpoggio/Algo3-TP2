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
public class Tablero {
  
    private ZonaMagica zonaMagica;
    private ZonaMonstruo zonaMonstruo;
    private Cementerio cementerio;
      
    public Tablero (){
    
        this.zonaMagica = new ZonaMagica ();
        this.zonaMonstruo = new ZonaMonstruo();
        this.cementerio = new Cementerio();
    }
    
    public boolean tirarCarta(CartaTrampa carta){
        return this.zonaMagica.tirarCarta(carta);
    }

    public boolean tirarCarta(Monstruo carta) {
        return this.zonaMonstruo.tirarCarta(carta);
    }
    
    public boolean tirarCarta(CartaMagica carta) {
        return this.zonaMagica.tirarCarta(carta);
    }
    

    public void matarMounstro(Monstruo monstruo) {
        this.zonaMonstruo.eliminar(monstruo);
        this.cementerio.enviarCarta(monstruo);
    }

    public int cantidadCartasCementerio() {
        return cementerio.cantidadCartas();
    }

    public int cantidadCartasZonaMonstruos() {
        return this.zonaMonstruo.cantidadCartas();
    }

    public int cantidadCartasZonaMagicas() {
        return this.zonaMagica.cantidadCartas();
    }
    public ZonaMonstruo getZonaMonstruo(){
        return this.zonaMonstruo;
    }

    public ZonaMagica getZonaMagica() {
        return this.zonaMagica;
    }

    public ArrayList<Carta> obtenerHechizosEnCampo(){
        return this.zonaMagica.cartasBocaAbajo();
    }
}
