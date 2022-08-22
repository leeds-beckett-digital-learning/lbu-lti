/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.leedsbeckett.lti.config;

import java.util.HashMap;

/**
 * Represents the configuration of the issuer of the client configuration. An
 * issuer can issue multiple LTI 1.3 tools.
 * 
 * @author jon
 */
public class IssuerLtiConfiguration
{
  String issuer;
  HashMap<String,ClientLtiConfiguration> clientmap = new HashMap<>();

  public IssuerLtiConfiguration( String issuer )
  {
    this.issuer = issuer;
  }

  public String getIssuerLtiConfiguration()
  {
    return issuer;
  }

  public void putClientLtiConfiguration( ClientLtiConfiguration client )
  {
    clientmap.put( client.getClientId(), client );
  }

  public ClientLtiConfiguration getClientLtiConfiguration( String client_id )
  {
    return clientmap.get( client_id );
  }  
}
