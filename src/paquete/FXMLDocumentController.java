/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;


/**
 *
 * @author Lucy
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Hyperlink lblUsuarios;

    @FXML
    private Hyperlink lblTipoEvaluaciones;

    @FXML
    private Hyperlink lblEmpresas;

    @FXML
    void abrirUsuarios(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/usuario/FXMLUsuarios.fxml"));
            Parent root1 = (Parent) fxmlLoader.load(); 
            Stage stage = new Stage(); 
            stage.setScene(new Scene(root1)); 
            stage.show();
            
            Stage stage2 = (Stage) lblUsuarios.getScene().getWindow();
            stage2.close();
        
            
        } catch(IOException e) {
            System.out.println("ERROR");
        }
        

    }

    @FXML
    void abrirEmpresas(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/empresa/FXMLEmpresa.fxml"));
            Parent root1 = (Parent) fxmlLoader.load(); 
            Stage stage = new Stage(); 
            stage.setScene(new Scene(root1)); 
            stage.show();
            
            Stage stage2 = (Stage) lblEmpresas.getScene().getWindow();
            stage2.close();
            
        } catch(IOException e) {
            System.out.println("ERROR");
        }
       
    }

    @FXML
    void abrirTipoEvaluaciones(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tipoevaluaciones/FXMLTipoEvaluacion.fxml"));
            Parent root1 = (Parent) fxmlLoader.load(); 
            Stage stage = new Stage(); 
            stage.setScene(new Scene(root1)); 
            stage.show();
            
            Stage stage2 = (Stage) lblTipoEvaluaciones.getScene().getWindow();
            stage2.close();
        } catch(IOException e) {
            System.out.println("ERROR");
        }

    }


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
