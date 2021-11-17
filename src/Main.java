import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

	@SuppressWarnings(value = "unused")
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		
		Class.forName("com.mysql.jdbc.Driver");
		
		String url = "jdbc:mysql://localhost:3306/dbproducts";
		String user = "root";
		String pwd = ""; 
		
		Connection conn = DriverManager.getConnection(url,user,pwd);
		Statement st = conn.createStatement();
		
		// Q1 - Afficher tous les produits qui se trouve dans la table PRODUIT.
		String req1 = "SELECT * FROM PRODUIT";
		ResultSet rst1 = st.executeQuery(req1);
		
		System.out.println("--------------------------------------------");
		System.out.println("1- La liste des produits est : ");
		while(rst1.next()) {
			System.out.println(String.format("%d - %s - %s - %f - %f", rst1.getInt(1), rst1.getString(2), rst1.getString(3), rst1.getDouble(4), rst1.getDouble(5)));
		}
		System.out.println("--------------------------------------------");
		// Q2 - b.	Afficher tous les produits qui ont un prix d’achat > 200 et qui ont un nom qui commence par A. 
		String req2 = "SELECT * FROM PRODUIT WHERE prix_achat > 34 and nom like 'p%'";
		ResultSet rst2 = st.executeQuery(req2); 
		
		System.out.println("--------------------------------------------");
		System.out.println("2- La liste des produits qui ont un prix d’achat > 200 et qui ont un nom qui commence par A est : ");
		while(rst2.next()) {
			System.out.println(String.format("%d - %s - %s - %f - %f", rst2.getInt(1), rst2.getString(2), rst2.getString(3), rst2.getDouble(4), rst2.getDouble(5)));
		}
		System.out.println("--------------------------------------------");
		
		// Q3 - c.	Afficher la somme des prix de vente et prix d’achat par famille.
		String req3 = "SELECT famille,SUM(prix_achat), SUM(prix_vente) FROM PRODUIT group by famille";
		ResultSet rst3 = st.executeQuery(req3); 
		
		System.out.println("--------------------------------------------");
		System.out.println("3- La liste des produits qui ont un prix d’achat > 200 et qui ont un nom qui commence par A est : ");
		while(rst3.next()) {
			System.out.println(String.format("%s - %f - %f", rst3.getString(1), rst3.getDouble(2), rst3.getDouble(3)));
		}
		System.out.println("--------------------------------------------");
		// Q4 - d.	Demander à l’utilisateur de saisir une famille, puis afficher tous les produit de cette famille. 
		String req4 = "SELECT * FROM PRODUIT WHERE famille = ?";
		PreparedStatement pst1 = conn.prepareStatement(req4);
		System.out.print("Donner une famille : ");
		pst1.setString(1, sc.nextLine());
		
		ResultSet rst4 = pst1.executeQuery();
		System.out.println("4- La liste des produits de la famille : ");
		while(rst4.next()) {
			System.out.println(String.format("%d - %s - %s - %f - %f", rst4.getInt(1), rst4.getString(2), rst4.getString(3), rst4.getDouble(4), rst4.getDouble(5)));
		}
		System.out.println("--------------------------------------------");
	
		// Q5 - e.	Demander à l’utilisateur de saisir les informations d’un produit, puis ajouter ce produit dans la base de données.
		String req5 = "INSERT INTO PRODUIT VALUES (null,?,?,?,?)";
		PreparedStatement pst2 = conn.prepareStatement(req5);
		
		System.out.print("Donner le nom : ");
		pst2.setString(1, sc.nextLine());
		System.out.print("Donner la famille : ");
		pst2.setString(2, sc.nextLine());
		System.out.print("Donner le prix achat : ");
		pst2.setDouble(3, Double.parseDouble(sc.nextLine()));
		System.out.print("Donner le prix vente : ");
		pst2.setDouble(4, Double.parseDouble(sc.nextLine()));
		
		int res = pst2.executeUpdate();
		System.out.println("Le nombre de lignes inseree est : " + res);
				
		// Q6 - f.	Demande à l’utilisateur d’augmenter le prix d’achat et le prix de vente par 10% des produit qui appartiennent à une famille donnée par l’utilisateur.  Afficher le nombre de produit modifiés
		String req6 = "UPDATE PRODUIT SET prix_achat = prix_achat + prix_achat*0.1,prix_vente = prix_vente + prix_vente*0.1 WHERE famille = ?";
		PreparedStatement pst3 = conn.prepareStatement(req6);
		System.out.print("Donner la famille : ");
		pst3.setString(1, sc.nextLine());
		
		res = pst3.executeUpdate();
		System.out.println("Le nombre de lignes mis a jour est : " + res);
		
		// Q7 - g.	Supprimer tous les produits qui ont un nom qui commence par A. afficher le nombre de produit supprimé. 
		String req7 = "DELETE FROM PRODUIT WHERE nom like 'A%'";
		PreparedStatement pst4 = conn.prepareStatement(req7);
		res = pst4.executeUpdate();
		
		System.out.println("Le nombre supprime est : " + res);
		conn.close();

	}

}
