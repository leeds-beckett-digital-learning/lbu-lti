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
public class LtiResourceClaim extends ClaimHashMap
{
  public static final String NAME = "https://purl.imsglobal.org/spec/lti/claim/resource_link";

  String id;
  String description;
  String title;
  
  public LtiResourceClaim( Claims claims )
  {
    super( claims, NAME );
    id          = super.getAsString( "id"         );
    description = super.getAsString( "decription" );
    title       = super.getAsString( "title"      );
  }

  public String getId()
  {
    return id;
  }

  public String getDescription()
  {
    return description;
  }

  public String getTitle()
  {
    return title;
  }
  
  
}
