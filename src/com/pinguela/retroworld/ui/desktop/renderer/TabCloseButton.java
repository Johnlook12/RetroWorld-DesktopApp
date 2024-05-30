package com.pinguela.retroworld.ui.desktop.renderer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.pinguela.retroworld.ui.desktop.RetroWorldWindow;
import com.pinguela.retroworld.ui.desktop.controller.CloseTabAction;
import com.pinguela.retroworld.ui.desktop.listeners.TabCloseListener;

public class TabCloseButton extends JPanel{
	private String title;
	private JTabbedPane tabbedPane;
	private TabCloseListener listener;
	
	public TabCloseButton(String title, JTabbedPane pane, TabCloseListener listener) {
		setOpaque(false);
		this.title=title;
		this.tabbedPane=pane;
		this.listener=listener;
		addButton();
	}
	private void addButton() {
		JLabel lblTitle = new JLabel(title);
        JButton btnClose = new JButton(new ImageIcon(RetroWorldWindow.class.getResource("/icons/icons8-cross-16.png")));
        
        btnClose.addActionListener(new CloseTabAction(tabbedPane, this, listener));

        add(lblTitle);
        add(btnClose);
	}
}
