create type full_name as(
	name text,
	last_father text,
	last_mother text
);

create table consult_type(
	idcnslt serial,
	cons_type text,
	constraint pktipo_consulta primary key(idcnslt)
);

create table pacient_type(
	idtypepacient serial,
	pacient_type text,
	constraint pkpaciente_tipo primary key(idtypepacient)
);

create table pacient(
	idpacient varchar(10) not null,
	pacient_name full_name not null,
	age int not null,
	sexo char(3),
	datebirth date not null, 
	mother_name varchar(50) not null,
	father_name varchar(50) not null,
	phone_number varchar(15),
	pacient_t int not null,
	weight decimal(3,2) not null,
	height int not null,
	temp decimal(2,2) not null,
	constraint pkpacient primary key(idpacient),
	foreign key (pacient_t) references pacient_type(idtypepacient)
);

create table job(
	idjob serial,
	job_name varchar(20),
	constraint pkjob primary key(idjob)
);

create table doctor(
	iddoctor varchar(10) not null,
	doctor_name full_name not null,
	dr_age int not null,
	dr_birth date not null,
	dr_phone varchar(15),
	specialty varchar(35),
	pass varchar(10),
	djobid int not null,
	constraint pkdoctor primary key(iddoctor),
	foreign key (djobid) references job(idjob)
);

create table assistant(
	idassistant varchar(10) not null,
	assistant_name full_name not null,
	assist_age int not null,
	assist_birth date not null,
	education varchar(25),
	assist_phone varchar(15),
	pass varchar(10),
	ajobid int not null,
	constraint pkassist primary key(idassistant),
	foreign key (ajobid) references job(idjob)
);

create table appointments(
	idappoint serial,
	reason_appoint int not null,
	diagnostic text,
	prescription text,
	constraint pkappoint primary key(idappoint),
	foreign key (reason_appoint) references consult_type(idcnslt)
);

create table vaccine_supplier(
	idsupplier serial,
	supplier_name varchar(50),
	constraint pksupplier primary key(idsupplier)
);

create table vaccine_manufacturer(
	idmanufacturer serial,
	manufacturer_name text not null,
	constraint pkmanufacturer primary key(idmanufacturer)
);

create table vaccines(
	idvaccine serial,
	vaccine_name text not null,
	vaccine_type text not null,
	vaccine_cost decimal(5,2) not null,
	exp date not null,
	lot char(10) not null,
	stock int not null,
	manufacturer int not null,
	supplier int not null,
	constraint pkvaccine primary key(idvaccine),
	foreign key (manufacturer) references vaccine_manufacturer(idmanufacturer)
	foreign key (supplier) references vaccine_supplier(idsupplier)
);

create table pacient_appointments(
	idpcnt_ppntmnt serial,
	id_pacient varchar(10) not null,
	appoint_date date not null,
	appoint_hour char(6),
	id_doctor varchar(10) not null,
	id_assist varchar(10) not null,
	id_appointment int not null,
	id_consultype int not null,
	constraint pk_pacientappointments primary key(idpcnt_ppntmnt),
	foreign key (id_pacient) references pacient(idpacient),
	foreign key (id_doctor) references doctor(iddoctor),
	foreign key (id_assist) references assistant(idassistant),
	foreign key (id_appointment) references appointments(idappoint),
	foreign key (id_consultype) references consult_type(idcnslt)
);

--Datos para tablas formulario
insert into consult_type (cons_type) values ('Revision General');
insert into consult_type (cons_type) values ('Vacunacion');
insert into consult_type (cons_type) values ('Padecimiento Especifico');

insert into pacient_type (pacient_type) values ('Primera Vez');
insert into pacient_type (pacient_type) values ('Regular');
insert into pacient_type (pacient_type) values ('Particular');

insert into job (job_name) values ('Doctor-Admin');
insert into job (job_name) values ('Medico General');
insert into job (job_name) values ('Asistente Medico');