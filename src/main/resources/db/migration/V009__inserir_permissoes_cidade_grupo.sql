INSERT INTO permissao (codigo, descricao) VALUES (13, 'ROLE_CADASTRAR_CIDADE');
INSERT INTO permissao (codigo, descricao) VALUES (14, 'ROLE_REMOVER_CIDADE');
INSERT INTO permissao (codigo, descricao) VALUES (15, 'ROLE_PESQUISAR_CIDADE');

INSERT INTO permissao (codigo, descricao) VALUES (16, 'ROLE_CADASTRAR_GRUPO');
INSERT INTO permissao (codigo, descricao) VALUES (17, 'ROLE_REMOVER_GRUPO');
INSERT INTO permissao (codigo, descricao) VALUES (18, 'ROLE_PESQUISAR_GRUPO');

INSERT INTO permissao (codigo, descricao) VALUES (19, 'ROLE_CADASTRAR_EVENTO');
INSERT INTO permissao (codigo, descricao) VALUES (20, 'ROLE_REMOVER_EVENTO');
INSERT INTO permissao (codigo, descricao) VALUES (21, 'ROLE_PESQUISAR_EVENTO');

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 13);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 14);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 15);

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 16);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 17);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 18);

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 19);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 20);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 21);


INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (2, 15);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (2, 18);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (2, 21);
