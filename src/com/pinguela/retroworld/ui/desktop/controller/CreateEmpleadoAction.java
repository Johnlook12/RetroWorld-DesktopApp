package com.pinguela.retroworld.ui.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.DataException;
import com.pinguela.ServiceException;
import com.pinguela.retroworld.model.Empleado;
import com.pinguela.retroworld.service.EmpleadoService;
import com.pinguela.retroworld.service.impl.EmpleadoServiceImpl;
import com.pinguela.retroworld.ui.desktop.dialog.CreateEmpleadoDialog;

public class CreateEmpleadoAction extends AbstractAction{
	private static Logger logger = LogManager.getLogger(CreateEmpleadoAction.class);
	private CreateEmpleadoDialog dialog;
	private EmpleadoService empleadoService;
	
	public CreateEmpleadoAction(CreateEmpleadoDialog dialog) {
		this.dialog = dialog;
		initServices();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Empleado empleado = dialog.getEmpleado();
			if(dialog.validarTextFields()) {
				if(!empleado.getPassword().equals(new String(dialog.getRepeatPassword()))) {
					JOptionPane.showMessageDialog(dialog, "Las constraseñas no coinciden", "Error contraseña", JOptionPane.ERROR_MESSAGE);
				} else {
					empleadoService.registrar(empleado);
					logger.info("Empleado con id "+empleado.getId()+" registrado correctamente");
					dialog.setVisible(false);
					new OpenEmpleadoSearchAction().actionPerformed(e);
					JOptionPane.showMessageDialog(dialog, "Empleado registrado correctamente");
				}				
			} else {
				JOptionPane.showMessageDialog(dialog, "Faltan campos por rellenar", "Error al crear empleado", JOptionPane.ERROR_MESSAGE);
			}
		} catch(DataException ex) {
			logger.error(ex.getMessage(), ex);
		} catch(ServiceException se) {
			logger.error(se.getMessage(), se);
		}
	}
	
	private void initServices() {
		empleadoService = new EmpleadoServiceImpl();
	}

}
