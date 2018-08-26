package dicionario;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class Client {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextArea textArea = new JTextArea();
	private JLabel lblPalavra_1 = new JLabel("");
	private JTextArea textArea_1 = new JTextArea();
	private static IntrServidor connectionOfDictionary = null;
	private JButton btnApagarPalavraAtual = new JButton("Apagar palavra atual");
	private String actualWord = null;
	private JPanel panel = new JPanel();
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String ipServer = JOptionPane.showInputDialog("Qual o ip do servidor que vc quer se conectar?");
					while(ipServer == null || ipServer.length() <= 0) {
						JOptionPane.showMessageDialog(null, "Escreva o ip corretamente", "Erro de preenchimento", JOptionPane.ERROR_MESSAGE);
						ipServer = JOptionPane.showInputDialog("Qual o ip do servidor que vc quer se conectar?");
					}
					connectionOfDictionary = (IntrServidor)Naming.lookup("rmi://"+ ipServer +"/DictionaryService");
					Client window = new Client();
					window.frame.setVisible(true);
				} catch (UnknownHostException uhe) {
					JOptionPane.showMessageDialog(null, "Esse servidor é desconhecido para nossos sistemas", "Erro de conexão", JOptionPane.ERROR_MESSAGE);
				} catch (NotBoundException e) {
					JOptionPane.showMessageDialog(null, "Houve algum problema no registro dos serviços", "Erro de conexão", JOptionPane.ERROR_MESSAGE);
				} catch (MalformedURLException mue) {
					JOptionPane.showMessageDialog(null, "Essa url não esta corretamente escrita", "Erro da url", JOptionPane.ERROR_MESSAGE);
				} catch (RemoteException re) {
					JOptionPane.showMessageDialog(null, "houve algum problema na conexão com o servidor", "Erro de conexão", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public Client() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 20));
		frame.setBounds(150, 150, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		frame.getContentPane().add(tabbedPane, "cell 0 0,grow");
		
		tabbedPane.addTab("Pesquisa", null, panel, null);
		panel.setLayout(new MigLayout("", "[grow]", "[][][][][grow][grow]"));
		
		JLabel lblProcurarPalavra = new JLabel("Procurar palavra");
		lblProcurarPalavra.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		panel.add(lblProcurarPalavra, "cell 0 0");
		
		textField = new JTextField();
		panel.add(textField, "flowx,cell 0 1,growx");
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Pesquisar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String word = textField.getText();
				String resp = null;
				try {
					if(word != null && word.length() > 0) {
						resp = connectionOfDictionary.getMeaningByWord(word);
						
						if(resp != null) {
							String[] explodedResp = resp.split(";");
							actualWord = explodedResp[0];
							lblPalavra_1.setText(explodedResp[0]);
							textArea_1.setText(getFormattedText(explodedResp[1]));
							btnApagarPalavraAtual.setVisible(true);
							textArea_1.setVisible(true);
							textField.setText("");
						} else {
							JOptionPane.showMessageDialog(null, "Essa palavra não existe em nossa base", "Informação", JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Escreva algo no campo palavra primeiro", "Erro de preenchimento", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Houve algum erro inesperado", "Erro ao apagar palavra", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		panel.add(btnNewButton, "cell 0 1");
		btnApagarPalavraAtual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				String resp = null;
				try {
					if(actualWord != null && actualWord.length() > 0) {
						resp = connectionOfDictionary.removeMeaning(actualWord);
						
						if(resp != null) {
							actualWord = null;
							lblPalavra_1.setText("");
							textArea_1.setText("");
							btnApagarPalavraAtual.setVisible(false);
							textArea_1.setVisible(false);
							textField.setText("");
							JOptionPane.showMessageDialog(null, resp, "Mensagem", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Essa palavra não existe em nossa base", "Erro ao apagar palavra", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Pesquise uma palavra primeiro", "Erro ao apagar palavra", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Houve algum erro inesperado", "Erro ao apagar palavra", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnApagarPalavraAtual.setVisible(false);
		panel.add(btnApagarPalavraAtual, "cell 0 2,alignx right");
		lblPalavra_1.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		panel.add(lblPalavra_1, "cell 0 3");
		textArea_1.setVisible(false);
		panel.add(textArea_1, "cell 0 4 1 2,grow");
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Adicionar", null, panel_1, null);
		panel_1.setLayout(new MigLayout("", "[grow]", "[][][][][grow][][][grow][]"));
		
		JLabel lblAdicionarNovaPalavra = new JLabel("Adicionar nova palavra");
		lblAdicionarNovaPalavra.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		panel_1.add(lblAdicionarNovaPalavra, "flowx,cell 0 0");
		
		JLabel lblPalavra = new JLabel("Palavra:");
		panel_1.add(lblPalavra, "cell 0 1");
		
		textField_1 = new JTextField();
		panel_1.add(textField_1, "flowx,cell 0 2,growx");
		textField_1.setColumns(10);
		
		JLabel lblSignificado = new JLabel("Significado:");
		panel_1.add(lblSignificado, "flowx,cell 0 3");
		panel_1.add(textArea, "cell 0 4 1 4,grow");
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String word = textField_1.getText();
				String meaning = textArea.getText();
				String resp = null;
				if(word == null || word.length() <= 0) {
					JOptionPane.showMessageDialog(null, "Preencha o campo 'palavra'", "Erro no preenchimento", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(meaning == null || meaning.length() <= 0) {
					JOptionPane.showMessageDialog(null, "Preencha o campo 'significado'", "Erro no preenchimento", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try {
					resp = connectionOfDictionary.AddNewMeaning(word, meaning);
					
					if(resp != null) {
						JOptionPane.showMessageDialog(null, resp, "Informação", JOptionPane.INFORMATION_MESSAGE);
						textField_1.setText("");
						textArea.setText("");
						tabbedPane.setSelectedIndex(0);
						textField.setText(word);
						btnNewButton.doClick();
					}
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Houve algum erro inesperado", "Erro ao apagar palavra", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		panel_1.add(btnAdicionar, "cell 0 8,alignx right");
	}

	private String getFormattedText(String text) {
		String[] explodedText = text.split(" ");
		String resp = "";
		if(explodedText.length > 1) {
			for (int i = 0; i < explodedText.length; i++) {
				resp += explodedText[i] + " ";
				if(i%10 == 0 && i != 0) {
					resp += "\r\n";
				}
			}
		} else {
			for (int i = 0; i < text.length(); i++) {
				resp += text.charAt(i) + " ";
				if(i%200 == 0 && i != 0) {
					resp += "\r\n";
				}
			}
		}
		
		return resp;
	}
}
