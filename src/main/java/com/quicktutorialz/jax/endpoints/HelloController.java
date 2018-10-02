package com.quicktutorialz.jax.endpoints;

import com.quicktutorialz.jax.entities.Todo;
import com.quicktutorialz.jax.services.TodoService;
import com.quicktutorialz.jax.services.TodoServiceImpl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Path("/hello")
public class HelloController {


    private TodoService todoService;

    public HelloController(){
        this.todoService = TodoServiceImpl.getInstance();
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTestMsg() {
        return "It works";
    }

    @GET
    @Path("/zucchi")
    public void doLoad(@Suspended final AsyncResponse asyncResponse,
                       @QueryParam("path") String path)  {

        CompletableFuture<Response> future = veryExpensiveOperation(path);
        future.thenAccept(resp -> asyncResponse.resume(resp));
    }
    private CompletableFuture<Response> veryExpensiveOperation(String path){
        CompletableFuture<Response> completableFuture = new CompletableFuture<>();

        new Thread(() -> {
            //do expensive stuff here
            completableFuture.complete(Response.ok().entity("Completed").build());
        }).start();

        return completableFuture;
    }



    @POST
    @Path("/goes")
    @Consumes("application/json")
    @Produces("application/json")
    public void createTodoAsync3(@Suspended final AsyncResponse asyncResponse, Todo todo) {

        CompletableFuture<Response> future = CompletableFuture.supplyAsync(() -> createTodo3(todo));
        future.thenAccept(resp -> asyncResponse.resume(resp));
    }
    private Response createTodo3(Todo todo) {
        //all logic goes here
        return Response.accepted().entity(todo).build();
    }

    @POST
    @Path("/goesy")
    @Consumes("application/json")
    @Produces("application/json")
    public void createTodoAsync4(@Suspended final AsyncResponse asyncResponse, Todo todo) {

        CompletableFuture.supplyAsync(() -> wrapResponse(todoService.create(todo)))
                         .thenAccept(resp -> asyncResponse.resume(resp));
    }


    private Response wrapResponse(Object entity){
        if(entity != null){
            return Response.accepted().entity(entity).build();
        }else{
            return Response.serverError().build();
        }
    }



}
