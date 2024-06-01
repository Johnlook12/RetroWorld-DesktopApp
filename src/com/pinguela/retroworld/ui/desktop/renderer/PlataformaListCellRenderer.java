package com.pinguela.retroworld.ui.desktop.renderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.pinguela.retroworld.model.Plataforma;

public class PlataformaListCellRenderer extends DefaultListCellRenderer{
	
	public PlataformaListCellRenderer() {
		
	}
	
	@Override
	 public Component getListCellRendererComponent(
		        JList<?> list,
		        Object value,
		        int index,
		        boolean isSelected,
		        boolean cellHasFocus) {
		Plataforma plataforma = (Plataforma) value;
		if(plataforma==null) {
			setText("Seleccionar plataforma");
		}else {
			if(list instanceof JList) {
				if(isSelected) {
					setBackground(Color.GRAY);
				} else {
					setBackground(list.getBackground());
				}
			}
			super.setText("• "+plataforma.getNombre());			
		}
		return this;
	}
}
