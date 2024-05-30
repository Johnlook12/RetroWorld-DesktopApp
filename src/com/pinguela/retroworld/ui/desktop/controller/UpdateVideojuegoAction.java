package com.pinguela.retroworld.ui.desktop.controller;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.model.Videojuego;
import com.pinguela.retroworld.service.VideojuegoService;
import com.pinguela.retroworld.service.impl.VideojuegoServiceImpl;
import com.pinguela.retroworld.ui.desktop.view.VideojuegoDetailView;

public class UpdateVideojuegoAction extends BaseAction{
	
	private static Logger logger = LogManager.getLogger(UpdateVideojuegoAction.class);
	private VideojuegoDetailView view = null;
	private VideojuegoService videojuegoService;
	
	public UpdateVideojuegoAction(VideojuegoDetailView view) {
		this.view=view;
		initServices();
	}
	
	public UpdateVideojuegoAction(VideojuegoDetailView view, String name, Icon icon) {
		super(name, icon);
		this.view=view;
		initServices();
	}
	
	public UpdateVideojuegoAction(VideojuegoDetailView view, String name) {
		super(name);
		this.view=view;
		initServices();
	}
	
	@Override
	public void doAction() {
		try {
			view.updateVideojuego();
			Videojuego videojuego = view.getVideojuego();
			if(videojuegoService.update(videojuego)) {
				logger.info("Videojuego con id: "+videojuego.getId()+" actualizado");
				JOptionPane.showMessageDialog(view, "Videojuego actualizado correctamente");
				view.updateView();
			} else {
				logger.error("error al actualizar el videojuego con id: "+videojuego.getId());
				JOptionPane.showMessageDialog(view, "Error al actualizar el videojuego", "Error de actualización", JOptionPane.ERROR_MESSAGE, null);
			}
			
		}catch(DataException de) {
			logger.error(de.getMessage(), de);
		}
	}
	
	private void initServices() {
		videojuegoService = new VideojuegoServiceImpl();
	}
}
