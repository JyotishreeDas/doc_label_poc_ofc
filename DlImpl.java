package com.kn.documentlabelling.dl.logic.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.apache.tika.exception.TikaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.kn.documentlabelling.dl.common.api.util.FileToTextConverter;
import com.kn.documentlabelling.dl.dataaccess.api.DocumentEntity;
import com.kn.documentlabelling.dl.dataaccess.api.dao.DocumentDao;
import com.kn.documentlabelling.dl.logic.api.Dl;
import com.kn.documentlabelling.general.logic.base.AbstractComponentFacade;

/**
 * Implementation of component interface of dl
 */
@Named
public class DlImpl extends AbstractComponentFacade implements Dl {

  /** Logger instance. */
  private static final Logger LOG = LoggerFactory.getLogger(DlImpl.class);

  /** @see #getDocumentDao() */
  @Inject
  private DocumentDao documentDao;

  /**
   * The constructor.
   */
  public DlImpl() {
    super();
  }

  /**
   * @return
   */
  public DocumentDao getDocumentDao() {

    return this.documentDao;
  }

  @SuppressWarnings("javadoc")
  @Override
  public Response findAllFiles() {

    List<DocumentEntity> files = this.documentDao.findAllFiles();
    return Response.status(200).entity(files).build();
  }

  @SuppressWarnings("javadoc")
  @Override
  public Response getDocumentContent(String fileName) throws IOException, SQLException {

    byte[] content = getDocumentDao().getDocumentContent(fileName);

    String encodeImage = Base64.getEncoder().withoutPadding().encodeToString(content);

    Map<String, String> jsonMap = new HashMap<String, String>();

    jsonMap.put("content", encodeImage);

    return Response.status(200).entity(jsonMap).build();

  }

  @SuppressWarnings("javadoc")
  @Override
  public Response getFileByName(String fileName) throws SQLException, IOException {

    InputStream fis = null;
    OutputStream fos = null;
    File file = null, ipFile = null;
    Map<String, String> jsonMap = new HashMap<String, String>();
    file = new File("testdata");
    if (!file.exists()) {
      file.mkdir();
    }
    ipFile = new File(file.getAbsolutePath() + "/" + fileName);
    FileToTextConverter ftxt = new FileToTextConverter();
    Object obj = this.documentDao.getFileByName(fileName);
    if (obj != null) {
      Blob b = (Blob) obj;
      fis = b.getBinaryStream();
      fos = new FileOutputStream(ipFile);
      IOUtils.copy(fis, fos);
    }
    String content = "";
    try {
      if (ipFile != null && ipFile.length() > 0) {
        content = ftxt.convertFileToText(ipFile, fileName);
      }
      fis = null;
      fos = null;
      file = null;
      ipFile.deleteOnExit();
      ipFile = null;

    } catch (SAXException e) {
      e.printStackTrace();
    } catch (TikaException e) {
      e.printStackTrace();
    }
    jsonMap.put("content", content);
    return Response.status(200).entity(jsonMap).build();
  }

}