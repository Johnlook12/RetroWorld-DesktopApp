package com.pinguela.retroworld.ui.desktop.controller;

import javax.swing.Icon;
import javax.swing.table.TableModel;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.model.AbstractCriteria;
import com.pinguela.retroworld.model.Results;
import com.pinguela.retroworld.model.Videojuego;
import com.pinguela.retroworld.service.VideojuegoCriteria;
import com.pinguela.retroworld.service.VideojuegoService;
import com.pinguela.retroworld.service.impl.VideojuegoServiceImpl;
import com.pinguela.retroworld.ui.desktop.model.VideojuegoTableModel;
import com.pinguela.retroworld.ui.desktop.view.PaginatedSearchView;

public class VideojuegoPagedSearchAction extends PagedSearchAction<Videojuego>{

	private VideojuegoService videojuegoService = null;

	public VideojuegoPagedSearchAction(int action, PaginatedSearchView view) {
		this(action, view, null, null);
	}

	public VideojuegoPagedSearchAction(int action, PaginatedSearchView view, String name) {
		this(action, view, name, null);
	}

	public VideojuegoPagedSearchAction(int action, PaginatedSearchView view, String name, Icon icon) {
		super(action,view,name,icon);
		initServices();
	}

	private void initServices() {
		videojuegoService = new VideojuegoServiceImpl();
	}

	@Override
	public Results<Videojuego> doSearch(AbstractCriteria criteria) throws DataException {
		VideojuegoCriteria videojuegoCriteria = (VideojuegoCriteria)criteria;
		Results<Videojuego> results = videojuegoService.findBy(videojuegoCriteria, 
				getCurrentPosition(), 
				PaginatedSearchView.PAGE_SIZE);	
		return results;
	}

	@Override
	public TableModel getResultsTableModel() {
		Results<Videojuego> results = (Results<Videojuego>) getView().getResults();
		VideojuegoTableModel model = new VideojuegoTableModel(results.getPage());
		return model;
	}

}
