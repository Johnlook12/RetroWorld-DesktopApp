package com.pinguela.retroworld.ui.desktop.controller;

import java.util.List;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.model.EstadoPedido;
import com.pinguela.retroworld.model.Pedido;
import com.pinguela.retroworld.service.PedidoService;
import com.pinguela.retroworld.service.impl.PedidoServiceImpl;
import com.pinguela.retroworld.ui.desktop.view.PedidoSearchView;

public class CompletarPedidoAction extends BaseAction{
	
	private static Logger logger = LogManager.getLogger(CompletarPedidoAction.class);
	
	private PedidoSearchView view;
	private PedidoService pedidoService;
	
	public CompletarPedidoAction(PedidoSearchView view) {
		this.view = view; 
		initServices();
	}
	
	public CompletarPedidoAction(PedidoSearchView view, String name) {
		 super(name);
		 this.view = view;
		 initServices();
	}
	
	public CompletarPedidoAction(PedidoSearchView view, String name, Icon icon) {
		 super(name, icon);
		 this.view = view;
		 initServices();
	}
	
	@Override
	public void doAction() {
		try {
			int filaSeleccionada = view.getTableResults().getSelectedRow();
			Pedido pedido = (Pedido)view.getTableResults().getModel().getValueAt(filaSeleccionada, 0);
			int answer = JOptionPane.showConfirmDialog(view, "¿Completar pedido?", "Confirmar completar", JOptionPane.YES_NO_OPTION);
			if(answer==JOptionPane.YES_OPTION) {
				pedido.setIdEstado(EstadoPedido.COMPLETADO);
				if(pedidoService.update(pedido)) {
					logger.info("Pedido con id "+pedido.getId()+" completado");
					JOptionPane.showMessageDialog(view, "Pedido completado correctamente");
					List<Pedido> pedidos = pedidoService.findByAll();
					view.setPedidos(pedidos);
					view.updateModel();
				} else {
					logger.error("Error al completar el pedido con id "+pedido.getId());
					JOptionPane.showMessageDialog(view, "No se pudo completar el pedido", "Error al completar", JOptionPane.ERROR_MESSAGE);
				}				
			}
		}catch(DataException de) {
			logger.error(de.getMessage(),de);
		}
	}
	
	private void initServices() {
		 pedidoService = new PedidoServiceImpl();
	}

}
