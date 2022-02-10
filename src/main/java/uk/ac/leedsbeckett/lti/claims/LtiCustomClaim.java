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
public class LtiCustomClaim extends ClaimHashMap
{
  public static final String NAME = "https://purl.imsglobal.org/spec/lti/claim/custom";
  
  String toolType;
  
  public LtiCustomClaim( Claims claims )
  {
    super( claims, NAME );
    toolType = super.getAsString( "lti.jonmaber.co.uk#tool_type" );
  }
  
  public String getToolType()
  {
    return toolType;
  }  
}
