package com.pinguela.retroworld.ui.desktop.controller;

import java.util.List;

import javax.swing.Icon;
import javax.swing.JTable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.model.Direccion;
import com.pinguela.retroworld.model.Pedido;
import com.pinguela.retroworld.model.Usuario;
import com.pinguela.retroworld.service.DireccionService;
import com.pinguela.retroworld.service.UsuarioService;
import com.pinguela.retroworld.service.impl.DireccionServiceImpl;
import com.pinguela.retroworld.service.impl.UsuarioServiceImpl;
import com.pinguela.retroworld.ui.desktop.dialog.PedidoDetailDialog;
import com.pinguela.retroworld.ui.desktop.model.LineaPedidoTableModel;
import com.pinguela.retroworld.ui.desktop.renderer.LineaPedidoTableCellRenderer;
import com.pinguela.retroworld.ui.desktop.utils.SwingUtils;
import com.pinguela.retroworld.ui.desktop.view.PedidoSearchView;

public abstract class OpenPedidoDetailAction extends BaseAction{
	private static Logger logger = LogManager.getLogger(OpenPedidoDetailAction.class);
	
	protected PedidoSearchView view;
	private UsuarioService usuarioService;
	private DireccionService direccionService;
	
	public OpenPedidoDetailAction(PedidoSearchView view) {
		initServices();
		this.view=view;
	}
	
	public OpenPedidoDetailAction(PedidoSearchView view, String name) {
		super(name);
		initServices();
		this.view=view;
	}
	
	public OpenPedidoDetailAction(PedidoSearchView view, String name, Icon icon) {
		 super(name, icon);
		 initServices();
		 this.view=view;
	}
	
	@Override
	public void doAction() {
		try {
			int filaSeleccionada = getTable().getSelectedRow();
			Pedido pedido = (Pedido)getTable().getModel().getValueAt(filaSeleccionada, 0);
			Usuario usuario = usuarioService.findById(pedido.getIdUsuario());
			PedidoDetailDialog dialog = new PedidoDetailDialog();			
			dialog.setUsuario(usuario);
			List<Direccion> direcciones = direccionService.findByIdUsuario(usuario.getId());
			dialog.setDirecciones(direcciones);
			dialog.setPedido(pedido);
			dialog.updateView();
			dialog.setTableCellRenderer(new LineaPedidoTableCellRenderer());
			LineaPedidoTableModel model = new LineaPedidoTableModel(pedido.getLineas());
			dialog.setModel(model);
			SwingUtils.centerOnScreen(dialog);
			dialog.setVisible(true);
		}catch(DataException de) {
			logger.error(de.getMessage(), de);
		}
		
	}

	private void initServices() {
		usuarioService = new UsuarioServiceImpl();
		direccionService = new DireccionServiceImpl();
	}
	
	public abstract JTable getTable();
}
