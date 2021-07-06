package com.empathic.demologin.model.shiro;

public class ShiroClient {
	private Long id;
	private Long version;
	private String passwordHash;
	private String username;
	private Long clientId;
	private String salt;
	private Boolean isGeneratedPassword;

	@Override
	public String toString() {
		return String.format(
				"ShiroClient[id=%d, version=%d, username='%s', clientId=%d, isGeneratedPassword=%b",
				id, version, username, clientId, isGeneratedPassword);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (!(obj instanceof ShiroClient)) {
			return false;
		} else {
			return
			this.id.equals(((ShiroClient) obj).getId()) &&
			this.version.equals(((ShiroClient) obj).getVersion()) &&
			this.passwordHash.equals(((ShiroClient) obj).getPasswordHash()) &&
			this.username.equals(((ShiroClient) obj).getUsername()) &&
			this.clientId.equals(((ShiroClient) obj).getClientId()) &&
			this.salt.equals(((ShiroClient) obj).getSalt()) &&
			this.isGeneratedPassword.equals(((ShiroClient) obj).getGeneratedPassword());
		}
	}

	public ShiroClient(Long id, Long version, String passwordHash, String username, Long clientId, String salt, Boolean isGeneratedPassword) {
		this.id = id;
		this.version = version;
		this.passwordHash = passwordHash;
		this.username = username;
		this.clientId = clientId;
		this.salt = salt;
		this.isGeneratedPassword = isGeneratedPassword;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Boolean getGeneratedPassword() {
		return isGeneratedPassword;
	}

	public void setGeneratedPassword(Boolean generatedPassword) {
		isGeneratedPassword = generatedPassword;
	}
}
