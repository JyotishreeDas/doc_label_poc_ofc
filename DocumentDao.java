package com.kn.documentlabelling.dl.dataaccess.api.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.kn.documentlabelling.dl.dataaccess.api.DocumentEntity;
import com.kn.documentlabelling.general.dataaccess.api.dao.ApplicationDao;

/**
 * Data access interface for Document entities
 */
public interface DocumentDao extends ApplicationDao<DocumentEntity> {

  /**
   * @return
   */
  @SuppressWarnings("javadoc")
  public List<DocumentEntity> findAllFiles();

  public byte[] getDocumentContent(String fileName) throws IOException, SQLException;

  @SuppressWarnings("javadoc")
  public Object getFileByName(String fileName);

}
