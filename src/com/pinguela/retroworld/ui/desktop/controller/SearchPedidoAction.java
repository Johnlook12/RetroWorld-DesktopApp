package com.pinguela.retroworld.ui.desktop.controller;

import javax.swing.Icon;

import com.pinguela.retroworld.service.PedidoService;
import com.pinguela.retroworld.service.impl.PedidoServiceImpl;
import com.pinguela.retroworld.ui.desktop.view.PedidoSearchView;

public class SearchPedidoAction extends BaseAction{
	private PedidoSearchView view;
	private PedidoService pedidoService;
	
	public SearchPedidoAction(PedidoSearchView view) {
		this.view=view;
	}
	
	public SearchPedidoAction(PedidoSearchView view, String name) {
		super(name);
		this.view=view;
	}
	
	public SearchPedidoAction(PedidoSearchView view, String name, Icon icon) {
		 super(name, icon);
			this.view=view;
	}
	
	@Override
	public void doAction() {
		
	}
	
	private void initServices() {
		pedidoService = new PedidoServiceImpl();
	}
}
