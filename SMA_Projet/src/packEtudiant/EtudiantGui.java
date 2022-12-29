package packEtudiant;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.gui.GuiEvent;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import packPrincipale.DeployerAgent;

import java.awt.Label;
import java.awt.TextField;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import packPrincipale.DeployerAgent;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import java.awt.Panel;

public class EtudiantGui extends JFrame {



	public EtudiantGui(String title) throws HeadlessException {
		setTitle(title);
		
	}
	private JPanel contentPane;
	private TextArea textArea;
	private TextField textField;
	private Etudiant CAgent;
	private JButton btnNewButton;
	private  DeployerAgent DA;
private TextArea textArea_1 ;
	public Etudiant getCAgent() {
		return CAgent;
	}

	public void setCAgent(Etudiant cAgent) {
		CAgent = cAgent;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EtudiantGui frame = new EtudiantGui();
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EtudiantGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1096, 689);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Label label = new Label("Nom Agent: ");
		label.setBounds(57, 52, 94, 22);
		contentPane.add(label);

		textField = new TextField();
		textField.setBounds(156, 52, 114, 22);
		contentPane.add(textField);

		Button button = new Button("Effacer");
		button.setBackground(Color.RED);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textArea.setText("");
				
			}
		});
		button.setBounds(94, 225, 89, 22);
		contentPane.add(button);

		textArea = new TextArea();
		textArea.setBackground(Color.WHITE);
		textArea.setBounds(207, 153, 366, 160);
		textArea.setFont(new Font("Arial", Font.BOLD, 14));
		//textArea.setEditable(false);
		contentPane.add(textArea);

		btnNewButton = new JButton("Envoyer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String agentName = textField.getText();
				String contenu = textArea.getText();
				GuiEvent gev = new GuiEvent(this, 1);
				Map<String, Object> params = new HashMap<>();
				params.put("agentEtudiant", agentName);
				params.put("contenu", contenu);
				gev.addParameter(params);
				CAgent.onGuiEvent(gev);
			}
		});
		btnNewButton.setForeground(Color.ORANGE);
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.setBounds(94, 178, 89, 23);
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.LIGHT_GRAY);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "champ pour l' envoie de message", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel.setBounds(10, 32, 584, 311);
		contentPane.add(panel);
		panel.setLayout(null);
						
						JLabel lblNewLabel = new JLabel("");
						lblNewLabel.setBounds(50, 58, 87, 22);
						panel.add(lblNewLabel);
						
						JLabel lblNewLabel_1 = new JLabel("Saisir  le message:");
						lblNewLabel_1.setBounds(60, 74, 143, 31);
						panel.add(lblNewLabel_1);
		this.setVisible(true);
		DA=new DeployerAgent();
		String titre=DA.E;
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "champ des messages re\u00E7ues", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel_1.setBounds(580, 346, 468, 275);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		 textArea_1 = new TextArea();
		textArea_1.setBounds(28, 49, 380, 160);
		panel_1.add(textArea_1);
		
		Button button_1 = new Button("Afficher le Message");
		button_1.setBounds(10, 21, 117, 22);
		panel_1.add(button_1);
		button_1.setBackground(Color.PINK);
		this.setTitle(titre);
	
	}

	public void ShowMessage(String msg) {
		
			textArea_1.append(msg + "\n");
		
	}
}
