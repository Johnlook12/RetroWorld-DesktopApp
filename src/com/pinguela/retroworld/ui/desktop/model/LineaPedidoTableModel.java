package com.pinguela.retroworld.ui.desktop.model;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.pinguela.retroworld.model.LineaPedido;

public class LineaPedidoTableModel extends DefaultTableModel{

public static final String[] COLUMN_NAMES = {"ID","VIDEOJUEGO","PRECIO"};
	
	public LineaPedidoTableModel(List<LineaPedido>lineas) {
		super(COLUMN_NAMES, lineas.size());
		LineaPedido lp = null;
		for(int row=0;row<lineas.size();row++) {
			lp = lineas.get(row);
			for(int col=0;col<COLUMN_NAMES.length;col++) {
				setValueAt(lp, row, col);
			}
		}
	}
	
}
