package br.com.fiap.resource;

import br.com.fiap.bo.TarifaBO;
import br.com.fiap.exception.TarifaException;
import br.com.fiap.to.TarifaTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/tarifas")
public class TarifaResource {

    private TarifaBO tarifaBO = new TarifaBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid TarifaTO tarifa) {
        try {
            TarifaTO resultado = tarifaBO.create(tarifa);
            Response.ResponseBuilder response = null;
            if (resultado != null){
                response = Response.created(null); //201 - CREATED
            }else {
                response = Response.status(400); //400 - BAD REQUEST
            }
            response.entity(resultado);
            return response.build();
        } catch (TarifaException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            ArrayList<TarifaTO> resultado = tarifaBO.findAll();
            Response.ResponseBuilder response = null;
            if (resultado != null){
                response = Response.ok(); //200 (OK)
            }else {
                response = Response.status(404); //404 (NOT FOUND)
            }
            response.entity(resultado);
            return response.build();
        } catch (TarifaException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build(); // 500 - Internal Server Error
        }
    }

    @GET
    @Path("/{id_tarifa}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id_tarifa") Long idTarifa) {
        try {
            TarifaTO resultado = tarifaBO.findById(idTarifa);
            Response.ResponseBuilder response = null;
            if (resultado != null){
                response = Response.ok(); //200 (OK)
            }else {
                response = Response.status(404); //404 (NOT FOUND)
            }
            response.entity(resultado);
            return response.build();
        } catch (TarifaException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build(); // 400 - Bad Request
        }
    }

    @PUT
    @Path("/{id_tarifa}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id_tarifa") Long idTarifa, @Valid TarifaTO tarifa) throws SQLException {
        try {
            tarifa.setIdTarifa(idTarifa);
            TarifaTO resultado = tarifaBO.update(tarifa);
            Response.ResponseBuilder response = null;
            if (resultado != null){
                response = Response.created(null); //201 - CREATED
            }else {
                response = Response.status(400); //400 - BAD REQUEST
            }
            response.entity(resultado);
            return response.build();
        } catch (TarifaException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build(); // 400 - Bad Request
        }
    }

    @DELETE
    @Path("/{id_tarifa}")
    public Response delete(@PathParam("id_tarifa") Long idTarifa) {
        try {
            Response.ResponseBuilder response = null;
            if (tarifaBO.delete(idTarifa)){
                response = Response.status(204); //204 - NO CONTENT
            }else {
                response = Response.status(404); //404 - NOT FOUND
            }
            return response.build();
        } catch (TarifaException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build(); // 400 - Bad Request
        }
    }
}
