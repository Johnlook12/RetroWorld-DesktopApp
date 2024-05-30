package com.pinguela.retroworld.ui.desktop.model;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.pinguela.retroworld.model.EstadoPedido;
import com.pinguela.retroworld.model.Pedido;

public class PedidoAbiertoTableModel extends DefaultTableModel{
	public static final String[] COLUMN_NAMES = {"ID","Fecha","Usuario","Estado","Precio", "Detalle", "Completar", "Cancelar"};

	public PedidoAbiertoTableModel(List<Pedido>pedidos) {
		super(COLUMN_NAMES, pedidos.size());
		Pedido p = null;
		for(int row=0;row<pedidos.size();row++) {
			p = pedidos.get(row);
			for(int col=0;col<COLUMN_NAMES.length;col++) {
				setValueAt(p, row, col);
			}
		}
	}

}
