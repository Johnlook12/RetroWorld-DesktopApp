package com.pinguela.retroworld.ui.desktop.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.model.Plataforma;
import com.pinguela.retroworld.model.Videojuego;
import com.pinguela.retroworld.service.PlataformaService;
import com.pinguela.retroworld.service.impl.PlataformaServiceImpl;
import com.pinguela.retroworld.ui.desktop.dialog.AsignarPlataformaDialog;
import com.pinguela.retroworld.ui.desktop.renderer.PlataformaListCellRenderer;
import com.pinguela.retroworld.ui.desktop.view.VideojuegoDetailView;

public class OpenAsignarPlataformaAction extends BaseAction{
	private Videojuego videojuego;
	private VideojuegoDetailView view;
	
	private static Logger logger = LogManager.getLogger(OpenAsignarIdiomaAction.class);
	private PlataformaService plataformaService;
	
	public OpenAsignarPlataformaAction(VideojuegoDetailView view) {
		initServices();
		this.view=view;
	}
	
	public OpenAsignarPlataformaAction(VideojuegoDetailView view, String name, Icon icon) {
		super(name,icon);
		initServices();
		this.view=view;
	}
	
	public OpenAsignarPlataformaAction(VideojuegoDetailView view, String name) {
		super(name);
		initServices();
		this.view=view;
	}
	
	@Override
	public void doAction() {
		try {
			videojuego = view.getVideojuego();
			AsignarPlataformaDialog dialog = new AsignarPlataformaDialog();
			dialog.setVideojuego(videojuego);
			dialog.addPropertyChangeListener(new PropertyChangeListener() {	
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					if(evt.getPropertyName().equalsIgnoreCase(VideojuegoDetailView.VIEOJUEGO_DETAIL_PROPERTY)) {
						view.setPlataformas((List)evt.getNewValue());						
					}
					if(evt.getPropertyName().equalsIgnoreCase(AsignarPlataformaDialog.CANCEL_ASIGNAR_PROPERTY)) {
						dialog.dispose();						
					}
				}
			});
			List<Plataforma> plataformasVideojuego = plataformaService.findByVideojuego(videojuego.getId());
			dialog.setSelectedPlataformas(plataformasVideojuego);
			dialog.addListToModel(plataformasVideojuego);
			dialog.setVisible(true);
			
			List<Plataforma> plataformas= plataformaService.findByAll();
			DefaultComboBoxModel model = new DefaultComboBoxModel<Plataforma>(plataformas.toArray(new Plataforma[plataformas.size()]));
			dialog.setModel(model);
			dialog.setPlataformaComboBoxRenderer(new PlataformaListCellRenderer());
		}catch(DataException de) {
			logger.error(de.getMessage(),de);
		}
	}
	
	private void initServices() {
		plataformaService = new PlataformaServiceImpl();
	}
}
