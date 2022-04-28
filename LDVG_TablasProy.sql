create type full_name as(
	name text,
	last_father text,
	last_mother text
);

create table consult_type(
	idcnslt int not null AUTO_INCREMENT,
	cons_type varchar(35),
	constraint pktipo_consulta primary key(idcnslt)
);
create table pacient_type(
	idtypepacient int not null AUTO_INCREMENT,
	pacient_type varchar(20),
	constraint pkpaciente_tipo primary key(idtypepacient)
);

create table pacient(
	idpacient int not null AUTO_INCREMENT,
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
	iddoctor int not null AUTO_INCREMENT,
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
	idassistant int not null AUTO_INCREMENT,
	assistant_name full_name not null,
	assist_age int not null,
	assist_birth date not null,
	education varchar(25),
	assist_phone varchar(15),
	constraint pkassist primary key(idassistant)
);

create table appointments(
	idappoint int not null AUTO_INCREMENT,
	appoint_cost decimal(5,2) not null,
	consult_tp int not null,
	constraint pkappoint primary key(idappoint),
	foreign key (consult_tp) references consult_type(idcnslt)
);

create table vaccine_supplier(
	idsupplier int not null AUTO_INCREMENT,
	supplier_name text not null,
	address varchar(50),
	supplier_phone varchar(15),
	constraint pksupplier primary key(idsupplier)
);

create table vaccines(
	idvaccine int not null AUTO_INCREMENT,
	vaccine_name text not null,
	vaccine_type text not null,
	vaccine_cost decimal(5,2) not null,
	exp date not null,
	lot char(10) not null,
	stock int not null,
	supplier int not null,
	constraint pkvaccine primary key(idvaccine),
	foreign key (supplier) references vaccine_supplier(idsupplier)
);

create table pacient_appointments(
	idpcnt_ppntmnt int not null AUTO_INCREMENT,
	id_pacient int not null,
	appoint_date date not null,
	appoint_hour char(6),
	id_doctor int not null,
	id_assist int not null,
	id_appointment int not null,
	id_consultype int not null,
	constraint pk_pacientappointments primary key(idpcnt_ppntmnt),
	foreign key (id_pacient) references pacient(idpacient),
	foreign key (id_doctor) references doctor(iddoctor),
	foreign key (id_assist) references assistant(idassistant),
	foreign key (id_appointment) references appointments(idappoint),
	foreign key (id_consultype) references consult_type(idcnslt)
);