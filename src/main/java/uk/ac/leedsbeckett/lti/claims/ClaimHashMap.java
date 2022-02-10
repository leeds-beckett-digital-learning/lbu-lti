/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.leedsbeckett.lti.claims;

import io.jsonwebtoken.Claims;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author jon
 */
public abstract class ClaimHashMap
{
  public HashMap map;
  
  public ClaimHashMap( Claims claims, String name )
  {
    map = claims.get( name, HashMap.class );
  }

  public Object getAsObject( String name )
  {
    if ( map == null ) return null;
    return map.get( name );
  }

  public String getAsString( String name )
  {
    Object value = getAsObject( name );
    if ( value == null ) return null;
    return value.toString();
  }
  
  public List getAsList( String name )
  {
    Object value = getAsObject( name );
    if ( value == null ) return null;
    if ( value instanceof List )
      return (List)value;
    return null;
  }
}
