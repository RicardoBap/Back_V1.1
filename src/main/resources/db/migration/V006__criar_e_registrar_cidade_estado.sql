CREATE TABLE estado (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	sigla VARCHAR (2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO estado (codigo, nome, sigla) VALUES (1, 'São Paulo', 'SP');
INSERT INTO estado (codigo, nome, sigla) VALUES (2, 'Rio de Janeiro', 'RJ');
INSERT INTO estado (codigo, nome, sigla) VALUES (3, 'Minas Gerais', 'MG');

CREATE TABLE cidade (
	codigo BIGINT PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	codigo_estado BIGINT NOT NULL,
	FOREIGN KEY (codigo_estado) REFERENCES estado(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO cidade (nome, codigo_estado) VALUES('Campinas', 1);
INSERT INTO cidade (nome, codigo_estado) VALUES('São Paulo', 1);
INSERT INTO cidade (nome, codigo_estado) VALUES('Ribeirão Preto', 1);
INSERT INTO cidade (nome, codigo_estado) VALUES('Santos', 1);

INSERT INTO cidade (nome, codigo_estado) VALUES('Rio de Janeiro', 2);

INSERT INTO cidade (nome, codigo_estado) VALUES('Belo Horizonte', 3);



ALTER TABLE grupo DROP COLUMN cidade;
ALTER TABLE grupo DROP COLUMN estado;
ALTER TABLE grupo ADD COLUMN codigo_cidade BIGINT(20);
ALTER TABLE grupo ADD CONSTRAINT fk_grupo_cidade FOREIGN KEY (codigo_cidade) 
	REFERENCES cidade (codigo);
	
UPDATE grupo SET codigo_cidade = 1;