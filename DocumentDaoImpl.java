package com.kn.documentlabelling.dl.dataaccess.impl.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Named;
import javax.persistence.Query;

import com.kn.documentlabelling.dl.common.api.constants.DocumentNamedQueries;
import com.kn.documentlabelling.dl.dataaccess.api.DocumentEntity;
import com.kn.documentlabelling.dl.dataaccess.api.dao.DocumentDao;
import com.kn.documentlabelling.general.dataaccess.base.dao.ApplicationDaoImpl;

/**
 * This is the implementation of {@link DocumentDao}.
 */
@Named
public class DocumentDaoImpl extends ApplicationDaoImpl<DocumentEntity> implements DocumentDao {

  /**
   * The constructor.
   */
  public DocumentDaoImpl() {

    super();
  }

  @SuppressWarnings("javadoc")
  @Override
  public Class<DocumentEntity> getEntityClass() {

    return DocumentEntity.class;
  }

  @SuppressWarnings("javadoc")
  @Override
  public List<DocumentEntity> findAllFiles() {

    Query query = getEntityManager().createNamedQuery(DocumentNamedQueries.GET_FILES_BY_NAME);

    return query.getResultList();
  }

  @Override
  public byte[] getDocumentContent(String fileName) throws IOException, SQLException {

    Query query = getEntityManager().createNamedQuery(DocumentNamedQueries.GET_FILE_CONTENT_BY_NAME);

    query.setParameter("fileName", fileName);

    byte[] buf = new byte[1024];
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    DocumentEntity documentEntity = (DocumentEntity) query.getSingleResult();
    InputStream in = (documentEntity.getFileContent()).getBinaryStream();
    int n = 0;
    while ((n = in.read(buf)) >= 0) {
      baos.write(buf, 0, n);
    }
    in.close();

    return baos.toByteArray();

  }

  @Override
  public Object getFileByName(String fileName) {

    Query query = getEntityManager().createNamedQuery(DocumentNamedQueries.GET_FILE_CONTENT_BY_NAME_FORTEXT);
    query.setParameter("fileName", fileName);
    return query.getSingleResult();
  }

}