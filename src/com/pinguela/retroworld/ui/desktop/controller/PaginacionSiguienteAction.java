package com.pinguela.retroworld.ui.desktop.controller;

import javax.swing.Icon;

import com.pinguela.retroworld.ui.desktop.view.PaginatedSearchView;

public class PaginacionSiguienteAction 
		extends PagedSearchAction{

	
	public PaginacionSiguienteAction(PaginatedSearchView view) {
		super(view);
	}

	public PaginacionSiguienteAction(PaginatedSearchView view, String name) {
		super(view, name);
	}
	
	public PaginacionSiguienteAction(PaginatedSearchView view, String name, Icon icon) {
		super(view, name, icon);
	}
	
	@Override	
	public int getCurrentPosition() {
		PaginatedSearchView view = getView();
		return view.getCurrentPosition()+PaginatedSearchView.PAGE_SIZE;
	}
}
