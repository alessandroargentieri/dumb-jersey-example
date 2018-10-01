package com.quicktutorialz.jax.endpoints;

import com.quicktutorialz.jax.entities.Todo;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.util.Map;
import com.google.gson.Gson;

/*
https://docs.oracle.com/cd/E19776-01/820-4867/6nga7f5np/index.html
 */

@Path("/ciao")
public class CiaoController {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTestMsg()
    {
        return "Ciao!";
    }

    @GET
    @Path("/voi")
    @Produces(MediaType.TEXT_PLAIN)
    public String getTestMsg2()
    {
        return "Ciao a tutti voi!";
    }

    @GET
    @Path("/noi/{nome}/e/{nome2}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getTestMsg3(@PathParam("nome") String nome, @PathParam("nome2") String nome2)
    {
        return "Ciao a noi " + nome + " e " + nome2 ;
    }


    @GET
    @Path("/get/casual/todo/{format}")
    @Produces({"application/xml", "application/json", "plain/text"})
    public Response getTestMsg3(@PathParam("format") String format)
    {
        Todo todo = new Todo("a todo");
        if("json".equals(format)){
            return Response.status(200).type("application/json").entity(todo.toJson()).build();
        }else if("xml".equals(format)){
            return Response.status(200).type("application/xml").entity(todo.toXml()).build();
        }else if("text".equals(format)){
            return Response.status(200).type("text/plain").entity(todo.toString()).build();
        }else{
            return Response.status(400).build();
        }
    }


    @GET
    @Path("/get/casual/todo")
    @Produces({"application/xml", "application/json", "plain/text"})
    public Response getTestMsg4(@QueryParam("format") String format)
    {
        Todo todo = new Todo("a todo");
        if("json".equals(format)){
            return Response.status(200).type("application/json").entity(todo.toJson()).build();
        }else if("xml".equals(format)){
            return Response.status(200).type("application/xml").entity(todo.toXml()).build();
        }else if("text".equals(format)){
            return Response.status(200).type("text/plain").entity(todo.toString()).build();
        }else{
            return Response.status(400).build();
        }
    }

    @POST
    @Path("/add/todo")
    @Consumes("application/x-www-form-urlencoded")
    @Produces({"application/xml", "application/json", "plain/text"})
    public Response getTestMsg5(    @Context HttpHeaders hh,
                                    @QueryParam("format") String format,
                                    @FormParam("description") String description)
    {
        MultivaluedMap<String, String> headerParams = hh.getRequestHeaders();
        Map<String, Cookie> cookieMap = hh.getCookies();

        Todo todo = new Todo(description);
        if("json".equals(format)){
            return Response.status(200).type("application/json").entity(todo.toJson()).build();
        }else if("xml".equals(format)){
            return Response.status(200).type("application/xml").entity(todo.toXml()).build();
        }else if("text".equals(format)){
            return Response.status(200).type("text/plain").entity(todo.toString()).build();
        }else{
            return Response.status(400).build();
        }
    }

    @Path("/test")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String testForm(@FormParam("accept") String accept) {
        return accept;
    }


    @Path("/go")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response testForms(Todo todo) throws IOException {

        Gson gson = new Gson();
        todo.setDescription(todo.getDescription()+ " added");
        return Response.status(200).entity(gson.toJson(todo)).build();
    }

    @Path("/gos")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response testFormsa(Todo todo) throws IOException {
        todo.setDescription(todo.getDescription()+ " added");
        return Response.status(200).entity(todo).build();
    }


}