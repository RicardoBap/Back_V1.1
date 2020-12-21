CREATE TABLE usuario(
	codigo BIGINT PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL UNIQUE,
	senha VARCHAR(150) NOT NULL,
	senha_usuario VARCHAR (150),
	ativo BOOLEAN DEFAULT TRUE NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE cargo (
	codigo BIGINT PRIMARY KEY,
	nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE permissao (
	codigo BIGINT PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE usuario_cargo (
	codigo_usuario BIGINT NOT NULL,
	codigo_cargo BIGINT NOT NULL,
	PRIMARY KEY (codigo_usuario, codigo_cargo),
	FOREIGN KEY (codigo_usuario) REFERENCES usuario(codigo),
	FOREIGN KEY (codigo_cargo) REFERENCES cargo(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE cargo_permissao (
	codigo_cargo BIGINT NOT NULL,
	codigo_permissao BIGINT NOT NULL,
	PRIMARY KEY (codigo_cargo, codigo_permissao),
	FOREIGN KEY (codigo_cargo) REFERENCES cargo(codigo),
	FOREIGN KEY (codigo_permissao) REFERENCES permissao(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO cargo (codigo, nome) VALUES (1, 'Administrador');
INSERT INTO cargo (codigo, nome) VALUES (2, 'RSG');
INSERT INTO cargo (codigo, nome) VALUES (3, 'Tesoureiro');
INSERT INTO cargo (codigo, nome) VALUES (4, 'Secretário');
INSERT INTO cargo (codigo, nome) VALUES (5, 'Membro');

INSERT INTO usuario (codigo, nome, email, senha, senha_usuario, ativo) VALUES (1, 'Administrador', 'ric_bap@hotmail.com', '$2a$10$CGMvlPHc51JqzXxEA5IbDecya8EU589DHVZ20diqgX6MXIYQ9tfEC', 'ric_bap@mail', 1);
INSERT INTO usuario (codigo, nome, email, senha, senha_usuario, ativo) VALUES (2, 'Maria Silva', 'maria@hotmail.com', '$2a$10$OahgZpsZEimxcPnTbC/J9uBurlX3QGRPCsvLN2RoGymGv0HHPMlpC', 'maria@mail', 1);

INSERT INTO permissao (codigo, descricao) VALUES (1, 'ROLE_CADASTRAR_CATEGORIA');
INSERT INTO permissao (codigo, descricao) VALUES (2, 'ROLE_REMOVER_CATEGORIA');
INSERT INTO permissao (codigo, descricao) VALUES (3, 'ROLE_PESQUISAR_CATEGORIA');

INSERT INTO permissao (codigo, descricao) VALUES (4, 'ROLE_CADASTRAR_PESSOA');
INSERT INTO permissao (codigo, descricao) VALUES (5, 'ROLE_REMOVER_PESSOA');
INSERT INTO permissao (codigo, descricao) VALUES (6, 'ROLE_PESQUISAR_PESSOA');

INSERT INTO permissao (codigo, descricao) VALUES (7, 'ROLE_CADASTRAR_LANCAMENTO');
INSERT INTO permissao (codigo, descricao) VALUES (8, 'ROLE_REMOVER_LANCAMENTO');
INSERT INTO permissao (codigo, descricao) VALUES (9, 'ROLE_PESQUISAR_LANCAMENTO');

INSERT INTO permissao (codigo, descricao) VALUES (10, 'ROLE_CADASTRAR_USUARIO');
INSERT INTO permissao (codigo, descricao) VALUES (11, 'ROLE_REMOVER_USUARIO');
INSERT INTO permissao (codigo, descricao) VALUES (12, 'ROLE_PESQUISAR_USUARIO');

INSERT INTO permissao (codigo, descricao) VALUES (13, 'ROLE_CADASTRAR_CIDADE');
INSERT INTO permissao (codigo, descricao) VALUES (14, 'ROLE_REMOVER_CIDADE');
INSERT INTO permissao (codigo, descricao) VALUES (15, 'ROLE_PESQUISAR_CIDADE');

INSERT INTO permissao (codigo, descricao) VALUES (16, 'ROLE_CADASTRAR_GRUPO');
INSERT INTO permissao (codigo, descricao) VALUES (17, 'ROLE_REMOVER_GRUPO');
INSERT INTO permissao (codigo, descricao) VALUES (18, 'ROLE_PESQUISAR_GRUPO');

INSERT INTO permissao (codigo, descricao) VALUES (19, 'ROLE_CADASTRAR_RECADO');
INSERT INTO permissao (codigo, descricao) VALUES (20, 'ROLE_REMOVER_RECADO');
INSERT INTO permissao (codigo, descricao) VALUES (21, 'ROLE_PESQUISAR_RECADO');

INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (1, 1);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (1, 2);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (1, 3);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (1, 4);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (1, 5);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (1, 6);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (1, 7);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (1, 8);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (1, 9);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (1, 10);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (1, 11);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (1, 12);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (1, 13);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (1, 14);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (1, 15);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (1, 16);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (1, 17);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (1, 18);

INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (2, 4);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (2, 6);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (2, 9);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (2, 13);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (2, 15);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (2, 16);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (2, 18);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (2, 21);

INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (3, 4);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (3, 6);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (3, 7);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (3, 9);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (3, 15);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (3, 18);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (3, 21);

INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (4, 4);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (4, 6);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (4, 9);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (4, 15);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (4, 18);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (4, 19);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (4, 21);

INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (5, 6);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (5, 9);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (5, 15);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (5, 18);
INSERT INTO cargo_permissao (codigo_cargo, codigo_permissao) VALUES (5, 21);

INSERT INTO usuario_cargo (codigo_usuario, codigo_cargo) VALUES (
	(SELECT codigo FROM usuario WHERE email = 'ric_bap@hotmail.com'), 1);
	
INSERT INTO usuario_cargo (codigo_usuario, codigo_cargo) VALUES (
	(SELECT codigo FROM usuario WHERE email = 'maria@hotmail.com'), 5);

