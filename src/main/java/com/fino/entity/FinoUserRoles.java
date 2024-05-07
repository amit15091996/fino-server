package com.fino.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FINO_USERS_ROLES_DETAILS")
@Entity
public class FinoUserRoles {

	@TableGenerator(allocationSize = 10, initialValue =2001, name = "fino_user_role_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "fino_user_role_sequence")
	private Long finoUserRolesId;
	@Column(length = 50,nullable = false)
	private String roleName;
	@Column(length = 500,nullable = false)
	private String roleDescription;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private FinoUserDetails finoUserDetails;
}
