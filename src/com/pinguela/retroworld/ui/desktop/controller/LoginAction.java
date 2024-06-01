package com.pinguela.retroworld.ui.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.model.Empleado;
import com.pinguela.retroworld.service.EmpleadoService;
import com.pinguela.retroworld.service.impl.EmpleadoServiceImpl;
import com.pinguela.retroworld.ui.desktop.RetroWorldWindow;
import com.pinguela.retroworld.ui.desktop.view.EmpleadoProfileView;
import com.pinguela.retroworld.ui.desktop.view.LoginView;

public class LoginAction extends AbstractAction{
	private static Logger logger = LogManager.getLogger(LoginAction.class);
	private LoginView view = null;
	private EmpleadoService empleadoService=null;
		
	
	public LoginAction(LoginView view) {
		setView(view);
		initServices();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Empleado empleado = empleadoService.autentificar(view.getEmpleadoEmail(), view.getEmpleadoPassword());
			if(empleado!=null && empleado.getFechaBaja()==null) {
				logger.info("Empleado con id "+empleado.getId()+" Autenticado correctamente");
				
				RetroWorldWindow window = RetroWorldWindow.getInstance();
				window.setEmpleadoAutenticado(empleado);
				view.setEmpleadoAutenticado(empleado);
				new OpenEmpleadoProfileAction(window).doAction();
				window.setVisible(true);
				if(empleado.getIdTipoEmpleado() == Empleado.TIPO_ADMINISTRADOR) {
					window.setAdministradorFunctionsVisible(true);
				} else {
					window.setAdministradorFunctionsVisible(false);
				}
			} else {
				JOptionPane.showMessageDialog(view.getParent(), "Usuario o contraseña incorrectos");
				logger.info("Fallo en la autenticación del empleado con id "+empleado.getId());
			}
		} catch(Exception ex) {
			logger.error(ex.getMessage(),ex);
		}
	}
	
	private void initServices() {
		empleadoService= new EmpleadoServiceImpl();
	}
	
	private void setView(LoginView view) {
		this.view = view;
	}

}
