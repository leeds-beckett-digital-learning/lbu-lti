/*
 * Copyright 2022 Leeds Beckett University.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.ac.leedsbeckett.lti.servlet;

import uk.ac.leedsbeckett.lti.LtiConfiguration;
import uk.ac.leedsbeckett.lti.state.LtiStateStore;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import uk.ac.leedsbeckett.lti.state.LtiState;

/**
 * An LTI tool should subclass this abstract class to implement the LTI
 * login functionality. The implementation needs to specify how to store
 * state.
 *
 * @author jon
 */
public abstract class LtiLoginServlet extends HttpServlet
{
  static Logger logger = Logger.getLogger( LtiLoginServlet.class.getName() );
  
  /**
   * If necessary sends the user a cookie check page which will present an
   * error if cookies aren't enabled. Otherwise creates a new LTI state 
   * object and starts the process of authenticating the user.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException
  {
    String paramNewWindow         = request.getParameter( "lti1p3_new_window" );
    String paramCookieCheckPassed = request.getParameter( "lti1p3_cookie_check_passed" );
    
    
    
    if ( StringUtils.isEmpty( paramNewWindow ) && StringUtils.isEmpty( paramCookieCheckPassed ) )
      sendCookieCheckPage( request, response );
    else
      forwardToVerification( request, response );    
  }

  /**
   * Inserts a couple of Javascript variables into a standard cookie check
   * web page.
   * 
   * @param request Servlet request
   * @param response Servlet response
   * @throws ServletException Should never be thrown.
   * @throws IOException Thrown if network connection is lost during data transfer.
   */
  protected void sendCookieCheckPage(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException
  {
    // Build the cookie check page, embedding data that will make sure
    // it comes back to the right URL.
    StringBuilder builder = new StringBuilder();
    builder.append( "var siteProtocol = '" );
    builder.append( request.getScheme() );
    builder.append( "';\n" );
    builder.append( "var contentUrl = '" );
    builder.append( request.getRequestURL() );
    builder.append( "?" );
    if ( request.getQueryString() != null )
    {
      builder.append( request.getQueryString() );
      builder.append( "&" );
    }    
    // This URL is used if the cookie test is passed so add parameter
    builder.append( "&lti1p3_cookie_check_passed=true" );
    builder.append( "';\n" );
    
    // Insert those two javascript variables into the page template.
    String html = CookieCheckPage.getPage( builder.toString() );
    
    // Send the page.
    logger.fine( "LtiLoginServlet.sendCookieCheckPage()");
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter())
    {
      out.print( html );
    }
  
  } 
  
  /**
   * Checks that the login refers to our tool, constructs some request parameters
   * and forwards the user to the configured authentication login url. 
   * (E.g. on developer.blackboard.com)
   * 
   * @param request The HTTP request.
   * @param response The HTTP response.
   * @throws ServletException If there is a problem working out how to forward the user's browser.
   * @throws IOException If, for example, the network connection is broken when sending data.
   */
  protected void forwardToVerification(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException
  {
    LtiConfiguration config = this.getLtiConfiguration( request.getServletContext() );
    if ( config == null )
      throw new ServletException( "Cannot load configuration." );

    LtiStateStore statestore = getLtiStateStore( request.getServletContext() );
    if ( statestore == null )
    {
      response.sendError( 500, "No state store configured." );
      return;
    }
    
    String target_link_uri = request.getParameter( "target_link_uri" );
    if ( StringUtils.isEmpty( target_link_uri ) )
    {
      response.sendError( 500, "No target link specified." );
      return;
    }
    
    String iss              = request.getParameter( "iss" );
    String login_hint       = request.getParameter( "login_hint" );
    String client_id        = request.getParameter( "client_id" );
    String lti_message_hint = request.getParameter( "lti_message_hint" );
    
    LtiConfiguration.Client client = config.getClient( iss, client_id );
    if ( client == null )
    {
      logger.log( Level.SEVERE, "Unable to find client in configuration." );
      response.sendError( 500, "Unable to find client in configuration." );
      return;
    }
    logger.log(Level.FINE, "LtiLoginServlet.forwardToVerification() iss              = {0}", iss              );
    logger.log(Level.FINE, "LtiLoginServlet.forwardToVerification() login_hint       = {0}", login_hint       );
    logger.log(Level.FINE, "LtiLoginServlet.forwardToVerification() client_id        = {0}", client_id        );
    logger.log(Level.FINE, "LtiLoginServlet.forwardToVerification() lti_message_hint = {0}", lti_message_hint );
    LtiState state = statestore.createState( client );
    
    QueryBuilder qb = new QueryBuilder();
    qb.add( "scope", "openid" );  // OIDC Scope
    qb.add( "response_type", "id_token"       );  // OIDC response is always an id token
    qb.add( "response_mode", "form_post"      );  // OIDC response is always a form post
    qb.add( "prompt",        "none"           );  // Don't prompt user on redirect
    qb.add( "client_id",     client_id        );  // Registered client id
    qb.add( "redirect_uri",  target_link_uri  );  // URL to return to after login
    qb.add( "state",         state.getId()    );  // Keep track of launch state id
    qb.add( "nonce",         state.getNonce() );  // Prevent replay attacks
    qb.add( "login_hint",    login_hint       );  // Login hint
    if ( !StringUtils.isEmpty( lti_message_hint ) )
      qb.add( "lti_message_hint", lti_message_hint );
        
    response.setStatus( 302 );
    response.setHeader( "Location", client.getAuthLoginUrl() + qb.get() );
  }
  
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
  protected abstract LtiStateStore getLtiStateStore( ServletContext context );
  
  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException
  {
    processRequest(request, response);
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException
  {
    processRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo()
  {
    return "Short description";
  }// </editor-fold>

}
