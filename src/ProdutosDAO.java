

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        conectaDAO conectar = new conectaDAO();
        conn = conectar.connectDB();
        try {
            PreparedStatement pst = conn.prepareStatement("insert into produtos (nome, valor, status) values (?,?,?)");
            pst.setString(1, produto.getNome());
            pst.setInt(2, produto.getValor());
            pst.setString(3, produto.getStatus());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Produto Salvo com sucesso!");
            
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        conectaDAO conectar = new conectaDAO();
        conn = conectar.connectDB();
        ArrayList<ProdutosDTO> listar = new ArrayList<>();
        try {
           PreparedStatement pst = conn.prepareStatement("select * from produtos");
           ResultSet rs = pst.executeQuery();
           while(rs.next()) {
               ProdutosDTO produto = new ProdutosDTO();
               produto.setId(rs.getInt("id"));
               produto.setNome(rs.getString("nome"));
               produto.setValor(rs.getInt("valor"));
               produto.setStatus(rs.getString("status"));
               listar.add(produto);
           }
           return listar;
        }
        catch(SQLException e) {
            return null;
        }
    }
    
    
    
        
}

