package com.iss.DAOimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.sql.DataSource;

import org.springframework.cache.annotation.Cacheable;

import com.iss.DAO.exchangeDAO;
import com.iss.model.currencyModel;

public class exchangeDAOImpl implements exchangeDAO {
	private DataSource dataSource;
	public static String tempScurr = "";

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public currencyModel findExchangeRates(String paramDate, String sCurr) {
		String sql = "SELECT * FROM CURRENCY WHERE CDATE = ? and SCURR= ?";
		String uSql = "SELECT * FROM CURRENCY WHERE CDATE = ? and TCURR =? and SCURR =?";
		PreparedStatement ps = null;

		System.out.println(paramDate + sCurr);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date sqlsDate = null;
		try {
			sqlsDate = new Date(sdf.parse(paramDate).getTime());
		} catch (ParseException e1) {

			e1.printStackTrace();
		}
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			if (!sCurr.equalsIgnoreCase("USD")) {
				ps = conn.prepareStatement(sql);
				tempScurr = sCurr;
				ps.setDate(1, sqlsDate);
				ps.setString(2, sCurr);
			} else if ((sCurr.equals("USD")) || (sCurr.equals("USD".toLowerCase()))) {
				ps = conn.prepareStatement(uSql);
				System.out.println(ps.toString());
				ps.setDate(1, sqlsDate);
				ps.setString(2, sCurr);
				ps.setString(3, tempScurr);
			}

			System.out.println(ps.toString());
			currencyModel currency = null;
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				System.out.println("\t" + rs.getDate(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t"
						+ rs.getString(5));

				currency = new currencyModel(rs.getDate(2), rs.getString(3), rs.getString(5), rs.getString(4));
				return currency;
			}

			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
