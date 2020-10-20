CREATE TABLE lancamento (
	codigo BIGINT PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL,
	data_vencimento DATE NOT NULL,
	data_pagamento DATE,
	valor DECIMAL(10,2) NOT NULL,
	observacao VARCHAR(100),
	tipo VARCHAR(20) NOT NULL,
	codigo_categoria BIGINT NOT NULL,
	codigo_pessoa BIGINT NOT NULL,
	FOREIGN KEY (codigo_categoria) REFERENCES categoria(codigo),
	FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES ('Sacola da 7° Tradição', '2020-10-01', null, 55.00, '', 'RECEITA', 1, 6);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES ('Conta de água', '2020-10-02', '2020-10-02', 100.32, null, 'DESPESA', 2, 6);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES ('Planilha nivelada', '2020-10-03', null, 120.00, null, 'DESPESA', 5, 6);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES ('Conta de luz', '2020-10-05', '2020-10-05', 110.44, null, 'DESPESA', 3, 6);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES ('Sacolda da 7° Tradição', '2020-10-05', null, 70.30, null, 'RECEITA', 1, 6);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES ('Sacola da 7° Tradição', '2020-10-07', '2020-07-07', 60.32, null, 'RECEITA', 1, 6);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES ('Limpeza do Terreno', '2020-10-08', null, 150.00, null, 'DESPESA', 4, 6);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES ('Sacola da 7° Tradição', '2020-10-09', '2020-10-09', 46.50, null, 'RECEITA', 1, 6);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES ('Impostos', '2020-10-10', null, 123.64, 'IPTU da casa', 'DESPESA', 6, 6);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES ('Revista Vivencia', '2020-10-11', null, 65.33, null, 'DESPESA', 5, 6);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES ('Sacola da 7° Tradição', '2020-10-13', '2020-10-13', 48.32, null, 'RECEITA', 1, 6);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES ('Papelaria', '2020-10-14', '2020-10-14', 20.52, null, 'DESPESA', 4, 6);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES ('Compra de cadeado', '2020-10-15', null, 20.32, null, 'DESPESA', 7, 6);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES ('Sacola da 7° Tradição', '2020-10-17', '2020-10-17', 44.32, null, 'RECEITA', 6, 6);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES ('Sacla da Gratidão', '2020-10-18', null, 10.20, null, 'DESPESA', 5, 6);