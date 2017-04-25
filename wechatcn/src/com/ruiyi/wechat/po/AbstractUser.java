package com.ruiyi.wechat.po;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractUser entity provides the base persistence definition of the User
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUser implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private String username;
	private String password;
	private Boolean activate;
	private String email;
	private String phone;
	private Timestamp creationDate;
	private String creationIp;
	private Float balance;
	private Short simnum;
	private String qq;
	private String wechat;
	private Set userParameters = new HashSet(0);
	private Set consumes = new HashSet(0);
	private Set userRoles = new HashSet(0);
	private Set users = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractUser() {
	}

	/** minimal constructor */
	public AbstractUser(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/** full constructor */
	public AbstractUser(User user, String username, String password,
			Boolean activate, String email, String phone,
			Timestamp creationDate, String creationIp, Float balance,
			Short simnum, String qq, String wechat, Set userParameters,
			Set consumes, Set userRoles, Set users) {
		this.user = user;
		this.username = username;
		this.password = password;
		this.activate = activate;
		this.email = email;
		this.phone = phone;
		this.creationDate = creationDate;
		this.creationIp = creationIp;
		this.balance = balance;
		this.simnum = simnum;
		this.qq = qq;
		this.wechat = wechat;
		this.userParameters = userParameters;
		this.consumes = consumes;
		this.userRoles = userRoles;
		this.users = users;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getActivate() {
		return this.activate;
	}

	public void setActivate(Boolean activate) {
		this.activate = activate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Timestamp getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreationIp() {
		return this.creationIp;
	}

	public void setCreationIp(String creationIp) {
		this.creationIp = creationIp;
	}

	public Float getBalance() {
		return this.balance;
	}

	public void setBalance(Float balance) {
		this.balance = balance;
	}

	public Short getSimnum() {
		return this.simnum;
	}

	public void setSimnum(Short simnum) {
		this.simnum = simnum;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return this.wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public Set getUserParameters() {
		return this.userParameters;
	}

	public void setUserParameters(Set userParameters) {
		this.userParameters = userParameters;
	}

	public Set getConsumes() {
		return this.consumes;
	}

	public void setConsumes(Set consumes) {
		this.consumes = consumes;
	}

	public Set getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set userRoles) {
		this.userRoles = userRoles;
	}

	public Set getUsers() {
		return this.users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}

}