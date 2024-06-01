package com.pinguela.retroworld.ui.desktop.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.Idioma;
import com.pinguela.retroworld.model.Videojuego;
import com.pinguela.retroworld.service.IdiomaService;
import com.pinguela.retroworld.service.impl.IdiomaServiceImpl;
import com.pinguela.retroworld.ui.desktop.dialog.AsignarIdiomaDialog;
import com.pinguela.retroworld.ui.desktop.renderer.IdiomaListCellRenderer;
import com.pinguela.retroworld.ui.desktop.view.VideojuegoDetailView;

public class OpenAsignarIdiomaAction extends BaseAction{
	private Videojuego videojuego;
	private VideojuegoDetailView view;
	
	private static Logger logger = LogManager.getLogger(OpenAsignarIdiomaAction.class);
	private IdiomaService idiomaService;
	
	public OpenAsignarIdiomaAction(VideojuegoDetailView view) {
		initServices();
		this.view=view;
	}
	
	public OpenAsignarIdiomaAction(VideojuegoDetailView view, String name, Icon icon) {
		super(name,icon);
		initServices();
		this.view=view;
	}
	
	public OpenAsignarIdiomaAction(VideojuegoDetailView view, String name) {
		super(name);
		initServices();
		this.view=view;
	}
	
	@Override
	public void doAction() {
		try {
			videojuego = view.getVideojuego();
			AsignarIdiomaDialog dialog = new AsignarIdiomaDialog();
			dialog.addPropertyChangeListener(new PropertyChangeListener() {
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					if(evt.getPropertyName().equalsIgnoreCase(VideojuegoDetailView.VIEOJUEGO_DETAIL_PROPERTY)) {
						view.setIdiomas((List)evt.getNewValue());
					}
					if(evt.getPropertyName().equalsIgnoreCase(AsignarIdiomaDialog.CANCEL_ASIGNAR_PROPERTY)) {
						dialog.dispose();
					}
					
				}
			});
			dialog.setVideojuego(videojuego);
			List<Idioma> idiomasVideojuego = idiomaService.findByVideojuego(videojuego.getId());
			dialog.setSelectedIdiomas(idiomasVideojuego);
			dialog.addListToModel(idiomasVideojuego);
			dialog.setVisible(true);
			
			List<Idioma> idiomas = idiomaService.findByAll();
			DefaultComboBoxModel model = new DefaultComboBoxModel<Idioma>(idiomas.toArray(new Idioma[idiomas.size()]));
			dialog.setModel(model);
			dialog.setIdiomaComboBoxRenderer(new IdiomaListCellRenderer());
		}catch(DataException de) {
			logger.error(de.getMessage(),de);
		}
	}
	
	private void initServices() {
		idiomaService = new IdiomaServiceImpl();
	}

}
