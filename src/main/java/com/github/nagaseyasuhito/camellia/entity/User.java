package com.github.nagaseyasuhito.camellia.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlTransient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Wither;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Wither
public class User extends BaseEntity {
	private static final long serialVersionUID = 952230607047813111L;

	@Column(nullable = false, unique = true)
	@Pattern(regexp = "[\\p{Alnum}]*")
	private String name;

	@Column(nullable = false)
	@XmlTransient
	private String password;

	@Column(nullable = false)
	private String apikey;

	public User(String name, String password) {
		this.name = name;
		this.password = password;
		this.apikey = UUID.randomUUID().toString().replaceAll("-", "");
	}
}
