use StudentAdvisor;
create table StudentAdvisor.Subject(
	subjectId int NOT NULL AUTO_INCREMENT,
	name varchar(25),
	constraint Subject_pk PRIMARY KEY (subjectId)
);