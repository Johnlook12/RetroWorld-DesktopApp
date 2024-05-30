package com.pinguela.retroworld.ui.desktop.controller;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.model.Anuncio;
import com.pinguela.retroworld.service.AnuncioService;
import com.pinguela.retroworld.service.impl.AnuncioServiceImpl;
import com.pinguela.retroworld.ui.desktop.dialog.CreateAnuncioDialog;

public class CreateAnuncioAction extends BaseAction{
	
	private static Logger logger = LogManager.getLogger(CreateAnuncioAction.class);
	private CreateAnuncioDialog dialog;
	private AnuncioService anuncioService;
	
	public CreateAnuncioAction(CreateAnuncioDialog dialog) {
		this.dialog=dialog;
		initServices();
	}
	
	public CreateAnuncioAction(CreateAnuncioDialog dialog, String name) {
		super(name);
		this.dialog=dialog;
		initServices();
	}
	
	public CreateAnuncioAction(CreateAnuncioDialog dialog, String name, Icon icon) {
		super(name, icon);
		this.dialog=dialog;
		initServices();
	}
	
	@Override
	public void doAction() {
		try {
			Anuncio anuncio=dialog.getAnuncio();
			anuncioService.create(anuncio);
			logger.info("Anuncio con id: "+anuncio.getId()+" creado");
			JOptionPane.showMessageDialog(dialog, "Anuncio creado correctamente");
			dialog.setVisible(false);
			new AddAnuncioImageAction(dialog).doAction();
		}catch(DataException de) {
			logger.error(de.getMessage(), de);
		}
	}
	
	private void initServices() {
		anuncioService = new AnuncioServiceImpl();
	}

}
