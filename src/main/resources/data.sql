-- Diese Daten werden beim Start in die Datenbank eingefügt, es sei denn
-- man setzt das folgende Flag, dann wird diese Funktion ausgeschaltet
--
-- spring.sql.init.mode property to never
CREATE TABLE country (
                         id   INTEGER      NOT NULL AUTO_INCREMENT,
                         name VARCHAR(128) NOT NULL,
                         PRIMARY KEY (id)
);

CREATE TABLE article (
                         id VARCHAR(127) NOT NULL,
                         url VARCHAR(255) NOT NULL,
                         title VARCHAR(255) NOT NULL,
                         summary VARCHAR(1023) NOT NULL,
                         PRIMARY KEY  (id)
);

INSERT INTO country (id, name) VALUES (1, 'USA');
INSERT INTO country (id, name) VALUES (2, 'France');
INSERT INTO country (id, name) VALUES (3, 'Brazil');
INSERT INTO country (id, name) VALUES (4, 'Italy');
INSERT INTO country (id, name) VALUES (5, 'Canada');
