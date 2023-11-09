/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.leedsbeckett.lti.registration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author jon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsumerConfiguration implements Serializable
{
  String issuer;
  String tokenEndpoint;

  String[] tokenEndpointAuthMethodsSupported;
  String[] tokenEndpointAuthSigningAlgValuesSupported;
  
  String jwksUri;
  String authorizationEndpoint;
  String registrationEndpoint;

  String[] scopesSupported;
  String[] responseTypesSupported;
  String[] subjectTypesSupported;
  String[] idTokenSigningAlgValuesSupported;
  String[] claimsSupported;
  
  LtiPlatformConfiguration imsPlatformConfiguration;
  
  public String getIssuer()
  {
    return issuer;
  }

  public void setIssuer( String issuer )
  {
    this.issuer = issuer;
  }

  @JsonProperty( "token_endpoint" )
  public String getTokenEndpoint()
  {
    return tokenEndpoint;
  }

  @JsonProperty( "token_endpoint" )
  public void setTokenEndpoint( String tokenEndpoint )
  {
    this.tokenEndpoint = tokenEndpoint;
  }

  @JsonProperty( "token_endpoint_auth_methods_supported" )
  public String[] getTokenEndpointAuthMethodsSupported()
  {
    return tokenEndpointAuthMethodsSupported;
  }

  @JsonProperty( "token_endpoint_auth_methods_supported" )
  public void setTokenEndpointAuthMethodsSupported( String[] tokenEndpointAuthMethodsSupported )
  {
    this.tokenEndpointAuthMethodsSupported = tokenEndpointAuthMethodsSupported;
  }

  @JsonProperty( "token_endpoint_auth_signing_alg_values_supported" )
  public String[] getTokenEndpointAuthSigningAlgValuesSupported()
  {
    return tokenEndpointAuthSigningAlgValuesSupported;
  }

  @JsonProperty( "token_endpoint_auth_signing_alg_values_supported" )
  public void setTokenEndpointAuthSigningAlgValuesSupported( String[] tokenEndpointAuthSigningAlgValuesSupported )
  {
    this.tokenEndpointAuthSigningAlgValuesSupported = tokenEndpointAuthSigningAlgValuesSupported;
  }

  @JsonProperty( "jwks_uri" )
  public String getJwksUri()
  {
    return jwksUri;
  }

  @JsonProperty( "jwks_uri" )
  public void setJwksUri( String jwksUri )
  {
    this.jwksUri = jwksUri;
  }

  @JsonProperty( "authorization_endpoint" )
  public String getAuthorizationEndpoint()
  {
    return authorizationEndpoint;
  }

  @JsonProperty( "authorization_endpoint" )
  public void setAuthorizationEndpoint( String authorizationEndpoint )
  {
    this.authorizationEndpoint = authorizationEndpoint;
  }

  @JsonProperty( "registration_endpoint" )
  public String getRegistrationEndpoint()
  {
    return registrationEndpoint;
  }

  @JsonProperty( "registration_endpoint" )
  public void setRegistrationEndpoint( String registrationEndpoint )
  {
    this.registrationEndpoint = registrationEndpoint;
  }

  @JsonProperty( "scopes_supported" )
  public String[] getScopesSupported()
  {
    return scopesSupported;
  }

  @JsonProperty( "scopes_supported" )
  public void setScopesSupported( String[] scopesSupported )
  {
    this.scopesSupported = scopesSupported;
  }

  @JsonProperty( "response_types_supported" )
  public String[] getResponseTypesSupported()
  {
    return responseTypesSupported;
  }

  @JsonProperty( "response_types_supported" )
  public void setResponseTypesSupported( String[] responseTypesSupported )
  {
    this.responseTypesSupported = responseTypesSupported;
  }

  @JsonProperty( "subject_types_supported" )
  public String[] getSubjectTypesSupported()
  {
    return subjectTypesSupported;
  }

  @JsonProperty( "subject_types_supported" )
  public void setSubjectTypesSupported( String[] subjectTypesSupported )
  {
    this.subjectTypesSupported = subjectTypesSupported;
  }

  @JsonProperty( "id_token_signing_alg_values_supported" )
  public String[] getIdTokenSigningAlgValuesSupported()
  {
    return idTokenSigningAlgValuesSupported;
  }

  @JsonProperty( "id_token_signing_alg_values_supported" )
  public void setIdTokenSigningAlgValuesSupported( String[] idTokenSigningAlgValuesSupported )
  {
    this.idTokenSigningAlgValuesSupported = idTokenSigningAlgValuesSupported;
  }

  @JsonProperty( "claims_supported" )
  public String[] getClaimsSupported()
  {
    return claimsSupported;
  }

  @JsonProperty( "claims_supported" )
  public void setClaimsSupported( String[] claimsSupported )
  {
    this.claimsSupported = claimsSupported;
  }

  @JsonProperty( "https://purl.imsglobal.org/spec/lti-platform-configuration" )
  public LtiPlatformConfiguration getImsPlatformConfiguration()
  {
    return imsPlatformConfiguration;
  }

  @JsonProperty( "https://purl.imsglobal.org/spec/lti-platform-configuration" )
  public void setImsPlatformConfiguration( LtiPlatformConfiguration imsPlatformConfiguration )
  {
    this.imsPlatformConfiguration = imsPlatformConfiguration;
  }
  
  
  
  
  
  static final public ConsumerConfiguration load( String rawconfig ) throws IOException
  {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue( rawconfig, ConsumerConfiguration.class );
  }
}
