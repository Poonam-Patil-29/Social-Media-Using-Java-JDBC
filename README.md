# Social-Media-Using-Java-JDBC
### Project Description :
Created a Facebook Management using Java and MySQL. Data entered by the user are stored in MYSQL database in tabular form.The Best Part of this code is that it is 100 % user friendly because of excess use of exceptional handling.Only authorized Users can have the accessibility to the program.After successful login user can able to view his own profile,user can able to update his account, user can able to delete account,user can search the profile of others,can able to create post,can able to see timeline and also use can see the posts which are created by other users.

### Requirements :
* Eclipse IDE
* MYSQL Workbeanch
* Java JDBC Connector

### Module Used :
* Date and Time
* MYSQL Connector

### About Mysql :
* Database - facebook
* host - localhost
* user - root
* password - root

Email is the Primary Key in userinformation and User_email is the Foreign key in user_post Table.

create table userinformation(userid int,name char(30)not null,address varchar(30)not null,age int not null,email varchar(30)primary key,gender varchar(20)not null,password varchar(30)not null);

create table user_post(post_id int primary key auto_increment,user_message varchar(30),user_email varchar(30),post_date varchar(20),post_like varchar(20),post_dislike varchar(20),foreign key(user_email)references userinformation(email));

### Database :

![Screenshot (12)](https://github.com/Poonam-Patil-29/Social-Media-Using-Java-JDBC/assets/104273538/d7b24e02-5727-46a8-ae3b-597e19beb9fe)



![Screenshot (13)](https://github.com/Poonam-Patil-29/Social-Media-Using-Java-JDBC/assets/104273538/a0dda458-b6e3-4324-8cf4-42cdd9cf4d8b)



