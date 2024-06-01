package com.pinguela.retroworld.ui.desktop.model;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.pinguela.retroworld.model.Modificacion;

public class ModificacionTableModel extends DefaultTableModel{

	public static final String[] COLUMN_NAMES = {"Fecha", "Empleado","Tipo de modificación"};

	public ModificacionTableModel(List<Modificacion> modificaciones) {
		super(COLUMN_NAMES, modificaciones.size());
		Modificacion m = null;
		for(int row=0;row<modificaciones.size();row++) {
			m = modificaciones.get(row);
			for(int col=0;col<COLUMN_NAMES.length;col++) {
				setValueAt(m, row, col);
			}
		}
	}	
}
