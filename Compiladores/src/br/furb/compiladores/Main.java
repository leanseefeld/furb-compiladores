package br.furb.compiladores;

import br.furb.compiladores.ui.MainWindow;
import wseefeld.ui.UIUtils;

public class Main {

	public static void main(String[] args) {
		UIUtils.changeLookAndFeelIfPossible(UIUtils.SupportedLookAndFeel.SYSTEM_DEFAULT);
		MainWindow mainWindow = new MainWindow();
		mainWindow.pack();
		mainWindow.setSize(mainWindow.getWidth(), 600);
		mainWindow.setVisible(true);
		UIUtils.centerOnScreen(mainWindow);
		//FIXME: remover isso
		System.out.println("fiz uma alteração");
	}

}
