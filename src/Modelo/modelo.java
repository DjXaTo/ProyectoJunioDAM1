package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class modelo extends conexion {

    //MÉTODOS DE EMPLEADOS
    public String[] getEmpleados() {
        int registros = 0;
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT count(nif) as total FROM Empleados");
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
            String q = "SELECT nif FROM Empleados";
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                Empleados[i] = res.getString("nif");
                i++;
            }
            res.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener datos\n\n" + e.getMessage());
            e.printStackTrace();
        }
        return Empleados;
    }

    public String getNombreCliente(String nif) {
        String nombre = "";
        String apellidos = "";
        try {
            String q = "SELECT Nombre, Apellidos FROM Empleados WHERE nif = '" + nif + "'";
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
        String[] columNames = {"nif", "Nombre", "Apellidos", "Fecha de nacimiento"};
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT count(nif) as total FROM Empleados");
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
            String q = "SELECT nif, Nombre, Apellidos, fechaNac FROM Empleados";
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                data[i][0] = res.getString("nif");
                data[i][1] = res.getString("Nombre");
                data[i][2] = res.getString("Apellidos");
                data[i][3] = res.getString("fechaNac");
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
        String[] columNames = {"nif", "Nombre", "Apellidos", "Fecha de Nacimiento"};
        Object[][] data = new String[registros][6];
        try {
            tablemodel.setDataVector(data, columNames);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al construir tabla\n\n" + e.getMessage());
            e.printStackTrace();
        }
        return tablemodel;
    }

    public boolean comprobarExistenciaEmpleado(String nif) {
        String q = "SELECT nif FROM Empleados WHERE nif = '" + nif + "'";
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

    public void crearEmpleado(String nif, String nombre, String apellidos, String fechaNacimiento) {
        String q = "INSERT INTO Empleados (nif, Nombre, Apellidos, fechaNac) "
                + "VALUES('" + nif + "','" + nombre + "', '" + apellidos + "','" + fechaNacimiento + "')";
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al introducir nuevo empleado\n\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void modificarEmpleado(String nif, String nnif, String nombre, String apellidos, String fechaNacimiento) {
        String q = "UPDATE Empleados SET nif = '" + nnif + "', Nombre = '" + nombre + "', Apellidos = '" + apellidos + "', fechaNac = '" + fechaNacimiento
                + "'";
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar el empleado\n\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void eliminarEmpleado(String nif) {
        String q = "DELETE FROM Empleados WHERE nif = '" + nif + "'";
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el empleado\n\n" + e.getMessage());
            e.printStackTrace();
        }
    }
    
    //MÉTODOS DE PROYECTOS
    public String[] getProyectos() {
        int registros = 0;
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT count(nif) as total FROM Empleados");
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
            String q = "SELECT codigoProyecto FROM Empleados";
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                Empleados[i] = res.getString("codigoProyecto");
                i++;
            }
            res.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener datos\n\n" + e.getMessage());
            e.printStackTrace();
        }
        return Empleados;
    }

    public String getTituloProyecto (String codigo) {
        String titulo = "";
        try {
            String q = "SELECT titulo FROM Proyectos WHERE codigoProyecto = '" + codigo + "'";
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                titulo = res.getString("titulo");
            }
            res.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el nombre y los apellidos\n\n" + e.getMessage());
            e.printStackTrace();
        }
        return titulo;
    }

    public DefaultTableModel tablaProyectos() {
        DefaultTableModel tablemodel = new ModeloTablaNoEditable();
        int registros = 0;
        String[] columNames = {"Código", "Titulo", "Fecha de inicio", "Fecha de finalización"};
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT count(codigoProyecto) as total FROM Empleados");
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
            String q = "SELECT codigoProyecto, titulo, fechaInicio, fechaFin FROM Proyectos";
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                data[i][0] = res.getString("codigoProyecto");
                data[i][1] = res.getString("titutlo");
                data[i][2] = res.getString("fechaInicio");
                data[i][3] = res.getString("fechaFin");
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

    public DefaultTableModel tablaProyectosVacia() {
        DefaultTableModel tablemodel = new ModeloTablaNoEditable();
        int registros = 0;
        String[] columNames = {"Código", "Titulo", "Fecha de inicio", "Fecha de finalización"};
        Object[][] data = new String[registros][6];
        try {
            tablemodel.setDataVector(data, columNames);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al construir tabla\n\n" + e.getMessage());
            e.printStackTrace();
        }
        return tablemodel;
    }

    public boolean comprobarExistenciaProyecto(String codigo) {
        String q = "SELECT codigoProyecto FROM Proyectos WHERE codigoProyecto = '" + codigo + "'";
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

    public void crearProyecto(String titulo, String fechaInicio, String fechaFin) {
        String q = "INSERT INTO Proyectos (titulo, fechaInicio, fechaFin) "
                + "VALUES('" + titulo + "','" + fechaInicio + "', '" + fechaFin + "')";
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al introducir nuevo empleado\n\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void modificarProyecto(String titulo, String fechaInicio, String fechaFin) {
        String q = "UPDATE Proyectos SET titulo = '" + titulo + "', fechaInicio = '" + fechaInicio + "', fechaFin = '" + fechaFin + "'";
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar el empleado\n\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void eliminarProyecto(String codigo) {
        String q = "DELETE FROM Proyectos WHERE codigoProyecto = '" + codigo + "'";
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el empleado\n\n" + e.getMessage());
            e.printStackTrace();
        }
    }
    
    //MÉTODOS DE EMPLEADOS Y PROYECTOS
    public String[] getEmpleadoProyecto(String nif){
        String[] cliente = new String[4];
        String q = "select * from Clientes where nif='" + nif + "'";
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                cliente[0] = res.getString("nif");
                cliente[1] = res.getString("nombre");
                cliente[2] = res.getString("apellidos");
                cliente[3] = res.getString("fechaNac");
            }
            res.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener datos\n\n" + e.getMessage());
            e.printStackTrace();
        }
        return cliente;
    }
    
    
    //Modelo de tabla no editable
    public class ModeloTablaNoEditable extends DefaultTableModel {

        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
    
}
