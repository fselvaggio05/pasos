package conexionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FactoryConnection {

    private final String dbDriver="com.mysql.cj.jdbc.Driver";
    private final String host="localhost";
    private final String port = "3306";
    private final String user ="root";

    private final String pass="root";

    private final String db = "pasos";

    private Connection conn;
    private int cantCon;


    private FactoryConnection()
    {
        try{
            Class.forName(dbDriver);
            conn=null;
            cantCon=0;

        }

        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    private static FactoryConnection instancia;

    public static FactoryConnection getInstancia() //devuelve una creacion de instancia de conexion para conectarme a la db
    {
        if(instancia==null)
        {
            instancia = new FactoryConnection();
        }

        return instancia;
    }

    public static void cerrarConexion(ResultSet rs, Statement stmt)
    {
		try {

			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		FactoryConnection.getInstancia().releaseConn();

    }


    //metodo para realizar la conexion a la bd. con la instancia creada en el constructor
    //realiza la conexion propiamente dicha
    public Connection getConn(){
        try {
            if(conn==null || conn.isClosed()){
                conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db+"?user="+user+"&password="+pass);
                cantCon++;
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return conn;
    }


    //Mmetodo para cerrar la conexion
    public void releaseConn(){
        try {
            cantCon--;
            if(cantCon==0){
                conn.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    
   

}
