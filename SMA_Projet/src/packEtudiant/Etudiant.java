package packEtudiant;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import jade.tools.introspector.gui.MessageTableModel;
import packPrincipale.DeployerAgent;

public class Etudiant extends GuiAgent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//oncréer l'objet gui de l'interface EtudiantGui
	private EtudiantGui gui;
	

	@Override
	protected void setup() {
		//on instancie l' objet ddans la classe EtudiantGui
		gui=new  EtudiantGui();
			gui.setCAgent(this);
			//on donne le nom à notre interface

			gui.setTitle(this.getLocalName());
			
	//ParallelBehaviour parallelBehaviour=new ParallelBehaviour();

	

 addBehaviour(new CyclicBehaviour() {
		@Override
		public void action() {

			
       // MessageTemplate msgT=MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM), MessageTemplate.MatchOntology("vente"));
			//la réception de message
			ACLMessage msg=receive();

			if(msg!=null) {
				//la récupératio du contenu de message
				String contenu=msg.getContent();
				//cette partie pour   ajouter les contenu ,les déstinatiares ,les récepteurs  du messages
				Connection conn = null;
				Statement stmt = null;
				String destinataire=msg.getSender().getLocalName();
				String expediteur=myAgent.getLocalName();
				try {
				    try {
				       Class.forName("com.mysql.jdbc.Driver");
				    } catch (Exception e) {
				       System.out.println(e);
				 }
				 conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sma_database", "root", "");
				 System.out.println("Connection is created successfully:");
				 
				 PreparedStatement pst1 = (PreparedStatement) conn.prepareStatement("select max(id)+1 from Table_Professeur_Etudiant ");
		            ResultSet rs = pst1.executeQuery();
		            String user_id ="" ;
		            while(rs.next())
		            {
		                user_id = rs.getString(1);
		            }
		            java.util.Date date=new java.util.Date();
					
					java.sql.Date sqlDate=new java.sql.Date(date.getTime());
					java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());
				
				 String sql = "insert into Table_Professeur_Etudiant (id,expediteur,destinataire,conntenu,date,time) values (?,?,?,?,?,?)";
				 try (PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql)) {
					 statement.setString(1, user_id.toString());

					 statement.setString(2, destinataire);
					    statement.setString(3, expediteur);
					    statement.setString(4, contenu);
					    statement.setDate(5, sqlDate);
					    statement.setTimestamp(6, sqlTime);

					    statement.executeUpdate();
					}
		           


				 System.out.println("Record is inserted in the table successfully..................");
				 } catch (SQLException excep) {
				    excep.printStackTrace();
				 } catch (Exception excep) {
				    excep.printStackTrace();
				 } finally {
				    try {
				       if (stmt != null)
				          conn.close();
				    } catch (SQLException se) {}
				    try {
				       if (conn != null)
				          conn.close();
				    } catch (SQLException se) {
				       se.printStackTrace();
				    }  
				 }
				 
				 //on va afficher le nom local de l' gent qu' aenvoyer le message
				gui.ShowMessage("Sender:\t"+msg.getSender().getLocalName());
				//l' affichage de contenu de message
				gui.ShowMessage("le contenu du message est:\t"+msg.getContent());
			


			}else block();
			}
	});
 
	}
	@Override
	protected void onGuiEvent(GuiEvent ev) {
		switch (ev.getType()) {
		case 1:
			//on va créer une Map  pour récupérer les paramétres
			Map<String ,Object> params=(Map<String,Object>)ev.getParameter(0);
			String contenu=(String)params.get("contenu");
			String agentEtudiant=(String)params.get("agentEtudiant");
			ACLMessage msg1=new ACLMessage(ACLMessage.REQUEST);
			msg1.addReceiver(new AID(agentEtudiant,AID.ISLOCALNAME));
			msg1.setContent(contenu);
			send(msg1);
			break;
		default	:
			break;
		}
		
	}
//	@Override
//	protected void takeDown() {
//	try {
//		DFService.deregister(this);
//	} catch (FIPAException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	}

}
