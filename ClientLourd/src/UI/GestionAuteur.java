package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.hibernate.mapping.Set;

import metier.entities.Auteur;
import metier.entities.Editeur;
import metier.entities.Livre;
import metier.entities.Theme;
import metier.sessions.IBiblioRemote;

import java.awt.Button;
import java.awt.GridLayout;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Label;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GestionAuteur extends JFrame {

	private JPanel contentPane;
	private JTextField nomAuteur;
	private JTable lAuteur;
	private JButton enregistrer;
	// remote
	private IBiblioRemote stub;
	// autres
	// private List<Editeur> editeurs;
	// private List<Theme> themes;
	private List<Auteur> auteurs;
	private List<Livre> livresPerAuteur;
	private boolean modif;
	private int indexModif;

	// graphyc
	private DefaultTableModel modelAuteur;
	private JTable lLivre;
	private DefaultTableModel modelLivre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionAuteur frame = new GestionAuteur();
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
	public GestionAuteur() {

		try {
			// conection
			Properties p = new Properties();
			p.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			Context coxt = new InitialContext(p);
			stub = (IBiblioRemote) coxt.lookup("ejb:testSer/bibliothequeEJB/bibEJB!metier.sessions.IBiblioRemote");

			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 995, 576);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			JLabel lblGestionAuteur = new JLabel("Gestion Auteur");
			lblGestionAuteur.setForeground(new Color(0, 128, 0));
			lblGestionAuteur.setBounds(370, 31, 173, 23);
			contentPane.add(lblGestionAuteur);

			Label label = new Label("Liste Livres :");
			label.setBounds(621, 88, 87, 22);
			contentPane.add(label);

			Label label_1 = new Label("Nom:");
			label_1.setBounds(10, 137, 54, 15);
			contentPane.add(label_1);

			nomAuteur = new JTextField();
			nomAuteur.setBounds(78, 137, 173, 23);
			contentPane.add(nomAuteur);
			nomAuteur.setColumns(10);

			Label label_3 = new Label("Liste Auteurs");
			label_3.setBounds(261, 88, 62, 22);
			contentPane.add(label_3);

			enregistrer = new JButton("Enregistrer");
			enregistrer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (modif == false) {
						if (nomAuteur.getText().equals("") == false) {
							Auteur a = new Auteur(nomAuteur.getText());
							if (stub.existeAuteur(a) == false) {
								stub.addAuteur(a);
								chargerAuteurs();
								modelLivre.getDataVector().removeAllElements();
								lLivre.repaint();
							} else {
								JOptionPane.showMessageDialog(null, "cet auteur existe deja ");
							}
						} else {
							JOptionPane.showMessageDialog(null, "remplir les champs");
						}

					} else {
						if (indexModif != -1) {
							Auteur a = auteurs.get(indexModif);
							String valAvant = a.getNomAuteur();
							a.setNomAuteurs(nomAuteur.getText());
							if (valAvant.equals(nomAuteur.getText())) {
								modif = false;
								indexModif = -1;
								nomAuteur.setText("");
								JOptionPane.showMessageDialog(null, "enregistrement effectué avec succès");
							} else if (stub.existeAuteur(a) == false) {
								stub.updateAuteur(a);
								chargerAuteurs();
								modelLivre.getDataVector().removeAllElements();
								lLivre.repaint();
								modif = false;
								indexModif = -1;
								nomAuteur.setText("");
								JOptionPane.showMessageDialog(null, "enregistrement effectué avec succès");
							} else if (a.getNomAuteur().equals("")) {
								JOptionPane.showMessageDialog(null, "remplir les champs");
							} else {
								JOptionPane.showMessageDialog(null, "cet auteur existe deja ");
							}

						}

					}

				}
			});
			enregistrer.setBounds(121, 186, 130, 23);
			contentPane.add(enregistrer);

			JButton supprimer = new JButton("Supprimer");
			supprimer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int i = lAuteur.getSelectedRow();
					if (i > -1) {
						Auteur a = auteurs.get(i);
						stub.removeAuteur(a);
						chargerAuteurs();
						modelLivre.getDataVector().removeAllElements();
						lLivre.repaint();
					}

				}
			});

			supprimer.setBounds(444, 481, 138, 23);
			contentPane.add(supprimer);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(261, 116, 321, 354);
			contentPane.add(scrollPane);
			modelAuteur = new DefaultTableModel() {
				@Override
				public boolean isCellEditable(int row, int column) {
					// all cells false
					return false;
				}
			};
			lAuteur = new JTable(modelAuteur);
			lAuteur.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					chargerLivresPerAuteur();
				}
			});

			scrollPane.setViewportView(lAuteur);
			modelAuteur.addColumn("Id");
			modelAuteur.addColumn("Nom");

			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(621, 116, 330, 356);
			contentPane.add(scrollPane_1);

			modelLivre = new DefaultTableModel() {
				@Override
				public boolean isCellEditable(int row, int column) {
					// all cells false
					return false;
				}
			};
			modelLivre.addColumn("Titre");
			modelLivre.addColumn("Auteur");
			modelLivre.addColumn("Editeur");
			modelLivre.addColumn("Théme");

			lLivre = new JTable(modelLivre);
			scrollPane_1.setViewportView(lLivre);

			JButton modifier = new JButton("Modifier");
			modifier.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					indexModif = lAuteur.getSelectedRow();
					modif = true;
					if (indexModif > -1) {
						Auteur a = auteurs.get(indexModif);
						nomAuteur.setText(a.getNomAuteur());
						// stub.updateAuteur(a);
						// chargerAuteurs();
						modelLivre.getDataVector().removeAllElements();
						lLivre.repaint();
					}
				}
			});

			modifier.setBounds(261, 481, 130, 23);
			contentPane.add(modifier);

			chargerAuteurs();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	public void chargerAuteurs() {

		auteurs = stub.consulterAuteurs();
		modelAuteur.getDataVector().removeAllElements();
		lAuteur.repaint();

		Object[] data = new Object[2];
		for (int i = 0; i < auteurs.size(); i++) {
			Auteur a = auteurs.get(i);
			data[0] = a.getIdAuteur();
			data[1] = a.getNomAuteur();
			modelAuteur.addRow(data);

		}

	}

	public void chargerLivresPerAuteur() {
		livresPerAuteur = stub.consulterLivres(auteurs.get(lAuteur.getSelectedRow()));
		modelLivre.getDataVector().removeAllElements();
		lLivre.repaint();

		Object[] data = new Object[5];
		for (int j = 0; j < livresPerAuteur.size(); j++) {
			Livre l = livresPerAuteur.get(j);
			data[0] = l.getTitre();
			data[1] = l.getAuteur().getNomAuteur();
			data[2] = l.getEditeur().getNomEditeur();
			data[3] = l.getTheme().getTitre();
			modelLivre.addRow(data);

		}

	}
}
