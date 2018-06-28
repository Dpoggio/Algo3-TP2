package vista;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import modelo.Estados.EstadoAtaque;
import modelo.Jugador;
import modelo.Monstruo;
import vista.Botones.BotonMonstruo;

import java.util.ArrayList;

public class PanelMonstruosObjetivos extends HBox {

    private ArrayList<BotonMonstruo> monstruos;
    private Button lp;

    public PanelMonstruosObjetivos(Jugador jugador, Stage stage, Monstruo atacante) {
        monstruos = new ArrayList<BotonMonstruo>();
        this.setVisible(true);
        for (int i = 0; i < 5; i++) {
            BotonMonstruo monsterbutton = new BotonMonstruo();
            monsterbutton.setPrefSize(80, 110);
            setSpacing(20);
            monstruos.add(monsterbutton);
            monsterbutton.setStyle("-fx-background-color: #808080");
            this.getChildren().add(monsterbutton);
        }
        actualizarPanel(jugador,stage, atacante);
    }

    public void actualizarPanel(Jugador jugador,Stage stage, Monstruo atacante){
        if(jugador.getZonaMonstruo().cantidadCartas() == 0){
            this.dibujarLifePoints();
            return;
        }
        for (int i = 0; i < jugador.getZonaMonstruo().cantidadCartas(); i++) {

            monstruos.get(i).setCarta((Monstruo) jugador.getZonaMonstruo().obtenerCartaPosicion(i));
            /**************/
            monstruos.get(i).activarHandleObjetivoFaseAtaque(stage, atacante);

            /******************/
            monstruos.get(i).setVisible(true);
            ImageView newImage;

            if(monstruos.get(i).getMonstruo().getEstado() instanceof EstadoAtaque){
                Image img = new Image(monstruos.get(i).getMonstruo().getUrlImagen(),100,110,true,false);
                newImage = new ImageView(img);
            }else{
                Image img = new Image("vista/imagenes/cartaAtras.jpg",100,110,true,false);
                newImage = new ImageView(img);
            }
            monstruos.get(i).setGraphic(newImage);
            monstruos.get(i).setPrefSize(62,91);
        }
    }

    private void dibujarLifePoints() {
        this.monstruos.forEach(x -> x.setVisible(false));
        this.lp = new Button();
        this.lp.setText("aaaaaaaaaaaaaaaaaaaaa"); //TO DO LIFE POINTS
        this.lp.setVisible(true);
        this.getChildren().add(this.lp);


    }


}