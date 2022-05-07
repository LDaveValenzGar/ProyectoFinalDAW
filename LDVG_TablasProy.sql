create type full_name as(
	name text,
	last_father text,
	last_mother text
);

create table consult_type(
	idcnslt serial,
	cons_type varchar(20),
	constraint pktipo_consulta primary key(idcnslt)
);

create table pacient_type(
	idtypepacient serial,
	pacient_type varchar(20),
	constraint pkpaciente_tipo primary key(idtypepacient)
);

create table pacient(
	idpacient varchar(10) not null,
	pacient_name full_name not null,
	age int not null,
	datebirth date not null, 
	parent_name full_name not null,
	phone_number varchar(15),
--	vaccines int not null,
	pacient_t int not null,
	constraint pkpacient primary key(idpacient),
	foreign key (pacient_t) references pacient_type(idtypepacient)
);

create table doctor(
	iddoctor varchar(10) not null,
	doctor_name full_name not null,
	dr_age int not null,
	dr_birth date not null,
	dr_phone varchar(15),
	specialty varchar(35),
	schedule varchar(20),
	position varchar(20),
	constraint pkdoctor primary key(iddoctor)
);

create table assistant(
	idassistant varchar(10) not null,
	assistant_name full_name not null,
	assist_age int not null,
	assist_birth date not null,
	education varchar(25),
	assist_phone varchar(15),
	constraint pkassist primary key(idassistant)
);

create table appointments(
	idappoint serial,
	appoint_cost decimal(5,2) not null,
	consult_tp int not null,
	constraint pkappoint primary key(idappoint),
	foreign key (consult_tp) references consult_type(idcnslt)
);

create table vaccine_supplier(
	idsupplier varchar(10) not null,
	supplier_name text not null,
	address varchar(50),
	supplier_phone varchar(15),
	constraint pksupplier primary key(idsupplier)
);

create table vaccines(
	idvaccine serial,
	vaccine_name text not null,
	vaccine_type text not null,
	vaccine_cost decimal(5,2) not null,
	exp date not null,
	lot char(10) not null,
	stock int not null,
	supplier varchar(10) not null,
	constraint pkvaccine primary key(idvaccine),
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