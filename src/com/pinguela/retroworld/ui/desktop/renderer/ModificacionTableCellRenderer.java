package com.pinguela.retroworld.ui.desktop.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.pinguela.retroworld.model.Modificacion;

public class ModificacionTableCellRenderer extends DefaultTableCellRenderer{
	
	public ModificacionTableCellRenderer() {
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
		Modificacion modificacion = (Modificacion) value;
		Component c = this;
		
		if(column==0) {
			setText(modificacion.getFecha().toString());
		} else if(column==1) {
			setText(String.valueOf(modificacion.getIdEmpleado()));
		} else if(column==2) {
			setText(modificacion.getTipoModificacion());
		}
		return c;
	}
	
}
