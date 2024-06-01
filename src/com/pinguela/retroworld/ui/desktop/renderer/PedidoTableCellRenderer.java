package com.pinguela.retroworld.ui.desktop.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.pinguela.retroworld.model.Pedido;

public class PedidoTableCellRenderer extends DefaultTableCellRenderer{
	
	public PedidoTableCellRenderer() {
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
		Pedido pedido = (Pedido)value;
		Component c = this;
		if(column==0) {
			setText(pedido.getId().toString());
		} if(column==1) {
			setText(pedido.getFecha().toString());
		} if(column==2) {
			setText(pedido.getNickNameUsuario());
		} if(column==3) {
			setText(pedido.getEstado());
		} if(column==4) {
			setText(String.valueOf(pedido.getPrecioTotal()));
		}
		return c;
	}
}
