
import Liracs.controller.skeletons.SkeletonAtualizarUser;
import Liracs.controller.skeletons.SkeletonAutenticar;
import Liracs.controller.skeletons.SkeletonInserirInstrucao;
import Liracs.controller.skeletons.SkeletonInserirInstrucaoComando;
import Liracs.controller.skeletons.SkeletonInserirUsuario;
import Liracs.controller.skeletons.SkeletonListarComandos;
import Liracs.controller.skeletons.SkeletonListarPorUsuario;
import Liracs.controller.skeletons.SkeletonProcurarUser;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nelore
 */
public class GestorSkeleton implements Runnable{
    
    private Socket socket;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    
    
    public GestorSkeleton(Socket socket) {
        this.socket = socket;
    }
    
    
    @Override
    public void run() {
        try {
            String aux ="aa";
            writer = new ObjectOutputStream(socket.getOutputStream());
            reader = new ObjectInputStream(socket.getInputStream());
            aux = reader.readUTF();
            switch (aux) {     //verifico a consistência de dados
                case "Autenticar usuario":
                    SkeletonAutenticar autenticar = new SkeletonAutenticar(this.socket, reader, writer);
                    autenticar.autenticar();
                    break;
                case "Cadastrar usuario":
                    SkeletonInserirUsuario inserirUser = new SkeletonInserirUsuario(this.socket, reader, writer);
                    inserirUser.inserirUsuario();
                    break;
                case "Atualizar usuario":
                    SkeletonAtualizarUser atualizarUser = new SkeletonAtualizarUser(this.socket, reader, writer);
                    atualizarUser.atualizar();
                    break;
                case "Procurar usuario":
                    SkeletonProcurarUser procurarUser = new SkeletonProcurarUser(this.socket, reader, writer);
                    procurarUser.procurarUser();
                    break;
                case "Listar comandos":
                    SkeletonListarComandos listarComandos = new SkeletonListarComandos(this.socket, reader, writer);
                    listarComandos.ListarComandos();
                    break;
                case "Listar comandos por usuario":
                    SkeletonListarPorUsuario listarPorUsuario = new SkeletonListarPorUsuario(this.socket, reader, writer);
                    listarPorUsuario.listarPorUsuario();
                    break;
                case "Inserir instrucao":
                    SkeletonInserirInstrucao inserirInstrucao = new SkeletonInserirInstrucao(this.socket, reader, writer);
                    inserirInstrucao.inserirInstrucao();
                    break;
                case "Inserir instrucao-Comando":
                    SkeletonInserirInstrucaoComando inserirInstrucaoComando = new SkeletonInserirInstrucaoComando(this.socket, reader, writer);
                    inserirInstrucaoComando.inserirInstrucaoComando();
                    break;
                default:
                    System.out.print("deu ruim");
            }
                    
            
        } catch (IOException ex) {
            Logger.getLogger(GestorSkeleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
