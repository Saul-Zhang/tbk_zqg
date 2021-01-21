package com.orange.tbk.api.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_user")
@Entity
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column
    private String username;

    @Column
    private String password;

//    @Column
//    private String nickname;

//    @Column
//    private Integer gender;

    @Column
    private Integer status;

    @Column
    private String mobile;

    @Column
    private String avatar;

    @Column(name = "realname_auth")
    private String realnameAuth;

//    @Column(name = "my_invite_code")
//    private String myInviteCode;

    @Column(name = "inviter_id")
    private String inviterId;

    @Column(name = "register_date")
    private Date registerDate;

    @Column(name = "comment")
    private String comment;

    private String token;

    private String grade;

//    @Column(name = "relation_id",length = 50)
//    private String relationId;

    @Column(name = "adzone_id",length = 12)
    private String adzoneId;
}
