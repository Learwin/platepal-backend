CREATE TABLE Zutat_Rezept (
    ZUTAT_ID        BIGINT      NOT NULL,
    REZEPT_ID        BIGINT      NOT NULL,
    MENGE        BIGINT      NOT NULL,
    EINHEIT_ID        BIGINT      NOT NULL,

    CONSTRAINT PK_Zutat_Rezept PRIMARY KEY (ZUTAT_ID, REZEPT_ID),
    FOREIGN KEY (ZUTAT_ID) REFERENCES Zutat(ID) ON DELETE CASCADE,
    FOREIGN KEY (REZEPT_ID) REFERENCES Rezept(ID) ON DELETE CASCADE,
    FOREIGN KEY (EINHEIT_ID) REFERENCES Einheit(ID) ON DELETE CASCADE
    );