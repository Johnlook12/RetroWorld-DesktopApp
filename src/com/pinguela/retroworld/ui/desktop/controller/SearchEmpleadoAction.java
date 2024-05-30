package com.pinguela.retroworld.ui.desktop.controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.model.Empleado;
import com.pinguela.retroworld.service.EmpleadoService;
import com.pinguela.retroworld.service.impl.EmpleadoServiceImpl;
import com.pinguela.retroworld.ui.desktop.model.EmpleadoTableModel;
import com.pinguela.retroworld.ui.desktop.view.EmpleadoSearchView;

public class SearchEmpleadoAction extends AbstractAction{
	private static Logger logger = LogManager.getLogger(SearchEmpleadoAction.class);
	private EmpleadoSearchView view;
	private EmpleadoService empleadoService;
	
	public SearchEmpleadoAction(EmpleadoSearchView view) {
		this.view=view;
		initServices();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			List<Empleado>empleados = new ArrayList<Empleado>();
			String email = view.getEmail();
			if(!email.isEmpty()) {
				Empleado empleado = empleadoService.findByEmail(email);
				empleados.add(empleado);
				EmpleadoTableModel model = new EmpleadoTableModel(empleados);
				view.setTableModel(model);
				view.updateView();
			}
		}catch(DataException de) {
			logger.error(de.getMessage(), de);
		}
	}
	
	private void initServices() {
		empleadoService = new EmpleadoServiceImpl();
	}

}
