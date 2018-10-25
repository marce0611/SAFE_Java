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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import paquete.Conexion;
import static usuario.FXMLIngUsuarioController.crearUsuario;

/**
 * FXML Controller class
 *
 * @author Lucy
 */
public class FXMLModUsuarioController implements Initializable {

    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnIngresar;
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

        cbContrato.setItems(contratosList);
        cbEmpresa.setItems(empresasList);
        cbPerfil.setItems(perfilList);

        try {
            Conexion conexion = new Conexion();
            Connection con = conexion.conectarBD("safe_db");

            String sql = "select * from contrato";

            PreparedStatement prdst = con.prepareStatement(sql);
            ResultSet result = prdst.executeQuery(sql);
            while (result.next()) {

                contratosList.add(result.getString("id"));

            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        try {
            Conexion conexion = new Conexion();
            Connection con = conexion.conectarBD("safe_db");

            String sql = "select * from empresa";

            PreparedStatement prdst = con.prepareStatement(sql);
            ResultSet result = prdst.executeQuery(sql);
            while (result.next()) {

                empresasList.add(result.getString("usuario_id"));

            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        try {
            Conexion conexion = new Conexion();
            Connection con = conexion.conectarBD("safe_db");

            String sql = "select * from perfil_usuario";

            PreparedStatement prdst = con.prepareStatement(sql);
            ResultSet result = prdst.executeQuery(sql);
            while (result.next()) {

                perfilList.add(result.getString("id"));

            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Stage stage2 = (Stage) btnCancelar.getScene().getWindow();
        stage2.close();
        
    }

    @FXML
    private void modificarUsuario(ActionEvent event) {
        
        BigDecimal rut = new BigDecimal(txtRut.getText());
        BigDecimal telefono = new BigDecimal(txtTelefono.getText());
        BigDecimal perfil = new BigDecimal(cbPerfil.getValue());
        
        if (modificarUsuario_1(rut, txtContrasena.getText(), txtNombre.getText(), txtPaterno.getText(), txtMaterno.getText(), txtDireccion.getText(), telefono, txtEmail.getText(), perfil)) {
            System.out.println("FUNCIONA!!!");
        }

        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setTitle("Modifcar usuario");
        alert2.setHeaderText("Usuario");
        alert2.setContentText("El usuario ha sido modificado");
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
    private void perfilUsuario(ActionEvent event) {
        cbPerfil.setItems(perfilList);
    }

    @FXML
    private void mostrarContratos(ActionEvent event) {
    }

    @FXML
    private void mostrarEmpresas(ActionEvent event) {
    }

    private static Boolean modificarUsuario_1(java.math.BigDecimal rut, java.lang.String contraseña, java.lang.String nombre, java.lang.String appaterno, java.lang.String apmaterno, java.lang.String direccion, java.math.BigDecimal telefono, java.lang.String email, java.math.BigDecimal idPerfil) {
        org.tempuri.Service1 service = new org.tempuri.Service1();
        org.tempuri.IService1 port = service.getBasicHttpBindingIService1();
        return port.modificarUsuario(rut, contraseña, nombre, appaterno, apmaterno, direccion, telefono, email, idPerfil);
    }

}
