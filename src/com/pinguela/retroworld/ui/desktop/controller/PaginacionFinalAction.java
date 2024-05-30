package com.pinguela.retroworld.ui.desktop.controller;

import javax.swing.Icon;

import com.pinguela.retroworld.ui.desktop.view.PaginatedSearchView;

public class PaginacionFinalAction extends PagedSearchAction {

	public PaginacionFinalAction(PaginatedSearchView view) {
		super(view);
	}

	public PaginacionFinalAction(PaginatedSearchView view, String name) {
		super(view, name);		
	}
	
	public PaginacionFinalAction(PaginatedSearchView view, String name, Icon icon) {
		super(view, name, icon);		
	}
	
	@Override
	public int getCurrentPosition() {
		int total = getView().getResults().getTotal();
		int sobrante = total%10;
		return total-sobrante+1;		
	}

}
