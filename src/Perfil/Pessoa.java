package perfil;

import java.util.Date;
public class Pessoa {
    String usuario, senha, especialidade,clienteAux;
    int protocolo;
    Date dataHora;


    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }


    public int getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(int protocolo) {
        this.protocolo = protocolo;
    }


    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
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

