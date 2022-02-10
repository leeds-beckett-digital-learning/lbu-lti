/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package uk.ac.leedsbeckett.lti.servlet;

import uk.ac.leedsbeckett.lti.LtiMessageLaunch;
import uk.ac.leedsbeckett.lti.LtiException;
import io.jsonwebtoken.Claims;
import uk.ac.leedsbeckett.lti.state.LtiStateStore;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import uk.ac.leedsbeckett.lti.claims.LtiClaims;
import uk.ac.leedsbeckett.lti.state.LtiState;

/**
 *
 * @author jon
 */
public abstract class LtiLaunchServlet extends HttpServlet
{

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest( HttpServletRequest request, HttpServletResponse response )
          throws ServletException, IOException
  {    
    if ( !"POST".equals( request.getMethod() ) )
    {
      response.sendError( 500, "Only POST method accepted at this URL." );
      return;
    }

    LtiStateStore statestore = getLtiStateStore( request.getServletContext() );
    if ( statestore == null )
    {
      response.sendError( 500, "No state store configured." );
      return;
    }

    String stateid = request.getParameter( "state" );
    if ( StringUtils.isEmpty( stateid ) )
    {
      response.sendError( 500, "This URL requires a parameter named 'state'." );
      return;
    }
    
    String id_token = request.getParameter( "id_token" );
    if ( StringUtils.isEmpty( id_token ) )
    {
      response.sendError( 500, "This URL requires a parameter named 'id_token'." );
      return;
    }
    
    LtiState state = statestore.getState( stateid );
    if ( state == null )
    {
      response.sendError( 500, "Requested LTI 1.3 state has expired or never existed." );
      return;
    }
    
    
    LtiMessageLaunch ml = new LtiMessageLaunch( request, state );
    try
    {
      ml.validate();
    }
    catch ( LtiException ex )
    {
      Logger.getLogger(LtiLaunchServlet.class.getName() ).severe( ex.getMessage() );
      response.sendError( 500, "Failed to validate the launch message. " + ex.getMessage() );
      return;
    }

    Claims claims = ml.getClaims();
    Logger.getLogger(LtiLaunchServlet.class.getName() ).log( Level.INFO, "Claims : \n" + claims.toString() );
    
    if ( !"LtiResourceLinkRequest".equals( claims.get( "https://purl.imsglobal.org/spec/lti/claim/message_type" ) ) )
    {
      response.sendError( 500, "An unknown type of message was found in the launch request." );
      return;      
    }
    
    if ( !"1.3.0".equals( claims.get( "https://purl.imsglobal.org/spec/lti/claim/version" ) ) )
    {
      response.sendError( 500, "This tool can only handle LTI version 1.3." );
      return;      
    }
    
    processLaunchRequest( new LtiClaims( ml.getClaims() ), ml.getState(), request, response );
  }
  
  protected abstract void processLaunchRequest( LtiClaims lticlaims, LtiState state, HttpServletRequest request, HttpServletResponse response )
          throws ServletException, IOException;
  
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
  protected void doGet( HttpServletRequest request, HttpServletResponse response )
          throws ServletException, IOException
  {
    processRequest( request, response );
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
  protected void doPost( HttpServletRequest request, HttpServletResponse response )
          throws ServletException, IOException
  {
    processRequest( request, response );
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
