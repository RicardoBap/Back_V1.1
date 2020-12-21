CREATE TABLE contato (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	codigo_grupo BIGINT(20) NOT NULL,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(100) NOT NULL,
	telefone VARCHAR(20) NOT NULL,
	FOREIGN KEY (codigo_grupo) REFERENCES grupo(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO contato(codigo, codigo_grupo, nome, email, telefone)
	VALUES(1, 1, 'Ricardo B. K.', 'ric_bap@hotmail.com', '19 99659-2800');