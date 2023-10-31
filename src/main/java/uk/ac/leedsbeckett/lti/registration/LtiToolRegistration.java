/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.leedsbeckett.lti.registration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 *
 * @author jon
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LtiToolRegistration
        implements Serializable
{
  String applicationType = "web";
  String[] responseTypes =
  {
    "id_token"
  };
  String[] grantTypes =
  {
    "implicit", "client_credentials"
  };
  String initiateLoginUri;
  String[] redirectUris;
  String clientName;
  String jwksUri;
  String tokenEndpointAuthMethod;
  LtiToolConfiguration ltiToolConfiguration;
  String scope;
  String logoUri;

  // In responses;
  String clientId;
  
  @JsonProperty("application_type")
  public String getApplicationType()
  {
    return applicationType;
  }

  @JsonProperty("application_type")
  public void setApplicationType( String applicationType )
  {
    this.applicationType = applicationType;
  }

  @JsonProperty("response_types")
  public String[] getResponseTypes()
  {
    return responseTypes;
  }

  @JsonProperty("response_types")
  public void setResponseTypes( String[] responseTypes )
  {
    this.responseTypes = responseTypes;
  }

  @JsonProperty("grant_types")
  public String[] getGrantTypes()
  {
    return grantTypes;
  }

  @JsonProperty("grant_types")
  public void setGrantTypes( String[] grantTypes )
  {
    this.grantTypes = grantTypes;
  }

  @JsonProperty("initiate_login_uri")
  public String getInitiateLoginUri()
  {
    return initiateLoginUri;
  }

  @JsonProperty("initiate_login_uri")
  public void setInitiateLoginUri( String initiateLoginUri )
  {
    this.initiateLoginUri = initiateLoginUri;
  }

  @JsonProperty("redirect_uris")
  public String[] getRedirectUris()
  {
    return redirectUris;
  }

  @JsonProperty("redirect_uris")
  public void setRedirectUris( String[] redirectUris )
  {
    this.redirectUris = redirectUris;
  }

  @JsonProperty("client_name")
  public String getClientName()
  {
    return clientName;
  }

  @JsonProperty("client_name")
  public void setClientName( String clientName )
  {
    this.clientName = clientName;
  }

  @JsonProperty("jwks_uri")
  public String getJwksUri()
  {
    return jwksUri;
  }

  @JsonProperty("jwks_uri")
  public void setJwksUri( String jwksUri )
  {
    this.jwksUri = jwksUri;
  }

  @JsonProperty("token_endpoint_auth_method")
  public String getTokenEndpointAuthMethod()
  {
    return tokenEndpointAuthMethod;
  }

  @JsonProperty("token_endpoint_auth_method")
  public void setTokenEndpointAuthMethod( String tokenEndpointAuthMethod )
  {
    this.tokenEndpointAuthMethod = tokenEndpointAuthMethod;
  }

  @JsonProperty("https://purl.imsglobal.org/spec/lti-tool-configuration")
  public LtiToolConfiguration getLtiToolConfiguration()
  {
    return ltiToolConfiguration;
  }

  @JsonProperty("https://purl.imsglobal.org/spec/lti-tool-configuration")
  public void setLtiToolConfiguration( LtiToolConfiguration ltiToolConfiguration )
  {
    this.ltiToolConfiguration = ltiToolConfiguration;
  }

  public String getScope()
  {
    return scope;
  }

  public void setScope( String scope )
  {
    this.scope = scope;
  }

  @JsonProperty("logo_uri")
  public String getLogoUri()
  {
    return logoUri;
  }

  @JsonProperty("logo_uri")
  public void setLogoUri( String logoUri )
  {
    this.logoUri = logoUri;
  }

  @JsonProperty("client_id")
  public String getClientId()
  {
    return clientId;
  }

  @JsonProperty("client_id")
  public void setClientId( String clientId )
  {
    this.clientId = clientId;
  }
}
