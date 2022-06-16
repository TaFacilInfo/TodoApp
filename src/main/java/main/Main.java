/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.ProjectController;
import java.sql.Connection;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

/**
 *
 * @author Zumbi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
//        //Teste para Inserir  
//        ProjectController projectController = new ProjectController();        
//              
//        Project project = new Project();
//        project.setName("Projeto Teste");
//        project.setDescription("Descrição");        
//        projectController.save(project);
        
        //Teste para Alterar
        ProjectController projectController = new ProjectController();  
        Project project = new Project();
        project.setId(1);
        project.setName("Novo Nome do Projeto");
        project.setDescription("Descrição");
        
        projectController.update(project);
        
        List<Project> projects = projectController.getAll();
        System.out.println("Total de Projetos = " + projects.size());
        
        
        //Teste Deleção
        projectController.removeById(1);
        
        
        
        
        
        
        
//        //Pedir Conexão
//        Connection c = ConnectionFactory.getConnection();
//        //Encerrar conexão
//        ConnectionFactory.closeConnection(c);
    }
    
}
