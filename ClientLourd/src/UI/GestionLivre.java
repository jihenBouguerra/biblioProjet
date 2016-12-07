package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JInternalFrame;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.BoxLayout;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Label;

import com.toedter.calendar.JDayChooser;

import metier.entities.Auteur;
import metier.entities.Editeur;
import metier.entities.Livre;
import metier.entities.Theme;
import metier.entities.testee;
import metier.sessions.IBiblioRemote;

import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import com.toedter.components.JLocaleChooser;
import com.toedter.components.JSpinField;
import javax.swing.JScrollPane;
import java.awt.Button;
import javax.swing.JScrollBar;

public class GestionLivre extends JFrame {

	// remote
	private IBiblioRemote stub;
	// autres
	private List<Editeur> editeurs;
	private List<Theme> themes;
	private List<Auteur> auteurs;
	private List<Livre> livres;
	private Livre livre;
	private int pos;
	// graphyc
	private DefaultTableModel model;
	private JComboBox<String> lTheme;
	private JComboBox<String> lEditeur;
	private JComboBox <String>lAuteur;
	private JPanel contentPane;
	private JTextField titre;
	private JButton enregister;
	private JTable table;
	private JTable lLivre;
	private JDateChooser dateApparition;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionLivre frame = new GestionLivre();
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
	public GestionLivre() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 919, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Label label = new Label("Titre:");
		label.setBounds(35, 115, 62, 22);
		contentPane.add(label);

		Label label_1 = new Label("Date Apparition :");
		label_1.setBounds(35, 156, 89, 22);
		contentPane.add(label_1);

		Label label_2 = new Label("Th\u00E9me:");
		label_2.setBounds(35, 197, 89, 22);
		contentPane.add(label_2);

		Label label_3 = new Label("Auteur:");
		label_3.setBounds(35, 236, 89, 22);
		contentPane.add(label_3);

		Label label_4 = new Label("Editeur:");
		label_4.setBounds(35, 280, 89, 22);
		contentPane.add(label_4);

		dateApparition = new JDateChooser();
		dateApparition.setBounds(152, 158, 87, 20);
		contentPane.add(dateApparition);

		titre = new JTextField();
		titre.setBounds(152, 113, 86, 20);
		contentPane.add(titre);
		titre.setColumns(10);

		lTheme = new JComboBox();
		lTheme.setBounds(152, 196, 87, 20);
		contentPane.add(lTheme);

		lAuteur = new JComboBox();
		lAuteur.setBounds(152, 238, 87, 20);
		contentPane.add(lAuteur);

		lEditeur = new JComboBox();
		lEditeur.setBounds(152, 282, 87, 20);
		contentPane.add(lEditeur);

		enregister = new JButton("Enregister");
		enregister.setBounds(150, 373, 89, 23);
		contentPane.add(enregister);
		livre = new Livre();
		JLabel lblGestionLivre = new JLabel("Gestion Livre ");
		lblGestionLivre.setForeground(new Color(0, 128, 0));
		lblGestionLivre.setBounds(424, 26, 173, 23);
		contentPane.add(lblGestionLivre);
		model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(283, 100, 610, 361);
		contentPane.add(scrollPane);
		lLivre = new JTable(model);
		scrollPane.setViewportView(lLivre);
		model.addColumn("Titre");
		model.addColumn("Auteur");
		model.addColumn("Editeur");
		model.addColumn("Théme");

		JButton supprimer = new JButton("Supprimer");

		supprimer.setBounds(533, 477, 89, 23);
		contentPane.add(supprimer);

		JButton modifier = new JButton("Modifier");

		modifier.setBounds(667, 477, 89, 23);
		contentPane.add(modifier);

		

		try {

			Properties p = new Properties();
			p.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			Context ct = new InitialContext(p);
			stub = (IBiblioRemote) ct.lookup("ejb:testSer/bibliothequeEJB/bibEJB!metier.sessions.IBiblioRemote");
			chargerEditeur();
			chargerTheme();
			chargerAuteur();
			chargerLivreTotal();
			ActionListener eAjouter = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					livre.setTitre(titre.getText());
					livre.setDateApparition(dateApparition.getDate());
					livre.setEditeur(editeurs.get(lEditeur.getSelectedIndex()));
					livre.setTheme(themes.get(lTheme.getSelectedIndex()));
					livre.setAuteur(auteurs.get(lAuteur.getSelectedIndex()));
					livre.setPrix(5);
					String[] data = new String[4];
					data[0] = livre.getTitre();
					data[1] = livre.getAuteur().getNomAuteur();
					data[2] = livre.getEditeur().getNomEditeur();
					data[3] = livre.getTheme().getTitre();

					if (livre.getIdLivre() == 0) {
						stub.addLivre(livre);
						titre.setText("");
						lTheme.setSelectedIndex(0);
						lAuteur.setSelectedIndex(0);
						lEditeur.setSelectedIndex(0);

						model.addRow(data);
					}

					else {
						stub.updateLivre(livre);
						for (int i = 0; i < 4; i++)
							model.setValueAt(data[i], pos, i);
						pos = 0;

					}

				}

			};

			ActionListener eModifier = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					pos = lLivre.getSelectedRow();
					if (pos != -1) {
						livre = livres.get(pos);
						titre.setText(livre.getTitre());
						lAuteur.setSelectedItem(livre.getAuteur().getNomAuteur());
						lTheme.setSelectedItem(livre.getTheme().getTitre());
					}

				}

			};

			ActionListener eSupprimer = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int sI = lLivre.getSelectedRow();
					if (sI != -1) {
						model.removeRow(sI);
						System.out.println(sI + "      " + livres.size());
						stub.removeLivre(livres.get(sI));
						livres.remove(sI);
					}

				}

			};

			enregister.addActionListener(eAjouter);
			modifier.addActionListener(eModifier);
			supprimer.addActionListener(eSupprimer);
			
			

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	public void chargerLivreTotal() {
		livres = stub.consulterLivres();
		Object[] data = new Object[6];
		int i;
		for (i = 0; i < livres.size(); i++) {
			Livre l = livres.get(i);
			data[0] = l.getTitre();
			data[1] = l.getAuteur().getNomAuteur();
			data[2] = l.getEditeur().getNomEditeur();
			data[3] = l.getTheme().getTitre();

			model.addRow(data);

		}

	}

	public void chargerEditeur() {
		editeurs = stub.consulterEditeurs();
		for (int i = 0; i < editeurs.size(); i++) {
			lEditeur.addItem(editeurs.get(i).getNomEditeur());

		}

	}

	public void chargerAuteur() {
		auteurs = stub.consulterAuteurs();
		for (int i = 0; i < auteurs.size(); i++) {
			lAuteur.addItem(auteurs.get(i).getNomAuteur());

		}

	}

	public void chargerTheme() {
		themes = stub.consulterThemes();
		for (int i = 0; i < themes.size(); i++) {
			lTheme.addItem(themes.get(i).getTitre());

		}
	}
}
