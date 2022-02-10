/*
 * Copyright 2022 Leeds Beckett University.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.ac.leedsbeckett.lti.servlet;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * A utility for encoding parameters and concatenating them into a query string.
 * 
 * @author jon
 */
public class QueryBuilder
{
  boolean started = false;
  StringBuilder sb = new StringBuilder();

  /**
   * Create a new builder with an initially empty query string.
   */
  public QueryBuilder()
  {
    sb.append( "?" );
  }
  
  /**
   * Add a parameter to the query
   * 
   * @param name The name of the parameter.
   * @param value The value of the parameter.
   */
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
  
  /**
   * Get the query that has been built.
   * 
   * @return The properly encoded query string including the ? character at the start.
   */
  public String get()
  {
    return sb.toString();
  }
}
