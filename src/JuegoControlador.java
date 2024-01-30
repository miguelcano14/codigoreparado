import java.util.Random;

// Controlador del juego que maneja la lógica del juego
public class JuegoControlador {

    private final JuegoInterfaz vista;
    private final int numeroAlAzar;
    private int intentosHechos;

    // Constructor de la clase JuegoControlador
    public JuegoControlador(JuegoInterfaz vista) {
        this.vista = vista;
        this.numeroAlAzar = generarNumero();
        this.intentosHechos = 0;
        vista.setControlador(this);
    }

    // Método privado para generar un número aleatorio entre 1 y 100
    private int generarNumero() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    // Método para verificar el intento del jugador
    public void verificarAdivinanza(String texto) {
        // Verificar si el jugador aún tiene intentos disponibles
        if (intentosHechos < JuegoInterfaz.MAX_INTENTOS) {
            try {
                int intentoUsuario = Integer.parseInt(texto);

                // Comparar el intento del jugador con el número aleatorio
                if (intentoUsuario == numeroAlAzar) {
                    vista.setResultado("¡Has Ganado!");
                    vista.mostrarGif("recursos/Victoria.gif");
                } else if (intentoUsuario < numeroAlAzar) {
                    vista.setResultado("Te has quedado corto.");
                    intentosHechos++;
                    vista.actualizarVidas(intentosHechos);
                } else {
                    vista.setResultado("Te has pasado.");
                    intentosHechos++;
                    vista.actualizarVidas(intentosHechos);
                }
            } catch (NumberFormatException ex) {
                vista.setResultado("¿Sabes leer?, Ingresa un NÚMERO.");
            }
        } else {
            vista.setResultado("¡Te has muerto jajajaja!, El número correcto era: " + numeroAlAzar);
            vista.mostrarGif("recursos/derrota.gif");
        }
    }

    // Método para reiniciar el juego
    public void reiniciarJuego() {
        intentosHechos = 0; // Reiniciar contador de intentos
        vista.reiniciarJuego(); // Reiniciar la interfaz del juego
    }
}
