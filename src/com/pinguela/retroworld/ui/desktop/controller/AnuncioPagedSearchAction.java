package com.pinguela.retroworld.ui.desktop.controller;

import javax.swing.Icon;
import javax.swing.table.TableModel;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.model.AbstractCriteria;
import com.pinguela.retroworld.model.Anuncio;
import com.pinguela.retroworld.model.Results;
import com.pinguela.retroworld.service.AnuncioCriteria;
import com.pinguela.retroworld.service.AnuncioService;
import com.pinguela.retroworld.service.impl.AnuncioServiceImpl;
import com.pinguela.retroworld.ui.desktop.model.AnuncioTableModel;
import com.pinguela.retroworld.ui.desktop.view.PaginatedSearchView;

public class AnuncioPagedSearchAction extends PagedSearchAction<Anuncio>{
	
	private AnuncioService anuncioService = null;

	public AnuncioPagedSearchAction(int action, PaginatedSearchView view) {
		this(action, view, null, null);
	}

	public AnuncioPagedSearchAction(int action, PaginatedSearchView view, String name) {
		this(action, view, name, null);
	}

	public AnuncioPagedSearchAction(int action, PaginatedSearchView view, String name, Icon icon) {
		super(action,view,name,icon);
		initServices();
	}

	private void initServices() {
		anuncioService = new AnuncioServiceImpl();
	}

	@Override
	public Results<Anuncio> doSearch(AbstractCriteria criteria) throws DataException {
		AnuncioCriteria anuncioCriteria = (AnuncioCriteria)criteria;
		Results<Anuncio> results = anuncioService.findBy(anuncioCriteria, 
				getCurrentPosition(), 
				PaginatedSearchView.PAGE_SIZE);	
		return results;
	}

	@Override
	public TableModel getResultsTableModel() {
		Results<Anuncio> results = (Results<Anuncio>) getView().getResults();
		AnuncioTableModel model = new AnuncioTableModel(results.getPage());
		return model;
	}
	
}
