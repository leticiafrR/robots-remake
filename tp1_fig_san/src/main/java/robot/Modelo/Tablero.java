package robot.Modelo;

import robot.Modelo.Personajes.*;

import java.util.ArrayList;
import java.util.HashSet;

public class Tablero {
    //palabra 'long' reservada del lenguaje alternativa:"Long"
    private int largo;
    private  int ancho;

    private Jugador jugador;

    private ArrayList<Robot> robots;

    private HashSet<Fuego> fuegos;

    public void startPoint(int nivel){

    }

    public boolean colisionRobots(){
        return false;
    }

    public boolean perseguirJugador(){
        return false;
    }

    public void moiverPersonaje(Personaje p){

    }

    public boolean win(){
        return false;
    }

    public boolean lose(){
        return false;
    }


}
