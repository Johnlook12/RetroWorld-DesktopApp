package com.pinguela.retroworld.ui.desktop.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.pinguela.retroworld.model.Desarrolladora;

public class DesarrolladoraListCellRenderer extends DefaultListCellRenderer{
	
	public DesarrolladoraListCellRenderer() {
		
	}
	
	@Override
	public Component getListCellRendererComponent( JList<?> list,
		        Object value,
		        int index,
		        boolean isSelected,
		        boolean cellHasFocus) {
		Desarrolladora desarrolladora = (Desarrolladora) value;
		if(desarrolladora==null) {
			setText("Seleccionar desarrolladora");
		}else {
			super.setText("• "+desarrolladora.getNombre());			
		}
		return this;
	}
}
