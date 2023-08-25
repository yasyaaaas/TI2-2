// package com.ti2cc;

import java.sql.*;

public class DAO {
	private Connection conexao;

	public DAO() {
		conexao = null;
	}

	public boolean conectar() {
		String driverName = "org.postgresql.Driver";
		String serverName = "localhost";
		String mydatabase = "Cd";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		}

		catch (ClassNotFoundException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		}

		catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}

	public boolean close() {
		boolean status = false;

		try {
			conexao.close();
			status = true;
		}

		catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return status;
	}

	public boolean inserirCd(Cd cd) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO cd (id, modelo, ano) "
					+ "VALUES (" + cd.getId() + ", '" + cd.getModelo() + "', '"
					+ cd.getAno());
			st.close();
			status = true;
		}

		catch (SQLException u) {
			throw new RuntimeException(u);
		}

		return status;
	}

	public boolean atualizarCd(Cd cd) {
		boolean status = false;

		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE Cd SET modelo = '" + cd.getModelo() + "', senha = '"
					+ cd.getAno() + "'"
					+ " WHERE id = " + cd.getId();
			st.executeUpdate(sql);
			st.close();
			status = true;
		}

		catch (SQLException u) {
			throw new RuntimeException(u);
		}

		return status;
	}

	public boolean excluirCd(int id) {
		boolean status = false;

		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM moto WHERE id = " + id);
			st.close();
			status = true;
		}

		catch (SQLException u) {
			throw new RuntimeException(u);
		}

		return status;
	}

	public Cd[] getCd() {
		Cd[] Cd = null;

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM Cd");

			if (rs.next()) {
				rs.last();
				CDs = new Cd[rs.getRow()];
				rs.beforeFirst();

				for (int i = 0; rs.next(); i++) {
					CDs[i] = new Cd(rs.getInt("id"), rs.getString("modelo"),
							rs.getInt("ano"));
				}
			}

			st.close();
		}

		catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return CDs;
	}
}
