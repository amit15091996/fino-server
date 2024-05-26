package com.fino.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "FINO_USERS_DETAILS")
@Entity
public class FinoUserDetails implements UserDetails {

	private static final long serialVersionUID = 5360359571813027649L;

	@TableGenerator(allocationSize = 1, initialValue = 234567, name = "fino_user_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "fino_user_sequence")
	private Long finoUserId;

	@Column(length = 50, nullable = false)
	private String firstName;
	@Column(length = 20, nullable = false)
	private String lastName;
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@Column(nullable = false)
	private LocalDate dateOfBirth;
	@Column(length = 1000, nullable = false)
	private String Password;
	@Column(length = 10, nullable = false, unique = true)
	private String mobileNumber;
	@Column(length = 50, nullable = false)
	private String emailId;
	@Column(columnDefinition = "BOOLEAN", nullable = false)
	private boolean isActive;

	@OneToMany(mappedBy = "finoUserDetails", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<FinoUserRoles> finoUserRoles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.finoUserRoles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).toList();
	}

	@Override
	public String getUsername() {

		return this.mobileNumber;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {

		return this.isActive;
	}

}
