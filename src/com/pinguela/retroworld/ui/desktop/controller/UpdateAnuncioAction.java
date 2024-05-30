package com.pinguela.retroworld.ui.desktop.controller;

import java.util.List;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.model.Anuncio;
import com.pinguela.retroworld.model.Modificacion;
import com.pinguela.retroworld.service.AnuncioService;
import com.pinguela.retroworld.service.impl.AnuncioServiceImpl;
import com.pinguela.retroworld.ui.desktop.view.AnuncioDetailView;

public class UpdateAnuncioAction extends BaseAction{

	private static Logger logger = LogManager.getLogger(UpdateAnuncioAction.class);
	private AnuncioDetailView view = null;
	private AnuncioService anuncioService = null;
	
	public UpdateAnuncioAction(AnuncioDetailView view) {
		this.view=view;
		initServices();
	}
	
	public UpdateAnuncioAction(AnuncioDetailView view, String name) {
		super(name);
		this.view=view;
		initServices();
	}
	
	public UpdateAnuncioAction(AnuncioDetailView view, String name, Icon icon) {
		super(name, icon);
		this.view=view;
		initServices();
	}
	
	@Override
	public void doAction() {
		try {
			view.updateAnuncio();
			Anuncio anuncio = view.getAnuncio();
			List<Modificacion> modificaciones = view.getModificaciones();
			if(anuncioService.update(anuncio, modificaciones)) {
				logger.info("anuncio con id: "+anuncio.getId()+" actualizado");
				JOptionPane.showMessageDialog(view, "Anuncio actualizado correctamente");
			} else {
				logger.error("error al actualizar el anuncio con id: "+anuncio.getId());
				JOptionPane.showMessageDialog(view, "Error al actualizar el anuncio", "Error de actualización", JOptionPane.ERROR_MESSAGE);
			}
		} catch(DataException de) {
			logger.error(de.getMessage(), de);
		}
	}
	
	private void initServices() {
		anuncioService = new AnuncioServiceImpl();
	}

}
