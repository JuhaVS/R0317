package tekstieditori;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;

public class tekstieditoriMetodit {

	public static void avaa() {
		// metodi joka luo avaa ikkunan josta voi valita avattavan tiedoston
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
			Tekstieditori.editorPane.setText(rivi);
		} catch (Exception e) {

			System.out.println("Tiedoston avaamisessa tapahtui virhe!");
			e.printStackTrace();
		}

	}

	public static void tallenna() {

		// tallenna metodi joka luo tallenna ikkunan josta voi valita minne tallennetaan
		JFileChooser valintaikkuna = new JFileChooser();
		valintaikkuna.showSaveDialog(null);

		String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
		System.out.println("Kirjoitettava tiedosto: " + uusiTiedosto);

		try {

			PrintWriter writer = new PrintWriter(uusiTiedosto);
			String sisalto = Tekstieditori.editorPane.getText();

			writer.println(sisalto);

			writer.flush();
			writer.close();

		} catch (Exception e) {

			System.out.println("Tiedoston tallennuksessa tapahtui virhe!");
			e.printStackTrace();
		}

	}

	public static void sulje() {

		System.exit(0);

	}

	public static void etsi() {
		// etsi metodi joka luo etsi ikkunan jolla voi etsi‰ tekstist‰ tietty‰ sanaa
		try {
			JFrame thirdFrame = new JFrame("Third Frame");

			GridLayout sijoittelija = new GridLayout(2, 2);
			thirdFrame.getContentPane().setLayout(sijoittelija);
			thirdFrame.setBounds(0, 0, 300, 100);
			thirdFrame.setLocationRelativeTo(null);
			thirdFrame.setVisible(true);
			thirdFrame.setTitle("Sanan etsint‰");
			thirdFrame.setResizable(false);
			thirdFrame.setAlwaysOnTop(true);

			JLabel etsitt‰v‰ = new JLabel("Mit‰ sanaa etsit‰‰n");
			thirdFrame.getContentPane().add(etsitt‰v‰);

			JTextField etsitt‰v‰1 = new JTextField();
			thirdFrame.getContentPane().add(etsitt‰v‰1);
			// OK nappi jolla itse etsiminen tapahtuu ja sille kuuntelija
			JButton ok = new JButton("Ok");
			ok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						String sisalto = Tekstieditori.editorPane.getText();
						sisalto = sisalto.toLowerCase();

						String haettava = etsitt‰v‰1.getText();
						Scanner lukija = new Scanner(Tekstieditori.editorPane.getText().toLowerCase());

						Document document = Tekstieditori.editorPane.getDocument();
						for (int index = 0; index + haettava.length() < sisalto.length(); index++) {
							String match = document.getText(index, haettava.length());
							if (haettava.equals(match)) {
								DefaultHighlighter.DefaultHighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(
										Color.YELLOW);
								Tekstieditori.editorPane.getHighlighter().addHighlight(index, index + haettava.length(),
										highlightPainter);
							}
						}
					} catch (Exception e) {

					}
				}
			});

			thirdFrame.getContentPane().add(ok);
			// Etsi sanaa ikkunan sulje nappi joka sulkee etsi ikkunan ja poistaa
			// highlightit
			JButton sulje = new JButton("Poista HL ja sulje");
			sulje.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					thirdFrame.dispose();
					String x = Tekstieditori.editorPane.getText();
					Tekstieditori.editorPane.setText(x);
				}
			});
			thirdFrame.getContentPane().add(sulje);

		} catch (Exception e) {

			System.out.println("Sanan etsimisikkunaa luotaessa tapahtui virhe!");
			e.printStackTrace();
		}

	}

	public static void korvaa() {
		// korvaa metodi joka luo korvaa ikkunan jossa voi korvata tietyn sanan
		// tekstiss‰ toisella
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
			// ok nappi jolla itse sanan korvaus tekstiss‰ tapahtuu ja sille kuuntelija
			JButton ok = new JButton("Ok");
			ok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {

						String sisalto = korvattava1.getText();
						sisalto = sisalto.toLowerCase();

						String sisalto2 = korvaava1.getText();
						sisalto2 = sisalto2.toLowerCase();

						String teksti = Tekstieditori.editorPane.getText();
						teksti = teksti.toLowerCase();

						String uusiTeksti = teksti.replaceAll(sisalto, sisalto2);
						Tekstieditori.editorPane.setText(uusiTeksti);
					} catch (Exception e) {

						System.out.println("Sanan vaihtamisessa tapahtui virhe!");
						e.printStackTrace();
					}
				}
			});

			secondFrame.getContentPane().add(ok);
			// sulje nappi joka sulkee korvaa ikkunan
			JButton sulje = new JButton("Sulje");
			sulje.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					secondFrame.dispose();
				}
			});
			secondFrame.getContentPane().add(sulje);
		} catch (Exception e) {

			System.out.println("Ikkunaa luodessa tapahtui virhe, kun yritettiin korvata sanaa!");
			e.printStackTrace();
		}

	}

	public static void tietoja() {

		JFrame fourthFrame = new JFrame("fourth Frame");
		fourthFrame.getContentPane().setLayout(null);
		fourthFrame.setBounds(0, 0, 400, 400);
		fourthFrame.setLocationRelativeTo(null);
		fourthFrame.setVisible(true);
		fourthFrame.setTitle("Tietoja ohjelmasta");
		fourthFrame.setResizable(false);
		fourthFrame.setAlwaysOnTop(true);
		Image image = null;
		try {
			URL url = new URL(
					"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRpbd-6VnvPVGxTw-D2pcMl4DQ-2LBnPbiFz6T_ybtfQl2Hs3MilQ");
			image = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel label = new JLabel(new ImageIcon(image));
		label.setBounds(70, 50, 259, 194);
		fourthFrame.getContentPane().add(label);

		JLabel tiedot = new JLabel("Tekij‰: Juha Suvanto");
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
}
