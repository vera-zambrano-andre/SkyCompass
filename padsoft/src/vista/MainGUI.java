package vista;

import javax.swing.*;

/**
 * Clase principal que lanza la aplicación gráfica.
 * Crea una instancia de VentanaPrincipal y la muestra.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class MainGUI {

	/**
     * Método principal. Punto de entrada de la aplicación.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
	public static void main(String[] args) {

		JFrame ventana = new VentanaPrincipal("Skycompass");
		
		ventana.setSize(1000,700);
		ventana.setVisible(true);	
	}

}