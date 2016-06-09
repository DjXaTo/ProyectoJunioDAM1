package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class modelo extends conexion{

    
    
    //MÉTODOS DE EMPLEADOS
    public String[] getEmpleados() {
        int registros = 0;
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT count(NIF) as total FROM Empleados");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al contar tuplas\n\n" + e.getMessage());
            e.printStackTrace();
        }
        String[] Empleados = new String[registros];
        try {
            String q = "SELECT NIF FROM Empleados";
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                Empleados[i] = res.getString("NIF");
                i++;
            }
            res.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener datos\n\n" + e.getMessage());
            e.printStackTrace();
        }
        return Empleados;
    }

    public String getNombreCliente(String dni) {
        String nombre = "";
        String apellidos = "";
        try {
            String q = "SELECT Nombre, Apellidos FROM Empleados WHERE NIF = '" + dni + "'";
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                nombre = res.getString("Nombre");
                apellidos = res.getString("Apellidos");
            }
            res.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el nombre y los apellidos\n\n" + e.getMessage());
            e.printStackTrace();
        }
        return nombre + " " + apellidos;
    }

    public DefaultTableModel tablaEmpleados() {
        DefaultTableModel tablemodel = new ModeloTablaNoEditable();
        int registros = 0;
        String[] columNames = {"NIF", "Nombre", "Apellidos", "Dirección", "Teléfono", "Correo"};
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT count(NIF) as total FROM Empleados");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al contar tuplas\n\n" + e.getMessage());
            e.printStackTrace();
        }
        Object[][] data = new String[registros][6];
        try {
            String q = "SELECT NIF, Nombre, Apellidos, Direccion, Telefono, Correo FROM Empleados";
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                data[i][0] = res.getString("NIF");
                data[i][1] = res.getString("Nombre");
                data[i][2] = res.getString("Apellidos");
                data[i][3] = res.getString("Direccion");
                data[i][4] = res.getString("Telefono");
                data[i][5] = res.getString("Correo");
                i++;
            }
            res.close();
            tablemodel.setDataVector(data, columNames);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener datos\n\n" + e.getMessage());
            e.printStackTrace();
        }
        return tablemodel;
    }

    public DefaultTableModel tablaEmpleadosVacia() {
        DefaultTableModel tablemodel = new ModeloTablaNoEditable();
        int registros = 0;
        String[] columNames = {"NIF", "Nombre", "Apellidos", "Dirección", "Teléfono", "Correo"};
        Object[][] data = new String[registros][6];
        try {
            tablemodel.setDataVector(data, columNames);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al construir tabla\n\n" + e.getMessage());
            e.printStackTrace();
        }
        return tablemodel;
    }

    public boolean comprobarExistenciaEmpleado(String dni) {
        String q = "SELECT NIF FROM Empleados WHERE NIF = '" + dni + "'";
        boolean resu = false;
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            res.next();
            if (res.getRow() == 0) {
                resu = false;
            } else {
                resu = true;
            }
            res.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al comprobar existencia\n\n" + e.getMessage());
            e.printStackTrace();
        }
        return resu;
    }

    public void crearEmpleado(String dni, String nombre, String apellidos, String direccion, String telefono, String correo) {
        String q = "INSERT INTO Empleados (NIF, Nombre, Apellidos, Direccion, Telefono, Correo) "
                + "VALUES('" + dni + "','" + nombre + "', '" + apellidos + "','" + direccion + "','" + telefono + "','" + correo + "')";
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al introducir nuevo empleado\n\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void modificarEmpleado(String dni, String ndni, String nombre, String apellidos, String direccion, String telefono, String correo) {
        String q = "UPDATE Empleados SET NIF = '" + ndni + "', Nombre = '" + nombre + "', Apellidos = '" + apellidos + "', Direccion = '" + direccion
                + "', Telefono = '" + telefono + "', Correo = '" + correo + "' WHERE NIF = '" + dni + "'";
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar el empleado\n\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void eliminarEmpleado(String dni) {
        String q = "DELETE FROM Empleados WHERE NIF = '" + dni + "'";
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el empleado\n\n" + e.getMessage());
            e.printStackTrace();
        }
    }
    
    //Modelo de tabla no editable
    public class ModeloTablaNoEditable extends DefaultTableModel {

        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
    
}
