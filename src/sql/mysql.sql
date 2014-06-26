drop database if exists eduplat;
create database eduplat character set utf8;
use eduplat;

/*==============================================================*/
/* Table: t_category    类别表                                        */
/*==============================================================*/
drop table if exists t_category;
create table t_category
(
   id              int not null AUTO_INCREMENT,
   name            char(100) not null,
   desc            varchar(255) default null,
   primary key (id),
   UNIQUE KEY (name)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: t_case    具体案例case表                                        */
/*==============================================================*/
drop table if exists t_case;
create table t_case
(
   id              int not null AUTO_INCREMENT,
   cId             int(20) not null,
   name            char(100) not null,
   design          char(100) not null,
   categoryId      int(20) not null,
   content         varchar(1000) not null,
   recommend       int(10) default 0,
   imgPath         varchar(255) not null,
   url             char(100) not nul,
   primary key (id)   
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


/*==============================================================*/
/* Table: t_catecategory    具体案例属于哪个类别表                                        */
/*==============================================================*/
drop table if exists t_catecategory;
create table t_catecategory
(
   id              int not null AUTO_INCREMENT,
   name            char(100) not null,
   primary key (id),
   UNIQUE KEY (name)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: t_casecontent    具体课程表                                        */
/*==============================================================*/
drop table if exists t_casecontent;
create table t_casecontent
(
   id              int not null AUTO_INCREMENT,
   cId             int(20) not null,
   name            char(100) not null,   
   content         varchar(1000) not null,  
   imgPath         varchar(255) not null, 
   url             char(100) not null,
   primary key (id)   
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
