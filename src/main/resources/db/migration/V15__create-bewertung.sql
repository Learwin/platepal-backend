CREATE TABLE Bewertung (
    ID                          BIGINT          NOT NULL AUTO_INCREMENT,
    ANZAHL_STERNE                    BIGINT    NULL ,
    KOMMENTAR                    BLOB          NULL,
    FOTO               VARCHAR(255)         NULL,
    REZEPT_ID                        BIGINT NOT NULL,
    USER_ID                        BIGINT NOT NULL,

    PRIMARY KEY (ID),
    CONSTRAINT FK_RezeptBewertung FOREIGN KEY (REZEPT_ID) REFERENCES Rezept(ID) ON DELETE CASCADE,
    CONSTRAINT FK_UserBewertung FOREIGN KEY (USER_ID) REFERENCES UserTable(ID) ON DELETE CASCADE
);