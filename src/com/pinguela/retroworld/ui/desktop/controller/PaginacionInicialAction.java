package com.pinguela.retroworld.ui.desktop.controller;

import javax.swing.Icon;

import com.pinguela.retroworld.ui.desktop.view.PaginatedSearchView;

public class PaginacionInicialAction 
		extends PagedSearchAction {
	
	public PaginacionInicialAction(PaginatedSearchView view) {
		super(view);
	}

	public PaginacionInicialAction(PaginatedSearchView view, String name) {
		super(view, name);
	}
	
	public PaginacionInicialAction(PaginatedSearchView view, String name, Icon icon) {
		super(view, name, icon);
	}
	
	@Override
	public int getCurrentPosition() {
		return 1;
	}
}
