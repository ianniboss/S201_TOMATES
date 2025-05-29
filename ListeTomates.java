package ihm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import modèle.*;

public class ListeTomates {
	private JFrame frame;
	private JList<String> listTomates;
	private JComboBox<String> comboBoxType;
	private JComboBox<String> comboBoxCouleur;
	private DefaultListModel<String> listModel;
	private List<Tomate> toutesLesTomates;
	private JButton btnPanier;

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
	}

	public ListeTomates() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Ô'Tomates");
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		// Header Panel
		JPanel headerPanel = new JPanel(new BorderLayout());
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

		// List Panel
		listModel = new DefaultListModel<>();
		listTomates = new JList<>(listModel);
		JScrollPane scrollPane = new JScrollPane(listTomates);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		// Filter Panel
		JPanel filterPanel = new JPanel();
		filterPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 128, 0)), "Filtres", 0, 0, new Font("Arial", Font.BOLD, 12), new Color(0, 128, 0)));
		filterPanel.setLayout(new GridLayout(1, 2));

		
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
			public void actionPerformed(ActionEvent e) {
				filtrerTomates();
			}
		});

		comboBoxCouleur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrerTomates();
			}
		});

		// Left (type) filter group
		ImageIcon tomatoIcon = new ImageIcon("D:\\ian\\SEM2\\S201\\programmation_SAE_S2-01_2025_GX_Y\\src\\main\\resources\\images\\tomatohat.jpg");
		Image tomatoImage = tomatoIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		JLabel lblTomatoIcon = new JLabel(new ImageIcon(tomatoImage));
		
		JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		typePanel.add(lblTomatoIcon);
		typePanel.add(comboBoxType);
		
		// Right (color) filter group
		ImageIcon colorIcon = new ImageIcon("D:\\ian\\SEM2\\S201\\programmation_SAE_S2-01_2025_GX_Y\\src\\main\\resources\\images\\color-wheel-icon.png");
		Image colorImage = colorIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		JLabel lblColorIcon = new JLabel(new ImageIcon(colorImage));

		JPanel colorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		colorPanel.add(lblColorIcon);
		colorPanel.add(comboBoxCouleur);

		filterPanel.add(typePanel);
		filterPanel.add(colorPanel);

		// Panel that contains filterPanel and decorative button
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(filterPanel, BorderLayout.CENTER);

		ImageIcon decoIcon = new ImageIcon("D:\\ian\\SEM2\\S201\\programmation_SAE_S2-01_2025_GX_Y\\src\\main\\resources\\images\\planicon.png");
		Image decoImage = decoIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		JButton btnDeco = new JButton(new ImageIcon(decoImage));
		btnDeco.setBorderPainted(false);
		btnDeco.setContentAreaFilled(false);
		bottomPanel.add(btnDeco, BorderLayout.EAST);

		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);


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
			boolean correspondType = typeSelectionne.equals("Toutes les tomates") || t.getType().getDénomination().equals(typeSelectionne);
			boolean correspondCouleur = couleurSelectionnee.equals("Toutes les couleurs") || t.getCouleur().getDénomination().equals(couleurSelectionnee);
			if (correspondType && correspondCouleur) {
				listModel.addElement(t.getDésignation());
			}
		}
	}
}
