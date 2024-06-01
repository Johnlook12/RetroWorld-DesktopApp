package com.pinguela.retroworld.ui.desktop.controller;

import javax.swing.Icon;
import javax.swing.table.TableModel;

import com.pinguela.retroworld.model.Anuncio;
import com.pinguela.retroworld.model.Results;
import com.pinguela.retroworld.ui.desktop.model.ModerarAnunciosTableModel;
import com.pinguela.retroworld.ui.desktop.view.PaginatedSearchView;

public class ModerarAnuncioPagedSearchAction extends AnuncioPagedSearchAction{
	
	public ModerarAnuncioPagedSearchAction(int action, PaginatedSearchView view) {
		this(action, view, null, null);
	}

	public ModerarAnuncioPagedSearchAction(int action, PaginatedSearchView view, String name) {
		this(action, view, name, null);
	}

	public ModerarAnuncioPagedSearchAction(int action, PaginatedSearchView view, String name, Icon icon) {
		super(action,view,name,icon);
	}
	
	@Override
	public TableModel getResultsTableModel() {
		Results<Anuncio> results = (Results<Anuncio>) getView().getResults();
		ModerarAnunciosTableModel model = new ModerarAnunciosTableModel(results.getPage());
		return model;
	}

}
