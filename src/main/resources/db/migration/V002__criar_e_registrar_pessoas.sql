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
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, telefone, cidade, encargo, ativo) VALUES ('Claudio', 'Estrada Velha de Indaiatuba', 'Km 7', null, 'Vila Palmeiras', '(19) 99345-9899', 'Campinas', 'Membro', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, telefone, cidade, encargo, ativo) VALUES ('Roberto', 'Rua Araçoiaba da Serra', '220', null, 'Cidade Jardim', '(19) 99234-3222', 'Campinas', 'RSG', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, telefone, cidade, encargo, ativo) VALUES ('Melo', 'Av. Rio de Janeiro', '123', null, 'São Bernerado', '(19) 03242-3242', 'Campinas', 'Membro', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, telefone, cidade, encargo, ativo) VALUES ('Barreto', 'Rua Esperito Santo', '321', null, 'São Bernardo', '(19) 03242-0900', 'Campinas', 'RSG Suplente', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, telefone, cidade, encargo, ativo) VALUES ('Gilmar', 'Rua 32', '100', 'apto 202', 'DIV IV', '(19) 99232-9900', 'Campinas', 'Tesoureiro', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, telefone, cidade, encargo, ativo) VALUES ('João Luís', 'Av. Almirante Mega', '1120', null, 'Jd. Campos Eliseos', '(19) 03268-6121', 'Campinas', 'Membro', false);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, telefone, cidade, encargo, ativo) VALUES ('Edisson', 'Rua da Manga', '433', null, 'Jd Chapadão', '(19) 03242-9090', 'Campinas', 'Membro', false);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, telefone, cidade, encargo, ativo) VALUES ('Bruno', 'Rua Guaracy', '190', null, 'Vila Angela Marta', '(19) 99332-1122', 'Campinas', 'Membro', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, telefone, cidade, encargo, ativo) VALUES ('Eliézer', 'Rua da Terra', '1233', 'Apto 10', 'São Bernerdo', '(19) 93232-5455', 'Campinas', 'Membro', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, telefone, cidade, encargo, ativo) VALUES ('Orlando', 'Rua Gastão da Rocha Leão', '556', 'apto 555', 'Jd. do Lago', '(19) 98832-7777', 'Campinas', 'Membro', true);