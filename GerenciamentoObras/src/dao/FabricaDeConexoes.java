package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class FabricaDeConexoes {
    // Design Patterns / Padrões de Projeto
    // Factory / Fabrica
    // Singleton / Único

    private static FabricaDeConexoes instance;

    private FabricaDeConexoes() {
    }

    public synchronized static FabricaDeConexoes getInstance() {
        if (instance == null) {
            instance = new FabricaDeConexoes();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        String url = "jdbc:sqlite:meuBanco.db";
        Connection con = DriverManager.getConnection(url);
        return con;
    }

}
