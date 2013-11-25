package com.github.nagaseyasuhito.camellia.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import com.github.nagaseyasuhito.camellia.jaxb.ISO8601DateAdapter;

@MappedSuperclass
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@EqualsAndHashCode(of = { "id" })
public abstract class BaseEntity implements Serializable {
	private static final long serialVersionUID = 305809933036192854L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@Version
	@XmlTransient
	private Long version;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@XmlJavaTypeAdapter(ISO8601DateAdapter.class)
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@XmlJavaTypeAdapter(ISO8601DateAdapter.class)
	private Date updatedAt;

	@PrePersist
	private void prePersist() {
		this.setCreatedAt(new Date(System.currentTimeMillis() / 1000L * 1000L));
		this.setUpdatedAt(this.getCreatedAt());
	}

	@PreUpdate
	private void preUpdate() {
		this.setUpdatedAt(new Date(System.currentTimeMillis() / 1000L * 1000L));
	}
}
