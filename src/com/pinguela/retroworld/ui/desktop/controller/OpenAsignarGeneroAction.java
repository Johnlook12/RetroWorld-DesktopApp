package com.pinguela.retroworld.ui.desktop.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.Genero;
import com.pinguela.retroworld.model.Videojuego;
import com.pinguela.retroworld.service.GeneroService;
import com.pinguela.retroworld.service.impl.GeneroServiceImpl;
import com.pinguela.retroworld.ui.desktop.dialog.AsignarGeneroDialog;
import com.pinguela.retroworld.ui.desktop.renderer.GeneroListCellRenderer;
import com.pinguela.retroworld.ui.desktop.view.VideojuegoDetailView;

public class OpenAsignarGeneroAction extends BaseAction{
	
	private Videojuego videojuego;
	private VideojuegoDetailView view;
	
	private static Logger logger = LogManager.getLogger(OpenAsignarIdiomaAction.class);
	private GeneroService generoService;
	
	public OpenAsignarGeneroAction(VideojuegoDetailView view) {
		initServices();
		this.view=view;
	}
	
	public OpenAsignarGeneroAction(VideojuegoDetailView view, String name, Icon icon) {
		super(name,icon);
		initServices();
		this.view=view;
	}
	
	public OpenAsignarGeneroAction(VideojuegoDetailView view, String name) {
		super(name);
		initServices();
		this.view=view;
	}
	
	@Override
	public void doAction() {
		try {
			videojuego = view.getVideojuego();
			AsignarGeneroDialog dialog = new AsignarGeneroDialog();
			dialog.setVideojuego(videojuego);
			dialog.addPropertyChangeListener(new PropertyChangeListener() {	
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					if(evt.getPropertyName().equalsIgnoreCase(VideojuegoDetailView.VIEOJUEGO_DETAIL_PROPERTY)) {
						view.setGeneros((List)evt.getNewValue());						
					}
					if(evt.getPropertyName().equalsIgnoreCase(AsignarGeneroDialog.CANCEL_ASIGNAR_PROPERTY)) {
						dialog.dispose();
					}
				}
			});
			List<Genero> generosVideojuego = generoService.findByVideojuego(videojuego.getId());
			dialog.setSelectedGeneros(generosVideojuego);
			dialog.addListToModel(generosVideojuego);
			dialog.setVisible(true);
			
			List<Genero> generos= generoService.findByAll();
			DefaultComboBoxModel model = new DefaultComboBoxModel<Genero>(generos.toArray(new Genero[generos.size()]));
			dialog.setModel(model);
			dialog.setGeneroComboBoxRenderer(new GeneroListCellRenderer());
		}catch(DataException de) {
			logger.error(de.getMessage(),de);
		}
	}
	
	private void initServices() {
		generoService = new GeneroServiceImpl();
	}
	
}
