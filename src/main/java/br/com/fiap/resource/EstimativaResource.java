package br.com.fiap.resource;

import br.com.fiap.bo.EstimativaBO;
import br.com.fiap.exception.EstimativaException;
import br.com.fiap.to.EstimativaTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/estimativas")
public class EstimativaResource {

    private EstimativaBO estimativaBO = new EstimativaBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid EstimativaTO estimativa) {
        try {
            EstimativaTO resultado = estimativaBO.create(estimativa);
            Response.ResponseBuilder response = null;
            if (resultado != null){
                response = Response.created(null); //201 - CREATED
            }else {
                response = Response.status(400); //400 - BAD REQUEST
            }
            response.entity(resultado);
            return response.build();
        } catch (EstimativaException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            ArrayList<EstimativaTO> resultado = estimativaBO.findAll();
            Response.ResponseBuilder response = null;
            if (resultado != null){
                response = Response.ok(); //200 (OK)
            }else {
                response = Response.status(404); //404 (NOT FOUND)
            }
            response.entity(resultado);
            return response.build();
        } catch (EstimativaException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build(); // 500 - Internal Server Error
        }
    }

    @GET
    @Path("/{id_estimativa}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id_estimativa") Long idEstimativa) {
        try {
            EstimativaTO resultado = estimativaBO.findById(idEstimativa);
            Response.ResponseBuilder response = null;
            if (resultado != null){
                response = Response.ok(); //200 (OK)
            }else {
                response = Response.status(404); //404 (NOT FOUND)
            }
            response.entity(resultado);
            return response.build();
        } catch (EstimativaException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build(); // 400 - Bad Request
        }
    }

    @PUT
    @Path("/{id_estimativa}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id_estimativa") Long idEstimativa, @Valid EstimativaTO estimativa) throws SQLException {
        try {
            estimativa.setIdEstimativa(idEstimativa);
            EstimativaTO resultado = estimativaBO.update(estimativa);
            Response.ResponseBuilder response = null;
            if (resultado != null){
                response = Response.created(null); //201 - CREATED
            }else {
                response = Response.status(400); //400 - BAD REQUEST
            }
            response.entity(resultado);
            return response.build();
        } catch (EstimativaException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build(); // 400 - Bad Request
        }
    }

    @DELETE
    @Path("/{id_estimativa}")
    public Response delete(@PathParam("id_estimativa") Long idEstimativa) {
        try {
            Response.ResponseBuilder response = null;
            if (estimativaBO.delete(idEstimativa)){
                response = Response.status(204); //204 - NO CONTENT
            }else {
                response = Response.status(404); //404 - NOT FOUND
            }
            return response.build();
        } catch (EstimativaException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build(); // 400 - Bad Request
        }
    }
}
