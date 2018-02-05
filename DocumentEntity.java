package com.kn.documentlabelling.dl.dataaccess.api;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kn.documentlabelling.dl.common.api.Document;
import com.kn.documentlabelling.general.dataaccess.api.ApplicationPersistenceEntity;

/**
 * @author nithnaik
 *
 */
@Entity
@Table(name = "FILES")
public class DocumentEntity extends ApplicationPersistenceEntity implements Document {

  private Long id;

  private String fileName;

  private Long fileSize;

  private Blob fileContent;

  private static final long serialVersionUID = 1L;

  @Override
  @SuppressWarnings("javadoc")
  @Column(name = "ID")
  @Id
  @GeneratedValue
  public Long getId() {

    return this.id;
  }

  @SuppressWarnings("javadoc")
  @Override
  public void setId(Long id) {

    this.id = id;
  }

  @SuppressWarnings("javadoc")
  @Column(name = "FILE_NAME")
  public String getFileName() {

    return this.fileName;
  }

  @SuppressWarnings("javadoc")
  public void setFileName(String fileName) {

    this.fileName = fileName;
  }

  @SuppressWarnings("javadoc")
  @Column(name = "FILE_SIZE")
  public Long getFileSize() {

    return this.fileSize;
  }

  @SuppressWarnings("javadoc")
  public void setFileSize(Long fileSize) {

    this.fileSize = fileSize;
  }

  @SuppressWarnings("javadoc")
  @Column(name = "FILE_CONTENT")
  @JsonIgnore
  public Blob getFileContent() {

    return this.fileContent;
  }

  @SuppressWarnings("javadoc")
  public void setFileContent(Blob fileContent) {

    this.fileContent = fileContent;
  }

}
