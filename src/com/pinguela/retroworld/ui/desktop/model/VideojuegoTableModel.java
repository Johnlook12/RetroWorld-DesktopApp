package com.pinguela.retroworld.ui.desktop.model;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.pinguela.retroworld.model.Videojuego;

public class VideojuegoTableModel extends DefaultTableModel{
	public static final String[] COLUMN_NAMES = {"ID","Nombre", "Fecha lanzamiento", "Desarrolladora", "Detalle"};
	
	public VideojuegoTableModel(List<Videojuego>videojuegos) {
		super(COLUMN_NAMES,videojuegos.size());
		Videojuego v = null;
		for(int row=0;row<videojuegos.size();row++) {
			v=videojuegos.get(row);
			for(int column=0;column<COLUMN_NAMES.length;column++) {
				setValueAt(v, row, column);
			}
		}
	}			
}
