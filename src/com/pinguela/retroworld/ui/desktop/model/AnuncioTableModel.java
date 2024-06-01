package com.pinguela.retroworld.ui.desktop.model;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.pinguela.retroworld.model.Anuncio;
//TODO: Con datos aquí en lista
public class AnuncioTableModel extends DefaultTableModel{
	
	public static final String[] COLUMN_NAMES = {"Título", "Estado","Fecha Inicio","Fecha Fin", "Precio", "Detalle","Dar de baja"};
	
	public AnuncioTableModel(List<Anuncio> anuncios) {
		super(COLUMN_NAMES, anuncios.size());
		Anuncio a = null;
		for(int row=0;row<anuncios.size();row++) {
			a = anuncios.get(row);
			for(int col=0;col<COLUMN_NAMES.length;col++) {
				setValueAt(a, row, col);
			}
		}
	}
	
	
}
