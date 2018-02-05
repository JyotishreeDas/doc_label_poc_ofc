package com.kn.documentlabelling.dl.service.api.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.kn.documentlabelling.dl.logic.api.Dl;
import com.kn.documentlabelling.general.common.api.RestService;

/**
 * The service interface for REST calls in order to execute the logic of component {@link Dl}.
 */
@Path("/documentlabellingservice")
@Produces(MediaType.APPLICATION_JSON)
public interface DlRestService extends RestService {

  /**
   * @return
   */
  @SuppressWarnings("javadoc")
  @GET
  @Path("/document/{id}/")
  public Response findAllFiles();

  @SuppressWarnings("javadoc")
  @GET
  @Path("/documenttext/{filename}")
  public Response fileToText(@PathParam("filename") String filename);
}