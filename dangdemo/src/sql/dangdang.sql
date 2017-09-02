create database if not exists dangdang default character set utf8;
use dangdang;


drop table if exists d_d_book;
drop table if exists d_d_comment_reply;
drop table if exists d_d_item;
drop table if exists d_d_receive_address;
drop table if exists d_d_send_way;



drop table if exists d_d_comment;
drop table if exists d_d_order;
drop table if exists d_d_product;
drop table if exists d_d_category;
drop table if exists d_d_user;




#
# Table structure for table d_d_book
#

DROP TABLE IF EXISTS d_d_book;
CREATE TABLE d_d_book (
  id int(12) NOT NULL,
  author varchar(200) NOT NULL,
  publishing varchar(200) NOT NULL,
  publish_time bigint(20) NOT NULL,
  word_number varchar(15) default NULL,
  which_edtion varchar(15) default NULL,
  total_page varchar(15) default NULL,
  print_time bigint(20) default NULL,
  isbn varchar(25) default NULL,
  author_summary text NOT NULL,
  catalogue text NOT NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table d_d_book
#

INSERT INTO d_d_book VALUES (1,'我写的','地球出版社',1237873000234,'1万','1','100',1,'12345678','作者是好人','这真是一本好书啊');
INSERT INTO d_d_book VALUES (2,'我写的','地球出版社',1237873000234,'1万','1','100',1,'12345678','作者是好人','这真是一本好书啊');
INSERT INTO d_d_book VALUES (3,'我写的','地球出版社',1237873000234,'1万','1','100',1,'12345678','作者是好人','这真是一本好书啊');
INSERT INTO d_d_book VALUES (4,'我写的','地球出版社',1237873000234,'1万','1','100',1,'12345678','作者是好人','这真是一本好书啊');
INSERT INTO d_d_book VALUES (5,'我写的','地球出版社',1237873000234,'1万','1','100',1,'12345678','作者是好人','这真是一本好书啊');
INSERT INTO d_d_book VALUES (6,'我写的','地球出版社',1237873000234,'1万','1','100',1,'12345678','作者是好人','这真是一本好书啊');
INSERT INTO d_d_book VALUES (7,'我写的','地球出版社',1237873000234,'1万','1','100',1,'12345678','作者是好人','这真是一本好书啊');
INSERT INTO d_d_book VALUES (8,'我写的','地球出版社',1237873000234,'1万','1','100',1,'12345678','作者是好人','这真是一本好书啊');
INSERT INTO d_d_book VALUES (9,'我写的','地球出版社',1237873000234,'1万','1','100',1,'12345678','作者是好人','这真是一本好书啊');
INSERT INTO d_d_book VALUES (10,'我写的','地球出版社',1237873000234,'1万','1','100',1,'12345678','作者是好人','这真是一本好书啊');
INSERT INTO d_d_book VALUES (11,'我写的','地球出版社',1237873000234,'1万','1','100',1,'12345678','作者是好人','这真是一本好书啊');
INSERT INTO d_d_book VALUES (12,'我写的','地球出版社',1237873000234,'1万','1','100',1,'12345678','作者是好人','这真是一本好书啊');
INSERT INTO d_d_book VALUES (13,'我写的','地球出版社',1237873000234,'1万','1','100',1,'12345678','作者是好人','这真是一本好书啊');
INSERT INTO d_d_book VALUES (14,'我写的','地球出版社',1237873000234,'1万','1','100',1,'12345678','作者是好人','这真是一本好书啊');
INSERT INTO d_d_book VALUES (15,'我写的','地球出版社',1237873000234,'1万','1','100',1,'12345678','作者是好人','这真是一本好书啊');
INSERT INTO d_d_book VALUES (16,'我写的','地球出版社',1237873000234,'1万','1','100',1,'12345678','作者是好人','这真是一本好书啊');
INSERT INTO d_d_book VALUES (17,'我写的','地球出版社',1237873000234,'1万','1','100',1,'12345678','作者是好人','这真是一本好书啊');
INSERT INTO d_d_book VALUES (18,'我写的','地球出版社',1237873000234,'1万','1','100',1,'12345678','作者是好人','这真是一本好书啊');
INSERT INTO d_d_book VALUES (19,'我写的','地球出版社',1237873000234,'1万','1','100',1,'12345678','作者是好人','这真是一本好书啊');
INSERT INTO d_d_book VALUES (20,'我写的','地球出版社',1237873000234,'1万','1','100',1,'12345678','作者是好人','这真是一本好书啊');
INSERT INTO d_d_book VALUES (21,'我写的','地球出版社',1237873000234,'1万','1','100',1,'12345678','作者是好人','这真是一本好书啊');
INSERT INTO d_d_book VALUES (22,'我写的','地球出版社',1237873000234,'1万','1','100',1,'12345678','作者是好人','这真是一本好书啊');
INSERT INTO d_d_book VALUES (23,'我写的','地球出版社',1237873000234,'1万','1','100',1,'12345678','作者是好人','这真是一本好书啊');
INSERT INTO d_d_book VALUES (24,'我写的','地球出版社',1237873000234,'1万','1','100',1,'12345678','作者是好人','这真是一本好书啊');

#
# Table structure for table d_d_category
#

DROP TABLE IF EXISTS d_d_category;
CREATE TABLE d_d_category (
  id int(12) NOT NULL auto_increment,
  turn int(3) default '10',
  en_name varchar(50) NOT NULL,
  cn_name varchar(50) NOT NULL,
  parent_id int(12),
  category_value varchar(200) default NULL,
  PRIMARY KEY  (id),
  UNIQUE KEY en_name (en_name),
  UNIQUE KEY category_value (category_value)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table d_d_category
#

INSERT INTO d_d_category VALUES (1,1,'Book','图书',null,'1');
INSERT INTO d_d_category VALUES (2,1,'Novel','小说',1,'1.2');
INSERT INTO d_d_category VALUES (3,2,'Youth','青春',1,'1.3');
INSERT INTO d_d_category VALUES (4,3,'Humanity And Social Science','人文社科',1,'1.4');
INSERT INTO d_d_category VALUES (5,4,'Management','管理',1,'1.5');
INSERT INTO d_d_category VALUES (6,5,'Children','少儿',1,'1.6');
INSERT INTO d_d_category VALUES (7,6,'Foreign Language','外语',1,'1.7');
INSERT INTO d_d_category VALUES (8,7,'Computer','计算机',1,'1.8');
INSERT INTO d_d_category VALUES (9,1,'Chinese Contemporary Novel','当代小说',2,'1.2.9');
INSERT INTO d_d_category VALUES (10,2,'Chinese Modern Novel','近现代小说',2,'1.2.10');
INSERT INTO d_d_category VALUES (11,3,'Chinese Classical  Novel','古典小说',2,'1.2.11');
INSERT INTO d_d_category VALUES (12,4,'Four Classic Novels','四大名著',2,'1.2.12');
INSERT INTO d_d_category VALUES (13,5,'Translated Works','世界名著',2,'1.2.13');
INSERT INTO d_d_category VALUES (14,1,'School','校园',3,'1.3.14');
INSERT INTO d_d_category VALUES (15,2,'Love','爱情/情感',3,'1.3.15');
INSERT INTO d_d_category VALUES (16,3,'Grow Up','叛逆/成长',3,'1.3.16');
INSERT INTO d_d_category VALUES (17,4,'Fantasy','玄幻',3,'1.3.17');
INSERT INTO d_d_category VALUES (18,5,'Original','原创',3,'1.3.18');
INSERT INTO d_d_category VALUES (19,1,'Political','政治',4,'1.4.19');
INSERT INTO d_d_category VALUES (20,2,'Economic','经济',4,'1.4.20');
INSERT INTO d_d_category VALUES (21,3,'Law','法律',4,'1.4.21');
INSERT INTO d_d_category VALUES (22,4,'Philosophy','哲学',4,'1.4.22');
INSERT INTO d_d_category VALUES (23,5,'History','历史',4,'1.4.23');
INSERT INTO d_d_category VALUES (24,1,'Commen Management','管理学',5,'1.5.24');
INSERT INTO d_d_category VALUES (25,2,'Strategic Management','战略管理',5,'1.5.25');
INSERT INTO d_d_category VALUES (26,3,'Marketing','市场营销',5,'1.5.26');
INSERT INTO d_d_category VALUES (27,4,'Business History','商业史传',5,'1.5.27');
INSERT INTO d_d_category VALUES (28,5,'E-Business','电子商务',5,'1.5.28');
INSERT INTO d_d_category VALUES (29,1,'0-2','0-2岁',6,'1.6.29');
INSERT INTO d_d_category VALUES (30,2,'3-6','3-6岁',6,'1.6.30');
INSERT INTO d_d_category VALUES (31,3,'7-10','7-10岁',6,'1.6.31');
INSERT INTO d_d_category VALUES (32,4,'11-14','11-14岁',6,'1.6.32');
INSERT INTO d_d_category VALUES (33,5,'Child Literature','儿童文学',6,'1.6.33');
INSERT INTO d_d_category VALUES (34,1,'English','英语',7,'1.7.34');
INSERT INTO d_d_category VALUES (35,2,'Japanese','日语',7,'1.7.35');
INSERT INTO d_d_category VALUES (36,3,'Korean','韩语',7,'1.7.36');
INSERT INTO d_d_category VALUES (37,4,'Russian','俄语',7,'1.7.37');
INSERT INTO d_d_category VALUES (38,5,'German','德语',7,'1.7.38');
INSERT INTO d_d_category VALUES (39,1,'Computer Theory','计算机理论',8,'1.8.39');
INSERT INTO d_d_category VALUES (40,2,'Database','数据库',8,'1.8.40');
INSERT INTO d_d_category VALUES (41,3,'Programming','程序设计',8,'1.8.41');
INSERT INTO d_d_category VALUES (42,4,'Artificial Intelligence','人工智能',8,'1.8.42');
INSERT INTO d_d_category VALUES (43,5,'Computer Examination','计算机考试',8,'1.8.43');

#
# Table structure for table d_d_comment
#


#
# Table structure for table d_d_product
#

DROP TABLE IF EXISTS d_d_product;
CREATE TABLE d_d_product (
  id int(12) NOT NULL auto_increment,
  category_id int(12) NOT NULL,
  product_name varchar(100) NOT NULL,
  description varchar(100) default NULL,
  add_time bigint(20) default NULL,
  fixed_price double NOT NULL,
  dang_price double NOT NULL,
  keywords varchar(200) default NULL,
  has_deleted int(1) NOT NULL default '0',
  product_pic varchar(200) default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table d_d_product
#

INSERT INTO d_d_product VALUES (1,9,'上课头疼的故事','上课头疼的故事',1237873000234,200,180,'key',0,'20.jpg');
INSERT INTO d_d_product VALUES (2,9,'上课头疼的故事','上课头疼的故事',1237873000234,200,179,'key',0,'19.jpg');
INSERT INTO d_d_product VALUES (3,9,'上课头疼的故事','上课头疼的故事',1237873000234,200,178,'key',0,'18.jpg');
INSERT INTO d_d_product VALUES (4,9,'上课头疼的故事','上课头疼的故事',1237873000234,200,177,'key',0,'17.jpg');
INSERT INTO d_d_product VALUES (5,9,'上课头疼的故事','上课头疼的故事',1237873000234,200,176,'key',0,'16.jpg');
INSERT INTO d_d_product VALUES (6,9,'上课头疼的故事','上课头疼的故事',1237873000234,200,175,'key',0,'15.jpg');
INSERT INTO d_d_product VALUES (7,9,'上课头疼的故事','上课头疼的故事',1237873000234,200,180,'key',0,'14.jpg');
INSERT INTO d_d_product VALUES (8,9,'上课头疼的故事','上课头疼的故事',1237873000234,200,180,'key',0,'13.jpg');
INSERT INTO d_d_product VALUES (9,9,'上课头疼的故事','上课头疼的故事',1237873000234,200,180,'key',0,'12.jpg');
INSERT INTO d_d_product VALUES (10,9,'上课头疼的故事','上课头疼的故事',1237873000234,200,180,'key',0,'11.jpg');
INSERT INTO d_d_product VALUES (11,9,'上课头疼的故事','上课头疼的故事',1237873000234,200,180,'key',0,'10.jpg');
INSERT INTO d_d_product VALUES (12,10,'上课睡觉的故事','上课睡觉的故事',1237873000234,200,180,'key',0,'9.jpg');
INSERT INTO d_d_product VALUES (13,10,'上课睡觉的故事','上课睡觉的故事',1237873000234,200,168,'key',0,'8.jpg');
INSERT INTO d_d_product VALUES (14,10,'上课睡觉的故事','上课睡觉的故事',1237873000234,200,178,'key',0,'7.jpg');
INSERT INTO d_d_product VALUES (15,10,'上课睡觉的故事','上课睡觉的故事',1237873000234,200,165,'key',0,'6.jpg');
INSERT INTO d_d_product VALUES (16,10,'上课睡觉的故事','上课睡觉的故事',1237873000234,200,188,'key',0,'5.jpg');
INSERT INTO d_d_product VALUES (17,10,'上课睡觉的故事','上课睡觉的故事',1237873000234,200,157,'key',0,'4.jpg');
INSERT INTO d_d_product VALUES (18,10,'上课睡觉的故事','上课睡觉的故事',1237873000234,200,180,'key',0,'3.jpg');
INSERT INTO d_d_product VALUES (19,10,'上课睡觉的故事','上课睡觉的故事',1237873000234,200,180,'key',0,'2.jpg');
INSERT INTO d_d_product VALUES (20,10,'上课睡觉的故事','上课睡觉的故事',1237873000234,200,180,'key',0,'1.jpg');
INSERT INTO d_d_product VALUES (21,10,'上课睡觉的故事','上课睡觉的故事',1237873000234,200,180,'key',0,'21.jpg');
INSERT INTO d_d_product VALUES (22,10,'上课睡觉的故事','上课睡觉的故事',1237873000234,200,180,'key',0,'22.jpg');
INSERT INTO d_d_product VALUES (23,10,'上课睡觉的故事','上课睡觉的故事',1237873000234,200,180,'key',0,'23.jpg');
INSERT INTO d_d_product VALUES (24,10,'上课睡觉的故事','上课睡觉的故事',1237873000234,200,180,'key',0,'24.jpg');


DROP TABLE IF EXISTS d_d_comment;
CREATE TABLE d_d_comment (
  id int(12) NOT NULL auto_increment,
  product_id int(12) NOT NULL,
  user_id int(12) NOT NULL,
  comment_title varchar(25) NOT NULL,
  comment_content varchar(3000) NOT NULL,
  comment_time bigint(20) NOT NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table d_d_comment
#


#
# Table structure for table d_d_comment_reply
#

DROP TABLE IF EXISTS d_d_comment_reply;
CREATE TABLE d_d_comment_reply (
  id int(12) NOT NULL auto_increment,
  comment_id int(12) NOT NULL,
  user_id int(12) NOT NULL,
  reply_time bigint(20) default NULL,
  reply_content varchar(3000) NOT NULL,
  turn int(11) default NULL, 
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table d_d_comment_reply
#


#
# Table structure for table d_d_item
#

DROP TABLE IF EXISTS d_d_item;
CREATE TABLE d_d_item (
  id int(12) NOT NULL auto_increment,
  order_id int(10) NOT NULL,
  product_id int(12) NOT NULL,
  dang_price double NOT NULL,
  product_num int(10) NOT NULL default '1',
  amount double NOT NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table d_d_item
#


#
# Table structure for table d_d_order
#

DROP TABLE IF EXISTS d_d_order;
CREATE TABLE d_d_order (
  id int(10) NOT NULL auto_increment,
  user_id int(12) NOT NULL,
  status int(10) NOT NULL,
  order_time bigint(20) NOT NULL,
  order_desc varchar(100) default NULL,
  total_price double NOT NULL,
  
  send_fee double NOT NULL,
  send_id int(12) default NULL,
  
  order_price double NOT NULL,
  send_time bigint(20) default NULL,
  is_delete bit(1) default '\0',
  
  receive_id int(10) NOT NULL,
  receive_name varchar(100) default NULL,
  full_address varchar(200) default NULL,
  postal_code varchar(8) default NULL,
  mobile varchar(20) default NULL,
  phone varchar(20) default NULL,
  
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table d_d_order
#



#
# Table structure for table d_d_receive_address
#

DROP TABLE IF EXISTS d_d_receive_address;
CREATE TABLE d_d_receive_address (
  id int(12) NOT NULL auto_increment,
  user_id int(12) NOT NULL,
  receive_name varchar(20) NOT NULL,
  full_address varchar(200) NOT NULL,
  postal_code varchar(8) NOT NULL,
  mobile varchar(15) NOT NULL,
  phone varchar(20) default NULL,
  is_default int(1) default '0',
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table d_d_receive_address
#


#
# Table structure for table d_d_send_way
#

DROP TABLE IF EXISTS d_d_send_way;
CREATE TABLE d_d_send_way (
  id int(11) NOT NULL auto_increment,
  is_close bit(1) default '\0',
  send_way varchar(30) NOT NULL,
  send_desc varchar(100) default NULL,
  send_fee double NOT NULL default '0',
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table d_d_send_way
#


#
# Table structure for table d_d_user
#

DROP TABLE IF EXISTS d_d_user;
CREATE TABLE d_d_user (
  id int(12) NOT NULL auto_increment,
  email varchar(50) NOT NULL,
  nickname varchar(50) default NULL,
  password varchar(50) NOT NULL,
  is_email_verify int(1) default '0',
  email_verify_code varchar(50) default NULL,
  last_login_time bigint(20) default NULL,
  last_login_ip varchar(15) default NULL,
  PRIMARY KEY  (id),
  UNIQUE KEY email (email),
  UNIQUE KEY nickname (nickname)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table d_d_user
#

INSERT INTO d_d_user VALUES (1,'abc@abc.com','abc','kAFQmDzST7DWlj99KOF/cg==',1,'E1524274-6268-4920-AFBF-2B71491D4816',0,NULL);
INSERT INTO d_d_user VALUES (2,'whatisjava@163.com','java','k/cloHQj/hyIn0SLM9IfRg==',1,'3B81C845-CFBF-4874-853A-3379FEE356B7',0,NULL);


#
#  Foreign keys for table d_d_book
#

ALTER TABLE d_d_book
  ADD FOREIGN KEY (id) REFERENCES d_d_product (id) ON UPDATE CASCADE;

alter table d_d_category add(
	FOREIGN KEY (parent_id) REFERENCES d_d_category (id) ON UPDATE CASCADE
);
alter table d_d_product add(
	FOREIGN KEY (category_id) REFERENCES d_d_category (id) ON UPDATE CASCADE
);
alter table d_d_item add(
	FOREIGN KEY (order_id) REFERENCES d_d_order (id) ON UPDATE CASCADE
);
alter table d_d_receive_address add(
	FOREIGN KEY (user_id) REFERENCES d_d_user (id) ON UPDATE CASCADE
);

alter table d_d_comment add(
	FOREIGN KEY (product_id) REFERENCES d_d_product (id) ON UPDATE CASCADE,
	FOREIGN KEY (user_id) REFERENCES d_d_user (id) ON UPDATE CASCADE
);
alter table d_d_comment_reply add(
	FOREIGN KEY (comment_id) REFERENCES d_d_comment (id) ON UPDATE CASCADE,
	FOREIGN KEY (user_id) REFERENCES d_d_user (id) ON UPDATE CASCADE
);

select * from  d_d_book;