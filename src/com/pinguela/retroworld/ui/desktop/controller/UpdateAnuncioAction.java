package com.pinguela.retroworld.ui.desktop.controller;

import java.util.List;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.Anuncio;
import com.pinguela.retroworld.model.Modificacion;
import com.pinguela.retroworld.service.AnuncioService;
import com.pinguela.retroworld.service.impl.AnuncioServiceImpl;
import com.pinguela.retroworld.ui.desktop.model.AnuncioTableModel;
import com.pinguela.retroworld.ui.desktop.view.AnuncioDetailView;
import com.pinguela.retroworld.ui.desktop.view.AnuncioSearchView;
import com.pinguela.retroworld.ui.desktop.view.PaginatedSearchView;

public class UpdateAnuncioAction extends BaseAction{

	private static Logger logger = LogManager.getLogger(UpdateAnuncioAction.class);
	private AnuncioDetailView view = null;
	private AnuncioService anuncioService = null;
	private PaginatedSearchView searchView;
	
	public UpdateAnuncioAction(AnuncioDetailView view, PaginatedSearchView searchView) {
		this(view, searchView, null, null);
	}
	
	public UpdateAnuncioAction(AnuncioDetailView view, PaginatedSearchView searchView, String name) {
		this(view, searchView, name, null);
	}
	
	public UpdateAnuncioAction(AnuncioDetailView view, PaginatedSearchView searchView, String name, Icon icon) {
		super(name, icon);
		this.searchView=searchView;
		this.view=view;
		initServices();
	}
	
	@Override
	public void doAction() {
		try {
			view.updateAnuncio();
			Anuncio anuncio = view.getAnuncio();
			AnuncioSearchView anuncioView = (AnuncioSearchView)searchView;
			List<Modificacion> modificaciones = view.getModificaciones();
			if(anuncioService.update(anuncio, modificaciones)) {
				logger.info("anuncio con id: "+anuncio.getId()+" actualizado");
				JOptionPane.showMessageDialog(view, "Anuncio actualizado correctamente");
				AnuncioTableModel model = new AnuncioTableModel(anuncioService.findBy(anuncioView.getCriteria(), 
						anuncioView.getCurrentPosition(), PaginatedSearchView.PAGE_SIZE).getPage());
				anuncioView.setTableModel(model);
				view.updateView();
				view.showEditInterface(false);
				anuncioView.addButtonsColumn();
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
