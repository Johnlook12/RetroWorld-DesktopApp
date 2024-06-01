package com.pinguela.retroworld.ui.desktop.controller;

import java.util.List;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.Empleado;
import com.pinguela.retroworld.service.EmpleadoService;
import com.pinguela.retroworld.service.impl.EmpleadoServiceImpl;
import com.pinguela.retroworld.ui.desktop.model.EmpleadoTableModel;
import com.pinguela.retroworld.ui.desktop.view.EmpleadoSearchView;

public class SoftDeleteEmpleadoAction extends BaseAction{

	private static Logger logger = LogManager.getLogger(SoftDeleteEmpleadoAction.class);

	private EmpleadoSearchView view;
	private EmpleadoService empleadoService;

	public SoftDeleteEmpleadoAction(EmpleadoSearchView view) {
		this.view=view;
		initServices();
	}

	public SoftDeleteEmpleadoAction(EmpleadoSearchView view, String name) {
		super(name);
		this.view=view;
		initServices();
	}

	public SoftDeleteEmpleadoAction(EmpleadoSearchView view, String name, Icon icon) {
		super(name, icon);
		this.view=view;
		initServices();
	}

	@Override
	public void doAction() {
		try {
			int filaSeleccionada = view.getTableResults().getSelectedRow();
			Empleado empleado = (Empleado) view.getTableResults().getValueAt(filaSeleccionada, 0);
			int answer = JOptionPane.showConfirmDialog(view, "¿Dar de baja empleado?", "Baja empleado", JOptionPane.YES_NO_OPTION);
			if(answer==JOptionPane.YES_OPTION) {
				if(empleadoService.delete(empleado.getId())) {
					logger.info("Empleado con id: "+empleado.getId()+" dado de baja");
					JOptionPane.showMessageDialog(view, "Empleado dado de baja correctamente");
					List<Empleado>empleados = empleadoService.findByAll();
					view.setEmpleados(empleados);
					EmpleadoTableModel model = new EmpleadoTableModel(view.getActiveEmpleados());
					view.setTableModel(model);
					
				}else {
					logger.error("error al dar de baja al empleado con id: "+empleado.getId());
					JOptionPane.showMessageDialog(view, "No se pudo dar de baja el empleado", "Error al dar de baja", JOptionPane.ERROR_MESSAGE);
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
