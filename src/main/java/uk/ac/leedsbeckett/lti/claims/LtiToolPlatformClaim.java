/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.leedsbeckett.lti.claims;

import io.jsonwebtoken.Claims;

/**
 *
 * @author jon
 */
public class LtiToolPlatformClaim extends ClaimHashMap
{
  public static final String NAME = "https://purl.imsglobal.org/spec/lti/claim/tool_platform";

  public LtiToolPlatformClaim( Claims claims )
  {
    super( claims, NAME );
  }
  
  public String getGuid()
  {
    return getAsString( "guid" );
  }

  public String getUrl()
  {
    return getAsString( "url" );
  }
}
