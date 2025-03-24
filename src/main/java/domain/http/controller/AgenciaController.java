package domain.http.controller;

import domain.http.Agencia;
import domain.http.service.http.AgenciaService;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/agencias")
public class AgenciaController {

    private AgenciaService agenciaService;

    AgenciaController (AgenciaService agenciaService){
        this.agenciaService = agenciaService;
    }

    @POST
    public RestResponse<Object> cadastrar(Agencia agencia, @Context UriInfo uriInfo){
        this.agenciaService.cadastrar(agencia);
        return RestResponse.created(uriInfo.getAbsolutePath());
    }
}

