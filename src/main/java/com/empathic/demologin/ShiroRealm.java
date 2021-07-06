package com.empathic.demologin;

import com.empathic.demologin.model.shiro.ShiroClient;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class ShiroRealm extends JdbcRealm {

	private List<ShiroClient> clients = new LinkedList<>();
	private Map<String, String> credentials = new HashMap<>();

	@Autowired
	public ShiroRealm(JdbcTemplate jdbcTemplate) {
		jdbcTemplate.query(
				"SELECT username, password_hash FROM shiro_client",
				resultSet -> {
					credentials.put(resultSet.getString(1), resultSet.getString(2));
				});
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		if (usernamePasswordToken.getUsername() == null || usernamePasswordToken.getUsername().isEmpty() ||
			!credentials.containsKey(usernamePasswordToken.getUsername())) {
			throw new UnknownAccountException();
		}
		return new SimpleAuthenticationInfo(usernamePasswordToken.getUsername(),
				credentials.get(usernamePasswordToken.getUsername()), getName());
	}
}