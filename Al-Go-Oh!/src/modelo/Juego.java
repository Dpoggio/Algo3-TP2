package modelo;

public class Juego {
        private static Juego instancia = new Juego();

        public static Juego getJuego() {
            return instancia;
        }

        private Juego(){
            jugadorActivo = new Jugador();
            jugadorOponente = new Jugador();
        }

    private Jugador jugadorActivo;

    private Jugador jugadorOponente;

    public static void reiniciarJuego() {
        instancia = new Juego();
    }

    private void cambiarTurno(){
        Jugador temp = this.jugadorActivo;
        this.jugadorActivo = this.jugadorOponente;
        this.jugadorOponente = temp;
    }

    public Jugador getJugadorOponente()
    {
        return jugadorOponente;
    }

    public Jugador getJugadorActivo() {
        return jugadorActivo;
    }
}
