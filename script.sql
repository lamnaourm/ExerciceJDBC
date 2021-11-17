create database if not exists dbproducts; 

use dbproducts;

create table if not exists produit (
    id int AUTO_INCREMENT PRIMARY key,
    nom VARCHAR(40),
    famille VARCHAR(40),
    prix_achat double,
    prix_vente double
);

insert into produit values (null,'prod1','Textile',34.5,36);
insert into produit values (null,'prod2','Epicerie',20.5,26);
insert into produit values (null,'prod3','Textile',36.5,40);
insert into produit values (null,'prod4','Epicerie',4.5,6);