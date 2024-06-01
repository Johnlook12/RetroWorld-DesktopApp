package com.pinguela.retroworld.ui.desktop.controller;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.DataException;
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
			view.addPropertyChangeListener(new PropertyChangeListener() {	
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					try {
						view.setEmpleados(empleadoService.findByAll());
						EmpleadoTableModel model = new EmpleadoTableModel(view.getActiveEmpleados());
						view.setTableModel(model);
						view.updateView();
					}catch(DataException de) {
						logger.error(de.getMessage(),de);
					}
				}
			});
			if(!email.isEmpty()) {
				Empleado empleado = empleadoService.findByEmail(email);
				if(empleado!=null) {					
					empleados.add(empleado);
					EmpleadoTableModel model = new EmpleadoTableModel(empleados);
					view.setTableModel(model);
					view.updateView();
				} 
			}
		}catch(DataException de) {
			logger.error(de.getMessage(), de);
		}
	}
	
	private void initServices() {
		empleadoService = new EmpleadoServiceImpl();
	}

}
