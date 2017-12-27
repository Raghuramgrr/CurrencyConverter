package com.iss.DAOimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.cache.annotation.Cacheable;

import com.iss.DAO.currencyDAO;
import com.iss.model.currencyModel;

public class currencyDAOImpl implements currencyDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * DAO Impl Hits the DB and Retrieves the data from db.
	 *
	 * Sample -
	 * http://ec2-**-**-98-69.compute-1.amazonaws.com:8080/converter/date?text=2017-01-01
	 * Returns all the Exchange rates on the specified Date
	 * 
	 * Try invalid Dates
	 * 
	 * @throws ParseException
	 *             If an Invalid Date is entered .
	 * 
	 */
	@Cacheable(value = "currencyCache", key = "#name")
	@Override
	public List<currencyModel> findByDate(String paramDate) {
		String sql = "SELECT * FROM CURRENCY WHERE CDATE = ?";
		List<currencyModel> listByDate = new ArrayList<currencyModel>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
			java.util.Date date = sdf.parse(paramDate);
			System.out.println(paramDate + "\t" + date);
			Date sqlDate = new Date(date.getTime());
			System.out.println(sqlDate);
			ps.setDate(1, sqlDate);

			currencyModel currency = null;
			System.out.println(ps.toString());

			ResultSet rs = ps.executeQuery();
			System.out.println(sqlDate + "\t" + rs.getFetchSize());
			while (rs.next()) {
				currency = new currencyModel(rs.getDate(2), rs.getString(3), rs.getString(5), rs.getString(4)

				);
				// slowQuery(2000L);
				listByDate.add(currency);
			}
			rs.close();
			ps.close();
			return listByDate;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ParseException e) {

			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return null;
	}

	@Override
	public List<currencyModel> findByDateRange(String sDate, String eDate) {
		List<currencyModel> listByDateRange = new ArrayList<currencyModel>();
		String sql = "SELECT * FROM CURRENCY WHERE CDATE between ? and ?";
		System.out.println(sDate + eDate);
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			java.sql.Date sqlsDate = new Date(sdf.parse(sDate).getTime());
			java.sql.Date sqleDate = new Date(sdf.parse(eDate).getTime());

			if (sqlsDate.before(sqleDate)) {
				ps.setDate(1, sqlsDate);
				ps.setDate(2, sqleDate);
			} else {
				return listByDateRange;
			}
			System.out.println(ps.toString());
			currencyModel currency = null;

			ResultSet rs = ps.executeQuery();

			System.out.println(rs.getRow());
			while (rs.next()) {
				currency = new currencyModel(rs.getDate(2), rs.getString(3), rs.getString(5), rs.getString(4)

				);
				listByDateRange.add(currency);
			}
			rs.close();
			ps.close();
			return listByDateRange;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	private void slowQuery(long seconds) {
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}

}