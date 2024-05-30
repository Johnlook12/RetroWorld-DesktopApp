package com.pinguela.retroworld.ui.desktop.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JTabbedPane;

import com.pinguela.retroworld.ui.desktop.listeners.TabCloseListener;

public class CloseTabAction extends AbstractAction{
	
	private JTabbedPane tabPane;
	private Component tabComponent;
	private String tabName;
	private TabCloseListener listener;
	
	public CloseTabAction(JTabbedPane pane, Component tabComponent, TabCloseListener listener) {
		this.tabPane = pane;
		this.tabComponent = tabComponent;
		this.listener=listener;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int index = tabPane.indexOfTabComponent(tabComponent);
        if (index != -1) {
            tabPane.removeTabAt(index);
            listener.onTabClose();
        }
	}
}