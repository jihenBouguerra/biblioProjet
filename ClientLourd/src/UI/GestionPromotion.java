package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import metier.entities.Auteur;
import metier.entities.Livre;
import metier.entities.Promotion;
import metier.sessions.IBiblioRemote;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JList;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GestionPromotion extends JFrame {

	private JPanel contentPane;
	private JTable lAuteur;
	private JButton enregistrer;
	// remote
	private IBiblioRemote stub;
	// autres

	private List<Livre> livres;
	private List<Promotion> promotions;
	private List<Livre> livrePerPromotion;

	private Promotion promotion;
	// graphique
	private JTable lPromotion;
	private JTable lLivre;
	private DefaultTableModel modelLivre;
	private DefaultTableModel modelLivrePP;
	private DefaultTableModel modelPromotion;
	private JTable lLivrePP;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionPromotion frame = new GestionPromotion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws NamingException
	 */
	public GestionPromotion() throws NamingException {
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

			JLabel lblGestionPromotions = new JLabel("Gestion Promotions ");
			lblGestionPromotions.setForeground(new Color(0, 128, 0));
			lblGestionPromotions.setBounds(445, 35, 159, 14);
			contentPane.add(lblGestionPromotions);

			JLabel lblNewLabel = new JLabel("Liste promotion");
			lblNewLabel.setBounds(392, 88, 110, 14);
			contentPane.add(lblNewLabel);

			JScrollPane scrollPane = new JScrollPane();

			scrollPane.setBounds(387, 113, 282, 315);
			contentPane.add(scrollPane);

			modelPromotion = new DefaultTableModel() {
				@Override
				public boolean isCellEditable(int row, int column) {
					// all cells false
					return false;
				}
			};
			modelPromotion.addColumn("ID");
			modelPromotion.addColumn("Date Debut");
			modelPromotion.addColumn("Date Fin");
			modelPromotion.addColumn("Valeur");

			lPromotion = new JTable(modelPromotion);
			lPromotion.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

				}
			});
			lPromotion.setBounds(295, 158, 206, 246);
			scrollPane.setViewportView(lPromotion);

			JLabel lblD = new JLabel("Valeur");
			lblD.setBounds(22, 414, 69, 14);
			contentPane.add(lblD);

			JLabel lblDateFin = new JLabel("Date fin :");
			lblDateFin.setBounds(22, 153, 81, 14);
			contentPane.add(lblDateFin);

			JDateChooser dateChooser = new JDateChooser();
			dateChooser.setBounds(113, 153, 232, 20);
			contentPane.add(dateChooser);

			JDateChooser dateChooser_1 = new JDateChooser();
			dateChooser_1.setBounds(113, 109, 232, 20);
			contentPane.add(dateChooser_1);

			JScrollPane sp1 = new JScrollPane();
			sp1.setBounds(22, 218, 322, 178);
			contentPane.add(sp1);
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
			sp1.setColumnHeaderView(lLivre);

			JLabel lblListeLivre = new JLabel("Liste livre");
			lblListeLivre.setBounds(22, 192, 69, 14);

			contentPane.add(lblListeLivre);

			JButton ajouter = new JButton("Ajouter");
			ajouter.setBounds(256, 450, 89, 23);
			contentPane.add(ajouter);

			JLabel label = new JLabel("Date debut :");
			label.setBounds(22, 109, 81, 14);
			contentPane.add(label);

			JSpinField spinField = new JSpinField();
			spinField.setValue(10);
			spinField.setMinimum(10);
			spinField.setMaximum(80);
			spinField.setBounds(113, 408, 232, 20);
			contentPane.add(spinField);

			JLabel lblListePromotion = new JLabel("Liste Livre");
			lblListePromotion.setBounds(702, 88, 110, 14);
			contentPane.add(lblListePromotion);

			JScrollPane scrollPane_2 = new JScrollPane();
			scrollPane_2.setBounds(699, 113, 209, 313);
			contentPane.add(scrollPane_2);
			modelLivrePP = new DefaultTableModel() {
				@Override
				public boolean isCellEditable(int row, int column) {
					chargerLivrePerPromotion();
					return false;
				}
			};
			modelLivrePP.addColumn("Titre");
			modelLivrePP.addColumn("Auteur");
			modelLivrePP.addColumn("Editeur");
			modelLivrePP.addColumn("Théme");
			lLivrePP = new JTable(modelLivrePP);
			scrollPane_2.setColumnHeaderView(lLivrePP);

			chargerLivreTotal();
			chargerPromotions();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	public void chargerPromotions() {
		promotions = stub.consulterPromotions();
		Object[] data = new Object[4];

		for (int i = 0; i < promotions.size(); i++) {
			Promotion l = promotions.get(i);
			data[0] = l.getIdPromotion();
			data[1] = l.getDateDebut();
			data[2] = l.getDateFin();
			data[3] = l.getVal();
			modelPromotion.addRow(data);

		}
	}

	public void chargerLivreTotal() {
		livres = stub.consulterLivres();
		Object[] data = new Object[4];

		for (int i = 0; i < livres.size(); i++) {
			Livre l = livres.get(i);
			data[0] = l.getTitre();
			data[1] = l.getAuteur().getNomAuteur();
			data[2] = l.getEditeur().getNomEditeur();
			data[3] = l.getTheme().getTitre();

			modelLivre.addRow(data);

		}

	}

	public void chargerLivrePerPromotion() {
		promotion = promotions.get(lPromotion.getSelectedRow());
		livrePerPromotion = (List<Livre>) promotion.getLivres();

		Object[] data = new Object[4];

		for (int i = 0; i < livrePerPromotion.size(); i++) {
			Livre l = livrePerPromotion.get(i);
			data[0] = l.getTitre();
			data[1] = l.getAuteur().getNomAuteur();
			data[2] = l.getEditeur().getNomEditeur();
			data[3] = l.getTheme().getTitre();

			modelLivrePP.addRow(data);

		}

	}
}
