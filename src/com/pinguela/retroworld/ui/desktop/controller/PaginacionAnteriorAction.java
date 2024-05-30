package com.pinguela.retroworld.ui.desktop.controller;

import javax.swing.Icon;

import com.pinguela.retroworld.ui.desktop.view.PaginatedSearchView;

public class PaginacionAnteriorAction extends PagedSearchAction {

	
	public PaginacionAnteriorAction(PaginatedSearchView view) {
		super(view);
	}

	public PaginacionAnteriorAction(PaginatedSearchView view, String name) {
		super(view, name);
	}
	
	public PaginacionAnteriorAction(PaginatedSearchView view, String name, Icon icon) {
		super(view, name, icon);
	}
	
	@Override
	public int getCurrentPosition() {
		PaginatedSearchView view = getView();
		if (view.getCurrentPosition()>PaginatedSearchView.PAGE_SIZE) {
			return view.getCurrentPosition()-PaginatedSearchView.PAGE_SIZE;
		} else {
			return 1;
		}
	}
}
