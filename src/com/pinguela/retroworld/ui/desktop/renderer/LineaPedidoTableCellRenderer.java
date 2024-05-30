package com.pinguela.retroworld.ui.desktop.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.pinguela.retroworld.model.LineaPedido;

public class LineaPedidoTableCellRenderer extends DefaultTableCellRenderer{
	
	public LineaPedidoTableCellRenderer() {
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
		LineaPedido linea = (LineaPedido)value;
		Component c = this;
		if(column==0) {
			setText(linea.getId().toString());
		} if(column==1) {
			setText(linea.getNombreVideojuego());
		} if(column==2) {
			setText(String.valueOf(linea.getPrecio()));
		}
		return c;
	}
	
}
