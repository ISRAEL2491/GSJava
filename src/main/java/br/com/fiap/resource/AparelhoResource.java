package br.com.fiap.resource;

import br.com.fiap.bo.AparelhoBO;
import br.com.fiap.exception.AparelhoException;
import br.com.fiap.to.AparelhoTO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/aparelhos")
public class AparelhoResource {

    private AparelhoBO aparelhoBO = new AparelhoBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criarAparelho(AparelhoTO aparelho) {
        try {
            aparelhoBO.criarAparelho(aparelho);
            return Response.status(Response.Status.CREATED).entity(aparelho).build();
        } catch (AparelhoException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarAparelhos() {
        List<AparelhoTO> aparelhos = aparelhoBO.listarAparelhos();
        return Response.ok(aparelhos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarAparelhoPorId(@PathParam("id") Integer id) {
        try {
            AparelhoTO aparelho = aparelhoBO.buscarAparelhoPorId(id);
            return Response.ok(aparelho).build();
        } catch (AparelhoException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarAparelho(@PathParam("id") Integer id, AparelhoTO aparelho) {
        try {
            aparelho.setIdAparelho(id);
            aparelhoBO.atualizarAparelho(aparelho);
            return Response.status(Response.Status.OK).entity(aparelho).build();
        } catch (AparelhoException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluirAparelho(@PathParam("id") Integer id) {
        try {
            aparelhoBO.excluirAparelho(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (AparelhoException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
