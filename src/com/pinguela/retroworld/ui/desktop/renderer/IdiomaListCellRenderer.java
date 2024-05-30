package com.pinguela.retroworld.ui.desktop.renderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.plaf.FontUIResource;

import com.pinguela.retroworld.model.Idioma;

public class IdiomaListCellRenderer extends DefaultListCellRenderer{
	
	public IdiomaListCellRenderer() {
	}
	
	@Override
	 public Component getListCellRendererComponent(
		        JList<?> list,
		        Object value,
		        int index,
		        boolean isSelected,
		        boolean cellHasFocus) {
		Idioma idioma = (Idioma) value;
		if(list instanceof JList) {
			if(isSelected) {
				setBackground(Color.GRAY);
			} else {
				setBackground(list.getBackground());
			}
		}
		super.setText("• "+idioma.getNombre());
		return this;
	}
}
