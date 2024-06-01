package com.pinguela.retroworld.ui.desktop.controller;

import java.util.List;

import javax.swing.Icon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.Anuncio;
import com.pinguela.retroworld.model.Modificacion;
import com.pinguela.retroworld.service.ModificacionService;
import com.pinguela.retroworld.service.impl.ModificacionServiceImpl;
import com.pinguela.retroworld.ui.desktop.dialog.ModificacionesDialog;
import com.pinguela.retroworld.ui.desktop.model.ModificacionTableModel;
import com.pinguela.retroworld.ui.desktop.view.AnuncioDetailView;

public class OpenModificacionesAction extends BaseAction{
	
	private static Logger logger = LogManager.getLogger(OpenModificacionesAction.class);
	
	private ModificacionService modificacionService;
	private AnuncioDetailView view;
	
	public OpenModificacionesAction(AnuncioDetailView view) {
		this(view, null, null);
	}
	
	public OpenModificacionesAction(AnuncioDetailView view, String name) {
		this(view, name, null);
	}
	
	public OpenModificacionesAction(AnuncioDetailView view, String name, Icon icon) {
		super(name, icon);
		this.view=view;
		initServices();
	}

	@Override
	public void doAction() {
		try {
			ModificacionesDialog dialog = new ModificacionesDialog();
			Anuncio anuncio = view.getAnuncio();
			List<Modificacion> modificaciones = modificacionService.findByIdAnuncio(anuncio.getId());	
			ModificacionTableModel model = new ModificacionTableModel(modificaciones);
			dialog.setModel(model);
			dialog.setAnuncioId(anuncio.getId());
			dialog.setVisible(true);
		}catch(DataException de) {
			logger.error(de.getMessage(), de);
		}
	}
	
	private void initServices() {
		modificacionService = new ModificacionServiceImpl();
	}
}
