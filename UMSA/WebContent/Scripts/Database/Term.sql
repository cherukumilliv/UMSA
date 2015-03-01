use StudentAdvisor;
create table StudentAdvisor.Term(
	termId int NOT NULL AUTO_INCREMENT,
	year varchar(4) NOT NULL,
	name varchar(10) NOT NULL,
	constraint Term_Pk PRIMARY KEY (termid)
)