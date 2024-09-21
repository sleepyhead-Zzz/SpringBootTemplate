create table if not exists springboottemplate.repair
(
    repair_id         bigint auto_increment
        primary key,
    device_type       varchar(50)          null,
    reporter          varchar(50)          null,
    device_name       varchar(50)          null,
    issue_description varchar(100)         null,
    location          varchar(50)          null,
    dept_id           bigint               null,
    creator_id        bigint               null,
    create_time       datetime             null,
    updater_id        bigint               null,
    update_time       datetime             null,
    deleted           tinyint(1) default 0 null
);

create table if not exists springboottemplate.sys_user
(
    user_id      bigint auto_increment comment '用户ID'
        primary key,
    post_id      bigint                  null comment '职位id',
    role_id      bigint                  null comment '角色id',
    dept_id      bigint                  null comment '部门ID',
    username     varchar(64)             not null comment '用户账号',
    nickname     varchar(32)             not null comment '用户昵称',
    user_type    smallint     default 0  null comment '用户类型（00系统用户）',
    email        varchar(128) default '' null comment '用户邮箱',
    phone_number varchar(18)  default '' null comment '手机号码',
    sex          smallint     default 0  null comment '用户性别（0男 1女 2未知）',
    avatar       varchar(512) default '' null comment '头像地址',
    password     varchar(128) default '' not null comment '密码',
    status       smallint     default 0  not null comment '帐号状态（1正常 2停用 3冻结）',
    login_ip     varchar(128) default '' null comment '最后登录IP',
    login_date   datetime                null comment '最后登录时间',
    is_admin     tinyint(1)   default 0  not null comment '超级管理员标志（1是，0否）',
    creator_id   bigint                  null comment '更新者ID',
    create_time  datetime                null comment '创建时间',
    updater_id   bigint                  null comment '更新者ID',
    update_time  datetime                null comment '更新时间',
    remark       varchar(512)            null comment '备注',
    deleted      tinyint(1)   default 0  not null comment '删除标志（0代表存在 1代表删除）'
)
    comment '用户信息表';

create table springboottemplate.sys_dept
(
    dept_id     bigint auto_increment comment '部门id'
        primary key,
    parent_id   bigint      default 0  not null comment '父部门id',
    ancestors   text                   not null comment '祖级列表',
    dept_name   varchar(64) default '' not null comment '部门名称',
    order_num   int         default 0  not null comment '显示顺序',
    leader_id   bigint                 null,
    leader_name varchar(64)            null comment '负责人',
    phone       varchar(16)            null comment '联系电话',
    email       varchar(128)           null comment '邮箱',
    status      smallint    default 0  not null comment '部门状态（0停用 1启用）',
    creator_id  bigint                 null comment '创建者ID',
    create_time datetime               null comment '创建时间',
    updater_id  bigint                 null comment '更新者ID',
    update_time datetime               null comment '更新时间',
    deleted     tinyint(1)  default 0  not null comment '逻辑删除'
)
    comment '部门表';



insert into springboottemplate.sys_user (user_id, post_id, role_id, dept_id, username, nickname,
                                         user_type, email, phone_number, sex, avatar, password,
                                         status, login_ip, login_date, is_admin, creator_id,
                                         create_time, updater_id, update_time, remark, deleted)
values (2, null, null, null, 'admin', 'superadmin', 0, '', '123456', 0, '',
        '$2a$10$MFUKIUS2wS/khRSRlW.VseoEN9QjqkTeq9b7eNmt.FxAZyQck3c1a', 1, '127.0.0.1',
        '2024-09-18 11:04:57', 0, null, '2024-09-17 11:28:31', 2, '2024-09-18 11:04:57', '', 0);