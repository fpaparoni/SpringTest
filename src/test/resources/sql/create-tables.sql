CREATE TABLE UTENTE (
	USERNAME VARCHAR2(100) NOT NULL, 
	PASSWORD VARCHAR2(100) NOT NULL, 
	DT_REGISTRAZIONE DATE
);

alter table UTENTE
add constraint UTENTE_PK primary key (USERNAME);

commit;