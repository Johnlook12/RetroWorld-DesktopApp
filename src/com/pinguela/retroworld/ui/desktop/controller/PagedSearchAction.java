package com.pinguela.retroworld.ui.desktop.controller;

import javax.swing.Icon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.model.AbstractCriteria;
import com.pinguela.retroworld.model.Anuncio;
import com.pinguela.retroworld.model.Results;
import com.pinguela.retroworld.model.Videojuego;
import com.pinguela.retroworld.service.AnuncioCriteria;
import com.pinguela.retroworld.service.AnuncioService;
import com.pinguela.retroworld.service.VideojuegoCriteria;
import com.pinguela.retroworld.service.VideojuegoService;
import com.pinguela.retroworld.service.impl.AnuncioServiceImpl;
import com.pinguela.retroworld.service.impl.VideojuegoServiceImpl;
import com.pinguela.retroworld.ui.desktop.model.AnuncioTableModel;
import com.pinguela.retroworld.ui.desktop.model.VideojuegoTableModel;
import com.pinguela.retroworld.ui.desktop.view.PaginatedSearchView;

public abstract class PagedSearchAction
		extends BaseAction {
	
	private static Logger logger = LogManager.getLogger(PagedSearchAction.class); 
	
	private PaginatedSearchView view = null;
	private AnuncioService anuncioService = null;
	private VideojuegoService videojuegoService = null;
	
	public PagedSearchAction(PaginatedSearchView view) {
		setView(view);
		initServices();
	}

	public PagedSearchAction(PaginatedSearchView view, String name) {
		super(name);
		setView(view);
		initServices();
	}
	
	public PagedSearchAction(PaginatedSearchView view, String name, Icon icon) {
		super(name, icon);
		setView(view);
		initServices();
	}
	
	private void initServices() {
		this.anuncioService = new AnuncioServiceImpl();
		this.videojuegoService = new VideojuegoServiceImpl();
	}
	
	/**
	 * Template method.
	 * Common behaviour.
	 */
	public void doAction() {
		try {						
			AbstractCriteria criteria = view.getCriteria();
			int currentPosition;
			currentPosition = getCurrentPosition();
			if(criteria instanceof AnuncioCriteria) {
				AnuncioCriteria anuncioCriteria = (AnuncioCriteria)criteria;
				Results<Anuncio> results = anuncioService.findBy(anuncioCriteria, 
						currentPosition, 
						PaginatedSearchView.PAGE_SIZE);
				view.setResults(results);
				AnuncioTableModel model = new AnuncioTableModel(results.getPage());
				view.setTableModel(model);
			} else if(criteria instanceof VideojuegoCriteria) {
				VideojuegoCriteria videojuegoCriteria = (VideojuegoCriteria)criteria;
				Results<Videojuego> results = videojuegoService.findBy(videojuegoCriteria, 
						currentPosition, 
						PaginatedSearchView.PAGE_SIZE);
				view.setResults(results);
				VideojuegoTableModel model = new VideojuegoTableModel(results.getPage());
				view.setTableModel(model);
			}
			view.setCurrentPosition(currentPosition);
			// Validar datos
			// ...			
			view.updateView();
			
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
	}

	protected PaginatedSearchView getView() {
		return this.view;
	}
	
	protected void setView(PaginatedSearchView view) {
		this.view = view;
	}
	
	/**
	 * Concrete behaviour.
	 */
	public abstract int getCurrentPosition();
}
