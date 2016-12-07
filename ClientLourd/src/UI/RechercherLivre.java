package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import metier.entities.Auteur;
import metier.entities.Editeur;
import metier.entities.Livre;
import metier.entities.Theme;
import metier.sessions.IBiblioRemote;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RechercherLivre extends JFrame {
	// remote
	private IBiblioRemote stub;
	// autres

	private List<Livre> livres;

	private JPanel contentPane;
	private DefaultTableModel modelLivre;
	private JTable lLivre;
	private JButton btnRechercher;
	private JTextField filter;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RechercherLivre frame = new RechercherLivre();
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
	public RechercherLivre() {
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

			modelLivre = new DefaultTableModel() {
				@Override
				public boolean isCellEditable(int row, int column) {
					// all cells false
					return false;
				}
			};

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(96, 93, 729, 384);
			contentPane.add(scrollPane);
			lLivre = new JTable(modelLivre);
			scrollPane.setViewportView(lLivre);

			btnRechercher = new JButton("Rechercher");

			btnRechercher.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String val = filter.getText();
					if (val.trim().equals("")) {
						JOptionPane.showMessageDialog(null, "remplir le champ");
					} else {
						chargerLivre(comboBox.getSelectedIndex());

					}

				}
			});

			btnRechercher.setBounds(734, 488, 89, 23);
			contentPane.add(btnRechercher);

			filter = new JTextField();
			filter.setBounds(96, 45, 279, 20);
			contentPane.add(filter);
			filter.setColumns(10);

			comboBox = new JComboBox();
			comboBox.setModel(
					new DefaultComboBoxModel(new String[] { "Total ", "Th\u00E9me", "Auteur", "Editeur", "Titre" }));
			comboBox.setBounds(669, 45, 156, 20);
			contentPane.add(comboBox);
			modelLivre.addColumn("Titre");
			modelLivre.addColumn("Auteur");
			modelLivre.addColumn("Editeur");
			modelLivre.addColumn("Théme");

			chargerLivre(comboBox.getSelectedIndex());
		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	public void chargerLivre(int index) {
		String fil = filter.getText().trim().toLowerCase();
		modelLivre.getDataVector().removeAllElements();
		lLivre.repaint();

		switch (index) {
		case 0:
			livres = stub.consulterLivres();
			break;
		case 1:
			Theme t = new Theme(fil);
			livres = stub.consulterLivres(t);
			break;
		case 2:
			Auteur a = new Auteur(fil);
			livres = stub.consulterLivres(a);
			break;
		case 3:
			Editeur e = new Editeur(fil);
			livres = stub.consulterLivres(e);
			break;
		case 4:
			livres = stub.consulterLivres(fil);
			break;

		}
		if (livres != null) {
			Object[] data = new Object[6];
			int i;
			for (i = 0; i < livres.size(); i++) {
				Livre l = livres.get(i);
				data[0] = l.getTitre();
				data[1] = l.getAuteur().getNomAuteur();
				data[2] = l.getEditeur().getNomEditeur();
				data[3] = l.getTheme().getTitre();

				modelLivre.addRow(data);

			}

		} else {
			JOptionPane.showMessageDialog(null, "Liste vide");
		}

	}

}
