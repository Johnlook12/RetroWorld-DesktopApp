package com.pinguela.retroworld.ui.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.model.Videojuego;
import com.pinguela.retroworld.service.DesarrolladoraService;
import com.pinguela.retroworld.service.VideojuegoService;
import com.pinguela.retroworld.service.impl.DesarrolladoraServiceImpl;
import com.pinguela.retroworld.service.impl.VideojuegoServiceImpl;
import com.pinguela.retroworld.ui.desktop.dialog.CreateVideojuegoDialog;

public class CreateVideojuegoAction extends BaseAction{
	
	private static Logger logger = LogManager.getLogger(CreateVideojuegoAction.class);
	private CreateVideojuegoDialog dialog;
	private VideojuegoService videojuegoService;
	private DesarrolladoraService desarrolladoraService;
	
	public CreateVideojuegoAction(CreateVideojuegoDialog dialog) {
		this.dialog=dialog;
		initServices();
	}
	
	public CreateVideojuegoAction(CreateVideojuegoDialog dialog, String name) {
		super(name);
		this.dialog=dialog;
		initServices();
	}
	
	public CreateVideojuegoAction(CreateVideojuegoDialog dialog, String name, Icon icon) {
		super(name,icon);
		this.dialog=dialog;
		initServices();
	}
	
	@Override
	public void doAction() {
		try {
			Videojuego videojuego = dialog.getVideojuego();
			videojuegoService.create(videojuego);	
			logger.info("videojuego con id "+videojuego.getId()+" registrado");
			JOptionPane.showMessageDialog(dialog, "Videojuego creado correctamente");
			dialog.setVisible(false);
			new AddVideojuegoImageAction(dialog).doAction();
		}catch(DataException de) {
			logger.error(de.getMessage(),de);
		}
	}
	
	private void initServices() {
		videojuegoService = new VideojuegoServiceImpl();
		desarrolladoraService = new DesarrolladoraServiceImpl();
	}

}
