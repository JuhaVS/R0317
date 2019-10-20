import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.event.InputEvent;

import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tekstieditori extends JFrame {

	private JPanel contentPane;
	JEditorPane editorPane = new JEditorPane();

	
	// Ohjelman käynnistys
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tekstieditori frame = new Tekstieditori();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
				} catch (Exception e) {
					System.out.println("Ohjelman käynnistymisessä tapahtui virhe!");
					e.printStackTrace();
				}
			}
		});
	}

	// Luodaan muistion ikkuna
	public Tekstieditori() {
		//Lisätään perus elementtejä
		setTitle("Juhan tekstieditori");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnTiedosto = new JMenu("Tiedosto");
		menuBar.add(mnTiedosto);
		// Lisätään avaa painikkeelle kuuntelija, jonka avulla voi valita mikä tiedosto avataan
		JMenuItem mntmAvaa = new JMenuItem("Avaa");
		mntmAvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JFileChooser valintaikkuna = new JFileChooser();
					valintaikkuna.showOpenDialog(null);
					String rivi = "";
					String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
					Scanner lukija = null;
					File tiedosto = new File(uusiTiedosto);
					try {

						lukija = new Scanner(tiedosto);

						while (lukija.hasNextLine()) {
							rivi += lukija.nextLine() + "\n";

						}

					} catch (Exception e) {

						System.out.println("Tiedoston lukemisessa tapahtui virhe!");
						e.printStackTrace();
					}
					editorPane.setText(rivi);
				} catch (Exception e) {

					System.out.println("Tiedoston avaamisessa tapahtui virhe!");
					e.printStackTrace();
				}
			}
		});
		mntmAvaa.setIcon(
				new ImageIcon(Tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		mntmAvaa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnTiedosto.add(mntmAvaa);
		//Tallenna napille kuuntelija ja tallennus toiminto
		JMenuItem mntmTallenna = new JMenuItem("Tallenna");
		mntmTallenna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser valintaikkuna = new JFileChooser();
				valintaikkuna.showSaveDialog(null);

				String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
				System.out.println("Kirjoitettava tiedosto: " + uusiTiedosto);

				try {

					PrintWriter writer = new PrintWriter(uusiTiedosto);
					String sisalto = editorPane.getText();

					writer.println(sisalto);

					writer.flush();
					writer.close();

				} catch (Exception e) {

					System.out.println("Tiedoston tallennuksessa tapahtui virhe!");
					e.printStackTrace();
				}

			}
		});
		mntmTallenna.setIcon(
				new ImageIcon(Tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		mntmTallenna.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnTiedosto.add(mntmTallenna);
		// Sulje nappi ja sille ohjelman sulkemis toiminto
		JMenuItem mntmSulje = new JMenuItem("Sulje");
		mntmSulje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mntmSulje.setIcon(
				new ImageIcon(Tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		mntmSulje.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		mnTiedosto.add(mntmSulje);

		JMenu mnMuokkaa = new JMenu("Muokkaa");
		menuBar.add(mnMuokkaa);
		// Etsi napille kuuntelija, jota painettaessa luodaan uusi ikkuna jolla voidaan etsiä sanaa. Helpompi varmaankin showInputDialogilla mutta olin jo tehnyt tällä tavalla 
		JMenuItem mntmEtsi = new JMenuItem("Etsi");
		mntmEtsi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					JFrame thirdFrame = new JFrame("Third Frame");

					GridLayout sijoittelija = new GridLayout(2, 2);
					thirdFrame.getContentPane().setLayout(sijoittelija);
					thirdFrame.setBounds(0, 0, 250, 80);
					thirdFrame.setLocationRelativeTo(null);
					thirdFrame.setVisible(true);
					thirdFrame.setTitle("Sanan etsintä");
					thirdFrame.setResizable(false);
					thirdFrame.setAlwaysOnTop(true);

					JLabel etsittävä = new JLabel("Mitä sanaa etsitään");
					thirdFrame.getContentPane().add(etsittävä);

					JTextField etsittävä1 = new JTextField();
					thirdFrame.getContentPane().add(etsittävä1);
					// OK nappi jolla itse etsiminen tapahtuu ja sille kuuntelija
					JButton ok = new JButton("Ok");
					ok.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							try {
								String sisalto = editorPane.getText();
								sisalto = sisalto.toLowerCase();

								String haettava = etsittävä1.getText();
								Scanner lukija = new Scanner(editorPane.getText());
								int indeksi;

								while (lukija.hasNextLine()) {
									String rivi = lukija.nextLine();
									//Sanan värjäys. setSelection ei toimi kunnolla while sisällä. Mikäli tekstissä on useampi etsittävä sana niin se sekoaa
									if (rivi.indexOf(haettava) > -1) {
										indeksi = rivi.indexOf(haettava);
										System.out.println(rivi);
										editorPane.setSelectionStart(indeksi);
										editorPane.setSelectionEnd(indeksi + haettava.length());
										editorPane.setSelectedTextColor(Color.YELLOW);
									}
								}
							} catch (Exception e) {

								System.out.println("Sanaa etsittäessä tapahtui virhe!");
								e.printStackTrace();
							}
						}
					});

					thirdFrame.getContentPane().add(ok);
					// Etsi sanaa ikkunan peruuta nappi joka sulkee etsi ikkunan
					JButton peruuta = new JButton("Peruuta");
					peruuta.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {

							thirdFrame.dispose();
						}
					});
					thirdFrame.getContentPane().add(peruuta);

				} catch (Exception e) {

					System.out.println("Sanan etsimisikkunaa luotaessa tapahtui virhe!");
					e.printStackTrace();
				}
			}

		});
		mntmEtsi.setIcon(new ImageIcon(
				Tekstieditori.class.getResource("/com/sun/javafx/scene/web/skin/UnorderedListBullets_16x16_JFX.png")));
		mntmEtsi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		mnMuokkaa.add(mntmEtsi);
		// Korvaa napille kuuntelija, jota painettaessa luodaan uusi ikkuna jolla voidaan korvata joku sana toisella. Varmaan helpompi myös showInputDialogilla  
		JMenuItem mntmKorvaa = new JMenuItem("Korvaa");
		mntmKorvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JFrame secondFrame = new JFrame("Second Frame");

					GridLayout sijoittelija = new GridLayout(3, 2);
					secondFrame.getContentPane().setLayout(sijoittelija);
					secondFrame.setBounds(0, 0, 300, 120);
					secondFrame.setLocationRelativeTo(null);
					secondFrame.setVisible(true);
					secondFrame.setTitle("Sanan korvaus");
					secondFrame.setResizable(false);
					secondFrame.setAlwaysOnTop(true);

					JLabel korvattava = new JLabel("Korvattava sana");
					secondFrame.getContentPane().add(korvattava);

					JTextField korvattava1 = new JTextField();
					secondFrame.getContentPane().add(korvattava1);

					JLabel korvaava = new JLabel("Korvaava sana");
					secondFrame.getContentPane().add(korvaava);

					JTextField korvaava1 = new JTextField();
					secondFrame.getContentPane().add(korvaava1);
					// ok nappi jolla itse sanan korvaus tekstissä tapahtuu ja sille kuuntelija
					JButton ok = new JButton("Ok");
					ok.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							try {

								String sisalto = korvattava1.getText();
								sisalto = sisalto.toLowerCase();

								String sisalto2 = korvaava1.getText();
								sisalto2 = sisalto2.toLowerCase();

								String teksti = editorPane.getText();
								teksti = teksti.toLowerCase();

								String uusiTeksti = teksti.replaceAll(sisalto, sisalto2);
								editorPane.setText(uusiTeksti);
							} catch (Exception e) {

								System.out.println("Sanan vaihtamisessa tapahtui virhe!");
								e.printStackTrace();
							}
						}
					});

					secondFrame.getContentPane().add(ok);
					// peruuta nappi joka sulkee korvaa ikkunan
					JButton peruuta = new JButton("Peruuta");
					peruuta.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {

							secondFrame.dispose();
						}
					});
					secondFrame.getContentPane().add(peruuta);
				} catch (Exception e) {

					System.out.println("Ikkunaa luodessa tapahtui virhe, kun yritettiin korvata sanaa!");
					e.printStackTrace();
				}
			}
		});
		mntmKorvaa.setIcon(
				new ImageIcon(Tekstieditori.class.getResource("/com/sun/javafx/scene/web/skin/Copy_16x16_JFX.png")));
		mntmKorvaa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		mnMuokkaa.add(mntmKorvaa);
		// Tietoja nappi joka avaa uuden ikkunan jossa tietoja ja sille kuuntelija
		JMenu mnTietoja = new JMenu("Tietoja");
		menuBar.add(mnTietoja);
		
		JMenuItem mntmTietojaOhjelmasta = new JMenuItem("Tietoja ohjelmasta");
		mntmTietojaOhjelmasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFrame fourthFrame = new JFrame("fourth Frame");
				fourthFrame.getContentPane().setLayout(null);
				fourthFrame.setBounds(0, 0, 400, 400);
				fourthFrame.setLocationRelativeTo(null);
				fourthFrame.setVisible(true);
				fourthFrame.setTitle("Tietoja ohjelmasta");
				fourthFrame.setResizable(false);
				fourthFrame.setAlwaysOnTop(true);

				JLabel kuva = new JLabel("");
				Image image = new ImageIcon(this.getClass().getResource("/images/kuva.jpg")).getImage();
				kuva.setIcon(new ImageIcon(image));
				fourthFrame.getContentPane().add(kuva);
				kuva.setBounds(70, 50, 259, 194);

				JLabel tiedot = new JLabel("Tekijä: Juha Suvanto");
				fourthFrame.getContentPane().add(tiedot);
				tiedot.setBounds(150, 170, 300, 200);
				// Sulje nappi joka sulkee tietoja ikkunan ja sen kuutelija
				JButton sulje = new JButton("Sulje");
				sulje.setBounds(170, 300, 70, 20);

				sulje.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						fourthFrame.dispose();

					}
				});
				fourthFrame.getContentPane().add(sulje, BorderLayout.SOUTH);

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
		// toolbar ja sen pikakuvakkeet. Sekä niiden toiminnot jotka ovat samoja kun valikossa
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		// toolbar eka nappi
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JFileChooser valintaikkuna = new JFileChooser();
					valintaikkuna.showOpenDialog(null);
					String rivi = "";
					String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
					Scanner lukija = null;
					File tiedosto = new File(uusiTiedosto);
					try {

						lukija = new Scanner(tiedosto);

						while (lukija.hasNextLine()) {
							rivi += lukija.nextLine() + "\n";

						}

					} catch (Exception e) {

						System.out.println("Tiedoston lukemisessa tapahtui virhe!");
						e.printStackTrace();
					}
					editorPane.setText(rivi);
				} catch (Exception e) {

					System.out.println("Tiedoston avaamisessa tapahtui virhe!");
					e.printStackTrace();
				}
			}
		});
		button.setIcon(new ImageIcon(Tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		toolBar.add(button);
		// toolbar toinen nappi
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser valintaikkuna = new JFileChooser();
				valintaikkuna.showSaveDialog(null);

				String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
				System.out.println("Kirjoitettava tiedosto: " + uusiTiedosto);

				try {

					PrintWriter writer = new PrintWriter(uusiTiedosto);
					String sisalto = editorPane.getText();

					writer.println(sisalto);

					writer.flush();
					writer.close();

				} catch (Exception a) {

					System.out.println("Tiedoston tallennuksessa tapahtui virhe!");
					a.printStackTrace();
				}
			}
		});
		button_1.setIcon(
				new ImageIcon(Tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		toolBar.add(button_1);
		// toolbar kolmas nappi sama toiminto kuin korvaa napilla
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					JFrame secondFrame = new JFrame("Second Frame");

					GridLayout sijoittelija = new GridLayout(3, 2);
					secondFrame.getContentPane().setLayout(sijoittelija);
					secondFrame.setBounds(0, 0, 300, 120);
					secondFrame.setLocationRelativeTo(null);
					secondFrame.setVisible(true);
					secondFrame.setTitle("Sanan korvaus");
					secondFrame.setResizable(false);
					secondFrame.setAlwaysOnTop(true);

					JLabel korvattava = new JLabel("Korvattava sana");
					secondFrame.getContentPane().add(korvattava);

					JTextField korvattava1 = new JTextField();
					secondFrame.getContentPane().add(korvattava1);

					JLabel korvaava = new JLabel("Korvaava sana");
					secondFrame.getContentPane().add(korvaava);

					JTextField korvaava1 = new JTextField();
					secondFrame.getContentPane().add(korvaava1);

					JButton ok = new JButton("Ok");
					ok.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							try {

								String sisalto = korvattava1.getText();
								sisalto = sisalto.toLowerCase();

								String sisalto2 = korvaava1.getText();
								sisalto2 = sisalto2.toLowerCase();

								String teksti = editorPane.getText();
								teksti = teksti.toLowerCase();

								String uusiTeksti = teksti.replaceAll(sisalto, sisalto2);
								editorPane.setText(uusiTeksti);
							} catch (Exception e) {

								System.out.println("Sanan vaihtamisessa tapahtui virhe");
								e.printStackTrace();
							}
						}
					});

					secondFrame.getContentPane().add(ok);

					JButton peruuta = new JButton("Peruuta");
					peruuta.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {

							secondFrame.dispose();
						}
					});
					secondFrame.getContentPane().add(peruuta);
				} catch (Exception a) {

					System.out.println("Ikkunaa luodessa tapahtui virhe, kun yritettiin korvata sanaa!");
					a.printStackTrace();
				}

			}
		});
		button_2.setIcon(
				new ImageIcon(Tekstieditori.class.getResource("/com/sun/javafx/scene/web/skin/Copy_16x16_JFX.png")));
		toolBar.add(button_2);

	}

}
