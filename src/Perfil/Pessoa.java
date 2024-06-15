package perfil;

import java.time.LocalDateTime;
import java.util.Date;
public class Pessoa {
    String usuario, senha, especialidade,clienteAux;
    String protocolo;
    LocalDateTime dataHora;


    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }


    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }


    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getClienteAux() {
        return clienteAux;
    }

    public void setClienteAux(String clienteAux) {
        this.clienteAux = clienteAux;
    }

    @Override
    public String toString() {
        return "Resultado da Agenda [Protocolo=" + protocolo + ", Usuario" + usuario +", Especialidade" + especialidade + ", data Agendada" + dataHora +"]";
    }

}

