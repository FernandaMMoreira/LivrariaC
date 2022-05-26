package br.com.livrariac.utils;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
		private final static String driver = "org.postgresql.Driver";
		//retirei o usuário e senha por questões de segurança
		private final static String usuario = "";
		private final static String senha = "";
		private final static String host = "ec2-34-194-158-176.compute-1.amazonaws.com";
		private final static String porta = "5432";
		private final static String banco = "d5o3n662mkk9cp";
		private final static String url = "jdbc:postgresql://" + host + ":" + porta + "/" + banco ;
		private static Connection conexao = null;
		
		public static Connection conectar() throws URISyntaxException, SQLException {
			try {
				Class.forName(driver);
				conexao = DriverManager.getConnection(url, usuario, senha);
				System.out.println("Conexão efetuada com sucesso");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return conexao;
		}
		public void fechar () {
			try {
				conexao.close();
				System.out.println("Conexao encerrada");
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
}
