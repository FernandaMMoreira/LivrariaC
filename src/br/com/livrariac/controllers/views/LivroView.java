package br.com.livrariac.controllers.views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import br.com.livrariac.DAOs.LivroDao;
import br.com.livrariac.controllers.views.LivroView;
import br.com.livrariac.beans.Livro;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LivroView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textTitulo;
	private JTextField textAutor;
	private JTextField textQuantidade;
	private JTextField textPTitulo;
	private JTextField textPCodigo_1;
	private JTable table;
	
	private List<Object> livro = new ArrayList<Object>();
	
	Livro lv = new Livro();
	LivroDao lvDao = new LivroDao();
	
	long time = System.currentTimeMillis(); //pegar a data e hora do cadastro 
	Timestamp timestamp = new Timestamp(time);
	
	private JTextField textCodigo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LivroView frame = new LivroView();
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LivroView() {
		//setAutoRequestFocus(false);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent arg0) {
				atualizarTabela();
				limpar();
			}
		});
		setTitle("Livraria C");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 556, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Titulo:");
		lblNewLabel.setBounds(10, 23, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Autor:");
		lblNewLabel_1.setBounds(10, 49, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Quantidade:");
		lblNewLabel_2.setBounds(10, 74, 76, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Codigo:");
		lblNewLabel_3.setBounds(10, 99, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		textTitulo = new JTextField();
		textTitulo.setBounds(47, 20, 327, 20);
		contentPane.add(textTitulo);
		textTitulo.setColumns(10);
		
		textAutor = new JTextField();
		textAutor.setBounds(47, 46, 327, 20);
		contentPane.add(textAutor);
		textAutor.setColumns(10);
		
		textQuantidade = new JTextField();
		textQuantidade.setBounds(87, 71, 86, 20);
		contentPane.add(textQuantidade);
		textQuantidade.setColumns(10);
		
		textCodigo = new JTextField();
		textCodigo.setEditable(false);
		textCodigo.setBounds(87, 96, 86, 20);
		contentPane.add(textCodigo);
		textCodigo.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 124, 520, 2);
		contentPane.add(separator);
		
		JLabel lblNewLabel_4 = new JLabel("Titulo:");
		lblNewLabel_4.setBounds(10, 153, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Codigo:");
		lblNewLabel_5.setBounds(10, 178, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		textPTitulo = new JTextField();
		textPTitulo.setBounds(57, 150, 327, 20);
		contentPane.add(textPTitulo);
		textPTitulo.addCaretListener(new CaretListener() {
			
			public void caretUpdate(CaretEvent arg0) {
				//atualizar tabela apenas com valores correspondentes aos digitados
				TableRowSorter<TableModel> filtro = null;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				filtro = new TableRowSorter<TableModel>(model);
				table.setRowSorter(filtro);
				
				if (textPTitulo.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {
					filtro.setRowFilter(RowFilter.regexFilter("(?i)" + textPTitulo.getText(), 1));
				}
				
			}
		});
		
		textPTitulo.setColumns(10);
		
		textPCodigo_1 = new JTextField();
		textPCodigo_1.setBounds(57, 175, 86, 20);
		contentPane.add(textPCodigo_1);
		textPCodigo_1.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				//atualizar tabela apenas com valores correspondentes aos digitados
				TableRowSorter<TableModel> filtro = null;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				filtro = new TableRowSorter<TableModel>(model);
				table.setRowSorter(filtro);
				
				if (textPCodigo_1.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {
					filtro.setRowFilter(RowFilter.regexFilter(textPCodigo_1.getText(), 0));
				}
				
			
			}
		});
		textPCodigo_1.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 203, 520, 2);
		contentPane.add(separator_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 235, 520, 184);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
	
			public void mousePressed(MouseEvent arg0) {
				setCamposFromTabela();
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Titulo", "Autor", "Quantidade"
			}
		));
		
		JLabel lblNewLabel_6 = new JLabel("Livros:");
		lblNewLabel_6.setBounds(10, 222, 106, 14);
		contentPane.add(lblNewLabel_6);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Atribuição dos valores no campo
				
				try {
					lv.setTitulo(textTitulo.getText());
					lv.setAutor(textAutor.getText());
					lv.setQuantidade(Integer.parseInt(textQuantidade.getText()));
					lv.setData(timestamp);
					System.out.println(timestamp);
		
					//Chamar metodo de create
					lvDao.CadastrarLivro(lv);
					
				}catch (Exception e)	 {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				atualizarTabela();
				limpar();
			}
		});
		btnCadastrar.setBounds(411, 19, 119, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					try {
						//atribuição dos valores dos campos para o objeto livro
						lv.setCodigo(Integer.parseInt(textCodigo.getText()));
						lv.setTitulo(textTitulo.getText());
						lv.setAutor(textAutor.getText());
						lv.setQuantidade(Integer.parseInt(textQuantidade.getText()));
						//chamada metodo update
						lvDao.AlterarLivro(lv);
						
					} catch (Exception el) {
						JOptionPane.showMessageDialog(null, el.getMessage());
					}
					atualizarTabela();
					limpar();
				}
				else {
					JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada");
				}
			}
		});
		btnAlterar.setBounds(411, 45, 119, 23);
		contentPane.add(btnAlterar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(411, 70, 119, 23);
		contentPane.add(btnLimpar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					Object[] options3 = {"Excluir", "Cancelar"};
					if(JOptionPane.showOptionDialog(null, "Certeza que deseja excluir o registro:\n>    -  "
							+ table.getValueAt(table.getSelectedRow(),0) + "  -  "
							+ table.getValueAt(table.getSelectedRow(),1), null,
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options3, options3[0]) == 0) {
						try {
							lv.setCodigo(Integer.parseInt(textCodigo.getText()));
							
							lvDao.deletarLivro(lv);
							
							atualizarTabela();
							limpar();			
					} catch (Exception el) {
						JOptionPane.showMessageDialog(null, el.getMessage());
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada");
			}
			}	
		});
		btnExcluir.setBounds(411, 95, 119, 23);
		contentPane.add(btnExcluir);
		
		JLabel lblNewLabel_4_1 = new JLabel("Buscar");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4_1.setBounds(10, 128, 46, 14);
		contentPane.add(lblNewLabel_4_1);
	}
		public void sair() {
			System.exit(0);
		}
		
		public void setCamposFromTabela() {
		textCodigo.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));	
		textTitulo.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
		textAutor.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 2)));
		textQuantidade.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 3)));
	}
		public void limpar() {
			textTitulo.setText(null);
			textAutor.setText(null);
			textQuantidade.setText(null);
			try {
				textCodigo.setText(String.valueOf(lvDao.RetornarProxCodigoLivro()));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		
		public void atualizarTabela() {
			try {
				livro = lvDao.buscarTodos();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setNumRows(0);
			for (int x=0; x!=livro.size(); x++)
			{
				model.addRow ((Object[]) livro.get(x));
			}
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		
	}
}
