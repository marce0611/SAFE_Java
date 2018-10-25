/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa;

/**
 *
 * @author Lucy
 */
public class Empresa {

    String id_usuario, nombre_empresa, run_empresa;

    public Empresa(String id_usuario, String nombre_empresa, String run_empresa) {
        this.id_usuario = id_usuario;
        this.nombre_empresa = nombre_empresa;
        this.run_empresa = run_empresa;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public String getRun_empresa() {
        return run_empresa;
    }

    public void setRun_empresa(String run_empresa) {
        this.run_empresa = run_empresa;
    }

    
}
