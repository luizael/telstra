package com.telstra.codechallenge.repositories;

import java.io.Serializable;
import java.util.Objects;

public class GitUser implements GitOperable, Serializable{
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private String id;
	private String login;
	private String html_url;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getHtml_url() {
		return html_url;
	}
	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}
	@Override
	public int hashCode() {
		return Objects.hash(html_url, id, login);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GitUser other = (GitUser) obj;
		return Objects.equals(html_url, other.html_url) && Objects.equals(id, other.id)
				&& Objects.equals(login, other.login);
	}
}
