package br.com.fiap.resource;

import br.com.fiap.bo.EstimativaBO;
import br.com.fiap.exception.EstimativaException;
import br.com.fiap.to.EstimativaTO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/estimativas")
public class EstimativaResource {

    private EstimativaBO estimativaBO = new EstimativaBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criarEstimativa(EstimativaTO estimativa) {
        try {
            estimativaBO.criarEstimativa(estimativa);
            return Response.status(Response.Status.CREATED).entity(estimativa).build();
        } catch (EstimativaException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarEstimativas() throws EstimativaException {
        List<EstimativaTO> estimativas = estimativaBO.listarEstimativas();
        return Response.ok(estimativas).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarEstimativaPorId(@PathParam("id") Integer id) {
        try {
            EstimativaTO estimativa = estimativaBO.buscarEstimativaPorId(id);
            return Response.ok(estimativa).build();
        } catch (EstimativaException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarEstimativa(@PathParam("id") Integer id, EstimativaTO estimativa) {
        try {
            estimativa.setIdEstimativa(id);
            estimativaBO.atualizarEstimativa(estimativa);
            return Response.status(Response.Status.OK).entity(estimativa).build();
        } catch (EstimativaException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluirEstimativa(@PathParam("id") Integer id) {
        try {
            estimativaBO.excluirEstimativa(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (EstimativaException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
