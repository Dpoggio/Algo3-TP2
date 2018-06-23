package modelo.CartasMagicas;

import modelo.*;

public class AgujeroNegro extends CartaMagica  {


    @Override
    public void activarEfecto(){
        Juego juego = Juego.getJuego();
        Jugador jugadorOponente = juego.getJugadorOponente();
        Jugador jugadorActivo = juego.getJugadorActivo();

        jugadorActivo.obtenerCampo().destruirZonaMonstruos();
        jugadorOponente.obtenerCampo().destruirZonaMonstruos();
        
        //jugadorActivo.obtenerCampo().destruirCarta(this);
        this.destruir();
    }
}
