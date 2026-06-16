package controlador;

import java.awt.event.*;
import javax.swing.*;

import sistema.Login;
import sistema.SkyCompass;
import usuario.Operador;
import usuario.Usuario;
import vista.*;

/**
 * Clase que representa el controlador de la vista de login de usuario.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ControlLoginUsuario implements ActionListener {

	private VentanaPrincipal frame;
	private SkyCompass app;
	private PanelLoginUsuario vista;

	/**
	 * Constructor de la clase ControlLoginUsuario.
	 * 
	 * @param frame ventana principal
	 * @param app   aplicacion
	 * @param vista vista
	 */
	public ControlLoginUsuario(VentanaPrincipal frame, SkyCompass app, PanelLoginUsuario vista) {
		this.frame = frame;
		this.vista = vista;
		this.app = app;
	}

	/**
	 * Maneja los eventos de la vista de login de usuario.
	 * 
	 * @param e evento de pulsar el botón
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Iniciar sesión")) {
			if (vista.getTipo().equals(VentanaPrincipal.LOGINGESTOR)) {
				this.loginGestor();
			} else if (vista.getTipo().equals(VentanaPrincipal.LOGINOPERADOR)) {
				this.loginOperador();

			} else if (vista.getTipo().equals(VentanaPrincipal.LOGINCONTROLADOR)) {
				this.loginControlador();
			}
		} else if (e.getActionCommand().equals("Cancelar")) {
			vista.update();
			frame.changeVisiblePanel(VentanaPrincipal.LOGIN);
		}

	}

	/**
	 * Realiza el login del gestor.
	 * 
	 */
	private void loginGestor() {
		String password = vista.getPassword();

		if (!app.loginGestor(password)) {
			if (password.equals("")) {
				JOptionPane.showMessageDialog(vista, "Contraseña vacía. Por favor, introduzca una contraseña",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			JOptionPane.showMessageDialog(vista, "DNI o contraseña incorrecto/a", "Error", JOptionPane.ERROR_MESSAGE);
			vista.update();
			return;
		}

		frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALGESTOR);
		vista.update();
	}

	/**
	 * Realiza el login del operador.
	 */
	private void loginOperador() {
		String DNI = vista.getDni();
		String password = vista.getPassword();
		Login res;

		if (DNI.equals("") && password.equals("")) {
			JOptionPane.showMessageDialog(vista,
					"DNI y contraseña vacios. Por favor, introduzca un DNI y una contraseña", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (DNI.equals("")) {
			JOptionPane.showMessageDialog(vista, "DNI vacio. Por favor, introduzca un DNI", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (password.equals("")) {
			JOptionPane.showMessageDialog(vista, "Contraseña vacía. Por favor, introduzca una contraseña", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		res = app.loginEmpleado(DNI, password);

		if (res.equals(Login.USUARIO_BLOQUEADO)) {
			JOptionPane.showMessageDialog(vista,
					"La contraseña ha sido introducida erróneamente más de 3 veces seguidas. Por favor, espere a que el gestor le desbloquee",
					"Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (res.equals(Login.FALLO_OPERADOR)) {
			Operador o = app.getOperadorPorDNI(DNI);
			JOptionPane.showMessageDialog(vista,
					"Contraseña incorrecta, 3 fallos supondrá un bloqueo temporal (" + o.getFallos()
							+ " fallos de 3)",
					"Error", JOptionPane.ERROR_MESSAGE);
			
			if(o.getFallos() == 3) {
				JOptionPane.showMessageDialog(vista,
						"Has sido bloqueado temporalmente",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
			
			return;
		}

		if (res.equals(Login.NO_EXISTE)) {
			JOptionPane.showMessageDialog(vista, "Operador no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
			vista.update();
			return;
		}

		if (res.equals(Login.EXITO)) {
			frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALOPERADOR);
			vista.update();
		}
	}

	/**
	 * Realiza el login del controlador.
	 */
	private void loginControlador() {
		String DNI = vista.getDni();
		String password = vista.getPassword();
		Login res;

		if (DNI.equals("") && password.equals("")) {
			JOptionPane.showMessageDialog(vista,
					"DNI y contraseña vacios. Por favor, introduzca un DNI y una contraseña", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (DNI.equals("")) {
			JOptionPane.showMessageDialog(vista, "DNI vacio. Por favor, introduzca un DNI", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (password.equals("")) {
			JOptionPane.showMessageDialog(vista, "Contraseña vacía. Por favor, introduzca una contraseña",
					"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		res = app.loginEmpleado(DNI, password);

		if (res.equals(Login.FALLO_CONTROLADOR)) {
			JOptionPane.showMessageDialog(vista, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (res.equals(Login.NO_EXISTE)) {
			JOptionPane.showMessageDialog(vista, "Controlador no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
			vista.update();
			return;
		}
		if (res.equals(Login.EXITO)) {
			frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALCONTROLADOR);
			vista.update();
		}

	}
}