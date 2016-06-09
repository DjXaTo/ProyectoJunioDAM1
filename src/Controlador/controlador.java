package Controlador;

import Vista.*;
import Modelo.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import javax.swing.JTextField;

public class controlador implements ActionListener, MouseListener {

    interfaz vista ; //PARA PODER ACCEDER A LA INTERFAZ DE LA APLICACIÓN
    modelo modelo = new modelo(); //PARA PODER ACCEDER AL MODELO Y A LOS DATOS DE LA BD

    public controlador(interfaz i) {
        this.vista = i;
        
    }
    
    public enum GestionProyectos {
        
        // Menu
        miNuevoEmp,
        miNuevoProy,
        miSalir,
        //Empleados
        btnModEmp,
        btnAsigEmpProy,
        btnEliminarEmp,
        //Proyectos
        btnVerParticipantes,
        btnModProy,
        btnEliminarProy,
        
    }

    public void iniciar () {
    
        //Limitaciones de escritura
        SCifras(vista.txtNIFEmp);
        SCifras(vista.txtCodigoProy);
        
        
    }
    
    
    
    
    
    
    public static void SCifras(JTextField a) {
        a.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isLetter(c)) {
                    e.consume();
                }
            }
        });
    }

    public static void SLetras(JTextField a) {
        a.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
    }
}
