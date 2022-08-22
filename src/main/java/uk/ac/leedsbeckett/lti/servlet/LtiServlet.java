/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.leedsbeckett.lti.servlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import uk.ac.leedsbeckett.lti.config.LtiConfiguration;
import uk.ac.leedsbeckett.lti.state.LtiState;
import uk.ac.leedsbeckett.lti.state.LtiStateStore;

/**
 *
 * @author jon
 * @param <T>
 */
public abstract class LtiServlet<T extends LtiState> extends HttpServlet
{  
  /**
   * Implementations use this method to tell this object how to get the configuration.
   * 
   * @param context The servlet context.
   * @return An LtiConfiguration object.
   */
  protected abstract LtiConfiguration getLtiConfiguration( ServletContext context );
  
  /**
   * Implementations use this method to tell this object how to store state.
   * @param context The servlet context.
   * @return An LtiStateStore object.
   */
  protected abstract LtiStateStore<T> getLtiStateStore( ServletContext context );  
}
