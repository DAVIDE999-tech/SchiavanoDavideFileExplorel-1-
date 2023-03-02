import java.awt.event.*;
import java.io.File;
import java.awt.*;
import javax.swing.*;

public class FileExplorer extends JFrame implements ActionListener {

	private JTextArea area, info;
	private JButton conferma, creaf, confermaF;
	private JMenuItem creaF, scegliF, esci, about, help;
	private JLabel immL;
	private FileProva fp;

	public FileExplorer() {
		super("FileExplorer");
		Container c = getContentPane();
		JPanel centro, est, sud, nord;

		// pannello centro
		centro = new JPanel();
		area = new JTextArea(30, 80);
		JScrollPane js = new JScrollPane(area);
		centro.add(js);

		// pannello est
		est = new JPanel();
		immL = new JLabel(new ImageIcon("img/juve.jpg"));
		est.add(immL);

		// pannello sud
		sud = new JPanel();
		conferma = new JButton("Scegli file");
		creaf = new JButton("Crea file");
		confermaF = new JButton("Conferma file");
		conferma.addActionListener(this);
		creaf.addActionListener(this);
		confermaF.addActionListener(this);
		confermaF.setVisible(false);
		sud.add(conferma);
		sud.add(creaf);
		sud.add(confermaF);

		// pannello nord
		nord = new JPanel();
		info = new JTextArea(3, 80);
		info.setEditable(false);
		nord.add(info);

		c.add(centro);
		c.add(est, "East");
		c.add(sud, "South");
		c.add(nord, "North");
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o == conferma) {
			JFileChooser jf = new JFileChooser();
			int i = jf.showDialog(null, "Seleziona file");
			if (i == JFileChooser.APPROVE_OPTION) {
				File f = jf.getSelectedFile();
				String nomef = f.getName();
				info.setText("Nome file: " + nomef + "\n");
				info.append("dimensione: " + f.length());
				if (nomef.endsWith(".txt") || nomef.endsWith(".java") || nomef.endsWith(".cpp")
						|| nomef.endsWith(".bat") || nomef.endsWith(".html")) {
					fp = new FileProva(f.getAbsolutePath(), "");
					area.setText(fp.leggiFile());
				} else if (nomef.endsWith(".gif") || nomef.endsWith(".jpg") || nomef.endsWith(".png")
						|| nomef.endsWith(".jpeg") || nomef.endsWith(".tiff"))
					immL.setIcon(new ImageIcon(f.getAbsolutePath()));
			}
		} else if (o == creaf) {
			confermaF.setVisible(true);
			conferma.setVisible(false);
			String s = JOptionPane.showInputDialog(this, "Inserisci il nome del file");
			fp = new FileProva("", s);
		} else if (o == confermaF) {
			fp.scriviFile(area.getText());
			conferma.setVisible(true);
			confermaF.setVisible(false);
		}
	}

	public static void main(String[] args) {
		new FileExplorer();
	}

}