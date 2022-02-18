DROP TABLE IF EXISTS Usuario CASCADE;
DROP TABLE IF EXISTS Comando CASCADE;
DROP TABLE IF EXISTS InstrucaoGravada CASCADE;
DROP TABLE IF EXISTS InstrucaoComando CASCADE;

CREATE TABLE Usuario(
    Cod_Usuario bigserial CONSTRAINT Key2 PRIMARY KEY,
    End_Foto Bytea,
    Cod_Senha text NOT NULL,
    Nom_Usuario text NOT NULL, 
    Des_Email text NOT NULL
);

CREATE TABLE Comando(
    Cod_Comando bigserial CONSTRAINT Key3 PRIMARY KEY,
    End_Comando text NOT NULL,
    Des_Comando text

);

CREATE TABLE InstrucaoGravada(
    Cod_Instrucao bigserial,
    Cod_Usuario bigserial NOT NULL,
    Comando_Voz text NOT NULL,
    Des_Comando text

);

CREATE TABLE InstrucaoComando(
    Cod_Instrucao bigserial,
    Cod_Usuario bigserial,
    Cod_Comando bigserial
);

ALTER TABLE InstrucaoGravada ADD CONSTRAINT Key1 PRIMARY KEY (Cod_Instrucao,Cod_Usuario);
ALTER TABLE InstrucaoGravada ADD CONSTRAINT "Relationship1" FOREIGN KEY (Cod_Usuario) REFERENCES Usuario (Cod_Usuario) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE InstrucaoComando ADD CONSTRAINT Key4 PRIMARY KEY (Cod_Comando,Cod_Usuario,Cod_Instrucao);
ALTER TABLE InstrucaoComando ADD CONSTRAINT "Relationship2" FOREIGN KEY (Cod_Usuario,Cod_Instrucao) REFERENCES InstrucaoGravada (Cod_Usuario,Cod_Instrucao) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE InstrucaoComando ADD CONSTRAINT "Relationship3" FOREIGN KEY (Cod_Comando) REFERENCES Comando (Cod_Comando) ON DELETE NO ACTION ON UPDATE NO ACTION;

          
INSERT INTO comando(
            cod_comando, end_comando, des_comando)
    VALUES (1, 'COM-Desligar computador', 'O computador é desligado imediatamente'),
    (2, 'COM-Reiniciar computador', 'O computador é reiniciado imediatamente'),
    (3, 'COM-Open chrome', 'Google Chrome é aberto'),
    (4, 'COM-Open firefox', 'Mozilla Firefox é aberto'),
    (5, 'COM-Open notepad', 'Bloco de notas é aberto'),
    (6, 'COM-Frame visible', 'Habilita a frame didatica'),
    (7, 'COM-Frame azul', 'Pinta a frame de azul'),
    (8, 'COM-Frame amarela', 'Pinta a frame de amarelo'),
    (9, 'COM-Frame vermelha', 'Pinta a frame de vermelho');
