package com.pinguela.retroworld.ui.desktop.controller;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.model.Direccion;
import com.pinguela.retroworld.model.Localidad;
import com.pinguela.retroworld.service.DireccionService;
import com.pinguela.retroworld.service.EmpleadoService;
import com.pinguela.retroworld.service.LocalidadService;
import com.pinguela.retroworld.service.impl.DireccionServiceImpl;
import com.pinguela.retroworld.service.impl.EmpleadoServiceImpl;
import com.pinguela.retroworld.service.impl.LocalidadServiceImpl;
import com.pinguela.retroworld.ui.desktop.RetroWorldWindow;
import com.pinguela.retroworld.ui.desktop.view.EmpleadoProfileView;

public class OpenEmpleadoProfileAction extends BaseAction{

	private static Logger logger = LogManager.getLogger(OpenEmpleadoProfileAction.class);
	private RetroWorldWindow window;

	LocalidadService localidadService=null;
	EmpleadoService empleadoService=null;
	DireccionService direccionService=null;

	public OpenEmpleadoProfileAction(RetroWorldWindow window) {
		this.window=window;
		initServices();
	}

	public OpenEmpleadoProfileAction(RetroWorldWindow window, String name) {
		super(name);
		this.window=window;
		initServices();
	}

	public OpenEmpleadoProfileAction(RetroWorldWindow window, String name, Icon icon) {
		super(name, icon);
		this.window=window;
		initServices();
	}

	@Override
	public void doAction() {
		try {
			EmpleadoProfileView view = new EmpleadoProfileView(window.getEmpleadoAutenticado());
			window.setCenterView(view);
			List<Localidad>localidades;
			Direccion direccion = view.getDireccion();
			Localidad localidad= localidadService.findById(direccion.getCodigoPostal());
			localidades = localidadService.findByIdProvincia(localidad.getIdProvincia());
			DefaultComboBoxModel<Localidad> localidadComboBoxModel = 
					new DefaultComboBoxModel<Localidad>(localidades.toArray(new Localidad[localidades.size()]));
			view.setModel(localidadComboBoxModel);
		} catch(DataException de) {
			logger.error(de.getMessage(), de);
		}
	}

	private void initServices() {
		localidadService = new LocalidadServiceImpl();
		empleadoService = new EmpleadoServiceImpl();
		direccionService = new DireccionServiceImpl();
	}
}
