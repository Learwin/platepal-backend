CREATE TABLE Rezept (
    ID                          BIGINT          NOT NULL AUTO_INCREMENT,
    ANWEISUNGEN                 BLOB    NULL ,
    ZEIT                        BIGINT          NULL,
    SCHWIERIGKEIT               BIGINT         NULL,
    DEFAULT_PORTIONEN           BIGINT           NULL,
    FOTO                        VARCHAR(255)    NULL,
    USER_ID                     BIGINT           NULL,
    DURCHSCHNITTLICHE_BEWERTUNG BIGINT           NULL,
    NAME                       VARCHAR(255)    NULL,

    CONSTRAINT pk_id PRIMARY KEY (ID)
);