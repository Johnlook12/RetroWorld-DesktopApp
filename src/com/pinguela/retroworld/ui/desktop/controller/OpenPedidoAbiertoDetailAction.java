package com.pinguela.retroworld.ui.desktop.controller;

import javax.swing.Icon;
import javax.swing.JTable;

import com.pinguela.retroworld.ui.desktop.view.PedidoSearchView;

public class OpenPedidoAbiertoDetailAction extends OpenPedidoDetailAction{

	public OpenPedidoAbiertoDetailAction(PedidoSearchView view) {
		super(view);
	}
	
	public OpenPedidoAbiertoDetailAction(PedidoSearchView view, String name) {
		super(view, name);
	}
	
	public OpenPedidoAbiertoDetailAction(PedidoSearchView view, String name, Icon icon) {
		 super(view, name, icon);
	}
	
	@Override
	public JTable getTable() {
		return super.view.getTableResults();
	}

}
