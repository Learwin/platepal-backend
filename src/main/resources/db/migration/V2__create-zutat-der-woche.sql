CREATE TABLE ZutatDerWoche (
    ID                          BIGINT          NOT NULL AUTO_INCREMENT,
    ZUTAT_ID                    BIGINT          NOT NULL ,
    VON                         DATE            NOT NULL,
    BIS                         DATE            NOT NULL,

    PRIMARY KEY (ID),
    FOREIGN KEY (ZUTAT_ID) REFERENCES Zutat(ID) ON DELETE CASCADE
);