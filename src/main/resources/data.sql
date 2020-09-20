insert into sport(id,name) values(1,'Tenis'),(2,'Badminton'),(3,'PingPong'),(4,'Squash');
insert into level(id,name) values (1,'Początkujacy'),(2,'Średniozaawansowany'),(3,'Zaawansowany');
insert into role(id,name) values (1,'ROLE_USER'),(2,'ROLE_ADMIN');
insert into user(id,mail,name,user_name,password,last_name,date_of_Birth,old,enabled) values (1,'jan.medyk97@gmail.com','Jan','Bayo','pies00','Medyk','2010-10-10',10,true);
insert into user(id,mail,name,user_name,password,last_name,date_of_Birth,old,enabled) values (2,'jan.meedyk97@gmail.com','Jana','Bayoa','pies00','Medyk','2010-10-10',10,true);
insert into city(id, name) values (1,'Wroclaw');

insert into club(id,city_id,addres,name) values (1,1,'Żbikowa 13', 'HastaLaVista'), (2,1,'Korona345','Wroclaw SquashClub');
