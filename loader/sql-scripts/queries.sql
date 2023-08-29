create database student_loader
create table student_details(
 id INT PRIMARY KEY auto_increment,
 name varchar(200) ,
 age int ,
 class int
)
create table student_address(
id int PRIMARY KEY auto_increment,
student_id INT,
street varchar(200),
city varchar(200),
state varchar(200),
zipcode int ,
FOREIGN KEY (student_id) REFERENCES student_details(id)
)

create table student_hobbies(
id int PRIMARY KEY auto_increment,
student_id INT,
hobby1 varchar(100),
hobby2 varchar(100),
hobby3 varchar(100),

FOREIGN KEY(student_id) REFERENCES student_details(id)
)

-- Get details

select  d.id,name,age,class,a.id address_id, street,city,state,zipcode,h.id hobby_id,hobby1,hobby2,hobby3
 from student_details d join student_address a on d.id = a.student_id
join student_hobbies h on d.id=h.student_id;

