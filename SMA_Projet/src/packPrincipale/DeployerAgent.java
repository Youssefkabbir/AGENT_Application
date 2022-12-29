package packPrincipale;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;
import packEtudiant.Etudiant;
import packEtudiant.EtudiantGui;
import packProfesseu.Professeur;

import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextField;

public class DeployerAgent extends JFrame {

	private JPanel contentPane;


	public static String E="Etudiant";
	public static String P="Professeur";
//déclaration  de Main conteneur 
	private static AgentContainer mainContainer ;
	//déclaration de deux  zones textAreas pour voir  les noms locales des agents créer 
	private TextArea textArea;
	private TextArea textArea_1;
//déclaration des  conteneurs 1 et 2
	private static AgentContainer agentConatianer1;
	private static AgentContainer agentConatianer2;
//création de l' agent Etudiant
	private Etudiant CAgent;
	// déclaration de les zones ou  on va saisir les noms  des agnets 
	public  TextField textField0;
	public  TextField textField0_1;




	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//on va déployer une fenétre qui s' apple Déployer
					DeployerAgent frame = new DeployerAgent();
					frame.setVisible(true);
			        //on va lancer la plate phorme jade 
						Runtime runtime=Runtime.instance();
						Properties properties=new ExtendedProperties();
						properties.setProperty(Profile.GUI,"true");
						ProfileImpl profileImpl=new ProfileImpl(properties);
						//on va créer le MainContainer
						mainContainer=runtime.createMainContainer(profileImpl);
						//on démare le MainConatiner
						mainContainer.start();
					
//##########################################################//
						// cette partie pour créer le conteneur1
						Runtime runtime1=Runtime.instance();
						ProfileImpl profileImpl1=new ProfileImpl();
						//on créer  liée le conteneur 1 à le MainContainer
						profileImpl.setParameter(ProfileImpl.MAIN_HOST, "localhost");
						 agentConatianer1=runtime1.createAgentContainer(profileImpl1);
//######################################################################//
							// cette partie pour créer le conteneur1
						 Runtime runtime2=Runtime.instance();
							ProfileImpl profileImpl2=new ProfileImpl();
							//on créer  liée le conteneur 2 à le MainContainer
							profileImpl.setParameter(ProfileImpl.MAIN_HOST, "localhost");
							 agentConatianer2=runtime2.createAgentContainer(profileImpl2);				      
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DeployerAgent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 817, 914);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "champ pour le professeur", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel.setBounds(10, 11, 564, 354);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nom du professeur:");
		lblNewLabel.setBounds(22, 41, 127, 20);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Deployer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					
				 	String name1=textField0.getText();
				 	E=name1;
				 	
				 	
					try {
						AgentController agentController=agentConatianer2.createNewAgent(name1, Professeur.class.getName(), new Object[] {});
						agentController.start();
						Ajeuter(name1+"\n");
					} catch (StaleProxyException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBounds(29, 153, 89, 23);
		panel.add(btnNewButton);
		
		 textArea = new TextArea();
		textArea.setBounds(129, 183, 380, 160);
		panel.add(textArea);
		
		 textField0 = new TextField();
		 textField0.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		textField0.getText();
		 	}
		 });
		textField0.setBounds(221, 41, 134, 22);
		panel.add(textField0);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "champ pour l' \u00E9tudiant", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel_1.setBounds(10, 376, 564, 319);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Nom de l'Etudiant:");
		lblNewLabel_1.setBounds(22, 41, 127, 20);
		panel_1.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Deployer");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			 	String name=textField0_1.getText();
			 //	E=name;
			 	
			 	
				try {
					AgentController agentController=agentConatianer1.createNewAgent(name, Etudiant.class.getName(), new Object[] {});
					agentController.start();
					Ajeuter1(name+"\n");

				} catch (StaleProxyException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBackground(Color.GREEN);
		btnNewButton_1.setBounds(29, 153, 89, 23);
		panel_1.add(btnNewButton_1);
		
		 textArea_1 = new TextArea();
		textArea_1.setBounds(143, 134, 380, 160);
		panel_1.add(textArea_1);
		
		 textField0_1 = new TextField();
		textField0_1.setBounds(221, 41, 134, 22);
		panel_1.add(textField0_1);
		this.setTitle("Déployer");
	}public void Ajeuter(String name) {
		
		textArea.append(name);;
	}
public void Ajeuter1(String name) {
		
		textArea_1.append(name);;
	}
}
