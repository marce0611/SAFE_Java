/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Lucy
 */
public class FormValidador {

    public static boolean textFieldNoVacio(TextField i) {
        boolean r = false;
        if (i.getText() != null && !i.getText().isEmpty()) {
            r = true;
        }
        return r;
    }

    public static boolean textFieldNoVacio(TextField i, Label l, String sValidacionText) {
        boolean r = true;
        String c = null;
        if (!textFieldNoVacio(i)) {
            r = false;
            c = sValidacionText;
        }
        l.setText(c);
        return r;
    }
    
        public static boolean validarCorreo(String correo) {

        boolean validacion = false;
        try {
            Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
            Matcher m = p.matcher(correo);
            
            if(m.find() && m.group().equals(correo)){
                validacion = true;
            }
        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }
        return validacion;

    }

    public static boolean validarRut(String rut) {

        boolean validacion = false;
        try {
            rut = rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }

        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }
        return validacion;
    }


}
