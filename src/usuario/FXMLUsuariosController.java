/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.datacontract.schemas._2004._07.backsafe.ArrayOfEntUsuario;
import org.datacontract.schemas._2004._07.backsafe.EntUsuario;

/**
 * FXML Controller class
 *
 * @author Lucy
 */
public class FXMLUsuariosController implements Initializable {

    @FXML
    private TableView<Usuario> table;
    @FXML
    private TableColumn<Usuario, String> clId;
    @FXML
    private TableColumn<Usuario, String> clRut;
    @FXML
    private TableColumn<Usuario, String> clContrasenia;
    @FXML
    private TableColumn<Usuario, String> clNombre;
    @FXML
    private TableColumn<Usuario, String> clPaterno;
    @FXML
    private TableColumn<Usuario, String> clMaterno;
    @FXML
    private TableColumn<Usuario, String> clDireccion;
    @FXML
    private TableColumn<Usuario, String> clTelefono;
    @FXML
    private TableColumn<Usuario, String> clEmail;
    @FXML
    private TableColumn<Usuario, String> clRegistro;
    @FXML
    private TableColumn<Usuario, String> clPerfil;

    private ObservableList<Usuario> usuariosList = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Usuario, String> clEstado;
    @FXML
    private TableColumn<Usuario, String> clEmpresa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        for (EntUsuario er : retornarUsuarios().getEntUsuario()) {

            usuariosList.add(new Usuario(er.getIdUsuario().getValue(), er.getRut().getValue(), er.getContraseña().getValue(), er.getNombre().getValue(),
                    er.getAppaterno().getValue(),
                    er.getApmaterno().getValue(),
                    er.getDireccion().getValue(),
                    er.getTelefono().getValue(),
                    er.getEmail().getValue(),
                    er.getFecRegistro().getValue(),
                    er.getEstado().getValue(),
                    er.getIdPerfil().getValue(),
                    er.getIdEmpresa().getValue()));

        }

        clId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clRut.setCellValueFactory(new PropertyValueFactory<>("rut_usuario"));
        clContrasenia.setCellValueFactory(new PropertyValueFactory<>("contrasena"));
        clNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        clPaterno.setCellValueFactory(new PropertyValueFactory<>("ape_paterno"));
        clMaterno.setCellValueFactory(new PropertyValueFactory<>("ape_materno"));
        clDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        clTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        clEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        clRegistro.setCellValueFactory(new PropertyValueFactory<>("registro"));
        clEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        clPerfil.setCellValueFactory(new PropertyValueFactory<>("perfil_usuario"));
        clEmpresa.setCellValueFactory(new PropertyValueFactory<>("idEmpresa"));

        table.setItems(usuariosList);
    }

    @FXML
    private Hyperlink lblInicio;

    @FXML
    void inicio(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/paquete/FXMLDocument.fxml"));
        try {
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

            Stage stage2 = (Stage) lblInicio.getScene().getWindow();
            stage2.close();

        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }

    @FXML
    void ingresarUsuario(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLIngUsuario.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException e) {
            System.out.println("ERROR");
        }

    }

    @FXML
    void modificarUsuario(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLModUsuario.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            System.out.println("ERROR");
        }

    }

    @FXML
    void eliminarUsuario(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLEliUsuario.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            System.out.println("ERROR");
        }

    }

    @FXML
    private void cargarDatos(ActionEvent event) {
        table.getItems().clear();

        for (EntUsuario er : retornarUsuarios().getEntUsuario()) {

            usuariosList.add(new Usuario(er.getIdUsuario().getValue(), er.getRut().getValue(), er.getContraseña().getValue(), er.getNombre().getValue(),
                    er.getAppaterno().getValue(),
                    er.getApmaterno().getValue(),
                    er.getDireccion().getValue(),
                    er.getTelefono().getValue(),
                    er.getEmail().getValue(),
                    er.getFecRegistro().getValue(),
                    er.getEstado().getValue(),
                    er.getIdPerfil().getValue(),
                    er.getIdEmpresa().getValue()));

        }

        clId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clRut.setCellValueFactory(new PropertyValueFactory<>("rut_usuario"));
        clContrasenia.setCellValueFactory(new PropertyValueFactory<>("contrasena"));
        clNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        clPaterno.setCellValueFactory(new PropertyValueFactory<>("ape_paterno"));
        clMaterno.setCellValueFactory(new PropertyValueFactory<>("ape_materno"));
        clDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        clTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        clEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        clRegistro.setCellValueFactory(new PropertyValueFactory<>("registro"));
        clEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        clPerfil.setCellValueFactory(new PropertyValueFactory<>("perfil_usuario"));
        clEmpresa.setCellValueFactory(new PropertyValueFactory<>("idEmpresa"));

        table.setItems(usuariosList);
    }

    private static ArrayOfEntUsuario retornarUsuarios() {
        org.tempuri.ServicioAppEscritorio service = new org.tempuri.ServicioAppEscritorio();
        org.tempuri.IServicioAppEscritorio port = service.getBasicHttpBindingIServicioAppEscritorio();
        return port.retornarUsuarios();
    }

}
