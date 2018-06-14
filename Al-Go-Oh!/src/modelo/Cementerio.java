package modelo;

import java.util.ArrayList;

public class Cementerio {

    private ArrayList<Carta> cartas;

    public Cementerio(){

        this.cartas = new ArrayList<Carta>();
    }


    public void enviarCarta(Carta carta) {
        this.cartas.add(carta);
    }

    public Carta buscarCarta(Carta carta) {
        return carta;
    }


    public boolean estaVacio(){
        return cartas.isEmpty();
    };

    public int cantidadCartas() {
        return this.cartas.size();
    }
}
