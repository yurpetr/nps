package com.yurpetr.nps.model;

import java.io.Serializable;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "nps_users")
public class User implements Serializable {
	public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getLogin() {
      return login;
   }

   public void setLogin(String login) {
      this.login = login;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getPointOfSale() {
      return pointOfSale;
   }

   public void setPointOfSale(String pointOfSale) {
      this.pointOfSale = pointOfSale;
   }

   public String getPowerBiUrl() {
      return powerBiUrl;
   }

   public void setPowerBiUrl(String powerBiUrl) {
      this.powerBiUrl = powerBiUrl;
   }

   public Collection<Role> getRoles() {
      return roles;
   }

   public void setRoles(Collection<Role> roles) {
      this.roles = roles;
   }

   private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty(message = "Логин обязателен")
	@Column(nullable = false, unique = true)
	private String login;
	@NotEmpty(message = "Задай пароль")
	@Column(nullable = false)
	private String password;
	@NotEmpty(message = "Точка продаж")
	@Column(nullable = false)
	private String pointOfSale;
		
	private String powerBiUrl;

	@ManyToMany
	@JoinTable(name = "nps_users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles;

	
	
}
