CREATE TABLE evento (
	codigo BIGINT PRIMARY KEY AUTO_INCREMENT,
	data_hora_evento DATETIME NOT NULL,
	data_referencia DATE,
	codigo_grupo BIGINT NOT NULL,
	tipo_evento VARCHAR(30) NOT NULL,
	tema VARCHAR(50) NOT NULL,
	palestrante VARCHAR(20),
	destaque BOOLEAN NOT NULL,
	FOREIGN KEY (codigo_grupo) REFERENCES grupo(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO evento (data_hora_evento, data_referencia, codigo_grupo, tipo_evento, tema, palestrante, destaque) 
VALUES ('2020-12-28 19:00:00', '2020-12-01', 1, 'Temática', 'Espiritualidade', 'Ricardo', true);
