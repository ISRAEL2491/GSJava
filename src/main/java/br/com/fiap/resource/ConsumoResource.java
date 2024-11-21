package br.com.fiap.resource;

import br.com.fiap.bo.ConsumoBO;
import br.com.fiap.exception.ConsumoException;
import br.com.fiap.to.ConsumoTO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/consumos")
public class ConsumoResource {

    private ConsumoBO consumoBO = new ConsumoBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criarConsumo(ConsumoTO consumo) {
        try {
            consumoBO.criarConsumo(consumo);
            return Response.status(Response.Status.CREATED).entity(consumo).build();
        } catch (ConsumoException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarConsumos() throws ConsumoException {
        List<ConsumoTO> consumos = consumoBO.listarConsumos();
        return Response.ok(consumos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarConsumoPorId(@PathParam("id") Integer id) {
        try {
            ConsumoTO consumo = consumoBO.buscarConsumoPorId(id);
            return Response.ok(consumo).build();
        } catch (ConsumoException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarConsumo(@PathParam("id") Integer id, ConsumoTO consumo) {
        try {
            consumo.setIdConsumo(id);
            consumoBO.atualizarConsumo(consumo);
            return Response.status(Response.Status.OK).entity(consumo).build();
        } catch (ConsumoException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluirConsumo(@PathParam("id") Integer id) {
        try {
            consumoBO.excluirConsumo(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (ConsumoException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
