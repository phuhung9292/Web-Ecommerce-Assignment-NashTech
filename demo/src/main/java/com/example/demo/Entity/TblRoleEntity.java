package com.example.demo.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_Role", schema = "dbo", catalog = "EcommerceNashTech")
public class TblRoleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "typeRole")
    private String typeRole;
    @Basic
    @Column(name = "isActive")
    private Boolean isActive;
    @OneToMany(mappedBy = "tblRoleByRoleId")
    private Collection<TblUserEntity> tblUsersById;

    }
