/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.leedsbeckett.lti.registration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author jon
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LtiToolConfigurationMessage
{
  String type;
  String targetLinkUri;
  String label;

  public String getType()
  {
    return type;
  }

  public void setType( String type )
  {
    this.type = type;
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

  public String getLabel()
  {
    return label;
  }

  public void setLabel( String label )
  {
    this.label = label;
  }

}
