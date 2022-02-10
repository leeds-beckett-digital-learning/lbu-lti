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
public class LtiRoleClaims extends ClaimList
{
  public static final String NAME = "https://purl.imsglobal.org/spec/lti/claim/roles";
  
  Object o;
  boolean standardinstructor=false;
  
  LtiRoleClaims( Claims claims )
  {
    super( claims, NAME );
    for ( Object item : list )
      if ( "http://purl.imsglobal.org/vocab/lis/v2/membership#Instructor".equals( item.toString() ) )
        standardinstructor=true;
  }

  public boolean isInStandardInstructorRole()
  {
    return standardinstructor;
  }
}
