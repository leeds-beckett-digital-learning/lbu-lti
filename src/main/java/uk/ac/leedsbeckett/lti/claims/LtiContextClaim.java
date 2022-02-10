/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.leedsbeckett.lti.claims;

import io.jsonwebtoken.Claims;
import java.util.List;

/**
 *
 * @author jon
 */
public class LtiContextClaim extends ClaimHashMap
{
  public static final String NAME = "https://purl.imsglobal.org/spec/lti/claim/context";
  
  List types;
  
  LtiContextClaim( Claims claims )
  {
    super( claims, NAME );
    types = super.getAsList( "type" );
  }

  public String getId()
  {
    return getAsString( "id" );
  }

  public String getLabel()
  {
    return getAsString( "label" );
  }

  public String getTitle()
  {
    return getAsString( "title" );
  }

  public int getTypeCount()
  {
    if ( types == null ) return 0;
    return types.size();
  }
  
  public String getType( int n )
  {
    if ( types == null ) return null;
    if ( n<0 || n>= types.size() )
      return null;
    return types.get( n ).toString();
  }
}
