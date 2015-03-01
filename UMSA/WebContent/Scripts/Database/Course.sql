use StudentAdvisor;

create table StudentAdvisor.Course(
	courseNumber varchar(10),
	courseName varchar(250),
 	description varchar(250),
 	credit int,
 	constraint course_pk PRIMARY KEY(courseNumber)
);



