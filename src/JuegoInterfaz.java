// Interfaz que define los métodos que deben implementar las vistas del juego
public interface JuegoInterfaz {

    int MAX_INTENTOS = 5;

    void setControlador(JuegoControlador controlador);

    void setResultado(String texto);

    void actualizarVidas(int intentosHechos);

    void reiniciarJuego();

    void mostrarGif(String rutaGif);

}
