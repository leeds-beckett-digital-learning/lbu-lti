/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.leedsbeckett.lti.config;

import java.io.Serializable;

/**
 *
 * @author jon
 */
public class ClientLtiConfigurationKey implements Serializable
{
  final String issuerName; 
  final String clientId;

  public ClientLtiConfigurationKey(String issuerName, String clientId)
  {
    this.issuerName = issuerName;
    this.clientId   = clientId;
  }

  public String getIssuerName()
  {
    return issuerName;
  }

  public String getClientId()
  {
    return clientId;
  }
  
}
