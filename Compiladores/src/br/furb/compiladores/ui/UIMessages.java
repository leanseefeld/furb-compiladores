package br.furb.compiladores.ui;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class UIMessages {
	private static final String BUNDLE_NAME = "br.furb.compiladores.ui.UIMessages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private UIMessages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
