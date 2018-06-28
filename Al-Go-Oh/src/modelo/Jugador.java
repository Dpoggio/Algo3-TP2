package modelo;

import modelo.Estados.EstadoBocaAbajo;
import modelo.Estados.EstadoDefensaBocaAbajo;
import modelo.Estados.EstadoBocaArriba;
import modelo.Estados.EstadoAtaque;
import modelo.Excepciones.InvocacionNoPosibleException;
import modelo.Mazo.Mazo;
import modelo.CartasMagicas.CartaCampoSinEfecto;
import modelo.Excepciones.ZonaVaciaException;
import modelo.Monstruos.DragonBlancoDeOjosAzules;

public class Jugador {
    //private Tablero tablero;
    private int puntosDeVida;
    private Mano mano;
    private Mazo mazo;

    private ZonaHechizos zonaHechizos;
    private ZonaMonstruo zonaMonstruo;
    private Cementerio cementerio;
    private CartaCampo cartaCampo;
    private int invocacionesPosibles;


    public Jugador(){
        puntosDeVida = 8000;
        this.mazo  = new Mazo();
        this.mano = new Mano();
        this.invocacionesPosibles = 1;
        this.zonaHechizos = new ZonaHechizos();
        this.zonaMonstruo = new ZonaMonstruo();
        this.cementerio = new Cementerio();
        this.cartaCampo = new CartaCampoSinEfecto();
    }
/*
    public Tablero obtenerCampo(){
        return this.tablero;
    }*/

    public int getPuntosDeVida() {
        return puntosDeVida;
    }

    public void setPuntosDeVida(int puntosDeVida) {
        this.puntosDeVida = puntosDeVida;
    }

    public void restarPuntosDeVida(int puntosDeVidaMenos){
        puntosDeVida -= puntosDeVidaMenos;
    }

    public int cantidadCartasCementerio() {
        return cementerio.cantidadCartas();
    }

    public void invocar(Monstruo monstruo) {
        this.verificarInvocacion();
        monstruo.requiereSacrificios();
        
        //Tablero campo = this.obtenerCampo();
        monstruo.setEstado(new EstadoAtaque());
        monstruo.asociarJugador(this);
        //campo.tirarCarta(monstruo);
        this.zonaMonstruo.colocarCarta(monstruo);
    }

    private int getInvocacionesPosibles() {
        return this.invocacionesPosibles;
    }

    public void invocar(Monstruo monstruo, Monstruo sacrificio1) {
        this.verificarInvocacion();
        monstruo.requiereSacrificios(sacrificio1);

        //Tablero campo = this.obtenerCampo();
        monstruo.setEstado(new EstadoAtaque());
        monstruo.asociarJugador(this);
        //campo.tirarCarta(monstruo);
        this.zonaMonstruo.colocarCarta(monstruo);
    }

    public void invocar(Monstruo monstruo, Monstruo sacrificio1, Monstruo sacrificio2) {
        this.verificarInvocacion();
        monstruo.requiereSacrificios(sacrificio1,sacrificio2);

        //Tablero campo = this.obtenerCampo();
        monstruo.setEstado(new EstadoAtaque());
        monstruo.asociarJugador(this);
        //campo.tirarCarta(monstruo);
        this.zonaMonstruo.colocarCarta(monstruo);
    }

    public void invocar(modelo.DragonDeOjosAzulesDefinitivo monstruo, DragonBlancoDeOjosAzules sacrificio1, modelo.DragonDeOjosAzulesDefinitivo sacrificio2, DragonBlancoDeOjosAzules sacrificio3) {
        this.verificarInvocacion();
        monstruo.requiereSacrificios(sacrificio1,sacrificio2,sacrificio3);

        //Tablero campo = this.obtenerCampo();
        monstruo.setEstado(new EstadoAtaque());
        monstruo.asociarJugador(this);
        //campo.tirarCarta(monstruo);
        this.zonaMonstruo.colocarCarta(monstruo);
    }

    public void colocar(Monstruo monstruo){
        this.verificarInvocacion();
        monstruo.requiereSacrificios();
        //Tablero campo = this.obtenerCampo();
        monstruo.setEstado(new EstadoDefensaBocaAbajo());
        monstruo.asociarJugador(this);
        //campo.tirarCarta(monstruo);
        this.zonaMonstruo.colocarCarta(monstruo);
        this.invocacionesPosibles -= 1;
    }

    public void colocar(Monstruo monstruo, Monstruo sacrificio1){
        this.verificarInvocacion();
        monstruo.requiereSacrificios(sacrificio1);
        //Tablero campo = this.obtenerCampo();
        monstruo.setEstado(new EstadoDefensaBocaAbajo());
        monstruo.asociarJugador(this);
        //campo.tirarCarta(monstruo);
        this.zonaMonstruo.colocarCarta(monstruo);
    }

    public void colocar(Monstruo monstruo, Monstruo sacrificio1, Monstruo sacrificio2){
        this.verificarInvocacion();
        monstruo.requiereSacrificios(sacrificio1,sacrificio2);
        //Tablero campo = this.obtenerCampo();
        monstruo.setEstado(new EstadoDefensaBocaAbajo());
        monstruo.asociarJugador(this);
        //campo.tirarCarta(monstruo);
        this.zonaMonstruo.colocarCarta(monstruo);

    }

    public void colocar(Monstruo monstruo, Monstruo sacrificio1, Monstruo sacrificio2, Monstruo sacrificio3){
        this.verificarInvocacion();
        monstruo.requiereSacrificios(sacrificio1,sacrificio2,sacrificio3);
        //Tablero campo = this.obtenerCampo();
        monstruo.setEstado(new EstadoDefensaBocaAbajo());
        monstruo.asociarJugador(this);
        //campo.tirarCarta(monstruo);
        this.zonaMonstruo.colocarCarta(monstruo);
    }

    private void verificarInvocacion() throws InvocacionNoPosibleException{
        if(this.getInvocacionesPosibles() <= 0){
            throw new InvocacionNoPosibleException();
        }
        this.invocacionesPosibles -= 1;
    }

    public void activarCarta(CartaMagica carta){
        carta.setEstado(new EstadoBocaArriba());
        carta.asociarJugador(this);
        this.zonaHechizos.colocarCarta(carta);
        carta.activarEfecto();
    }

    public void colocarCarta(CartaMagica carta){
        carta.setEstado(new EstadoBocaAbajo());
        carta.asociarJugador(this);
        this.zonaHechizos.colocarCarta(carta);
    }

    public void colocarCarta(CartaTrampa carta) {
        carta.setEstado(new EstadoBocaAbajo());
        carta.asociarJugador(this);
        //this.obtenerCampo().tirarCarta(carta);
        this.zonaHechizos.colocarCarta(carta);
    }
    
    public void colocarCarta(CartaCampo carta) {
        this.cartaCampo = carta;
    }
        
    public Mano getMano() {
        return mano;
    }

    public void extraerCartaDelMazo() {
        Carta carta = mazo.extaerCarta();
        mano.agregarCarta(carta);
    }

    public void activarCartaCampo() {
        this.cartaCampo.activarEfecto();
    }
   
    public void revertirCartaCampo() {
        this.getZonaMonstruo().revertirCartaCampo();
    }  


    public int obtenerTamanioMazo() {
        return this.getMazo().getTamanio();
    }

    private Mazo getMazo() {
        return this.mazo;
    }

    public void activarCartaTrampa(Monstruo atacante, Monstruo defensor) {
        //tablero.activarEfectoCartaTrampa(atacante,defensor);
        zonaHechizos.activarEfectoPrimerCartaTrampa(atacante,defensor);
    }

    public void destruirCarta(Carta carta) {
        this.zonaHechizos.eliminar(carta);
        this.cementerio.enviarCarta(carta);
    }

    public void matarMonstruo(Monstruo monstruo) {
        this.zonaMonstruo.eliminar(monstruo);
        this.cementerio.enviarCarta(monstruo);
    }

    public void destruirZonaMonstruos() {
        zonaMonstruo.destruirZona();
    }

    public ZonaMonstruo getZonaMonstruo(){
        return this.zonaMonstruo;
    }

    public ZonaHechizos getZonaHechizos() {
        return this.zonaHechizos;
    }


    public void destruirMonstruoConMenorAtaque() {
        try{
        if (this.getZonaMonstruo().zonaVacia())  throw new ZonaVaciaException();
        Monstruo monstruo =  this.getZonaMonstruo().destruirMonstruoConMenorAtaque();
        this.matarMonstruo(monstruo);
        }catch (ZonaVaciaException e){}
    }

    public int cantidadCartasZonaMonstruos() {
        return this.zonaMonstruo.cantidadCartas();
    }

    public int cantidadCartasZonaMagicas() {
        return this.zonaHechizos.cantidadCartas();
    }

    public Carta buscarCartaEnCementerio(Carta carta) {
        return this.cementerio.buscarCarta(carta);
    }

    public boolean contieneExodia(){
        return this.mano.contieneExodia();
    }

    public void quitarCartaDeMano(Carta carta) {
        mano.quitarCarta(carta);
    }

    public void resetearInvocacionesPosibles() {
        this.invocacionesPosibles = 1;
    }
}
