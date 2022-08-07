package de.yusuf.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class AbstractController {
	protected final Logger log = LoggerFactory.getLogger( this.getClass() );
	public abstract void init() throws IOException;
	protected void message( FacesMessage.Severity severity, String summary, String detail){
		FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( severity, summary, detail ) );
	}
	protected String path() throws IOException {
		return Paths.get("").toRealPath().toString() + "/apache-tomcat-9.0.60/webapps/ROOT/resources/database/products.xml";
	}
}
