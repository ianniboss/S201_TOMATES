package ihm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import modèle.*;

public class ListeTomates {
	private JFrame frame;
	private JList<String> listTomates;
	private JComboBox<String> comboBoxType;
	private JComboBox<String> comboBoxCouleur;
	private DefaultListModel<String> listModel;
	private List<Tomate> toutesLesTomates;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListeTomates window = new ListeTomates();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	};
	


	/**
	 * Create the application.
	 */
	public ListeTomates() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Ô'Tomates");
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(new BorderLayout());
		headerPanel.setBackground(Color.WHITE);
		frame.getContentPane().add(headerPanel, BorderLayout.NORTH);

		ImageIcon titleIcon = new ImageIcon("D:\\ian\\SEM2\\S201\\programmation_SAE_S2-01_2025_GX_Y\\src\\main\\resources\\images\\tomatoicon.png");
		Image scaledTitleImage = titleIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		JLabel lblTitle = new JLabel("Nos graines de tomates", new ImageIcon(scaledTitleImage), JLabel.CENTER);
		lblTitle.setHorizontalTextPosition(SwingConstants.LEFT);
		lblTitle.setVerticalTextPosition(SwingConstants.CENTER);		
		lblTitle.setFont(new Font("Poor Richard", Font.BOLD, 20));
		lblTitle.setForeground(new Color(0, 128, 0));
		headerPanel.add(lblTitle, BorderLayout.CENTER);

		ImageIcon icon = new ImageIcon("D:\\ian\\SEM2\\S201\\programmation_SAE_S2-01_2025_GX_Y\\src\\main\\resources\\images\\pngtree-shopping-cart-convenient-icon-image_1287807.jpg"); 
		Image image = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
		btnPanier = new JButton("0,00 €", new ImageIcon(image));
		btnPanier.setFont(new Font("Arial", Font.PLAIN, 14));
		headerPanel.add(btnPanier, BorderLayout.EAST);

		listModel = new DefaultListModel<>();
		listTomates = new JList<>(listModel);
		JScrollPane scrollPane = new JScrollPane(listTomates);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		JPanel filterPanel = new JPanel();
		filterPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GREEN), "Filtres", 0, 0, new Font("Arial", Font.BOLD, 12), Color.GREEN));
		filterPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		comboBoxType = new JComboBox<>();
		comboBoxType.addItem("Toutes les tomates");
		for (TypeTomate type : TypeTomate.values()) {
			comboBoxType.addItem(type.getDénomination());
		}

		comboBoxCouleur = new JComboBox<>();
		comboBoxCouleur.addItem("Toutes les couleurs");
		for (Couleur couleur : Couleur.values()) {
			comboBoxCouleur.addItem(couleur.getDénomination());
		}

		comboBoxType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				filtrerTomates();
			}
		});

		comboBoxCouleur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				filtrerTomates();
			}
		});

		ImageIcon tomatoIcon = new ImageIcon("D:\\ian\\SEM2\\S201\\programmation_SAE_S2-01_2025_GX_Y\\src\\main\\resources\\images\\tomatohat.jpg");
		Image tomatoImage = tomatoIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
		JLabel lblTomatoIcon = new JLabel(new ImageIcon(tomatoImage));
		filterPanel.add(lblTomatoIcon);
		filterPanel.add(comboBoxType);
		
		ImageIcon colorIcon = new ImageIcon("D:\\ian\\SEM2\\S201\\programmation_SAE_S2-01_2025_GX_Y\\src\\main\\resources\\images\\color-wheel-icon.png");
		Image colorImage = colorIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
		JLabel lblColorIcon = new JLabel(new ImageIcon(colorImage));
		filterPanel.add(lblColorIcon);
		filterPanel.add(comboBoxCouleur);
		frame.getContentPane().add(filterPanel, BorderLayout.SOUTH);

		loadTomates();
		filtrerTomates();
	}

	private void loadTomates() {
		Tomates base = OutilsBaseDonneesTomates.générationBaseDeTomates("src/main/resources/data/tomates.json");
		toutesLesTomates = base.getTomates();
	}

	private void filtrerTomates() {
		String typeSelectionne = (String) comboBoxType.getSelectedItem();
		String couleurSelectionnee = (String) comboBoxCouleur.getSelectedItem();

		listModel.clear();

		for (int i = 0; i < toutesLesTomates.size(); i++) {
			Tomate t = toutesLesTomates.get(i);

			boolean correspondType = typeSelectionne.equals("Toutes les tomates") ||
				t.getType().getDénomination().equals(typeSelectionne);

			boolean correspondCouleur = couleurSelectionnee.equals("Toutes les couleurs") ||
				t.getCouleur().getDénomination().equals(couleurSelectionnee);

			if (correspondType && correspondCouleur) {
				listModel.addElement(t.getDésignation());
			}
		}
	}
	private JButton btnPanier;
}
