package com.pinguela.retroworld.ui.desktop.controller;

import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.pinguela.retroworld.service.AnuncioService;
import com.pinguela.retroworld.ui.desktop.utils.SwingUtils;

public class LoadAnuncioImagesAction {
	private AnuncioService anuncioService = null;

	
	public LoadAnuncioImagesAction() {
		
	}
	
//	public void onAction() {
//		List<BufferedImage> imagenes = null;
//		imagenes = imageService.getVideojuegoImages(gameName);
//		
//		if(!imagenes.isEmpty()) {
//			try {
//			BufferedImage mainImage = SwingUtils.resizeImage(imagenes.get(0), MAIN_IMAGE_WIDTH);
//			JLabel mainImageLbl = new JLabel(new ImageIcon(mainImage));
//			mainImagePane.add(mainImageLbl);
//			for(BufferedImage imagen:imagenes) {	
//					BufferedImage resizedImage = SwingUtils.resizeImage(imagen, CARRUSEL_IMAGE_WIDTH);
//					JLabel imageLbl = new JLabel(new ImageIcon(resizedImage));
//					addImageOnClickAction(imageLbl, imagen);
//					imageCarruselPane.add(imageLbl);
//				}
//			} catch(Exception e) {
//				logger.error(e.getMessage(), e);
//			}
//	}
}
