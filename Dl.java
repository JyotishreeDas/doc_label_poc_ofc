package com.kn.documentlabelling.dl.logic.api;

import java.io.IOException;
import java.sql.SQLException;

import javax.ws.rs.core.Response;

/**
 * Interface for Dl component.
 */
public interface Dl {

  /**
   * Returns a Document by its id 'id'.
   *
   * @param id The id 'id' of the Document.
   * @return The {@link DocumentEto} with id 'id'
   */
  Response findAllFiles();

  /**
   * Fetches the document content from Blob in database.
   *
   * @param fileName
   * @return
   * @throws IOException
   * @throws SQLException
   */
  Response getDocumentContent(String fileName) throws IOException, SQLException;

  public Response getFileByName(String fileName) throws SQLException, IOException;

}