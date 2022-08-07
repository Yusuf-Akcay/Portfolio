package de.yusuf;

import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

public class CustomExceptionHandlerFactory extends ExceptionHandlerFactory  {
  private ExceptionHandlerFactory exceptionHandlerFactory;

  public CustomExceptionHandlerFactory() {}

  public CustomExceptionHandlerFactory(
    ExceptionHandlerFactory exceptionHandlerFactory ) {
    this.exceptionHandlerFactory = exceptionHandlerFactory;
  }

  @Override
  public ExceptionHandler getExceptionHandler() {
      return new CustomExceptionHandler(exceptionHandlerFactory.getExceptionHandler());
  }
}

class CustomExceptionHandler extends ExceptionHandlerWrapper  {
  private final ExceptionHandler exceptionHandler;

  public CustomExceptionHandler(ExceptionHandler exceptionHandler) {
    this.exceptionHandler = exceptionHandler;
  }

  @Override
  public ExceptionHandler getWrapped() {
    return exceptionHandler;
  }

  @Override
  public void handle() throws FacesException {
    final Iterator<ExceptionQueuedEvent> queue = getUnhandledExceptionQueuedEvents().iterator();

    while (queue.hasNext()) {
      ExceptionQueuedEvent item = queue.next();
      ExceptionQueuedEventContext exceptionQueuedEventContext = (ExceptionQueuedEventContext) item.getSource();

      try {
        Throwable throwable = exceptionQueuedEventContext.getException();
        System.err.println("Exception: " + throwable.getMessage());

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> requestMap = context.getExternalContext().getRequestMap();
        NavigationHandler nav = context.getApplication().getNavigationHandler();

        requestMap.put("error-message", throwable.getMessage());
        requestMap.put("error-stack", throwable.getStackTrace());
        nav.handleNavigation(context, null, "/error");
        context.renderResponse();
      } finally {
        queue.remove();
      }
    }
  }
}