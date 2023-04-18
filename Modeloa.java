import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class Modeloa extends AbstractTableModel
{
	private ArrayList<Mikroprozesadorea> mikroprozesadoreak;
	private String[] eremuak = {"Kodea","Ekoizlea","Modeloa","Socketa","Frekuentzia","Prezioa","Deskontua"};

	public Modeloa()
	{
		DatuBasea.konexioaEzarri();
		mikroprozesadoreak = DatuBasea.mikroprozesadoreakBilatu();
	}

	@Override
	public int getRowCount()
	{
		return mikroprozesadoreak.size();
	}

	@Override
	public int getColumnCount()
	{
		return eremuak.length;
	}

	@Override
	public String getColumnName(int columnIndex)
	{
		return eremuak[columnIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columIndex)
	{
		switch (columIndex)
		{
			case 0:
				return mikroprozesadoreak.get(rowIndex).getKodea();
			case 1:
				return mikroprozesadoreak.get(rowIndex).getEkoizlea();
			case 2:
				return mikroprozesadoreak.get(rowIndex).getModeloa();
			case 3:
				return mikroprozesadoreak.get(rowIndex).getSocketa();
			case 4:
				return mikroprozesadoreak.get(rowIndex).getFrekuentzia();
			case 5:
				return mikroprozesadoreak.get(rowIndex).getPrezioa();
			case 6:
				return mikroprozesadoreak.get(rowIndex).getDeskontua();
			default:
				return null;
		}
	}

	Mikroprozesadorea getMikroprozesadorea(int rowIndex)
	{
		return mikroprozesadoreak.get(rowIndex);
	}

	void eguneratu()
	{
		mikroprozesadoreak = DatuBasea.mikroprozesadoreakBilatu();
		fireTableDataChanged();
	}
}