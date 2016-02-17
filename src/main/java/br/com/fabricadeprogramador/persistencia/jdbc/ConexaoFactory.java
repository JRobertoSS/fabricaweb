package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

	public static Connection getConnection() {
		
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/fabricaweb", "root", "1234");
		} catch (SQLException e) {
			//relançando a exception
			throw new RuntimeException(e);
		}
	}

}
