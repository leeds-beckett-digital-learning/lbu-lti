/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.leedsbeckett.lti.registration;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.HashMap;

/**
 *
 * @author jon
 */
@JsonSerialize( using = LtiToolConfigurationCustomParametersSerializer.class )
@JsonDeserialize( using = LtiToolConfigurationCustomParametersDeserializer.class )
public class LtiToolConfigurationCustomParameters
{
  HashMap<String,String> params = new HashMap<>();
  public String getParameter( String name )
  {
    return params.get( name );
  }
  public void setParameter( String name, String value )
  {
    params.put( name, value );
  }
}
