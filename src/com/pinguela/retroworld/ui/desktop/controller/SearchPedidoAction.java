package com.pinguela.retroworld.ui.desktop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.Pedido;
import com.pinguela.retroworld.service.PedidoService;
import com.pinguela.retroworld.service.impl.PedidoServiceImpl;
import com.pinguela.retroworld.ui.desktop.model.PedidoAbiertoTableModel;
import com.pinguela.retroworld.ui.desktop.model.PedidoCerradoTableModel;
import com.pinguela.retroworld.ui.desktop.view.PedidoSearchView;

public class SearchPedidoAction extends BaseAction{
	private static Logger logger = LogManager.getLogger(SearchPedidoAction.class);

	private PedidoSearchView view;
	private PedidoService pedidoService;

	public SearchPedidoAction(PedidoSearchView view) {
		this(view, null, null);
	}

	public SearchPedidoAction(PedidoSearchView view, String name) {
		this(view, name, null);
	}

	public SearchPedidoAction(PedidoSearchView view, String name, Icon icon) {
		super(name, icon);
		this.view=view;
		initServices();
	}

	@Override
	public void doAction() {
		try {
			int selectedId = view.getSelectedIdRadioButton();
			List<Pedido>result = new ArrayList<Pedido>();
			if(view.getIdValue()!=null && selectedId==Integer.valueOf(view.ID_PEDIDO)) {
				Pedido p = pedidoService.findById(view.getIdValue());
				result.add(p);
			} else if(view.getIdValue()!=null && selectedId==Integer.valueOf(view.ID_USUARIO)){
				result = pedidoService.findByUsuario(view.getIdValue());
			} else {
				result = pedidoService.findByAll();
			}
			view.setPedidos(result);
			PedidoAbiertoTableModel abiertoModel = new PedidoAbiertoTableModel(view.getPedidosAbiertos());
			PedidoCerradoTableModel cerradoModel = new PedidoCerradoTableModel(view.getPedidosCerrados());
			view.setModel(abiertoModel, cerradoModel);
			view.addButtonsColumn();
		}catch(DataException de) {
			logger.error(de.getMessage(), de);
		}
	}

	private void initServices() {
		pedidoService = new PedidoServiceImpl();
	}
}
