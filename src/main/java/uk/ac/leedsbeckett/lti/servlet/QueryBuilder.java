/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.leedsbeckett.lti.servlet;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author jon
 */
public class QueryBuilder
{
  boolean started = false;
  StringBuilder sb = new StringBuilder();

  public QueryBuilder()
  {
    sb.append( "?" );
  }
  
  public void add( String name, String value )
  {
    if ( started )
      sb.append( "&" );
    else
      started = true;
    sb.append( URLEncoder.encode( name, StandardCharsets.UTF_8 ) );
    sb.append( "=" );
    sb.append( URLEncoder.encode( value, StandardCharsets.UTF_8 ) );
  }
  
  public String get()
  {
    return sb.toString();
  }
}
