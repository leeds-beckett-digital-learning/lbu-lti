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
public abstract class ClaimList
{
  public List list;
  
  public ClaimList( Claims claims, String name )
  {
    list = (List)claims.get( name );
  }
 
  public int getSize()
  {
    if ( list == null ) return 0;
    return list.size();
  }
  
  public Object getAsObject( int n )
  {
    if ( list == null || n<0 || n>=list.size() ) return null;
    return list.get( n );
  }

  public String getAsString( int n )
  {
    Object value = getAsObject( n );
    if ( value == null ) return null;
    return value.toString();
  }
}
