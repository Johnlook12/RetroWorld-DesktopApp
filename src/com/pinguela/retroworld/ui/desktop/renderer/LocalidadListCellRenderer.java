package com.pinguela.retroworld.ui.desktop.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.pinguela.retroworld.model.Localidad;

public class LocalidadListCellRenderer extends DefaultListCellRenderer{
	
	@Override
	public Component getListCellRendererComponent( JList<?> list,
	        Object value,
	        int index,
	        boolean isSelected,
	        boolean cellHasFocus) {
	Localidad localidad = (Localidad) value;
	super.setText(localidad.getNombre());
	return this;
}
}
