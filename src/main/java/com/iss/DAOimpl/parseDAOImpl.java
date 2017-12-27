package com.iss.DAOimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.iss.DAO.parseDAO;
import com.iss.model.currencyModel;

public class parseDAOImpl implements parseDAO {
	private DataSource dataSource;
	public List<currencyModel> listByDate = new ArrayList<currencyModel>();

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insert(currencyModel currency) {
		String sql = "INSERT INTO CURRENCY " + "(CDATE, SCURR, TCURR,RATE) VALUES (?, ?, ?,?)";
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			java.util.Date utilStartDate = currency.getDate();
			java.sql.Date sqlDate = new java.sql.Date(utilStartDate.getTime());
			ps.setDate(1, sqlDate);
			ps.setString(2, currency.getSourceCurrency());
			ps.setString(3, currency.getTargetCurrency());
			ps.setString(4, currency.getConversionRate());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

	}

	@Override
	public void truncateTable() {
		System.out.println("In Truncation table");
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String del = "TRUNCATE TABLE CURRENCY";

		try {
			PreparedStatement del_ps = conn.prepareStatement(del);
			del_ps.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
