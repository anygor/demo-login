package com.empathic.demologin.service;

import com.empathic.demologin.model.shiro.ShiroClient;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class LoginService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public ShiroClient getShiroClientByUsername(String username) {
		List<ShiroClient> list = new LinkedList<>();
		jdbcTemplate.query("SELECT id, version, password_hash, username, client_id, salt, is_generated_password FROM shiro_client WHERE username = '" + username + "'", rs -> {
			list.add(new ShiroClient(rs.getLong(1), rs.getLong(2), rs.getString(3), rs.getString(4), rs.getLong(5), rs.getString(6), rs.getBoolean(7)));
		});
		return list.size() > 0 ? list.get(0) : null;
	}

	public String getCipher(String line, String salt) {
		salt = salt + "OTE4ZTYzZDUtYzQyNi00MzdmLWFkNGQtZThiMTA4MjUxNWM4";
		return new Sha256Hash(line, salt, 1024).toBase64();
	}
}
