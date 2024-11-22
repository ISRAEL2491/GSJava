package br.com.fiap.resource;

import br.com.fiap.bo.UsuarioBO;
import br.com.fiap.exception.UsuarioException;
import br.com.fiap.to.UsuarioTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/usuarios")
public class UsuarioResource {

    private UsuarioBO usuarioBO = new UsuarioBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid UsuarioTO usuario) {
        try {
            UsuarioTO resultado = usuarioBO.create(usuario);
            Response.ResponseBuilder response = null;
            if (resultado != null){
                response = Response.created(null); //201 - CREATED
            }else {
                response = Response.status(400); //400 - BAD REQUEST
            }
            response.entity(resultado);
            return response.build();
        } catch (UsuarioException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            ArrayList<UsuarioTO> resultado = usuarioBO.findAll();
            Response.ResponseBuilder response = null;
            if (resultado != null){
                response = Response.ok(); //200 (OK)
            }else {
                response = Response.status(404); //404 (NOT FOUND)
            }
            response.entity(resultado);
            return response.build();
        } catch (UsuarioException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build(); // 500 - Internal Server Error
        }
    }

    @GET
    @Path("/{id_usuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id_usuario") Long idUsuario) {
        try {
            UsuarioTO resultado = usuarioBO.findById(idUsuario);
            Response.ResponseBuilder response = null;
            if (resultado != null){
                response = Response.ok(); //200 (OK)
            }else {
                response = Response.status(404); //404 (NOT FOUND)
            }
            response.entity(resultado);
            return response.build();
        } catch (UsuarioException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build(); // 400 - Bad Request
        }
    }

    @PUT
    @Path("/{id_usuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id_usuario") Long idUsuario, @Valid UsuarioTO usuario) throws SQLException {
        try {
            usuario.setIdUsuario(idUsuario);
            UsuarioTO resultado = usuarioBO.update(usuario);
            Response.ResponseBuilder response = null;
            if (resultado != null){
                response = Response.created(null); //201 - CREATED
            }else {
                response = Response.status(400); //400 - BAD REQUEST
            }
            response.entity(resultado);
            return response.build();
        } catch (UsuarioException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build(); // 400 - Bad Request
        }
    }

    @DELETE
    @Path("/{id_usuario}")
    public Response delete(@PathParam("id_usuario") Long idUsuario) {
        try {
            Response.ResponseBuilder response = null;
            if (usuarioBO.delete(idUsuario)){
                response = Response.status(204); //204 - NO CONTENT
            }else {
                response = Response.status(404); //404 - NOT FOUND
            }
            return response.build();
        } catch (UsuarioException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build(); // 400 - Bad Request
        }
    }
}
