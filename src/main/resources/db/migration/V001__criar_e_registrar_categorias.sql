CREATE TABLE categoria (
	codigo BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO categoria (nome) VALUES ('7º Tradição');
INSERT INTO categoria (nome) VALUES ('Água');
INSERT INTO categoria (nome) VALUES ('Luz');
INSERT INTO categoria (nome) VALUES ('Manutenção');
INSERT INTO categoria (nome) VALUES ('Escritório ESL');
INSERT INTO categoria (nome) VALUES ('Impostos');
INSERT INTO categoria (nome) VALUES ('Outros');