/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.leedsbeckett.lti.config;

import java.security.PublicKey;

/**
 * Represents the configuration of an LTI 1.3 client. I.e. information about
 * an LTI tool which has been advertised to the rest of the world.
 * 
 * @author jon
 */
public class ClientLtiConfiguration
{
  boolean bdefault;
  String clientId;
  String authLoginUrl;
  String authTokenUrl;
  PublicKey publicKey;
  String[] deploymentIds;

  public ClientLtiConfiguration( String client_id )
  {
    this.clientId = client_id;
  }

  public boolean isDefault()
  {
    return bdefault;
  }

  void setDefault( boolean bdefault )
  {
    this.bdefault = bdefault;
  }

  public String getClientId()
  {
    return clientId;
  }

  void setClientId( String clientId )
  {
    this.clientId = clientId;
  }

  public String getAuthLoginUrl()
  {
    return authLoginUrl;
  }

  void setAuthLoginUrl( String authLoginUrl )
  {
    this.authLoginUrl = authLoginUrl;
  }

  public String getAuthTokenUrl()
  {
    return authTokenUrl;
  }

  void setAuthTokenUrl( String authTokenUrl )
  {
    this.authTokenUrl = authTokenUrl;
  }

  public PublicKey getPublicKey()
  {
    return publicKey;
  }

  void setPublicKey( PublicKey publicKey )
  {
    this.publicKey = publicKey;
  }

  public String[] getDeploymentIds()
  {
    return deploymentIds;
  }

  void setDeploymentIds( String[] deploymentIds )
  {
    this.deploymentIds = deploymentIds;
  }    

  
}
