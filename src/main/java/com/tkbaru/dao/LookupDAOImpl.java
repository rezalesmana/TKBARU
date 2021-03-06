package com.tkbaru.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.tkbaru.model.Lookup;

public class LookupDAOImpl implements LookupDAO {

	private static final Logger logger = LoggerFactory.getLogger(LookupDAOImpl.class);
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Lookup> getLookupByCategory(String categoryCode) {
		List<Lookup> result = new ArrayList<Lookup>();
		
		String sqlquery = "SELECT * FROM tb_lookup WHERE UCASE(category) = UCASE('" + categoryCode + "')";
	
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlquery);
		
		for (Map<String, Object> row : rows) {
			Lookup res = new Lookup();
			res.setLookupId(Integer.valueOf(String.valueOf(row.get("lookup_id"))));
			res.setLookupCategory(String.valueOf(row.get("category")));
			res.setLookupCode(String.valueOf(row.get("lookup_code")));
			res.setShortVal(String.valueOf(row.get("short_val")));
			res.setLongVal(String.valueOf(row.get("long_val")));
			res.setLookupDescription(String.valueOf(row.get("description")));
			res.setOrderNum(Integer.valueOf(String.valueOf(row.get("order_num"))));
			res.setLookupStatus(String.valueOf(row.get("status")));
			res.setLookupMaintainability(String.valueOf(row.get("maintainable")));
			
			result.add(res);
		}		
		
		return result;
	}

	@Override
	public List<Lookup> getAllLookup() {
		List<Lookup> result = new ArrayList<Lookup>();
		
		String sqlquery = "SELECT * FROM tb_lookup";
	
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlquery);
		
		for (Map<String, Object> row : rows) {
			Lookup res = new Lookup();
			res.setLookupId(Integer.valueOf(String.valueOf(row.get("lookup_id"))));
			res.setLookupCategory(String.valueOf(row.get("category")));
			res.setLookupCode(String.valueOf(row.get("lookup_code")));
			res.setShortVal(String.valueOf(row.get("short_val")));
			res.setLongVal(String.valueOf(row.get("long_val")));
			res.setLookupDescription(String.valueOf(row.get("description")));
			res.setOrderNum(Integer.valueOf(String.valueOf(row.get("order_num"))));
			res.setLookupStatus(String.valueOf(row.get("status")));
			res.setLookupMaintainability(String.valueOf(row.get("maintainable")));
			
			result.add(res);
		}		
		
		return result;
	}

	@Override
	public Lookup getLookupById(int selectedId) {
		Lookup result = new Lookup();
		
		String sqlquery = 
				"SELECT lookup_id,       "+
				"		category,        "+
				"		lookup_code,     "+
				"       short_val,       "+
				"       long_val,        "+
				"       description,     "+
				"       order_num,       "+
				"       status,          "+
				"       maintainable     "+
				"FROM tb_lookup          "+
				"WHERE lookup_id = ?     ";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		result = jdbcTemplate.queryForObject(sqlquery, new Object[] { selectedId }, new RowMapper<Lookup>() {
			@Override
			public Lookup mapRow(ResultSet rs, int rowNum) throws SQLException {
				Lookup l = new Lookup();
				
				l.setLookupId(rs.getInt("lookup_id"));
				l.setLookupCategory(rs.getString("category"));
				l.setLookupCode(rs.getString("lookup_code"));
				l.setShortVal(rs.getString("short_val"));
				l.setLongVal(rs.getString("long_val"));
				l.setLookupDescription(rs.getString("description"));
				l.setOrderNum(rs.getInt("order_num"));
				l.setLookupStatus(rs.getString("status"));
				l.setLookupMaintainability(rs.getString("maintainable"));
				
				return l;
			}
		});
				
		return result;
	}

	@Override
	public List<Lookup> getAllCategory() {
		List<Lookup> result = new ArrayList<Lookup>();
		
		String sqlquery = "SELECT DISTINCT category FROM tb_lookup";
	
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlquery);
		
		for (Map<String, Object> row : rows) {
			Lookup res = new Lookup();
			res.setLookupCategory(String.valueOf(row.get("category")));
			
			result.add(res);
		}		
		
		return result;
	}

	@Override
	public void addLookup(Lookup lookup) {
        String sql = "INSERT INTO tb_lookup (category, lookup_code, short_val, long_val, description, order_num, status, maintainable) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        try {
        	jdbcTemplate.update(sql, new Object[] { lookup.getLookupCategory(), lookup.getLookupCode(), lookup.getShortVal(), lookup.getLongVal(),
        											lookup.getLookupDescription(), lookup.getOrderNum(), lookup.getLookupStatus(), lookup.getLookupMaintainability() });
        } catch(Exception err) {
        	logger.info ("Error : " + err.getMessage());
        }
	}

	@Override
	public void editLookup(Lookup lookup) {
        String query = "UPDATE tb_lookup SET category = ?, lookup_code = ?, short_val = ?, long_val = ?, description = ?, order_num = ?, status = ?, maintainable = ? " +
				"WHERE lookup_id = ? ";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        int out = 0;

        try {
        	Object[] args = new Object[] { 	lookup.getLookupCategory(), lookup.getLookupCode(), lookup.getShortVal(), lookup.getLongVal(),
											lookup.getLookupDescription(), lookup.getOrderNum(), lookup.getLookupStatus(), lookup.getLookupMaintainability(), lookup.getLookupId() };

        	out = jdbcTemplate.update(query, args);        	
        } catch (Exception err) {
        	logger.info ("Error : " + err.getMessage());
        }

        logger.info("Lookup updated successfully, row updated : " + out);
	}

	@Override
	public void deleteLookup(int selectedId) {
        String query = "DELETE FROM tb_lookup WHERE lookup_id = ? ";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
         
        int out = 0;
        
        try {
        	out = jdbcTemplate.update(query, selectedId);
        } catch (Exception err) {
        	logger.info ("Error : " + err.getMessage());
        }

        logger.info("Lookup deleted successfully, row deleted : " + out);
	}

}
