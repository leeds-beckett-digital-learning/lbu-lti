/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.leedsbeckett.lti.config;

import io.jsonwebtoken.SigningKeyResolver;

/**
 * Represents the configuration of an LTI 1.3 client. I.e. information about
 * an LTI tool which has been advertised to the rest of the world.
 *
 * @author jon
 */
public interface ClientLtiConfiguration extends SigningKeyResolver
{
  public String getIssuerId();
  public String getToolId();
  public String getClientId();
  public String getAuthLoginUrl();
  public String getAuthTokenUrl();
  public String getAuthJwksUrl();
  public KeyConfiguration getKeyConfiguration( String kid );
  public String[] getDeploymentIds();
}
