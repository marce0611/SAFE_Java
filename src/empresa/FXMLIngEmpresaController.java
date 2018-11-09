/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.datacontract.schemas._2004._07.backsafe.ArrayOfEntUsuario;
import org.datacontract.schemas._2004._07.backsafe.EntUsuario;

/**
 * FXML Controller class
 *
 * @author Lucy
 */
public class FXMLIngEmpresaController implements Initializable {

    @FXML
    private TextField txtRut;
    @FXML
    private TextField txtNombre;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnIngresar;
    @FXML
    private ComboBox<String> cbId;

    private ObservableList<String> usuarioList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        cbId.setItems(usuarioList);

        for (EntUsuario er : retornarUsuarios().getEntUsuario()) {

            usuarioList.add(er.getIdUsuario().getValue());

        }

    }

    /**
     * Initializes the controller class.
     */
    @FXML
    void cancelar(ActionEvent event) {
        Stage stage2 = (Stage) btnCancelar.getScene().getWindow();
        stage2.close();

    }

    @FXML
    void ingresarEmpresa(ActionEvent event) throws SQLException {

        BigDecimal id = new BigDecimal(cbId.getValue());
        
        crearEmpresa(id, txtNombre.getText(), txtRut.getText());
        
        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setTitle("Ingresar empresa");
        alert2.setHeaderText("Empresa");
        alert2.setContentText("La empresa ha sido ingresada");
        alert2.showAndWait();

        txtNombre.clear();
        txtRut.clear();

    }

    private static Boolean crearEmpresa(java.math.BigDecimal usuarioId, java.lang.String nomEmpresa, java.lang.String runEmpresa) {
        org.tempuri.ServicioAppEscritorio service = new org.tempuri.ServicioAppEscritorio();
        org.tempuri.IServicioAppEscritorio port = service.getBasicHttpBindingIServicioAppEscritorio();
        return port.crearEmpresa(usuarioId, nomEmpresa, runEmpresa);
    }

    @FXML
    private void mostrarId(ActionEvent event) {
        
        cbId.setItems(usuarioList);
    }

    private static ArrayOfEntUsuario retornarUsuarios() {
        org.tempuri.ServicioAppEscritorio service = new org.tempuri.ServicioAppEscritorio();
        org.tempuri.IServicioAppEscritorio port = service.getBasicHttpBindingIServicioAppEscritorio();
        return port.retornarUsuarios();
    }

}
