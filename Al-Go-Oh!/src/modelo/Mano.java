package modelo;

import java.util.ArrayList;

public class Mano {

    private ArrayList<Carta> cartas;


    public Mano(){
        cartas = new ArrayList<>();
    }

    public boolean estaVacia() {
        return cartas.isEmpty();
    }

    public int cantidadCartas() {
        return cartas.size();
    }

    public void iniciarMano(Mazo mazo) {
        cartas.add(new CartaMonstruo());
        cartas.add(new CartaMonstruo());
        cartas.add(new CartaMonstruo());
        cartas.add(new CartaMonstruo());
        cartas.add(new CartaMonstruo());

    }

    public void extraerCarta(Mazo mazo) {
        cartas.add(new CartaMonstruo());
    }
}
