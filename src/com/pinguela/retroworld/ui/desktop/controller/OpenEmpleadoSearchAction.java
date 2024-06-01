package com.pinguela.retroworld.ui.desktop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.Empleado;
import com.pinguela.retroworld.service.EmpleadoService;
import com.pinguela.retroworld.service.impl.EmpleadoServiceImpl;
import com.pinguela.retroworld.ui.desktop.RetroWorldWindow;
import com.pinguela.retroworld.ui.desktop.model.EmpleadoTableModel;
import com.pinguela.retroworld.ui.desktop.view.EmpleadoSearchView;

public class OpenEmpleadoSearchAction extends BaseAction{
	
	private static Logger logger = LogManager.getLogger(OpenEmpleadoSearchAction.class);
	private EmpleadoService empleadoService;
	
	public OpenEmpleadoSearchAction() {
		initServices();
	}
	
	public OpenEmpleadoSearchAction(String name) {
		super(name);
		initServices();
	}
	
	public OpenEmpleadoSearchAction(String name, Icon icon) {
		super(name, icon);
		initServices();
	}
	
	@Override
	public void doAction() {
		try {
			List<Empleado> empleados = empleadoService.findByAll();
			
			EmpleadoSearchView view = new EmpleadoSearchView();
			RetroWorldWindow window = RetroWorldWindow.getInstance();
			view.setEmpleados(empleados);
			EmpleadoTableModel model = new EmpleadoTableModel(view.getActiveEmpleados());
			view.setTableModel(model);
			view.updateView();
			window.setCenterView(view);
		} catch(DataException de) {
			logger.error(de.getMessage(), de);
		}
	}
	
	private void initServices() {
		this.empleadoService = new EmpleadoServiceImpl();
	}
	
}
