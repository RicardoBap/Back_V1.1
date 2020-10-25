CREATE TABLE pessoa (
	codigo BIGINT PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	logradouro VARCHAR(30),
	numero VARCHAR(10),
	complemento VARCHAR(20),
	bairro VARCHAR(20),
	telefone VARCHAR(15),
	cidade VARCHAR(20),
	encargo VARCHAR(20),
	ativo BOOLEAN NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, telefone, cidade, encargo, ativo) VALUES ('Ricardo', 'Rua Itapecerica da Serra', '530', null, 'Cidade Jardim', '(19) 99659-2800', 'Campinas', 'Secretário', true);
