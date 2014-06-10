package br.furb.compiladores.ui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class TeamDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public TeamDialog() {
		setType(Type.UTILITY);
		setModal(true);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle(UIMessages.getString("TeamDialog.lbl_Team")); //$NON-NLS-1$
		setBounds(100, 100, 450, 205);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblAluno = new JLabel(UIMessages.getString("TeamDialog.lbl_Student")); //$NON-NLS-1$
			GridBagConstraints gbc_lblAluno = new GridBagConstraints();
			gbc_lblAluno.anchor = GridBagConstraints.NORTHWEST;
			gbc_lblAluno.insets = new Insets(0, 0, 5, 5);
			gbc_lblAluno.gridx = 0;
			gbc_lblAluno.gridy = 0;
			contentPanel.add(lblAluno, gbc_lblAluno);
		}
		{
			JTextArea txtrWilliamLeanderSeefeld = new JTextArea();
			txtrWilliamLeanderSeefeld.setFont(new Font("Tahoma", Font.PLAIN, 14)); //$NON-NLS-1$
			txtrWilliamLeanderSeefeld.setBackground(UIManager.getColor("Panel.background")); //$NON-NLS-1$
			txtrWilliamLeanderSeefeld.setEditable(false);
			txtrWilliamLeanderSeefeld.setText(UIMessages.getString("TeamDialog.value_Student")); //$NON-NLS-1$
			GridBagConstraints gbc_txtrWilliamLeanderSeefeld = new GridBagConstraints();
			gbc_txtrWilliamLeanderSeefeld.anchor = GridBagConstraints.WEST;
			gbc_txtrWilliamLeanderSeefeld.insets = new Insets(0, 0, 5, 0);
			gbc_txtrWilliamLeanderSeefeld.fill = GridBagConstraints.VERTICAL;
			gbc_txtrWilliamLeanderSeefeld.gridx = 1;
			gbc_txtrWilliamLeanderSeefeld.gridy = 0;
			contentPanel.add(txtrWilliamLeanderSeefeld, gbc_txtrWilliamLeanderSeefeld);
		}
		{
			JSeparator separator = new JSeparator();
			GridBagConstraints gbc_separator = new GridBagConstraints();
			gbc_separator.fill = GridBagConstraints.BOTH;
			gbc_separator.insets = new Insets(0, 0, 5, 0);
			gbc_separator.gridwidth = 2;
			gbc_separator.gridx = 0;
			gbc_separator.gridy = 1;
			contentPanel.add(separator, gbc_separator);
		}
		{
			JLabel lblcones = new JLabel(UIMessages.getString("TeamDialog.lbl_Icons")); //$NON-NLS-1$
			GridBagConstraints gbc_lblcones = new GridBagConstraints();
			gbc_lblcones.anchor = GridBagConstraints.WEST;
			gbc_lblcones.insets = new Insets(0, 0, 5, 5);
			gbc_lblcones.gridx = 0;
			gbc_lblcones.gridy = 2;
			contentPanel.add(lblcones, gbc_lblcones);
		}
		{
			JButton btnLink = new JButton(UIMessages.getString("TeamDialog.ref_icons8_Name")); //$NON-NLS-1$
			btnLink.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (Desktop.isDesktopSupported()) {
						try {
							Desktop.getDesktop().browse(new URI(UIMessages.getString("TeamDialog.ref_icons8_Site"))); //$NON-NLS-1$
						} catch (IOException | URISyntaxException ex) {
							ex.printStackTrace();
						}
					}
				}
			});
			btnLink.setContentAreaFilled(false);
			btnLink.setHorizontalAlignment(SwingConstants.LEADING);
			btnLink.setForeground(SystemColor.textHighlight);
			btnLink.setBorderPainted(false);
			btnLink.setBorder(null);
			btnLink.setOpaque(false);
			GridBagConstraints gbc_btnLink = new GridBagConstraints();
			gbc_btnLink.anchor = GridBagConstraints.WEST;
			gbc_btnLink.insets = new Insets(0, 0, 5, 0);
			gbc_btnLink.gridx = 1;
			gbc_btnLink.gridy = 2;
			contentPanel.add(btnLink, gbc_btnLink);
		}
		{
			JLabel lblData = new JLabel(UIMessages.getString("TeamDialog.lbl_Date")); //$NON-NLS-1$
			GridBagConstraints gbc_lblData = new GridBagConstraints();
			gbc_lblData.anchor = GridBagConstraints.WEST;
			gbc_lblData.insets = new Insets(0, 0, 0, 5);
			gbc_lblData.gridx = 0;
			gbc_lblData.gridy = 3;
			contentPanel.add(lblData, gbc_lblData);
		}
		{
			JLabel label = new JLabel(UIMessages.getString("TeamDialog.value_Date")); //$NON-NLS-1$
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.WEST;
			gbc_label.gridx = 1;
			gbc_label.gridy = 3;
			contentPanel.add(label, gbc_label);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton(UIMessages.getString("TeamDialog.btn_Ok")); //$NON-NLS-1$
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
