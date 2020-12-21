CREATE TABLE grupo (
	codigo BIGINT PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(30) NOT NULL,
	logradouro VARCHAR(50) NOT NULL,
	numero VARCHAR(10) NOT NULL,
	complemento VARCHAR(30),
	bairro VARCHAR(20) NOT NULL,
	cep VARCHAR(15),
	dia_reuniao VARCHAR(100) NOT NULL,
	cidade VARCHAR(20) NOT NULL,
	estado VARCHAR(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO grupo (nome, logradouro, numero, complemento, bairro, cep, dia_reuniao, cidade, estado) 
VALUES 
('Salva vidas', 'Rua Guaraci', '38', null, 'Vila Angela Marta', '13031-460', '2º, 4º E 6º às 19:00 horas', 'Campinas', 'São Paulo');
