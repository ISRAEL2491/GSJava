package br.com.fiap.resource;

import br.com.fiap.bo.AparelhoBO;
import br.com.fiap.exception.AparelhoException;
import br.com.fiap.to.AparelhoTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/aparelhos")
public class AparelhoResource {

    private AparelhoBO aparelhoBO = new AparelhoBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid AparelhoTO aparelho) {
        try {
            AparelhoTO resultado = aparelhoBO.create(aparelho);
            Response.ResponseBuilder response = null;
            if (resultado != null){
                response = Response.created(null); //201 - CREATED
            }else {
                response = Response.status(400); //400 - BAD REQUEST
            }
            response.entity(resultado);
            return response.build();
        } catch (AparelhoException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            ArrayList<AparelhoTO> resultado = aparelhoBO.findAll();
            Response.ResponseBuilder response = null;
            if (resultado != null){
                response = Response.ok(); //200 (OK)
            }else {
                response = Response.status(404); //404 (NOT FOUND)
            }
            response.entity(resultado);
            return response.build();
        } catch (AparelhoException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build(); // 500 - Internal Server Error
        }
    }

    @GET
    @Path("/{id_consumo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id_consumo") Long idAparelho) {
        try {
            AparelhoTO resultado = aparelhoBO.findById(idAparelho);
            Response.ResponseBuilder response = null;
            if (resultado != null){
                response = Response.ok(); //200 (OK)
            }else {
                response = Response.status(404); //404 (NOT FOUND)
            }
            response.entity(resultado);
            return response.build();
        } catch (AparelhoException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build(); // 400 - Bad Request
        }
    }

    @PUT
    @Path("/{id_consumo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id_consumo") Long idAparelho, @Valid AparelhoTO aparelho) throws SQLException {
        try {
            aparelho.setIdAparelho(idAparelho);
            AparelhoTO resultado = aparelhoBO.update(aparelho);
            Response.ResponseBuilder response = null;
            if (resultado != null){
                response = Response.created(null); //201 - CREATED
            }else {
                response = Response.status(400); //400 - BAD REQUEST
            }
            response.entity(resultado);
            return response.build();
        } catch (AparelhoException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build(); // 400 - Bad Request
        }
    }

    @DELETE
    @Path("/{id_consumo}")
    public Response delete(@PathParam("id_consumo") Long idAparelho) {
        try {
            Response.ResponseBuilder response = null;
            if (aparelhoBO.delete(idAparelho)){
                response = Response.status(204); //204 - NO CONTENT
            }else {
                response = Response.status(404); //404 - NOT FOUND
            }
            return response.build();
        } catch (AparelhoException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build(); // 400 - Bad Request
        }
    }
}
