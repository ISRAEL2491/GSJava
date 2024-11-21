package br.com.fiap.resource;

import br.com.fiap.bo.TarifaBO;
import br.com.fiap.exception.TarifaException;
import br.com.fiap.to.TarifaTO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tarifas")
public class TarifaResource {

    private TarifaBO tarifaBO = new TarifaBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criarTarifa(TarifaTO tarifa) {
        try {
            tarifaBO.criarTarifa(tarifa);
            return Response.status(Response.Status.CREATED).entity(tarifa).build();
        } catch (TarifaException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTarifas() throws TarifaException {
        List<TarifaTO> tarifas = tarifaBO.listarTarifas();
        return Response.ok(tarifas).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarTarifaPorId(@PathParam("id") Integer id) {
        try {
            TarifaTO tarifa = tarifaBO.buscarTarifaPorId(id);
            return Response.ok(tarifa).build();
        } catch (TarifaException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarTarifa(@PathParam("id") Integer id, TarifaTO tarifa) {
        try {
            tarifa.setIdTarifa(id);
            tarifaBO.atualizarTarifa(tarifa);
            return Response.status(Response.Status.OK).entity(tarifa).build();
        } catch (TarifaException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluirTarifa(@PathParam("id") Integer id) {
        try {
            tarifaBO.excluirTarifa(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (TarifaException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
