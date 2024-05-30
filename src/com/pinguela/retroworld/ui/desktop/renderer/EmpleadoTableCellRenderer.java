package com.pinguela.retroworld.ui.desktop.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.pinguela.retroworld.model.Empleado;

public class EmpleadoTableCellRenderer extends DefaultTableCellRenderer{
	
	public EmpleadoTableCellRenderer() {
		
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
		Empleado empleado = (Empleado)value;
		Component c = this;
		if(column==0) {
			setText(empleado.getId().toString());
		} if(column==1) {
			setText(empleado.getNombre());
		} if(column==2) {
			setText(empleado.getApellido1());
		} if(column==3) {
			setText(empleado.getDocumentoIdentidad());
		} if(column==4) {
			setText(empleado.getEmail());
		}
		return c;
	}

}
