package domain.http.service.http;

import domain.http.Agencia;
import domain.http.AgenciaHttp;
import domain.http.SituacaoCadastral;
import domain.http.exceptions.AgenciaNaoAtivaOuNaoEncontradaException;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AgenciaService {

    @RestClient
    private SituacaoCadastralHtppService situacaoCadastralHtppService;

    private List<Agencia> agencias = new ArrayList<>();

    public void cadastrar(Agencia agencia){
        AgenciaHttp agenciaHttp =
                situacaoCadastralHtppService.buscarPorCnpj(agencia.getCnpj());
        if(agenciaHttp != null &&
                agenciaHttp.getSituacaoCadastral().equals(SituacaoCadastral.ATIVO)){
            agencias.add(agencia);
        } else {
            throw new AgenciaNaoAtivaOuNaoEncontradaException();
        }
    }

    public Agencia buscarPorId(Interger id){
        return agencias.stream()
                .filter(agencia -> agencia.getId().equals(id)).toList().getFirst();
    }

    public void deletar(Integer id){
        agencias.removeIf(agencia -> agencia.getId().equals(id));
    }

    public void alterar(Agencia agencia){
        deletar(agencia.getId());
        cadastrar(agencia);
    }
}
