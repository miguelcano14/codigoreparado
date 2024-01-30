import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Clase que implementa la interfaz para la vista del juego
public class JuegoVista implements JuegoInterfaz {

    private JPanel panelPrincipal;
    private JTextField jugador;
    private JLabel resultado;
    private List<JLabel> vidas;
    private JuegoControlador controlador;

    // Método para mostrar un GIF en una ventana emergente
    public void mostrarGif(String rutaGif) {
        ImageIcon gifIcon = new ImageIcon(rutaGif);
        JLabel label = new JLabel(gifIcon);
        JOptionPane.showMessageDialog(null, label);

        controlador.reiniciarJuego(); // Reiniciar el juego después de mostrar el GIF
    }

    // Constructor de la clase JuegoVista
    public JuegoVista() {
        // Inicializar el panel principal y establecer su diseño
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(7, 1));

        // Crear componentes de la interfaz: etiqueta de instrucciones, campo de texto, botones, etiquetas de vidas y resultado
        JLabel etiquetaInstrucciones = new JLabel("Intenta adivinar el número entre el 1 y el 100:");
        jugador = new JTextField(10);
        JButton botonAdivinar = new JButton("Adivinar");
        JButton botonReset = new JButton("Resetear Juego");
        resultado = new JLabel();

        JPanel panelVidas = new JPanel();
        vidas = new ArrayList<>();
        for (int i = 0; i < JuegoInterfaz.MAX_INTENTOS; i++) {
            JLabel corazon = new JLabel(new ImageIcon("recursos/corazon.png"));
            vidas.add(corazon);
            panelVidas.add(corazon);
        }

        // Agregar listeners a los botones para manejar eventos
        botonAdivinar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.verificarAdivinanza(jugador.getText());
            }
        });

        botonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.reiniciarJuego();
            }
        });

        // Agregar componentes al panel principal en orden
        panelPrincipal.add(etiquetaInstrucciones);
        panelPrincipal.add(jugador);
        panelPrincipal.add(botonAdivinar);
        panelPrincipal.add(botonReset);
        panelPrincipal.add(panelVidas);
        panelPrincipal.add(resultado);
    }

    // Método para obtener el panel principal
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    // Implementación de métodos de la interfaz JuegoInterfaz

    @Override
    public void setControlador(JuegoControlador controlador) {
        this.controlador = controlador;
    }

    @Override
    public void setResultado(String texto) {
        resultado.setText(texto);
    }

    @Override
    public void actualizarVidas(int intentosHechos) {
        if (intentosHechos <= JuegoInterfaz.MAX_INTENTOS) {
            vidas.get(JuegoInterfaz.MAX_INTENTOS - intentosHechos).setVisible(false);
        }
    }

    @Override
    public void reiniciarJuego() {
        jugador.setEnabled(true);
        resultado.setText("");

        for (JLabel corazon : vidas) {
            corazon.setVisible(true);
            jugador.setText("");
        }
    }
}
