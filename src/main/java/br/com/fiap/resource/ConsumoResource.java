package br.com.fiap.resource;

import br.com.fiap.bo.ConsumoBO;
import br.com.fiap.exception.ConsumoException;
import br.com.fiap.to.ConsumoTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/consumos")
public class ConsumoResource {

    private ConsumoBO consumoBO = new ConsumoBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid ConsumoTO consumo) {
        try {
            ConsumoTO resultado = consumoBO.create(consumo);
            Response.ResponseBuilder response = null;
            if (resultado != null){
                response = Response.created(null); //201 - CREATED
            }else {
                response = Response.status(400); //400 - BAD REQUEST
            }
            response.entity(resultado);
            return response.build();
        } catch (ConsumoException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            ArrayList<ConsumoTO> resultado = consumoBO.findAll();
            Response.ResponseBuilder response = null;
            if (resultado != null){
                response = Response.ok(); //200 (OK)
            }else {
                response = Response.status(404); //404 (NOT FOUND)
            }
            response.entity(resultado);
            return response.build();
        } catch (ConsumoException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build(); // 500 - Internal Server Error
        }
    }

    @GET
    @Path("/{id_consumo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id_consumo") Long idConsumo) {
        try {
            ConsumoTO resultado = consumoBO.findById(idConsumo);
            Response.ResponseBuilder response = null;
            if (resultado != null){
                response = Response.ok(); //200 (OK)
            }else {
                response = Response.status(404); //404 (NOT FOUND)
            }
            response.entity(resultado);
            return response.build();
        } catch (ConsumoException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build(); // 400 - Bad Request
        }
    }

    @PUT
    @Path("/{id_consumo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id_consumo") Long idConsumo, @Valid ConsumoTO consumo) throws SQLException {
        try {
            consumo.setIdConsumo(idConsumo);
            ConsumoTO resultado = consumoBO.update(consumo);
            Response.ResponseBuilder response = null;
            if (resultado != null){
                response = Response.created(null); //201 - CREATED
            }else {
                response = Response.status(400); //400 - BAD REQUEST
            }
            response.entity(resultado);
            return response.build();
        } catch (ConsumoException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build(); // 400 - Bad Request
        }
    }

    @DELETE
    @Path("/{id_consumo}")
    public Response delete(@PathParam("id_consumo") Long idConsumo) {
        try {
            Response.ResponseBuilder response = null;
            if (consumoBO.delete(idConsumo)){
                response = Response.status(204); //204 - NO CONTENT
            }else {
                response = Response.status(404); //404 - NOT FOUND
            }
            return response.build();
        } catch (ConsumoException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build(); // 400 - Bad Request
        }
    }
}
