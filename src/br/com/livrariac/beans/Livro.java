package br.com.livrariac.beans;

import java.sql.Timestamp;


public class Livro {

	private int codigo;
	private String titulo;
	private String autor;
	private int quantidade;
	private Timestamp data;
	
	//construtor vazio
	
	public Livro () {
		
	}
	//getes e setes
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Timestamp getData() {
		return data;
	}
	public void setData(java.sql.Timestamp timestamp) {
		this.data = timestamp;
	}
	
}