package br.furb.compiladores.ui;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import wseefeld.ui.UIUtils;
import wseefeld.utils.StringUtils;
import br.furb.compiladores.analyzer.Mensagem;
import br.furb.compiladores.controller.Compilador;
import br.furb.compiladores.io.Persistencia;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private JTextField textFieldStatus;
	private final Action newAction = new NewAction();
	private final Action openAction = new OpenAction();
	private final Action saveAction = new SaveAction();
	private final Action copyAction = new CopyAction();
	private final Action pasteAction = new PasteAction();
	private final Action cutAction = new CutAction();
	private final Action compileAction = new CompileAction();
	private final Action makeAction = new MakeAction();
	private final Action showTeamAction = new ShowTeamAction();
	private final Action selectAllAction = new SelectAllAction();
	private JTextArea textAreaEditor;

	private File arquivoFonte;
	private boolean codigoModificado = false;
	private boolean alterandoCodigo = false;
	private JTextPane textPaneMessages;

	public MainWindow() {
		setTitle(UIMessages.getString("MainWindow.Title")); //$NON-NLS-1$
		getContentPane().setLayout(new BorderLayout(1, 1));

		JPanel panelButtons = new JPanel();
		getContentPane().add(panelButtons, BorderLayout.NORTH);
		panelButtons.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNovo = new JButton();
		btnNovo.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnNovo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		btnNovo.setText(UIMessages.getString("MainWindow.btn_New")); //$NON-NLS-1$
		btnNovo.setAction(newAction);
		btnNovo.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNovo.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNovo.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNovo.setIcon(new ImageIcon(MainWindow.class.getResource("/icons/add_file/add_file-48.png")));
		btnNovo.setMnemonic('N');
		panelButtons.add(btnNovo);

		JButton btnAbrir = new JButton(openAction);
		btnAbrir.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAbrir.setIcon(new ImageIcon(MainWindow.class.getResource("/icons/documents_folder/documents_folder-48.png"))); //$NON-NLS-1$
		btnAbrir.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAbrir.setMnemonic('A');
		panelButtons.add(btnAbrir);

		JButton btnSalvar = new JButton(saveAction);
		btnSalvar.setIcon(new ImageIcon(MainWindow.class.getResource("/icons/save/save-48.png")));
		btnSalvar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSalvar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSalvar.setMnemonic('S');
		panelButtons.add(btnSalvar);

		JButton btnCopiar = new JButton(copyAction); //$NON-NLS-1$
		btnCopiar.setIcon(new ImageIcon(MainWindow.class.getResource("/icons/copy/copy-48.png"))); //$NON-NLS-1$
		btnCopiar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCopiar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCopiar.setMnemonic('C');
		panelButtons.add(btnCopiar);

		JButton btnColar = new JButton(pasteAction);
		btnColar.setIcon(new ImageIcon(MainWindow.class.getResource("/icons/cllipboard/cllipboard-48.png"))); //$NON-NLS-1$
		btnColar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnColar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnColar.setMnemonic('L');
		panelButtons.add(btnColar);

		JButton btnRecortar = new JButton(cutAction);
		btnRecortar.setIcon(new ImageIcon(MainWindow.class.getResource("/icons/cut/cut-48.png"))); //$NON-NLS-1$
		btnRecortar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRecortar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRecortar.setMnemonic('R');
		panelButtons.add(btnRecortar);

		JButton btnCompilar = new JButton(compileAction);
		btnCompilar.setIcon(new ImageIcon(MainWindow.class.getResource("/icons/play/play-48.png"))); //$NON-NLS-1$
		btnCompilar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCompilar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCompilar.setMnemonic('O');
		panelButtons.add(btnCompilar);

		JButton btnGerarCodigo = new JButton(makeAction);
		btnGerarCodigo.setIcon(new ImageIcon(MainWindow.class.getResource("/icons/settings/settings-48.png"))); //$NON-NLS-1$
		btnGerarCodigo.setHorizontalTextPosition(SwingConstants.CENTER);
		btnGerarCodigo.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnGerarCodigo.setMnemonic('G');
		panelButtons.add(btnGerarCodigo);

		JButton btnEquipe = new JButton(showTeamAction);
		btnEquipe.setIcon(new ImageIcon(MainWindow.class.getResource("/icons/group/group-48.png"))); //$NON-NLS-1$
		btnEquipe.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEquipe.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEquipe.setMnemonic('E');
		panelButtons.add(btnEquipe);

		JPanel panelInfo = new JPanel();
		panelInfo.setMinimumSize(new Dimension(10, 200));
		getContentPane().add(panelInfo, BorderLayout.SOUTH);
		panelInfo.setLayout(new BorderLayout(0, 0));

		textPaneMessages = new JTextPane();
		textPaneMessages.setEditable(false);
		float lineHeight = textPaneMessages.getFontMetrics(textPaneMessages.getFont()).getLineMetrics("Wj", getGraphics()).getHeight();
		textPaneMessages.setPreferredSize(new Dimension(20, (int) (lineHeight * 5)));
		panelInfo.add(textPaneMessages, BorderLayout.NORTH);

		JScrollPane scrollPaneMessages = new JScrollPane(textPaneMessages);
		scrollPaneMessages.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneMessages.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelInfo.add(scrollPaneMessages, BorderLayout.CENTER);

		textFieldStatus = new JTextField();
		textFieldStatus.setText(UIMessages.getString("MainWindow.status_Unmodified")); //$NON-NLS-1$
		panelInfo.add(textFieldStatus, BorderLayout.SOUTH);
		textFieldStatus.setEditable(false);
		textFieldStatus.setColumns(10);

		JScrollPane scrollPaneEditor = new JScrollPane();
		scrollPaneEditor.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneEditor.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scrollPaneEditor, BorderLayout.CENTER);

		textAreaEditor = new JTextArea();
		textAreaEditor.setBorder(new NumberedBorder());
		textAreaEditor.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				documentChanged();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				documentChanged();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				documentChanged();
			}

			private void documentChanged() {
				if (!alterandoCodigo && !codigoModificado) {
					codigoModificado = true;
					atualizarStatus();
				}
			}
		});
		scrollPaneEditor.setViewportView(textAreaEditor);

		mapAction(KeyStroke.getKeyStroke("control N"), newAction, btnNovo); //$NON-NLS-1$
		mapAction(KeyStroke.getKeyStroke("control A"), openAction, btnAbrir, //$NON-NLS-1$
				textAreaEditor);
		mapAction(KeyStroke.getKeyStroke("control S"), saveAction, btnSalvar); //$NON-NLS-1$
		mapAction(KeyStroke.getKeyStroke("control C"), copyAction, btnCopiar); //$NON-NLS-1$
		mapAction(KeyStroke.getKeyStroke("control V"), pasteAction, btnColar); //$NON-NLS-1$
		mapAction(KeyStroke.getKeyStroke("control X"), cutAction, btnRecortar); //$NON-NLS-1$
		mapAction(KeyStroke.getKeyStroke("control T"), selectAllAction, textAreaEditor); //$NON-NLS-1$
		mapAction(KeyStroke.getKeyStroke("F8"), compileAction, btnCompilar); //$NON-NLS-1$
		mapAction(KeyStroke.getKeyStroke("F9"), makeAction, btnGerarCodigo); //$NON-NLS-1$
		mapAction(KeyStroke.getKeyStroke("F1"), showTeamAction, btnEquipe); //$NON-NLS-1$

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
		textAreaEditor.requestFocus();
		limparMensagens();
	}

	public void setMessage(String mensagem) {
		this.textPaneMessages.setText(mensagem);
	}

	public void limparMensagens() {
		textPaneMessages.setText("");
	}

	private void atualizarStatus() {
		String mensagem = StringUtils.EMPTY_STR;
		if (arquivoFonte != null) {
			mensagem += arquivoFonte.getAbsolutePath() + UIMessages.getString("MainWindow.status_Separator"); //$NON-NLS-1$
		}
		mensagem += codigoModificado ? UIMessages.getString("MainWindow.status_Modified") : UIMessages.getString("MainWindow.status_Unmodified"); //$NON-NLS-1$ //$NON-NLS-2$
		setStatusMessage(mensagem);
	}

	private void mapAction(KeyStroke keyStroke, Action action, JComponent... cmps) {
		for (JComponent cmp : cmps) {
			cmp.getActionMap().put(action.getValue(Action.NAME), action);
			cmp.getInputMap().put(keyStroke, action.getValue(Action.NAME));

		}
		getRootPane().getActionMap().put(action.getValue(Action.NAME), action);
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, action.getValue(Action.NAME));
	}

	public void setCodigo(String codigo) {
		this.alterandoCodigo = true;
		this.textAreaEditor.setText(codigo);
		this.alterandoCodigo = false;
		this.codigoModificado = false;
		atualizarStatus();
	}

	public String getContent() {
		return this.textAreaEditor.getText();
	}

	public void setArquivoFonte(File arquivoFonte) {
		this.arquivoFonte = arquivoFonte;
		try {
			setCodigo(Persistencia.ler(arquivoFonte));
			limparMensagens();
		} catch (IOException e) {
			tratarErro(e);
		}
	}

	private void tratarErro(Throwable t) {
		t.printStackTrace();
		String msg = t.getMessage();
		if (StringUtils.isEmpty(msg)) {
			msg = t.toString();
		}
		setStatusMessage(UIMessages.getString("MainWindow.error_Message") + msg); //$NON-NLS-1$
	}

	private void setStatusMessage(String message) {
		textFieldStatus.setText(message);
	}

	private File escolherArquivoFonte(File diretorioAtual) {
		JFileChooser chooser = new JFileChooser(arquivoFonte);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setMultiSelectionEnabled(false);
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile();
		}
		return null;
	}

	private static void copyToClipboard(String text) {
		StringSelection selection = new StringSelection(text);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, null);
	}

	// Ações

	private class NewAction extends AbstractAction {

		public NewAction() {
			putValue(NAME, UIMessages.getString("MainWindow.btn_New")); //$NON-NLS-1$
			putValue(SHORT_DESCRIPTION, "Criar um novo arquivo."); //$NON-NLS-1$
			putValue(MNEMONIC_KEY, (int) 'N');
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			setArquivoFonte(null);
		}
	}

	private class OpenAction extends AbstractAction {
		public OpenAction() {
			putValue(NAME, UIMessages.getString("MainWindow.btn_Open")); //$NON-NLS-1$
			putValue(SHORT_DESCRIPTION, UIMessages.getString("MainWindow.ttip_Open")); //$NON-NLS-1$
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			File f = escolherArquivoFonte(arquivoFonte);
			if (f != null) {
				setArquivoFonte(f);
			}
		}
	}

	private class SaveAction extends AbstractAction {
		public SaveAction() {
			putValue(NAME, UIMessages.getString("MainWindow.btn_Save")); //$NON-NLS-1$
			putValue(SHORT_DESCRIPTION, UIMessages.getString("MainWindow.ttip_Save")); //$NON-NLS-1$
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (codigoModificado) {
				File f = null;
				if (MainWindow.this.arquivoFonte == null) {
					f = MainWindow.this.arquivoFonte = escolherArquivoFonte(null);
				} else {
					f = MainWindow.this.arquivoFonte;
				}

				if (f != null) {
					try {
						Persistencia.salvar(f, getContent());
						codigoModificado = false;
						limparMensagens();
						atualizarStatus();
					} catch (IOException ex) {
						tratarErro(ex);
					}
				}
			}
		}

	}

	private class CopyAction extends AbstractAction {
		public CopyAction() {
			putValue(NAME, UIMessages.getString("MainWindow.btn_Copy")); //$NON-NLS-1$
			putValue(SHORT_DESCRIPTION, UIMessages.getString("MainWindow.ttip_Copy")); //$NON-NLS-1$
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			copyToClipboard(textAreaEditor.getSelectedText());
		}
	}

	private class PasteAction extends AbstractAction {
		public PasteAction() {
			putValue(NAME, UIMessages.getString("MainWindow.btn_Paste")); //$NON-NLS-1$
			putValue(SHORT_DESCRIPTION, UIMessages.getString("MainWindow.ttip_Paste")); //$NON-NLS-1$
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			Transferable contents = clipboard.getContents(null);
			if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				try {
					String strContent = (String) contents.getTransferData(DataFlavor.stringFlavor);
					textAreaEditor.insert(strContent, textAreaEditor.getCaretPosition());
				} catch (Exception ex) {
					ex.printStackTrace();
					tratarErro(ex);
				}
			}
		}
	}

	private class CutAction extends AbstractAction {
		public CutAction() {
			putValue(NAME, UIMessages.getString("MainWindow.btn_Cut")); //$NON-NLS-1$
			putValue(SHORT_DESCRIPTION, UIMessages.getString("MainWindow.ttip_Cut")); //$NON-NLS-1$
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String selectedText = textAreaEditor.getSelectedText();
			int length;
			if (selectedText != null && (length = selectedText.length()) > 0) {
				StringBuilder newContent = new StringBuilder();
				String content = getContent();
				int caretPosition = textAreaEditor.getSelectionStart();
				newContent.append(content.substring(0, caretPosition));
				newContent.append(content.substring(caretPosition + length));
				copyToClipboard(selectedText);
				setCodigo(newContent.toString());
			}

		}
	}

	private class CompileAction extends AbstractAction {
		public CompileAction() {
			putValue(NAME, UIMessages.getString("MainWindow.btn_Compile")); //$NON-NLS-1$
			putValue(SHORT_DESCRIPTION, UIMessages.getString("MainWindow.ttip_Compile")); //$NON-NLS-1$
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			limparMensagens();
			Mensagem resultado = Compilador.compilar(getContent());
			setMessage(resultado.getMensagem());
		}
	}

	private class MakeAction extends AbstractAction {
		public MakeAction() {
			putValue(NAME, UIMessages.getString("MainWindow.btn_MakeCode")); //$NON-NLS-1$
			putValue(SHORT_DESCRIPTION, UIMessages.getString("MainWindow.ttip_Make")); //$NON-NLS-1$
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			//			addToken(Messages.getString("MainWindow.error_MakeNotImplemented")); //$NON-NLS-1$
		}
	}

	private class ShowTeamAction extends AbstractAction {
		public ShowTeamAction() {
			putValue(NAME, UIMessages.getString("MainWindow.btn_Team")); //$NON-NLS-1$
			putValue(SHORT_DESCRIPTION, UIMessages.getString("MainWindow.ttip_ShowTeam")); //$NON-NLS-1$
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			TeamDialog teamDialog = new TeamDialog();
			UIUtils.centerOnScreen(teamDialog);
			teamDialog.setVisible(true);
		}
	}

	private class SelectAllAction extends AbstractAction {
		public SelectAllAction() {
			putValue(NAME, UIMessages.getString("MainWindow.action_SelectAll")); //$NON-NLS-1$
			putValue(SHORT_DESCRIPTION, UIMessages.getString("MainWindow.ttip_SelectAll")); //$NON-NLS-1$
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			textAreaEditor.setSelectionStart(0);
			textAreaEditor.setSelectionEnd(textAreaEditor.getText().length());
		}
	}

}
