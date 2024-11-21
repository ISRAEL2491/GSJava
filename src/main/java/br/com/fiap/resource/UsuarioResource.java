package br.com.fiap.resource;

import br.com.fiap.bo.UsuarioBO;
import br.com.fiap.exception.UsuarioException;
import br.com.fiap.to.UsuarioTO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/usuarios")
public class UsuarioResource {

    private UsuarioBO usuarioBO = new UsuarioBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criarUsuario(UsuarioTO usuario) {
        try {
            usuarioBO.criarUsuario(usuario);
            return Response.status(Response.Status.CREATED).entity(usuario).build();
        } catch (UsuarioException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarUsuarios() throws UsuarioException {
        List<UsuarioTO> usuarios = usuarioBO.listarUsuarios();
        return Response.ok(usuarios).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarUsuarioPorId(@PathParam("id") Integer id) {
        try {
            UsuarioTO usuario = usuarioBO.buscarUsuarioPorId(id);
            return Response.ok(usuario).build();
        } catch (UsuarioException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarUsuario(@PathParam("id") Integer id, UsuarioTO usuario) {
        try {
            usuario.setIdUsuario(id);
            usuarioBO.atualizarUsuario(usuario);
            return Response.status(Response.Status.OK).entity(usuario).build();
        } catch (UsuarioException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluirUsuario(@PathParam("id") Integer id) {
        try {
            usuarioBO.excluirUsuario(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (UsuarioException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
