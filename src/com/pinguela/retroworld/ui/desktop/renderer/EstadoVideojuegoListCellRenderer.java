package com.pinguela.retroworld.ui.desktop.renderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.pinguela.retroworld.model.EstadoVideojuego;

public class EstadoVideojuegoListCellRenderer extends DefaultListCellRenderer{
	
	public EstadoVideojuegoListCellRenderer() {
	}

	@Override
	public Component getListCellRendererComponent( JList<?> list,
		        Object value,
		        int index,
		        boolean isSelected,
		        boolean cellHasFocus) {
		EstadoVideojuego estado = (EstadoVideojuego) value;
		super.setText("• "+estado.getNombre());
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
