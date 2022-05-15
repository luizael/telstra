package com.telstra.codechallenge.repositories;

import java.io.Serializable;
import java.util.Objects;

public class GitRepository implements GitOperable, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String html_url;
	private String watchers_count;
	private String language;
	private String description;
	private String name;
	
	public String getHtml_url() {
		return html_url;
	}
	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}
	public String getWatchers_count() {
		return watchers_count;
	}
	public void setWatchers_count(String watchers_count) {
		this.watchers_count = watchers_count;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		return Objects.hash(description, html_url, language, name, watchers_count);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GitRepository other = (GitRepository) obj;
		return Objects.equals(description, other.description) && Objects.equals(html_url, other.html_url)
				&& Objects.equals(language, other.language) && Objects.equals(name, other.name)
				&& Objects.equals(watchers_count, other.watchers_count);
	}
	
	
}
