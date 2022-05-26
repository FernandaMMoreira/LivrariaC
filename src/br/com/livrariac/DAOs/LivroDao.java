package br.com.livrariac.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.livrariac.beans.Livro;
import br.com.livrariac.utils.Conexao;

public class LivroDao {
	
	public void CadastrarLivro(Livro lv) throws SQLException, Exception{
		try {
			String sql = "INSERT INTO livro (titulo, autor, quantidade, data) VALUES (?, ?, ?, ?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, lv.getTitulo());
			sqlPrep.setString(2, lv.getAutor());
			sqlPrep.setInt(3, lv.getQuantidade());
			sqlPrep.setTimestamp(4, lv.getData());
			sqlPrep.execute();
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void AlterarLivro(Livro lv) throws SQLException, Exception{
		try {
			String sql = "UPDATE livro SET titulo=?, autor=?, quantidade=? WHERE codigo=?";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, lv.getTitulo());
			sqlPrep.setString(2, lv.getAutor());
			sqlPrep.setInt(3, lv.getQuantidade());
			sqlPrep.setInt(4, lv.getCodigo());
			sqlPrep.execute();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void deletarLivro(Livro lv)  throws Exception{
		try {
			String sql = "DELETE FROM livro WHERE codigo=?";
			PreparedStatement sqlPrep = (PreparedStatement) Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, lv.getCodigo());
			sqlPrep.execute();
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public List<Object> buscarTodos() throws Exception{
		List<Object> livro = new ArrayList<Object>();
		try {
			String sql = "SELECT * FROM livro";
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next())
			{
				Object[] linha = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)};
				livro.add(linha);
			}
			state.close();
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return livro;
	}
	
	public int RetornarProxCodigoLivro() throws Exception {
		try {
			String sql = "SELECT MAX(codigo)+1 AS codigo FROM livro ";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			ResultSet rs = sqlPrep.executeQuery();
			if (rs.next()) {
				return rs.getInt("codigo");
			}else {
				return 1;
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return 1;
		}
		
	}
	
	
	
	
	
	
	
	
}
	
