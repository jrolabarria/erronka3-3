import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DatuBasea
{
	private static Connection konexioa;

	public static Connection konexioaEzarri()
	{
		String url;
		String erabiltzailea;
		String pasahitza;

		if (konexioa == null)
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				url = "jdbc:mysql://localhost:3306/osagaiak";
		        	erabiltzailea = "root";
		        	pasahitza = "programazioa";
				konexioa = DriverManager.getConnection(url,erabiltzailea,pasahitza);
			}
			catch (ClassNotFoundException e)
			{
				System.out.println("Salbuespena: " + e);
			}
			catch (InstantiationException e)
			{
				System.out.println("Salbuespena: " + e);
			}
			catch (IllegalAccessException e)
			{
				System.out.println("Salbuespena: " + e);
			}
			catch (SQLException e)
			{
				System.out.println("Salbuespena: " + e);
			}
		}

		return konexioa;
	}

	public static void konexioaItxi()
	{
		if (konexioa != null)
		{
			try
			{
				konexioa.close();
			}
			catch (SQLException e)
			{
				System.out.println("Salbuespena: " + e);
			}

			konexioa = null;
		}
	}

	public static void mikroprozesadoreaGehitu(Mikroprozesadorea mikroprozesadorea)
	{
		Statement kontsulta;
		String sql;

		try
		{
			kontsulta = konexioa.createStatement();
			sql = "insert into mikroprozesadoreak";
			sql = sql + " (kodea,ekoizlea,modeloa,socketa,frekuentzia,prezioa,deskontua)";
			sql = sql + " values ('" + mikroprozesadorea.getKodea() + "',";
			sql = sql +"'" + mikroprozesadorea.getEkoizlea() + "',";
			sql = sql +"'" + mikroprozesadorea.getModeloa() + "',";
			sql = sql + "'" + mikroprozesadorea.getSocketa() + "',";
			sql = sql + mikroprozesadorea.getFrekuentzia() + ",";
			sql = sql + mikroprozesadorea.getPrezioa() +",";
			sql = sql + mikroprozesadorea.getDeskontua() + ")";
			kontsulta.executeUpdate(sql);
			kontsulta.close();
		}
		catch (SQLException e)
		{
			System.out.println("Salbuespena: " + e);
		}
	}

	public static Mikroprozesadorea mikroprozesadoreaBilatu(String kodea)
	{
		Statement kontsulta;
		ResultSet erregistroa;
		String sql;
		String ekoizlea;
		String modeloa;
		String socketa;
		double frekuentzia;
		double prezioa;
		int deskontua;
		Mikroprozesadorea mikroprozesadorea = null;

		try
		{
			kontsulta = konexioa.createStatement();
			sql = "select * from mikroprozesadoreak where kodea='" + kodea + "'";
			erregistroa = kontsulta.executeQuery(sql);
									
			if (erregistroa.next())
			{
				ekoizlea = erregistroa.getString("ekoizlea");
				modeloa = erregistroa.getString("modeloa");
				socketa = erregistroa.getString("socketa");
				frekuentzia = erregistroa.getDouble("frekuentzia");
				prezioa = erregistroa.getDouble("prezioa");
				deskontua = erregistroa.getInt("deskontua");
				mikroprozesadorea = new Mikroprozesadorea(kodea,ekoizlea,modeloa,socketa,frekuentzia,prezioa,deskontua);
			}

			erregistroa.close();
			kontsulta.close();
		}
		catch (SQLException e)
		{
			System.out.println("Salbuespena: " + e);
		}

		return mikroprozesadorea;
	}

	public static ArrayList<Mikroprozesadorea> mikroprozesadoreakBilatu()
	{
		Statement kontsulta;
		String sql;
		String kodea;
		String ekoizlea;
		String modeloa;
		String socketa;
		double frekuentzia;
		double prezioa;
		int deskontua;
		ResultSet erregistroak;
		ArrayList<Mikroprozesadorea> mikroprozesadoreak = new ArrayList<>();

		try
		{
			kontsulta = konexioa.createStatement();
			sql = "select * from mikroprozesadoreak";
			erregistroak = kontsulta.executeQuery(sql);
									
			while (erregistroak.next())
			{
				kodea = erregistroak.getString("kodea");
				ekoizlea = erregistroak.getString("ekoizlea");
				modeloa = erregistroak.getString("modeloa");
				socketa = erregistroak.getString("socketa");
				frekuentzia = erregistroak.getDouble("frekuentzia");
				prezioa = erregistroak.getDouble("prezioa");
				deskontua = erregistroak.getInt("deskontua");
				mikroprozesadoreak.add(new Mikroprozesadorea(kodea,ekoizlea,modeloa,socketa,frekuentzia,prezioa,deskontua));
			}
			erregistroak.close();
			kontsulta.close();
		}
		catch (SQLException e)
		{
			System.out.println("Salbuespena: " + e);
		}

		return mikroprozesadoreak;
	}

	public static void mikroprozesadoreaAldatu(Mikroprozesadorea mikroprozesadorea)
	{
		Statement kontsulta;
		String sql;

		try
		{
			kontsulta = konexioa.createStatement();
			sql = "update mikroprozesadoreak set ";
			sql = sql + "ekoizlea='" + mikroprozesadorea.getEkoizlea() +"',";
			sql = sql + "modeloa='" + mikroprozesadorea.getModeloa() +"',";
			sql = sql + "socketa='" + mikroprozesadorea.getSocketa() +"',";
			sql = sql + "frekuentzia=" + mikroprozesadorea.getFrekuentzia() +",";
			sql = sql + "prezioa=" + mikroprozesadorea.getPrezioa() +",";
			sql = sql + "deskontua=" + mikroprozesadorea.getDeskontua();
			sql = sql + " where kodea='" + mikroprozesadorea.getKodea() +"'";
			kontsulta.executeUpdate(sql);
		}
		catch (SQLException e)
		{
			System.out.println("Salbuespena: " + e);
		}
	}

	public static void mikroprozesadoreaEzabatu(Mikroprozesadorea mikroprozesadorea)
	{
		Statement kontsulta;
		String sql;

		try
		{
			kontsulta = konexioa.createStatement();
			sql = "delete from mikroprozesadoreak where kodea='" + mikroprozesadorea.getKodea() + "'";
			kontsulta.executeUpdate(sql);
			kontsulta.close();
		}
		catch (SQLException e)
		{
			System.out.println("Salbuespena: " + e);
		}
	}
}

