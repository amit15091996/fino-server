package com.fino.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "FINO_CLIENT_DETAILS")
@Entity
public class ClientDetails {
    @TableGenerator(allocationSize = 10, initialValue = 2001, name = "fino_client_sequence")
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "fino_client_sequence")
    private Long clientId;
    @Column(length = 200)
    private String clientName;
    @Column(length = 200)
    private String bankName;
}
