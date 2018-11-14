/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lucy
 */
public class FXMLEliUsuarioController implements Initializable {

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
    private Button btnCancelar;


    @FXML
    void eliminarUsuario(ActionEvent event) throws SQLException {
        
    
    
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Ventana de confirmación");
    alert.setHeaderText("Confirmación");
    alert.setContentText("¿Está seguro que desea eliminar?");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK){
        
            


            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Eliminar usuario");
            alert2.setHeaderText("Usuario");
            alert2.setContentText("El usuario ha sido eliminado");
            alert2.showAndWait();
            
            txtId.setText("");
        } else {
            alert.close();
        }
    

    }
    
    @FXML
    void cancelar(ActionEvent event) {
        Stage stage2 = (Stage) btnCancelar.getScene().getWindow();
        stage2.close();
    }
    
}
