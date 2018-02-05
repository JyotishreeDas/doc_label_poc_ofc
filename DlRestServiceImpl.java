package com.kn.documentlabelling.dl.service.impl.rest;

import java.io.IOException;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;

import com.kn.documentlabelling.dl.logic.api.Dl;
import com.kn.documentlabelling.dl.service.api.rest.DlRestService;

/**
 * The service implementation for REST calls in order to execute the logic of component {@link Dl}.
 */
@Named("DlRestService")
public class DlRestServiceImpl implements DlRestService {

  @Inject
  private Dl dl;

  @Override
  public Response findAllFiles() {

    return this.dl.findAllFiles();
  }

  @Override
  public Response fileToText(String filename) {

    System.out.println("filename-----" + filename);

    try {
      return this.dl.getFileByName(filename);
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("Return null");
    return null;

  }
}