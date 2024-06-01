package com.pinguela.retroworld.ui.desktop.controller;

import java.util.List;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.EstadoPedido;
import com.pinguela.retroworld.model.Pedido;
import com.pinguela.retroworld.service.PedidoService;
import com.pinguela.retroworld.service.impl.PedidoServiceImpl;
import com.pinguela.retroworld.ui.desktop.model.PedidoAbiertoTableModel;
import com.pinguela.retroworld.ui.desktop.model.PedidoCerradoTableModel;
import com.pinguela.retroworld.ui.desktop.view.PedidoSearchView;

public class CancelarPedidoAction extends BaseAction{
	
	private static Logger logger = LogManager.getLogger(CancelarPedidoAction.class);
	
	private PedidoSearchView view;
	private PedidoService pedidoService;
	
	public CancelarPedidoAction(PedidoSearchView view) {
		this.view=view;
		initServices();
	}
	
	public CancelarPedidoAction(PedidoSearchView view, String name) {
		super(name);
		this.view=view;
		initServices();
	}
	
	public CancelarPedidoAction(PedidoSearchView view, String name, Icon icon) {
		super(name, icon);
		this.view=view;
		initServices();
	}
	
	@Override
	public void doAction() {
		try {
			int filaSeleccionada = view.getTableResults().getSelectedRow();
			Pedido pedido = (Pedido)view.getTableResults().getModel().getValueAt(filaSeleccionada, 0);
			int answer = JOptionPane.showConfirmDialog(view, "¿Cancelar pedido?", "Confirmar cancelar", JOptionPane.YES_NO_OPTION);
			if(answer==JOptionPane.YES_OPTION) {
				pedido.setIdEstado(EstadoPedido.CANCELADO);
				if(pedidoService.update(pedido)) {
					logger.info("Pedido con id "+pedido.getId()+" cancelado");
					JOptionPane.showMessageDialog(view, "Pedido cancelado correctamente");
					List<Pedido> pedidos = pedidoService.findByAll();
					view.setPedidos(pedidos);
					PedidoAbiertoTableModel abiertosModel = new PedidoAbiertoTableModel(view.getPedidosAbiertos());
					PedidoCerradoTableModel cerradosModel = new PedidoCerradoTableModel(view.getPedidosCerrados());
					view.setModel(abiertosModel, cerradosModel);
					view.addButtonsColumn();
				} else {
					logger.error("Error al cancelar el pedido con id "+pedido.getId());
					JOptionPane.showMessageDialog(view, "No se pudo cancelar el pedido", "Error al cancelar", JOptionPane.ERROR_MESSAGE);
				}							
			}
		}catch(DataException de) {
			logger.error(de.getMessage(), de);
		}
	}
	
	private void initServices() {
		pedidoService = new PedidoServiceImpl();
	}
	
}
