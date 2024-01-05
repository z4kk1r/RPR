BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "drzava" (
	"id"	INTEGER NOT NULL UNIQUE,
	"naziv"	TEXT,
	"glavni_grad"	TEXT,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "grad" (
	"id"	INTEGER NOT NULL UNIQUE,
	"naziv"	TEXT,
	"broj_stanovnika"	INTEGER,
	"drzava"	INTEGER,
	PRIMARY KEY("id" AUTOINCREMENT)
);
INSERT INTO "drzava" VALUES (1,'Engleska','4');
INSERT INTO "drzava" VALUES (2,'BiH','1');
INSERT INTO "drzava" VALUES (3,'Hrvatska','2');
INSERT INTO "drzava" VALUES (4,'Srbija','3');
INSERT INTO "grad" VALUES (1,'Sarajevo',275000,2);
INSERT INTO "grad" VALUES (2,'Zagreb',50000,3);
INSERT INTO "grad" VALUES (3,'Beograd',275000,4);
INSERT INTO "grad" VALUES (4,'London',2575000,1);
INSERT INTO "grad" VALUES (5,'Pariz',4575000,null);
INSERT INTO "grad" VALUES (6,'Ankara',3575000,null);

COMMIT;
