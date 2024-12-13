insert into USERTABLE (ID, USERNAME) VALUES ('5', 'Angela Merkel');
insert into USERTABLE (ID, USERNAME) VALUES ('8', 'Barack Obama');
insert into USERTABLE (ID, USERNAME) VALUES ('11', 'Beyonce');
insert into USERTABLE (ID, USERNAME) VALUES ('14', 'Tobias Quix');

UPDATE Rezept SET USER_ID = '14' WHERE ID = '5';
UPDATE Rezept SET USER_ID = '11' WHERE ID = '8';
UPDATE Rezept SET USER_ID = '8' WHERE ID = '11';
UPDATE Rezept SET USER_ID = '5' WHERE ID = '14';