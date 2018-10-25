/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa;

import paquete.Conexion;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private ComboBox<String> idUsuario;

    @FXML
    private Button btnIngresar;

    private ObservableList<String> usuariosList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        idUsuario.setItems(usuariosList);

        try {
            Conexion conexion = new Conexion();
            Connection con = conexion.conectarBD("safe_db");

            String sql = "select * from usuario";

            PreparedStatement prdst = con.prepareStatement(sql);
            ResultSet result = prdst.executeQuery(sql);
            while (result.next()) {

                usuariosList.add(result.getString("id"));

            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
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
    void mostrarIdUsuario(ActionEvent event) {
        idUsuario.setItems(usuariosList);
    }

    @FXML
    void ingresarEmpresa(ActionEvent event) throws SQLException {
        Conexion conexion = new Conexion();
        Connection con = conexion.conectarBD("safe_db");

        String sql = "insert into empresa values('" + idUsuario.getValue() + "', '" + txtNombre.getText() + "', '" + txtRut.getText() + "')";

        Statement stmn = con.createStatement();
        stmn.executeUpdate(sql);

        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setTitle("Ingresar empresa");
        alert2.setHeaderText("Empresa");
        alert2.setContentText("La empresa ha sido ingresada");
        alert2.showAndWait();
        
        txtNombre.clear();
        txtRut.clear();

    }

}
