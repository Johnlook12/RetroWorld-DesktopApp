package com.pinguela.retroworld.ui.desktop.model;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.pinguela.retroworld.model.Empleado;

public class EmpleadoTableModel extends DefaultTableModel{
	public static final String[] COLUMN_NAMES = {"ID","NOMBRE","APELLIDO","DNI/NIE","EMAIL", "Baja"};
	
	public EmpleadoTableModel(List<Empleado>empleados) {
		super(COLUMN_NAMES, empleados.size());
		Empleado e = null;
		for(int row=0;row<empleados.size();row++) {
			e = empleados.get(row);
			for(int col=0;col<COLUMN_NAMES.length;col++) {
				setValueAt(e, row, col);
			}
		}
	}
}
