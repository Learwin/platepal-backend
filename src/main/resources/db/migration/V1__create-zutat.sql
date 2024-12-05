CREATE TABLE Zutat (
    ID                          BIGINT          NOT NULL AUTO_INCREMENT,
    NAME                        VARCHAR(255)    NOT NULL,
    KCAL                        DECIMAL(10,4)         NULL,
    FETT                        DECIMAL(10,4)          NULL,
    GESAETTIGTE_FETTSAEUREN     DECIMAL(10,4)          NULL,
    KOHLENHYDRATE               DECIMAL(10,4)          NULL,
    ZUCKER                      DECIMAL(10,4)          NULL,
    BALLASTSTOFFE               DECIMAL(10,4)          NULL,
    EIWEISS                     DECIMAL(10,4)          NULL,
    SALZ                        DECIMAL(10,4)          NULL,
    FOTO                        VARCHAR(255)    NULL,

    CONSTRAINT pk_id PRIMARY KEY (ID)
);