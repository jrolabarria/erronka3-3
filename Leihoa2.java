import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Leihoa2 extends JDialog
{
	private JLabel lKodea;
	private JLabel lEkoizlea;
	private JLabel lModeloa;
	private JLabel lSocketa;
	private JLabel lFrekuentzia;
	private JLabel lPrezioa;
	private JLabel lDeskontua;
	private JTextField tKodea;
	private JTextField tEkoizlea;
	private JTextField tModeloa;
	private JTextField tSocketa;
	private JTextField tFrekuentzia;
	private JTextField tPrezioa;
	private JTextField tDeskontua;
	private JButton bAdos;
	private JButton bUtzi;
	private JPanel pMikroprozesadorea;
	private JPanel pBotoiak;
	private Mikroprozesadorea mikroprozesadorea = new Mikroprozesadorea();

	public Leihoa2(java.awt.Frame leihoNagusia, String izenburua, boolean leihoModala)
	{
		super(leihoNagusia, izenburua, leihoModala);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		lKodea = new JLabel("Kodea");
		lEkoizlea = new JLabel("Ekoizlea");
		lModeloa = new JLabel("Modeloa");
		lSocketa = new JLabel("Socketa");
		lFrekuentzia = new JLabel("Frekuentzia");
		lPrezioa = new JLabel("Prezioa");
		lDeskontua = new JLabel("Deskontua");
	
		tKodea = new JTextField();
		tEkoizlea = new JTextField();
		tModeloa = new JTextField();
		tSocketa = new JTextField();
		tFrekuentzia = new JTextField();
		tPrezioa = new JTextField();
		tDeskontua = new JTextField();

		tKodea.setPreferredSize(new Dimension(200,20));
		tFrekuentzia.setPreferredSize(new Dimension(200,20));
		tPrezioa.setPreferredSize(new Dimension(200,20));
		tDeskontua.setPreferredSize(new Dimension(200,20));
		tEkoizlea.setPreferredSize(new Dimension(200,20));
		tModeloa.setPreferredSize(new Dimension(200,20));
		tSocketa.setPreferredSize(new Dimension(200,20));

		bAdos = new JButton("Gehitu");
		bUtzi = new JButton("Utzi");

		bAdos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				klikAdos();
			}
		}
		);
		
		bUtzi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				klikUtzi();
			}
		}
		);

		pMikroprozesadorea = new JPanel();
		pMikroprozesadorea.setLayout(new GridBagLayout());
		pMikroprozesadorea.add(lKodea,getConstraints(0,0,GridBagConstraints.LINE_END));
		pMikroprozesadorea.add(tKodea,getConstraints(1,0,GridBagConstraints.LINE_START));
		pMikroprozesadorea.add(lEkoizlea,getConstraints(0,1,GridBagConstraints.LINE_END));
		pMikroprozesadorea.add(tEkoizlea,getConstraints(1,1,GridBagConstraints.LINE_START));
		pMikroprozesadorea.add(lModeloa,getConstraints(0,2,GridBagConstraints.LINE_END));
		pMikroprozesadorea.add(tModeloa,getConstraints(1,2,GridBagConstraints.LINE_START));
		pMikroprozesadorea.add(lFrekuentzia,getConstraints(0,3,GridBagConstraints.LINE_END));
		pMikroprozesadorea.add(tFrekuentzia,getConstraints(1,3,GridBagConstraints.LINE_START));
		pMikroprozesadorea.add(lSocketa,getConstraints(0,4,GridBagConstraints.LINE_END));
		pMikroprozesadorea.add(tSocketa,getConstraints(1,4,GridBagConstraints.LINE_START));
		pMikroprozesadorea.add(lFrekuentzia,getConstraints(0,5,GridBagConstraints.LINE_END));
		pMikroprozesadorea.add(tFrekuentzia,getConstraints(1,5,GridBagConstraints.LINE_START));
		pMikroprozesadorea.add(lPrezioa,getConstraints(0,6,GridBagConstraints.LINE_END));
		pMikroprozesadorea.add(tPrezioa,getConstraints(1,6,GridBagConstraints.LINE_START));
		pMikroprozesadorea.add(lDeskontua,getConstraints(0,7,GridBagConstraints.LINE_END));
		pMikroprozesadorea.add(tDeskontua,getConstraints(1,7,GridBagConstraints.LINE_START));

		pBotoiak = new JPanel();
		pBotoiak.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pBotoiak.add(bAdos);
		pBotoiak.add(bUtzi);

		getContentPane().add(pMikroprozesadorea, BorderLayout.CENTER);
		getContentPane().add(pBotoiak, BorderLayout.SOUTH);
		pack();
	}

	public Leihoa2(java.awt.Frame leihoNagusia, String izenburua, boolean leihoModala, Mikroprozesadorea mikroprozesadorea)
	{
		this(leihoNagusia, izenburua, leihoModala);
		this.mikroprozesadorea = mikroprozesadorea;
		bAdos.setText("Aldatu");
		tKodea.setText(mikroprozesadorea.getKodea());
		tEkoizlea.setText(mikroprozesadorea.getEkoizlea());
		tModeloa.setText(mikroprozesadorea.getModeloa());
		tSocketa.setText(mikroprozesadorea.getSocketa());
		tFrekuentzia.setText(String.valueOf(mikroprozesadorea.getFrekuentzia()));
		tPrezioa.setText(String.valueOf(mikroprozesadorea.getPrezioa()));
		tDeskontua.setText(String.valueOf(mikroprozesadorea.getDeskontua()));
	}

	private GridBagConstraints getConstraints(int x, int y, int anchor)
	{
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,5,0,5);
		c.gridx = x;
		c.gridy = y;
		c.anchor = anchor;
		return c;
	}

	private void klikAdos()
	{
		if (datuakZuzenak())
		{
			setDatuak();
			if (bAdos.getText().equals("Gehitu"))
			{
				gehitu();
			}
			else
			{
				aldatu();
			}
		}
	}

	private void klikUtzi()
	{
		dispose();
	}

	private boolean datuakZuzenak()
	{
		String kodea = tKodea.getText();
		String ekoizlea = tEkoizlea.getText();
		String modeloa = tModeloa.getText();
		String socketa = tSocketa.getText();
		String frekuentzia = tFrekuentzia.getText();
		String prezioa = tPrezioa.getText();
		String deskontua = tDeskontua.getText();
		
		if (kodea.isEmpty()
			|| ekoizlea.isEmpty()
			|| modeloa.isEmpty()
			|| socketa.isEmpty()
			|| frekuentzia.isEmpty()
			|| prezioa.isEmpty() 
			|| deskontua.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Eremu guztiak bete behar dira.", "Mikroprozesadorea " + bAdos.getText().toLowerCase(), JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		else
		{
			try
			{
				Double.parseDouble(frekuentzia);
			}
			catch (NumberFormatException e)
			{
				JOptionPane.showMessageDialog(this,"Sartutako frekuentzia ez da zuzena",
					"Mikroprozesadorea " + bAdos.getText().toLowerCase(),
					JOptionPane.INFORMATION_MESSAGE);
				return false;				
			}

			try
			{
				Double.parseDouble(prezioa);
			}
			catch (NumberFormatException e)
			{
				JOptionPane.showMessageDialog(this,"Sartutako prezioa ez da zuzena",
					"Mikroprozesadorea " + bAdos.getText().toLowerCase(),
					JOptionPane.INFORMATION_MESSAGE);
				return false;				
			}

			try
			{
				Integer.parseInt(deskontua);
			}
			catch (NumberFormatException e)
			{
				JOptionPane.showMessageDialog(this,"Sartutako deskontua ez da zuzena",
					"Mikroprozesadorea " + bAdos.getText().toLowerCase(),
					JOptionPane.INFORMATION_MESSAGE);
				return false;				
			}

		}

		return true;
	}

	private void setDatuak()
	{
		String kodea = tKodea.getText();
		String ekoizlea = tEkoizlea.getText();
		String modeloa = tModeloa.getText();
		String socketa = tSocketa.getText();
		double frekuentzia = Double.parseDouble(tFrekuentzia.getText());
		double prezioa = Double.parseDouble(tPrezioa.getText());
		int deskontua = Integer.parseInt(tDeskontua.getText());

		mikroprozesadorea.setKodea(kodea);
		mikroprozesadorea.setEkoizlea(ekoizlea);
		mikroprozesadorea.setModeloa(modeloa);
		mikroprozesadorea.setSocketa(socketa);
		mikroprozesadorea.setFrekuentzia(frekuentzia);
		mikroprozesadorea.setPrezioa(prezioa);
		mikroprozesadorea.setDeskontua(deskontua);
	}

	private void gehitu()
	{
		DatuBasea.mikroprozesadoreaGehitu(mikroprozesadorea);
		dispose();
		modeloaEguneratu();
	}

	private void aldatu()
	{
		DatuBasea.mikroprozesadoreaAldatu(mikroprozesadorea);
		dispose();
		modeloaEguneratu();
	}

	private void modeloaEguneratu()
	{
		Leihoa1 l = (Leihoa1) getOwner();
		l.modeloaEguneratu();
	}
}