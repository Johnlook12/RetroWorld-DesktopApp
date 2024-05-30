package com.pinguela.retroworld.ui.desktop.renderer;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.pinguela.retroworld.model.Anuncio;

public class AnuncioTableCellRenderer extends DefaultTableCellRenderer{
	
	public AnuncioTableCellRenderer() {
		
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
		Anuncio anuncio = (Anuncio) value;
		Component c = this;
		
		if(column==0) {
			setText(anuncio.getTitulo());
		} else if(column==1) {
			setText(anuncio.getEstadoAnuncio());
		} else if(column==2) {
			//TODO: DateFormatter
			setText(anuncio.getFechaInicio().toString());
		} else if(column==3) {
			setText(anuncio.getPrecio().toString());
		}
		return c;
	}
}
