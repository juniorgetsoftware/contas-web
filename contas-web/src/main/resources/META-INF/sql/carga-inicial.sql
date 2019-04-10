insert into categoria(id, nome) values (1, 'Alimentação');
insert into categoria(id, nome) values (2, 'Transporte');
insert into categoria(id, nome) values (3, 'Água');
insert into categoria(id, nome) values (4, 'Internet');
insert into categoria(id, nome) values (5, 'Luz');
insert into categoria(id, nome) values (6, 'Receita');
insert into categoria(id, nome) values (7, 'Saúde');
insert into categoria(id, nome) values (8, 'Outra');
insert into conta (categoria_id, data_vencimento, data_pagamento, is_quitada, tipo_conta, titulo, valor)  values (1, '2019-01-01', '2019-01-01', true, 'DESPESA', 'Almoço no bom d+', 88.50);
insert into conta (categoria_id, data_vencimento, data_pagamento, is_quitada, tipo_conta, titulo, valor)  values (1, '2019-01-10', '2019-01-10', true, 'DESPESA', 'Almoço no bom d+', 28.80);
insert into conta (categoria_id, data_vencimento, data_pagamento, is_quitada, tipo_conta, titulo, valor)  values (1, '2019-02-15', '2019-02-15', true, 'DESPESA', 'Almoço no bom d+', 33.55);
insert into conta (categoria_id, data_vencimento, data_pagamento, is_quitada, tipo_conta, titulo, valor)  values (1, '2019-02-17', '2019-02-17', true, 'DESPESA', 'Almoço no bom d+', 17.60);
insert into conta (categoria_id, data_vencimento, data_pagamento, is_quitada, tipo_conta, titulo, valor)  values (1, '2019-03-18', '2019-03-18', true, 'DESPESA', 'Almoço no bom d+', 69.66);
insert into conta (categoria_id, data_vencimento, data_pagamento, is_quitada, tipo_conta, titulo, valor)  values (1, '2019-03-20', '2019-03-20', true, 'DESPESA', 'Almoço no bom d+', 22.32);
insert into conta (categoria_id, data_vencimento, data_pagamento, is_quitada, tipo_conta, titulo, valor)  values (1, '2019-04-01', '2019-04-01', true, 'DESPESA', 'Almoço no bom d+', 19.78);
insert into conta (categoria_id, data_vencimento, data_pagamento, is_quitada, tipo_conta, titulo, valor)  values (1, '2019-04-08', '2019-04-08', true, 'DESPESA', 'Almoço no bom d+', 61.77);
insert into conta (categoria_id, data_vencimento, data_pagamento, is_quitada, tipo_conta, titulo, valor)  values (3, '2019-01-05', '2019-04-05', true, 'DESPESA', 'Conta de água', 50.97);
insert into conta (categoria_id, data_vencimento, data_pagamento, is_quitada, tipo_conta, titulo, valor)  values (3, '2019-02-05', '2019-04-05', true, 'DESPESA', 'Conta de água', 48.65);
insert into conta (categoria_id, data_vencimento, data_pagamento, is_quitada, tipo_conta, titulo, valor)  values (3, '2019-03-05', '2019-04-05', true, 'DESPESA', 'Conta de água', 98.78);
insert into conta (categoria_id, data_vencimento, data_pagamento, is_quitada, tipo_conta, titulo, valor)  values (3, '2019-04-05', '2019-04-05', true, 'DESPESA', 'Conta de água', 25.32);
insert into conta (categoria_id, data_vencimento, is_quitada, tipo_conta, titulo, valor)  values (3, '2019-05-05',  false, 'DESPESA', 'Conta de água', 22.88);
insert into conta (categoria_id, data_vencimento, data_pagamento, is_quitada, tipo_conta, titulo, valor)  values (5, '2019-01-05', '2019-04-05', true, 'DESPESA', 'Conta de luz', 80.97);
insert into conta (categoria_id, data_vencimento, data_pagamento, is_quitada, tipo_conta, titulo, valor)  values (5, '2019-02-05', '2019-04-05', true, 'DESPESA', 'Conta de luz', 48.25);
insert into conta (categoria_id, data_vencimento, data_pagamento, is_quitada, tipo_conta, titulo, valor)  values (5, '2019-03-05', '2019-04-05', true, 'DESPESA', 'Conta de luz', 68.768);
insert into conta (categoria_id, data_vencimento, data_pagamento, is_quitada, tipo_conta, titulo, valor)  values (5, '2019-04-05', '2019-04-05', true, 'DESPESA', 'Conta de luz', 95.92);
insert into conta (categoria_id, data_vencimento, is_quitada, tipo_conta, titulo, valor)  values (5, '2019-05-05',  false, 'DESPESA', 'Conta de luz', 92.98);
insert into conta (categoria_id, data_vencimento, data_pagamento, is_quitada, tipo_conta, titulo, valor)  values (7, '2019-03-15', '2019-03-20', true, 'DESPESA', 'Exames médicos', 95.09);
insert into conta (categoria_id, data_vencimento, data_pagamento, is_quitada, tipo_conta, titulo, valor)  values (7, '2019-04-15', '2019-04-20', true, 'DESPESA', 'Exames médicos', 50.00);
insert into conta (categoria_id, data_vencimento, data_pagamento, is_quitada, tipo_conta, titulo, valor)  values (6, '2019-01-07', '2019-04-07', true, 'RECEITA', 'Salário', 4123.00);
insert into conta (categoria_id, data_vencimento, data_pagamento, is_quitada, tipo_conta, titulo, valor)  values (6, '2019-02-07', '2019-04-07', true, 'RECEITA', 'Salário', 4123.00);
insert into conta (categoria_id, data_vencimento, data_pagamento, is_quitada, tipo_conta, titulo, valor)  values (6, '2019-03-07', '2019-04-07', true, 'RECEITA', 'Salário', 4123.00);
insert into conta (categoria_id, data_vencimento, data_pagamento, is_quitada, tipo_conta, titulo, valor)  values (6, '2019-04-07', '2019-04-07', false, 'RECEITA', 'Salário', 4123.00);
insert into conta (categoria_id, data_vencimento, is_quitada, tipo_conta, titulo, valor)  values (6, '2019-05-07', false, 'RECEITA', 'Salário', 4123.00);
insert into conta (categoria_id, data_vencimento, data_pagamento, is_quitada, tipo_conta, titulo, valor)  values (8, '2019-04-03', '2019-04-05', true, 'RECEITA', 'Outra receita', 75.33);
insert into conta (categoria_id, data_vencimento, is_quitada, tipo_conta, titulo, valor)  values (5, '2019-04-01', false, 'DESPESA', 'Conta de energia', 123.50);
insert into conta (categoria_id, data_vencimento, is_quitada, tipo_conta, titulo, valor)  values (6, '2019-04-07', false, 'RECEITA', 'Salário', 2123.86);