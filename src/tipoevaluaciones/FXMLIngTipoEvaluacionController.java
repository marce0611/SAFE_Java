/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipoevaluaciones;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import paquete.Conexion;

/**
 * FXML Controller class
 *
 * @author Lucy
 */
public class FXMLIngTipoEvaluacionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private Button btnCancelar;

    @FXML
    private TextField txtDescripcion;


    @FXML
    void cancelar(ActionEvent event) {
        Stage stage2 = (Stage) btnCancelar.getScene().getWindow();
        stage2.close();

    }

    @FXML
    void ingresarTipoEvaluacion(ActionEvent event) throws SQLException {
        
            if (crearTipoEvaluacion(txtDescripcion.getText())) {
            System.out.println("Tipo de evaluacion ingresado");
        }
            
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Ingresar tipo de evaluacion");
            alert2.setHeaderText("Tipo de evaluacion");
            alert2.setContentText("El tipo de evaluacion ha sido ingresado");
            alert2.showAndWait();
            
            txtDescripcion.clear();

    }

    private static Boolean crearTipoEvaluacion(java.lang.String descripcion) {
        org.tempuri.Service1 service = new org.tempuri.Service1();
        org.tempuri.IService1 port = service.getBasicHttpBindingIService1();
        return port.crearTipoEvaluacion(descripcion);
    }



    
}
