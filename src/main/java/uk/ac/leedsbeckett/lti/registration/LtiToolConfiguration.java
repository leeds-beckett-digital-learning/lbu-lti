/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.leedsbeckett.lti.registration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 *
 * @author jon
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LtiToolConfiguration implements Serializable
{
  String domain;
  String targetLinkUri;
  LtiToolConfigurationCustomParameters customParameters;
  String[] claims;
  LtiToolConfigurationMessage[] messages;
  String description;

  // In responses
  String deploymentId;
  
  public String getDomain()
  {
    return domain;
  }

  public void setDomain( String domain )
  {
    this.domain = domain;
  }

  @JsonProperty("target_link_uri")
  public String getTargetLinkUri()
  {
    return targetLinkUri;
  }

  @JsonProperty("target_link_uri")
  public void setTargetLinkUri( String targetLinkUri )
  {
    this.targetLinkUri = targetLinkUri;
  }

  @JsonProperty("custom_parameters")
  public LtiToolConfigurationCustomParameters getCustomParameters()
  {
    return customParameters;
  }

  @JsonProperty("custom_parameters")
  public void setCustomParameters( LtiToolConfigurationCustomParameters customParameters )
  {
    this.customParameters = customParameters;
  }

  public String[] getClaims()
  {
    return claims;
  }

  public void setClaims( String[] claims )
  {
    this.claims = claims;
  }

  public LtiToolConfigurationMessage[] getMessages()
  {
    return messages;
  }

  public void setMessages( LtiToolConfigurationMessage[] messages )
  {
    this.messages = messages;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription( String description )
  {
    this.description = description;
  }

  @JsonProperty("deployment_id")
  public String getDeploymentId()
  {
    return deploymentId;
  }

  @JsonProperty("deployment_id")
  public void setDeploymentId( String deploymentId )
  {
    this.deploymentId = deploymentId;
  }
  
  
}
