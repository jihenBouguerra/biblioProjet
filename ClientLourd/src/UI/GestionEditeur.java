package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import metier.entities.Auteur;
import metier.entities.Livre;
import metier.sessions.IBiblioRemote;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GestionEditeur extends JFrame {

	private JPanel contentPane;
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
	private DefaultTableModel modelEditeur;
	private JTable lLivre;
	private JTable lAuteur;
	private DefaultTableModel modelLivre;
	private JTextField nomEditeur;
	private JButton enregistrer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionEditeur frame = new GestionEditeur();
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
	public GestionEditeur() {
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
			GridBagLayout gbl_contentPane = new GridBagLayout();
			gbl_contentPane.columnWidths = new int[]{54, 173, 130, 53, 138, 39, 330, 0};
			gbl_contentPane.rowHeights = new int[]{31, 23, 34, 22, 44, 286, 23, 0};
			gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			contentPane.setLayout(gbl_contentPane);
									
												JLabel lblGestionAuteur = new JLabel("Gestion Editeur");
												lblGestionAuteur.setForeground(new Color(0, 128, 0));
												GridBagConstraints gbc_lblGestionAuteur = new GridBagConstraints();
												gbc_lblGestionAuteur.fill = GridBagConstraints.VERTICAL;
												gbc_lblGestionAuteur.insets = new Insets(0, 0, 5, 5);
												gbc_lblGestionAuteur.gridwidth = 3;
												gbc_lblGestionAuteur.gridx = 2;
												gbc_lblGestionAuteur.gridy = 1;
												contentPane.add(lblGestionAuteur, gbc_lblGestionAuteur);
						
									Label label_3 = new Label("Liste Editeurs");
									GridBagConstraints gbc_label_3 = new GridBagConstraints();
									gbc_label_3.anchor = GridBagConstraints.NORTH;
									gbc_label_3.fill = GridBagConstraints.HORIZONTAL;
									gbc_label_3.insets = new Insets(0, 0, 5, 5);
									gbc_label_3.gridx = 2;
									gbc_label_3.gridy = 3;
									contentPane.add(label_3, gbc_label_3);
									
												Label label = new Label("Liste Livres :");
												GridBagConstraints gbc_label = new GridBagConstraints();
												gbc_label.anchor = GridBagConstraints.NORTHWEST;
												gbc_label.insets = new Insets(0, 0, 5, 0);
												gbc_label.gridx = 6;
												gbc_label.gridy = 3;
												contentPane.add(label, gbc_label);
									
												Label label_1 = new Label("Nom:");
												GridBagConstraints gbc_label_1 = new GridBagConstraints();
												gbc_label_1.anchor = GridBagConstraints.SOUTH;
												gbc_label_1.fill = GridBagConstraints.HORIZONTAL;
												gbc_label_1.insets = new Insets(0, 0, 5, 5);
												gbc_label_1.gridx = 0;
												gbc_label_1.gridy = 4;
												contentPane.add(label_1, gbc_label_1);
						
									nomEditeur = new JTextField();
									GridBagConstraints gbc_nomEditeur = new GridBagConstraints();
									gbc_nomEditeur.anchor = GridBagConstraints.SOUTH;
									gbc_nomEditeur.fill = GridBagConstraints.HORIZONTAL;
									gbc_nomEditeur.insets = new Insets(0, 0, 5, 5);
									gbc_nomEditeur.gridx = 1;
									gbc_nomEditeur.gridy = 4;
									contentPane.add(nomEditeur, gbc_nomEditeur);
									nomEditeur.setColumns(10);
						
									JScrollPane scrollPane = new JScrollPane();
									GridBagConstraints gbc_scrollPane = new GridBagConstraints();
									gbc_scrollPane.fill = GridBagConstraints.BOTH;
									gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
									gbc_scrollPane.gridheight = 2;
									gbc_scrollPane.gridwidth = 3;
									gbc_scrollPane.gridx = 2;
									gbc_scrollPane.gridy = 4;
									contentPane.add(scrollPane, gbc_scrollPane);
									lAuteur = new JTable(modelEditeur);
									lAuteur.addMouseListener(new MouseAdapter() {
										@Override
										public void mouseClicked(MouseEvent e) {
											chargerLivresPerAuteur();
										}
									});
									
												scrollPane.setViewportView(lAuteur);
			
						JScrollPane scrollPane_1 = new JScrollPane();
						GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
						gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
						gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
						gbc_scrollPane_1.gridheight = 2;
						gbc_scrollPane_1.gridx = 6;
						gbc_scrollPane_1.gridy = 4;
						contentPane.add(scrollPane_1, gbc_scrollPane_1);
						
									lLivre = new JTable(modelLivre);
									scrollPane_1.setViewportView(lLivre);
			
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
						
									enregistrer = new JButton("Enregistrer");
									enregistrer.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {

											if (modif == false) {
												if (nomEditeur.getText().equals("") == false) {
													Auteur a = new Auteur(nomEditeur.getText());
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
													a.setNomAuteurs(nomEditeur.getText());
													if (valAvant.equals(nomEditeur.getText())) {
														modif = false;
														indexModif = -1;
														nomEditeur.setText("");
														JOptionPane.showMessageDialog(null, "enregistrement effectué avec succès");
													} else if (stub.existeAuteur(a) == false) {
														stub.updateAuteur(a);
														chargerAuteurs();
														modelLivre.getDataVector().removeAllElements();
														lLivre.repaint();
														modif = false;
														indexModif = -1;
														nomEditeur.setText("");
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
									GridBagConstraints gbc_enregistrer = new GridBagConstraints();
									gbc_enregistrer.anchor = GridBagConstraints.NORTH;
									gbc_enregistrer.fill = GridBagConstraints.HORIZONTAL;
									gbc_enregistrer.insets = new Insets(0, 0, 5, 5);
									gbc_enregistrer.gridx = 1;
									gbc_enregistrer.gridy = 5;
									contentPane.add(enregistrer, gbc_enregistrer);
						
									JButton modifier = new JButton("Modifier");
									modifier.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											indexModif = lAuteur.getSelectedRow();
											modif = true;
											if (indexModif > -1) {
												Auteur a = auteurs.get(indexModif);
												nomEditeur.setText(a.getNomAuteur());
												// stub.updateAuteur(a);
												// chargerAuteurs();
												modelLivre.getDataVector().removeAllElements();
												lLivre.repaint();
											}
										}
									});
									GridBagConstraints gbc_modifier = new GridBagConstraints();
									gbc_modifier.anchor = GridBagConstraints.NORTH;
									gbc_modifier.fill = GridBagConstraints.HORIZONTAL;
									gbc_modifier.insets = new Insets(0, 0, 0, 5);
									gbc_modifier.gridx = 2;
									gbc_modifier.gridy = 6;
									contentPane.add(modifier, gbc_modifier);
						GridBagConstraints gbc_supprimer = new GridBagConstraints();
						gbc_supprimer.anchor = GridBagConstraints.NORTH;
						gbc_supprimer.fill = GridBagConstraints.HORIZONTAL;
						gbc_supprimer.insets = new Insets(0, 0, 0, 5);
						gbc_supprimer.gridx = 4;
						gbc_supprimer.gridy = 6;
						contentPane.add(supprimer, gbc_supprimer);
			modelEditeur = new DefaultTableModel() {
				@Override
				public boolean isCellEditable(int row, int column) {
					// all cells false
					return false;
				}
			};
			modelEditeur.addColumn("Id");
			modelEditeur.addColumn("Nom");

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

			chargerAuteurs();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	public void chargerAuteurs() {

		auteurs = stub.consulterAuteurs();
		modelEditeur.getDataVector().removeAllElements();
		lAuteur.repaint();

		Object[] data = new Object[2];
		for (int i = 0; i < auteurs.size(); i++) {
			Auteur a = auteurs.get(i);
			data[0] = a.getIdAuteur();
			data[1] = a.getNomAuteur();
			modelEditeur.addRow(data);

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
