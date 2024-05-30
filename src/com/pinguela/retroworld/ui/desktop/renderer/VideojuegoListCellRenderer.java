package com.pinguela.retroworld.ui.desktop.renderer;

import javax.swing.*;
import java.awt.*;
import java.util.function.Supplier;

import com.pinguela.retroworld.model.Videojuego;

public class VideojuegoListCellRenderer extends DefaultListCellRenderer{
	private Supplier<String> highlightTextSupplier;

	public VideojuegoListCellRenderer(Supplier<String> highlightTextSupplier) {
		this.highlightTextSupplier=highlightTextSupplier;
	}

	@Override
	public Component getListCellRendererComponent(
			JList<?> list,
			Object value,
			int index,
			boolean isSelected,
			boolean cellHasFocus) {
		Videojuego videojuego = (Videojuego) value;
		if (videojuego == null) {
			return this;
		}
		String text = getVideojuegoDisplayText(videojuego);
		text = HtmlHighlighter.highlightText(text, highlightTextSupplier.get());
		this.setText(text);
		return this;
	}

	public static String getVideojuegoDisplayText(Videojuego v) {
		if (v == null) {
			return "";
		}
		return v.getNombre();
	}
}
