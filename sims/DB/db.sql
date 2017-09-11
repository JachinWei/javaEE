-- sims =  student information management system;
create database if not exists sims;
use sims;

-- 创建用户表
create table sims_user(
	id int not null primary key auto_increment comment '主键',
	username varchar(20) not null comment '用户名',
	password varchar(20) not null comment '密码',
	power_level tinyint(4) not null comment '权限级别-超级管理员=1，管理员=2，学生=3'
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

insert into sims_user (username, password, power_level) values 
	('root', 'root123', 1),
	('manager', 'manager123', 2),
	('曾小贤', 'zxx123', 3),
	('胡一菲', 'hyf123', 3),
	('张益达', 'zyd123', 3),
	('秦羽墨', 'qym123', 3);

-- 创建课程信息表 
create table sims_course(
	id int not null primary key auto_increment comment '主键',
	title varchar(20) not null comment '课程名',
	credit int(4) not null comment '学分'
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

insert into sims_course (title, credit) values 
	('高等数学', 3),
	('离散数学', 3),
	('Java', 3),
	('UML', 3);
	
-- 创建班级信息表 
create table sims_class(
	id int not null primary key auto_increment comment '主键',
	title varchar(20) not null comment '班级名',
	grade int(4) not null comment '所属年级' 
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

insert into sims_class (title, grade) values
	('软件1班', 2014),
	('软件2班', 2014),
	('软件3班', 2014),
	('软件4班', 2014);

-- 创建学生信息表 
create table sims_student(
	id int not null primary key auto_increment comment '主键',
	uid int not null comment 'sims_user外键',
	class_id int comment 'sims_class外键',
	name varchar(20) not null comment '姓名',
	stu_number varchar(20) not null comment '学号',
	email varchar(20) comment '邮箱',
	sex tinyint(4) default 0 comment '性别：0-保密，1-男，2-女',
	birthday date comment '生日',
	phone varchar(20) comment '手机号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into sims_student (uid, class_id, name, stu_number) values 
	(3, 4, '曾小贤', '201441404417'),
	(4, 4, '胡一菲', '201441404418'),
	(5, 4, '张益达', '201441404419'),
	(6, 4, '秦羽墨', '201441404420');
	
-- 创建学生选课成绩表 
create table sims_score(
	id int not null primary key auto_increment comment '主键',
	stu_id int not null comment '学生表外键',
	course_id int not null comment 'sims_course外键',
	semester tinyint(4) not null comment '学期：1-上学期，2-下学期',
	school_year tinyint(4) not null comment '学年：1-大一，2-大二，3-大三，依次类推',
	score float(5,2) not null default 0 comment '分数'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into sims_score (stu_id, course_id, semester, school_year, score) values
	(1, 1, 1, 2, 99.5),
	(1, 2, 1, 2, 99.5),
	(1, 3, 1, 2, 99.5),
	(1, 4, 1, 2, 99.5),
	(2, 1, 1, 2, 88.5),
	(2, 2, 1, 2, 88.5),
	(2, 3, 1, 2, 88.5),
	(2, 4, 1, 2, 88.5),
	(3, 1, 1, 2, 100),
	(3, 2, 1, 2, 100),
	(3, 3, 1, 2, 100),
	(3, 4, 1, 2, 100),
	(4, 1, 1, 2, 23),
	(4, 2, 1, 2, 23),
	(4, 3, 1, 2, 23),
	(4, 4, 1, 2, 23);
	
	