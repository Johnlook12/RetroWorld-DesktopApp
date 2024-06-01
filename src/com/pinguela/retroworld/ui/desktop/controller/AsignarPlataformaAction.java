package com.pinguela.retroworld.ui.desktop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.Plataforma;
import com.pinguela.retroworld.service.VideojuegoService;
import com.pinguela.retroworld.service.impl.VideojuegoServiceImpl;
import com.pinguela.retroworld.ui.desktop.dialog.AsignarPlataformaDialog;

public class AsignarPlataformaAction extends BaseAction{
	private static Logger logger = LogManager.getLogger(AsignarIdiomaAction.class);
	private VideojuegoService videojuegoService;
	private AsignarPlataformaDialog dialog;
	
	public AsignarPlataformaAction(AsignarPlataformaDialog dialog) {
		this.dialog=dialog;
		initServices();
	}
	
	public AsignarPlataformaAction(AsignarPlataformaDialog dialog, String name) {
		super(name);
		this.dialog=dialog;
		initServices();
	}
	
	public AsignarPlataformaAction(AsignarPlataformaDialog dialog, String name, Icon icon) {
		super(name, icon);
		this.dialog=dialog;
		initServices();
	}
	
	@Override
	public void doAction() {
		try {
			List<Plataforma> plataformas = dialog.getSelectedPlataformas();
			List<Integer> idsPlataformas = new ArrayList<Integer>();
			for(Plataforma p:plataformas) {
				idsPlataformas.add(p.getId());
			}
			videojuegoService.asignarPlataformas(dialog.getIdVideojuego(), idsPlataformas);
			logger.info("plataformas con id:"+idsPlataformas+" asignadas al videojuego con id: "+dialog.getIdVideojuego());
			dialog.dispose();
			JOptionPane.showMessageDialog(dialog, "Plataformas asignadas correctamente");
		}catch(DataException de) {
			logger.error(de.getMessage(), de);
		}
	}

	private void initServices() {
		videojuegoService = new VideojuegoServiceImpl();
	}
}
