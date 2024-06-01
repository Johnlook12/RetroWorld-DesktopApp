package com.pinguela.retroworld.ui.desktop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.Idioma;
import com.pinguela.retroworld.service.VideojuegoService;
import com.pinguela.retroworld.service.impl.VideojuegoServiceImpl;
import com.pinguela.retroworld.ui.desktop.dialog.AsignarIdiomaDialog;

public class AsignarIdiomaAction extends BaseAction{
	
	private static Logger logger = LogManager.getLogger(AsignarIdiomaAction.class);
	private VideojuegoService videojuegoService;
	private AsignarIdiomaDialog dialog;
	
	public AsignarIdiomaAction(AsignarIdiomaDialog dialog) {
		this.dialog=dialog;
		initServices();
	}
	
	public AsignarIdiomaAction(AsignarIdiomaDialog dialog, String name) {
		super(name);
		this.dialog=dialog;
		initServices();
	}
	
	public AsignarIdiomaAction(AsignarIdiomaDialog dialog, String name, Icon icon) {
		super(name, icon);
		this.dialog=dialog;
		initServices();
	}
	
	@Override
	public void doAction() {
		try {
			List<Idioma> idiomas = dialog.getSelectedIdiomas();
			List<Integer> idsIdioma = new ArrayList<Integer>();
			for(Idioma i:idiomas) {
				idsIdioma.add(i.getId());
			}
			videojuegoService.asignarIdiomas(dialog.getIdVideojuego(), idsIdioma);
			logger.info("Idiomas con id:"+idsIdioma+" asignados al videojuego con id: "+dialog.getIdVideojuego());
			dialog.dispose();
			JOptionPane.showMessageDialog(dialog, "Idiomas asignados correctamente");
		}catch(DataException de) {
			logger.error(de.getMessage(), de);
		}
	}

	private void initServices() {
		videojuegoService = new VideojuegoServiceImpl();
	}
}
