package com.pinguela.retroworld.ui.desktop.renderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.pinguela.retroworld.model.Genero;

public class GeneroListCellRenderer extends DefaultListCellRenderer {
	
	public GeneroListCellRenderer() {
		
	}
	
	@Override
	public Component getListCellRendererComponent( JList<?> list,
		        Object value,
		        int index,
		        boolean isSelected,
		        boolean cellHasFocus) {
		Genero genero = (Genero) value;
		super.setText("• "+genero.getNombre());
		if(list instanceof JList) {
			if(isSelected) {
				setBackground(Color.GRAY);
			} else {
				setBackground(list.getBackground());
			}
		}
		return this;
	}
}
