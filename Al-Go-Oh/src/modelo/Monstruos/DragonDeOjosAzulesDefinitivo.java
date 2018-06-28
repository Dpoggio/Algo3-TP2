package modelo;

import modelo.Excepciones.MonstruoDeFusionException;
import modelo.Monstruos.DragonBlancoDeOjosAzules;

public class DragonDeOjosAzulesDefinitivo extends Monstruo {


    @Override
    public void requiereSacrificios(){
        throw new MonstruoDeFusionException();
    }

    @Override
    public void requiereSacrificios(Monstruo sacrificio1){
        throw new MonstruoDeFusionException();
    }

    @Override
    public void requiereSacrificios(Monstruo sacrificio1, Monstruo sacrificio2){
        throw new MonstruoDeFusionException();
    }

    //@Override // SEGREGACION INTERFAZ, NO RECIBIR MONSTRUO
    public void requiereSacrificios(DragonBlancoDeOjosAzules sacrificio1, DragonBlancoDeOjosAzules sacrificio2, DragonBlancoDeOjosAzules sacrificio3){
        //if ((sacrificio1 instanceof DragonBlancoDeOjosAzules) && (sacrificio2 instanceof DragonBlancoDeOjosAzules) && (sacrificio3 instanceof DragonBlancoDeOjosAzules) ) {
            sacrificio1.destruir();
            sacrificio2.destruir();
            sacrificio3.destruir();
        //}
        //else{
        //    throw new MonstruoDeFusionException();
        //}
    }


}
