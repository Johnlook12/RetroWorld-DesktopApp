package com.pinguela.retroworld.ui.desktop.controller;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.model.EstadoVideojuego;
import com.pinguela.retroworld.model.Results;
import com.pinguela.retroworld.model.Videojuego;
import com.pinguela.retroworld.service.EstadoVideojuegoService;
import com.pinguela.retroworld.service.VideojuegoCriteria;
import com.pinguela.retroworld.service.VideojuegoService;
import com.pinguela.retroworld.service.impl.EstadoVideojuegoServiceImpl;
import com.pinguela.retroworld.service.impl.VideojuegoServiceImpl;
import com.pinguela.retroworld.ui.desktop.dialog.CreateAnuncioDialog;

public class OpenCreateAnuncioAction extends BaseAction{
	
	private static Logger logger = LogManager.getLogger(OpenCreateAnuncioAction.class);
	private VideojuegoService videojuegoService;
	private EstadoVideojuegoService estadoVideojuegoService;
	
	public OpenCreateAnuncioAction() {
		initServices();
	}
	
	public OpenCreateAnuncioAction(String name) {
		super(name);
		initServices();
	}
	
	public OpenCreateAnuncioAction(String name, Icon icon) {
		super(name, icon);
		initServices();
	}
	
	@Override
	public void doAction() {
		try {
			CreateAnuncioDialog dialog = new CreateAnuncioDialog();
			VideojuegoCriteria criteria = new VideojuegoCriteria();
			Results<Videojuego>videojuegos = videojuegoService.findBy(criteria, 1, Integer.MAX_VALUE);
			List<EstadoVideojuego>estados = estadoVideojuegoService.findByAll();
			DefaultComboBoxModel<EstadoVideojuego> estadoModel = new DefaultComboBoxModel<EstadoVideojuego>(
					estados.toArray(new EstadoVideojuego[estados.size()]));
			DefaultComboBoxModel<Videojuego>videojuegoModel = new DefaultComboBoxModel<Videojuego>(videojuegos.getPage()
					.toArray(new Videojuego[videojuegos.getPage().size()]));
			dialog.setVideojuegos(videojuegos);
			dialog.setModel(videojuegoModel, estadoModel);
			dialog.setRenderer();
			dialog.setVisible(true);
		}catch(DataException de) {
			logger.error(de.getMessage(), de);
		}
	}
	
	private void initServices() {
		videojuegoService = new VideojuegoServiceImpl();
		estadoVideojuegoService = new EstadoVideojuegoServiceImpl();
	}

}
