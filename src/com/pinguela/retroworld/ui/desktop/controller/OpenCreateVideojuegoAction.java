	package com.pinguela.retroworld.ui.desktop.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.Desarrolladora;
import com.pinguela.retroworld.service.DesarrolladoraService;
import com.pinguela.retroworld.service.impl.DesarrolladoraServiceImpl;
import com.pinguela.retroworld.ui.desktop.dialog.CreateVideojuegoDialog;

public class OpenCreateVideojuegoAction extends BaseAction{
	
	private static Logger logger = LogManager.getLogger(OpenCreateVideojuegoAction.class);
	private DesarrolladoraService desarrolladoraService;
	
	public OpenCreateVideojuegoAction() {
		initServices();
	}
	
	public OpenCreateVideojuegoAction(String name) {
		super(name);
		initServices();
	}
	
	public OpenCreateVideojuegoAction(String name, Icon icon) {
		super(name,icon);
		initServices();
	}
	
	@Override
	public void doAction() {
		try {
			CreateVideojuegoDialog dialog = new CreateVideojuegoDialog();
			dialog.addPropertyChangeListener(new PropertyChangeListener() {
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					if(evt.getPropertyName().equalsIgnoreCase(CreateVideojuegoDialog.CLOSE_DIALOG_PROPERTY)) {
						dialog.dispose();
					}
				}
			});
			List<Desarrolladora> desarrolladoras = desarrolladoraService.findByAll();
			dialog.setDesarrolladoras(desarrolladoras);
			DefaultComboBoxModel<Desarrolladora> desarrolladoraModel = new DefaultComboBoxModel<Desarrolladora>(desarrolladoras.toArray(new Desarrolladora[desarrolladoras.size()]));
			dialog.setModal(true);
			dialog.setModel(desarrolladoraModel);
			dialog.setRenderer();
			dialog.setVisible(true);			
		}catch(DataException de) {
			logger.error(de.getMessage(), de);
		}
	}
	
	private void initServices() {
		desarrolladoraService = new DesarrolladoraServiceImpl();
	}

}
