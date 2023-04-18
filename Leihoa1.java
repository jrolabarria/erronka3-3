import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class Leihoa1 extends JFrame
{
	private JTable taula;
	private Modeloa modeloa;
	private JButton bGehitu;
	private JButton bAldatu;
	private JButton bEzabatu;
	private JPanel pBotoiak;

	public Leihoa1()
	{
		setBounds(0, 0, 768, 384);
		setTitle("Mikroprozesadoreak");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		bGehitu = new JButton("Gehitu");
		bAldatu = new JButton("Aldatu");
		bEzabatu = new JButton("Ezabatu");

		bGehitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				klikGehitu();
			}
		}
		);
		
		bAldatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				klikAldatu();
			}
		}
		);

		bEzabatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				klikEzabatu();
			}
		}
		);
		
		pBotoiak = new JPanel();
		pBotoiak.add(bGehitu);
		pBotoiak.add(bAldatu);
		pBotoiak.add(bEzabatu);
		getContentPane().add(pBotoiak, BorderLayout.NORTH);

		modeloa = new Modeloa();
		taula = new JTable(modeloa);
		taula.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		taula.setBorder(null);
		getContentPane().add(new JScrollPane(taula), BorderLayout.CENTER);
	}

	private void klikGehitu()
	{
		Leihoa2 leihoa2 = new Leihoa2(this, "Mikroprozesadorea gehitu", true);
		leihoa2.setLocationRelativeTo(this);
		leihoa2.setVisible(true);
	}

	private void klikAldatu()
	{
		int lerroa = taula.getSelectedRow();
		if (lerroa == -1)
		{
			JOptionPane.showMessageDialog(this,
					"Ez dago mikroprozesadorerik aukeratuta.",
					"Mikroprozesadorea aldatu",
					JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			Mikroprozesadorea mikroprozesadorea = modeloa.getMikroprozesadorea(lerroa);
			Leihoa2 leihoa2 = new Leihoa2(this, "Mikroprozesadorea aldatu", true, mikroprozesadorea);
			leihoa2.setLocationRelativeTo(this);
			leihoa2.setVisible(true);
		}
	}

	private void klikEzabatu()
	{
		int lerroa;
		int aukera;

		lerroa = taula.getSelectedRow();

		if (lerroa == -1 )
		{
			JOptionPane.showMessageDialog(this,
					"Ez dago mikroprozesadorerik aukeratuta",
					"Mikroprozesadorea ezabatu", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			Mikroprozesadorea mikroprozesadorea = modeloa.getMikroprozesadorea(lerroa);
			aukera = JOptionPane.showConfirmDialog(this,
					"Ziur zaude?",
					"Mikroprozesadorea ezabatu", JOptionPane.YES_NO_OPTION);

			if (aukera == JOptionPane.YES_OPTION)
			{
				DatuBasea.mikroprozesadoreaEzabatu(mikroprozesadorea);
				modeloaEguneratu();
			}
		}			
	}

	public void modeloaEguneratu()
	{
		modeloa.eguneratu();
	}
}