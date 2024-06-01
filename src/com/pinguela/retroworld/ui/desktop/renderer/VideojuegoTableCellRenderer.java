package com.pinguela.retroworld.ui.desktop.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.model.Videojuego;

public class VideojuegoTableCellRenderer extends DefaultTableCellRenderer{
	private static Logger logger = LogManager.getLogger(VideojuegoTableCellRenderer.class);
	
	public VideojuegoTableCellRenderer() {
		
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
		Videojuego videojuego = (Videojuego) value;
		Component c = this;
		
		if(column==0) {
			setText(String.valueOf(videojuego.getId()));
		} else if(column==1) {
			setText(videojuego.getNombre());
		} else if(column==2) {
			setText(videojuego.getFechaLanzamiento().toString());
		} else if(column==3) {
			setText(videojuego.getNombreDesarrolladora());
		}
		return c;
	}
}
