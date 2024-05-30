package com.pinguela.retroworld.ui.desktop.controller;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.model.Direccion;
import com.pinguela.retroworld.model.Empleado;
import com.pinguela.retroworld.service.DireccionService;
import com.pinguela.retroworld.service.EmpleadoService;
import com.pinguela.retroworld.service.impl.DireccionServiceImpl;
import com.pinguela.retroworld.service.impl.EmpleadoServiceImpl;
import com.pinguela.retroworld.ui.desktop.view.EmpleadoProfileView;

public class UpdateEmpleadoAction extends BaseAction{
	
	private static Logger logger = LogManager.getLogger(UpdateEmpleadoAction.class);
	private EmpleadoProfileView view;
	private EmpleadoService empleadoService;
	private DireccionService direccionService;
	
	public UpdateEmpleadoAction(EmpleadoProfileView view) {
		this.view=view;
		initServices();
	}
	
	public UpdateEmpleadoAction(EmpleadoProfileView view, String name) {
		super(name);
		this.view=view;
		initServices();
	}
	
	public UpdateEmpleadoAction(EmpleadoProfileView view, String name, Icon icon) {
		super(name, icon);
		this.view=view;
		initServices();
	}
	
	@Override
	public void doAction() {
		try {
			view.updateEmpleado();
			Direccion direccion = view.getDireccion();
			Empleado empleado = view.getEmpleado();
			if(empleadoService.update(empleado) && direccionService.update(direccion)) {
				JOptionPane.showMessageDialog(view, "Datos actualizados correctamente");
				logger.info("empleado con id "+empleado.getId()+" actualizado");			
			}			
		}catch(DataException de) {
			logger.error(de.getMessage(), de);
		}
	}
	
	private void initServices() {
		empleadoService = new EmpleadoServiceImpl();
		direccionService = new DireccionServiceImpl();
	}

}
