package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Label;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import metier.entities.Auteur;
import metier.entities.Editeur;
import metier.entities.Livre;
import metier.entities.Theme;
import metier.sessions.IBiblioRemote;
import java.awt.Button;
import javax.swing.JButton;

public class GestionTheme extends JFrame {

	private JPanel contentPane;
	private JTable lTheme;
	private JTextField nomAuteur;
	private JTextField descriptionAuteur;
	// autres
	private List<Editeur> editeurs;
	private List<Theme> themes;
	private List<Auteur> auteurs;
	private List<Livre> livres;
	// remote
	private IBiblioRemote stub;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionTheme frame = new GestionTheme();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void chargerLivre() {
		livres = stub.consulterLivres();
		for (int i = 0; i < livres.size(); i++) {

		}
	}

	/**
	 * Create the frame.
	 */
	public GestionTheme() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblGestionAuteur = new JLabel("Gestion Auteur");
		lblGestionAuteur.setForeground(new Color(0, 128, 0));
		lblGestionAuteur.setBounds(290, 21, 173, 23);
		contentPane.add(lblGestionAuteur);

		JList lLivre = new JList();
		lLivre.setBounds(521, 105, 135, 244);
		contentPane.add(lLivre);

		Label label = new Label("Liste Livres :");
		label.setBounds(521, 78, 98, 15);
		contentPane.add(label);

		Label label_1 = new Label("Nom:");
		label_1.setBounds(10, 102, 54, 15);
		contentPane.add(label_1);

		Label label_2 = new Label("Description:");
		label_2.setBounds(10, 146, 60, 22);
		contentPane.add(label_2);

		Label label_3 = new Label("Liste Th\u00E9mes");
		label_3.setBounds(249, 78, 98, 22);
		contentPane.add(label_3);

		lTheme = new JTable();
		lTheme.setBounds(249, 106, 214, 243);
		contentPane.add(lTheme);

		nomAuteur = new JTextField();
		nomAuteur.setBounds(83, 100, 135, 20);
		contentPane.add(nomAuteur);
		nomAuteur.setColumns(10);

		descriptionAuteur = new JTextField();
		descriptionAuteur.setColumns(10);
		descriptionAuteur.setBounds(83, 146, 135, 20);
		contentPane.add(descriptionAuteur);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBounds(367, 369, 89, 23);
		contentPane.add(btnSupprimer);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(129, 213, 89, 23);
		contentPane.add(btnAjouter);
	}

}
