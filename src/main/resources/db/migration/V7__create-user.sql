CREATE TABLE UserTable (
    ID                          BIGINT          NOT NULL AUTO_INCREMENT,
    USERNAME                    VARCHAR(255)    NULL ,
    PASSWORT                    VARCHAR(255)          NULL,
    EMAIL_ADRESSE               VARCHAR(255)         NULL,
    FLAG                        BIGINT,

    PRIMARY KEY (ID)
);

ALTER TABLE rezept ADD
FOREIGN KEY (USER_ID)
REFERENCES UserTable(ID);