package tekstieditori;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JEditorPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tekstieditori extends JFrame {

	private JPanel contentPane;
	static JEditorPane editorPane = new JEditorPane();

	// Ohjelman k‰ynnistys
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tekstieditori frame = new Tekstieditori();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
				} catch (Exception e) {
					System.out.println("Ohjelman k‰ynnistymisess‰ tapahtui virhe!");
					e.printStackTrace();
				}
			}
		});
	}

	// Luodaan muistion ikkuna
	public Tekstieditori() {
		// Lis‰t‰‰n perus elementtej‰
		setTitle("Juhan tekstieditori");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnTiedosto = new JMenu("Tiedosto");
		menuBar.add(mnTiedosto);
		// Lis‰t‰‰n avaa painikkeelle kuuntelija, ja metodi avaa();
		JMenuItem mntmAvaa = new JMenuItem("Avaa");
		mntmAvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tekstieditoriMetodit.avaa();

			}
		});
		mntmAvaa.setIcon(
				new ImageIcon(Tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		mntmAvaa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnTiedosto.add(mntmAvaa);
		// Tallenna napille kuuntelija ja metodi tallenna();
		JMenuItem mntmTallenna = new JMenuItem("Tallenna");
		mntmTallenna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				tekstieditoriMetodit.tallenna();

			}
		});
		mntmTallenna.setIcon(
				new ImageIcon(Tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		mntmTallenna.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnTiedosto.add(mntmTallenna);
		// Sulje nappi ja sille ohjelman sulkemis metodi
		JMenuItem mntmSulje = new JMenuItem("Sulje");
		mntmSulje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tekstieditoriMetodit.sulje();
			}
		});
		mntmSulje.setIcon(
				new ImageIcon(Tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		mntmSulje.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		mnTiedosto.add(mntmSulje);

		JMenu mnMuokkaa = new JMenu("Muokkaa");
		menuBar.add(mnMuokkaa);
		// Etsi napille kuuntelija ja metodi etsi();
		JMenuItem mntmEtsi = new JMenuItem("Etsi");
		mntmEtsi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tekstieditoriMetodit.etsi();

			}

		});
		mntmEtsi.setIcon(new ImageIcon(
				Tekstieditori.class.getResource("/com/sun/javafx/scene/web/skin/UnorderedListBullets_16x16_JFX.png")));
		mntmEtsi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		mnMuokkaa.add(mntmEtsi);
		// Korvaa napille kuuntelija ja metodi korvaa();
		JMenuItem mntmKorvaa = new JMenuItem("Korvaa");
		mntmKorvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tekstieditoriMetodit.korvaa();
			}
		});
		mntmKorvaa.setIcon(
				new ImageIcon(Tekstieditori.class.getResource("/com/sun/javafx/scene/web/skin/Copy_16x16_JFX.png")));
		mntmKorvaa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		mnMuokkaa.add(mntmKorvaa);
		// Tietoja nappi ja metodi tietoja();
		JMenu mnTietoja = new JMenu("Tietoja");
		menuBar.add(mnTietoja);

		JMenuItem mntmTietojaOhjelmasta = new JMenuItem("Tietoja ohjelmasta");
		mntmTietojaOhjelmasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				tekstieditoriMetodit.tietoja();

			}
		});

		mntmTietojaOhjelmasta.setIcon(new ImageIcon(
				Tekstieditori.class.getResource("/com/sun/javafx/scene/control/skin/caspian/dialog-information.png")));
		mntmTietojaOhjelmasta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		mnTietoja.add(mntmTietojaOhjelmasta);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		contentPane.add(editorPane, BorderLayout.CENTER);
		// toolbar ja sen pikakuvakkeet.
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		// toolbar eka nappi sama toiminto kuin avaa napilla
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tekstieditoriMetodit.avaa();
			}
		});
		button.setIcon(new ImageIcon(Tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		toolBar.add(button);
		// toolbar toinen nappi sama toiminto kuin tallenna napilla
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				tekstieditoriMetodit.tallenna();
			}
		});
		button_1.setIcon(
				new ImageIcon(Tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		toolBar.add(button_1);
		// toolbar kolmas nappi sama toiminto kuin korvaa napilla
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				tekstieditoriMetodit.korvaa();

			}
		});
		button_2.setIcon(
				new ImageIcon(Tekstieditori.class.getResource("/com/sun/javafx/scene/web/skin/Copy_16x16_JFX.png")));
		toolBar.add(button_2);

	}
}
