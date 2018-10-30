/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import paquete.Conexion;

/**
 * FXML Controller class
 *
 * @author Lucy
 */
public class FXMLModEmpresaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    private TextField txtId;

    @FXML
    private TextField txtRut;

    @FXML
    private TextField txtNombre;

    @FXML
    private Button btnCancelar;

    @FXML
    void cancelar(ActionEvent event) {
        Stage stage2 = (Stage) btnCancelar.getScene().getWindow();
        stage2.close();

    }

    @FXML
    void modificarEmpresa(ActionEvent event) throws SQLException {
        
        

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Ventana de confirmación");
    alert.setHeaderText("Confirmación");
    alert.setContentText("¿Está seguro que desea modificar?");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK){
        
            Conexion conexion = new Conexion();
            Connection con = conexion.conectarBD("safe_db");
            
            String sql = "update empresa set nombre_empresa='"+txtNombre.getText()+"', run_empresa='"+txtRut.getText()+"' where usuario_id='"+txtId.getText()+"'";

            Statement stmn = con.createStatement();
            stmn.executeUpdate(sql);


            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Modificar empresa");
            alert2.setHeaderText("Empresa");
            alert2.setContentText("La empresa ha sido modificada");
            alert2.showAndWait();
            
            txtId.setText("");
            txtNombre.setText("");
            txtRut.setText("");

        } else {
            alert.close();
        }   
      

    }
    
    
     
    
}