/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.leedsbeckett.lti.registration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 *
 * @author jon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LtiPlatformConfiguration implements Serializable
{
  String productFamilyCode;
  String version;
  LtiSupportedMessage[] messagesSupported;
  String[] variables;

  
  @JsonProperty( "product_family_code" )
  public String getProductFamilyCode()
  {
    return productFamilyCode;
  }

  @JsonProperty( "product_family_code" )
  public void setProductFamilyCode( String productFamilyCode )
  {
    this.productFamilyCode = productFamilyCode;
  }

  public String getVersion()
  {
    return version;
  }

  public void setVersion( String version )
  {
    this.version = version;
  }

  @JsonProperty( "messages_supported" )
  public LtiSupportedMessage[] getMessagesSupported()
  {
    return messagesSupported;
  }

  @JsonProperty( "messages_supported" )
  public void setMessagesSupported( LtiSupportedMessage[] messagesSupported )
  {
    this.messagesSupported = messagesSupported;
  }

  public String[] getVariables()
  {
    return variables;
  }

  public void setVariables( String[] variables )
  {
    this.variables = variables;
  }  
}
