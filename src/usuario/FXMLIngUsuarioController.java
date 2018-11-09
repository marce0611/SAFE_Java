/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.datacontract.schemas._2004._07.backsafe.ArrayOfEntPerfilUsuario;
import org.datacontract.schemas._2004._07.backsafe.EntPerfilUsuario;
import paquete.Conexion;

/**
 * FXML Controller class
 *
 * @author Lucy
 */
public class FXMLIngUsuarioController implements Initializable {

    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtPaterno;
    @FXML
    private TextField txtMaterno;
    @FXML
    private TextField txtRut;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtEmail;
    @FXML
    private ComboBox<String> cbPerfil;
    @FXML
    private Pane pMedico;
    @FXML
    private TextField txtDisponibilidad;
    @FXML
    private TextField txtMedicoCorreo;
    @FXML
    private TextField txtMedicoTelefono;
    @FXML
    private Pane pTrabajador;
    @FXML
    private TextField txtEstado;
    @FXML
    private TextField txtTrabajadorCorreo;
    @FXML
    private TextField txtTrabajadorTelefono;
    @FXML
    private ComboBox<String> cbContrato;
    @FXML
    private ComboBox<String> cbEmpresa;
    @FXML
    private TextField txtContrasena;

    private ObservableList<String> contratosList = FXCollections.observableArrayList();
    private ObservableList<String> empresasList = FXCollections.observableArrayList();
    private ObservableList<String> perfilList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        cbPerfil.setItems(perfilList);

        for (EntPerfilUsuario er : retornarPerfilUsuarios().getEntPerfilUsuario()) {

            perfilList.add(er.getIdPerfil().getValue());

        }

    }

    @FXML
    private void cancelar(ActionEvent event) {
        Stage stage2 = (Stage) btnCancelar.getScene().getWindow();
        stage2.close();
    }

    @FXML
    private void ingresarUsuario(ActionEvent event) throws SQLException {

        BigDecimal rut = new BigDecimal(txtRut.getText());
        BigDecimal telefono = new BigDecimal(txtTelefono.getText());
        BigDecimal perfil = new BigDecimal(cbPerfil.getValue());

        Integer perfil2 = new Integer(cbPerfil.getValue());

        if (crearUsuario(rut, txtContrasena.getText(), txtNombre.getText(), txtPaterno.getText(), txtMaterno.getText(), txtDireccion.getText(), telefono, txtEmail.getText(), perfil)) {
            System.out.println("Usuario ingresado");
        }

        /*if (perfil2 == 5) {
            if (crearUsuario(rut, txtContrasena.getText(), txtNombre.getText(), txtPaterno.getText(), txtMaterno.getText(), txtDireccion.getText(), telefono, txtEmail.getText(), perfil)) {
                System.out.println("Usuario medico ingresado");
            }
        } else if (perfil2 == 6) {
            if (crearUsuario(rut, txtContrasena.getText(), txtNombre.getText(), txtPaterno.getText(), txtMaterno.getText(), txtDireccion.getText(), telefono, txtEmail.getText(), perfil)) {
                System.out.println("Usuario trabajadoringresado");
            }

        } else {
            
        }
         */
        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setTitle("Ingresar usuario");
        alert2.setHeaderText("Usuario");
        alert2.setContentText("El usuario ha sido ingresado");
        alert2.showAndWait();

        txtRut.clear();
        txtContrasena.clear();
        txtNombre.clear();
        txtPaterno.clear();
        txtMaterno.clear();
        txtDireccion.clear();
        txtTelefono.clear();
        txtEmail.clear();
        txtTrabajadorCorreo.clear();
        txtTrabajadorTelefono.clear();
        txtEstado.clear();
        txtDisponibilidad.clear();
        txtMedicoCorreo.clear();
        txtMedicoTelefono.clear();

    }

    @FXML
    private void perfilUsuario(ActionEvent event
    ) {

        cbPerfil.setItems(perfilList);

        Integer perfil = new Integer(cbPerfil.getValue());

        if (perfil == 5) {
            pMedico.setDisable(false);
            pTrabajador.setDisable(true);
        } else if (perfil == 6) {
            pMedico.setDisable(true);
            pTrabajador.setDisable(false);
        } else {
            pMedico.setDisable(true);
            pTrabajador.setDisable(true);
        }

    }

    @FXML
    private void mostrarContratos(ActionEvent event
    ) {
        //cbContrato.setItems(contratosList);
    }

    @FXML
    private void mostrarEmpresas(ActionEvent event
    ) {

        //cbEmpresa.setItems(empresasList);
    }

    private static Boolean crearUsuario(java.math.BigDecimal rut, java.lang.String contraseña, java.lang.String nombre, java.lang.String appaterno, java.lang.String apmaterno, java.lang.String direccion, java.math.BigDecimal telefono, java.lang.String email, java.math.BigDecimal idPerfil) {
        org.tempuri.ServicioAppEscritorio service = new org.tempuri.ServicioAppEscritorio();
        org.tempuri.IServicioAppEscritorio port = service.getBasicHttpBindingIServicioAppEscritorio();
        return port.crearUsuario(rut, contraseña, nombre, appaterno, apmaterno, direccion, telefono, email, idPerfil);
    }

    private static ArrayOfEntPerfilUsuario retornarPerfilUsuarios() {
        org.tempuri.ServicioAppEscritorio service = new org.tempuri.ServicioAppEscritorio();
        org.tempuri.IServicioAppEscritorio port = service.getBasicHttpBindingIServicioAppEscritorio();
        return port.retornarPerfilUsuarios();
    }

}
