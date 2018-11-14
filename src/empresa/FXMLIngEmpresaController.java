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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import validador.FormValidador;

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
    private TextField txtDireccion;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtCorreo;
    @FXML
    private Label lblRut;
    @FXML
    private Label lblDireccion;
    @FXML
    private Label lblCorreo;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblTelefono;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

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

        boolean nombre = FormValidador.textFieldNoVacio(txtNombre, lblNombre, "Campo requerido");
        boolean rut = FormValidador.textFieldNoVacio(txtRut, lblRut, "Campo requerido");
        boolean direccion = FormValidador.textFieldNoVacio(txtDireccion, lblDireccion, "Campo requerido");
        boolean telef = FormValidador.textFieldNoVacio(txtTelefono, lblTelefono, "Campo requerido");
        boolean correo = FormValidador.textFieldNoVacio(txtCorreo, lblCorreo, "Campo requerido");

        if (nombre && rut && direccion && telef && correo) {

            if (FormValidador.validarRut(txtRut.getText())) {
                String r = txtRut.getText().replace(".", "");
                
                BigDecimal telefono = new BigDecimal(txtTelefono.getText());

                crearEmpresa(txtNombre.getText(), r, txtDireccion.getText(), telefono, txtCorreo.getText());

                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Ingresar empresa");
                alert2.setHeaderText("Empresa");
                alert2.setContentText("La empresa ha sido ingresada");
                alert2.showAndWait();

                txtNombre.clear();
                txtRut.clear();
                txtDireccion.clear();
                txtTelefono.clear();
                txtCorreo.clear();
            } else {
                lblRut.setText("Rut inválido");
            }
        }

    }

    private static Boolean crearEmpresa(java.lang.String nomEmpresa, java.lang.String runEmpresa, java.lang.String dirEmpresa, java.math.BigDecimal telEmpresa, java.lang.String corEmpresa) {
        org.tempuri.ServicioAppEscritorio service = new org.tempuri.ServicioAppEscritorio();
        org.tempuri.IServicioAppEscritorio port = service.getBasicHttpBindingIServicioAppEscritorio();
        return port.crearEmpresa(nomEmpresa, runEmpresa, dirEmpresa, telEmpresa, corEmpresa);
    }

}
