--sql da pergunta 2
SELECT
	COUNT(id_consulta) AS total_faltas
FROM consulta
WHERE status = 'FALTOU';

--sql pergunta 3
SELECT 
    COUNT(id_convenio) AS total_pacientes_com_convnenio_2
FROM paciente
WHERE id_convenio = 2;

--sql pergunta 5
SELECT
    co.nome AS consultorio,
    COUNT(c.id_consulta) AS total_consultas
FROM consulta AS c
JOIN consultorio AS co
    ON co.id_consultorio = c.id_consultorio
WHERE c.status = 'REALIZADA'
GROUP BY co.nome
ORDER BY total_consultas DESC;