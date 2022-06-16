/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

/**
 *
 * @author Jose Carlos
 */
public class ProjectController {

    //Métodos
    public void save(Project project){
        String sql = "INSERT INTO projects("
                + "name, "
                + "description, "
                + "createdAt, "
                + "updateAt) "
                + "VALUES (?, ?, ?, ?)";
        
        Connection con = null;
        PreparedStatement statement = null;
        
        try {
            //Cria uma conesão com o banco
            con = ConnectionFactory.getConnection();
            
            //Cria um PreparedStatement, classe usada para usar a query            
            statement = con.prepareStatement(sql);
            
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdateAt().getTime()));
            
            //Executa a sql para inserção dos dados
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar o projeto. ", ex);
        } finally {
            ConnectionFactory.closeConnection(con, statement); 
        }        
    }
    
    public void update(Project project){
        String sql = "UPDATE projects SET "
                + "name = ?, "
                + "description = ?, "
                + "createdAt = ?, "
                + "updateAt = ? "
                + "WHERE id = ?";
        
        Connection con = null;
        PreparedStatement statement = null;
        
        try {
            //Cria a conexão com o banco de dados.
            con = ConnectionFactory.getConnection();
            
            //Cria um PreparedStatement, classe usada para preparar a query.
            statement = con.prepareStatement(sql);
            
            //Setendo os valores do statement.
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdateAt().getTime()));
            statement.setInt(5, project.getId());
            
            //Executando a sql para atualização dos dados
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar o projeto.", ex);
        } finally {
            ConnectionFactory.closeConnection(con, statement);
        }
    }
    
    public List<Project> getAll() {
        String sql = "SELECT * FROM projects";
        
        List<Project> projects = new ArrayList<>();
        
        Connection con = null;
        PreparedStatement statement = null;
        
        //Classe que vai recuperar os dados do banco de dados
        ResultSet resultSet = null;
        
        try {            
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(sql);
            
            resultSet = statement.executeQuery();
            
            //Enquanto existir dados no banco de dados, faça...
            while (resultSet.next()){
                Project project = new Project();
                
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setCreatedAt(resultSet.getDate("createdAt"));
                project.setUpdateAt(resultSet.getDate("updateAt"));
                
                //Adicionar o contato recuperado a lista de contatos
                projects.add(project);                
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar os projetos. ", ex);
        } finally {
            ConnectionFactory.closeConnection(con, statement, resultSet);
        }
        return projects;
    }
    
    public void removeById(int idProject) {
        String sql = "DELETE FROM projects WHERE id = ?";
        
        Connection con = null;
        PreparedStatement statement = null;
        
        try {
            //Estabelecendo a conexão com o banco de dados.
            con = ConnectionFactory.getConnection();
            
            //Preparando a query.
            statement = con.prepareStatement(sql);
            
            //Setendo os valores do statement.
            statement.setInt(1, idProject);
            
            //Executando a query.
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar projeto. ",ex);
        } finally {
            ConnectionFactory.closeConnection(con, statement);
        }        
    }    
}
