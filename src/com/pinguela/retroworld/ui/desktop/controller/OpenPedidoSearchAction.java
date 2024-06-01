package com.pinguela.retroworld.ui.desktop.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.Icon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.Pedido;
import com.pinguela.retroworld.service.PedidoService;
import com.pinguela.retroworld.service.impl.PedidoServiceImpl;
import com.pinguela.retroworld.ui.desktop.RetroWorldWindow;
import com.pinguela.retroworld.ui.desktop.model.PedidoAbiertoTableModel;
import com.pinguela.retroworld.ui.desktop.model.PedidoCerradoTableModel;
import com.pinguela.retroworld.ui.desktop.view.PedidoSearchView;

public class OpenPedidoSearchAction extends BaseAction{
	private static Logger logger = LogManager.getLogger(OpenPedidoSearchAction.class);
	
	private PedidoService pedidoService;
	
	public OpenPedidoSearchAction() {
		initServices();
	}
	
	public OpenPedidoSearchAction(String name) {
		super(name);
		initServices();
	}
	
	public OpenPedidoSearchAction(String name, Icon icon) {
		super(name, icon);
		initServices();
	}
	
	@Override
	public void doAction() {
		try {
			PedidoSearchView view = new PedidoSearchView();
			
			List<Pedido>pedidos = pedidoService.findByAll();
			view.setPedidos(pedidos);
			PedidoAbiertoTableModel abiertosModel = new PedidoAbiertoTableModel(view.getPedidosAbiertos());
			PedidoCerradoTableModel cerradosModel = new PedidoCerradoTableModel(view.getPedidosCerrados());
			view.setModel(abiertosModel, cerradosModel);
			view.addButtonsColumn();
			RetroWorldWindow.getInstance().setCenterView(view);
		}catch(DataException de) {
			logger.error(de.getMessage(), de);
		}		
	}
	
	private void initServices() {
		pedidoService = new PedidoServiceImpl();
	}
}
